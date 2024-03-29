import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Person {
    /**
     * Az adott személy mérgezett állapota
     */
    private boolean isPoisoned;

    /**
     * Az adott személy neve
     */
    private String name;

    /**
     * A szoba, amelyikben az adott személy tartózkodik
     */
    private Room currentRoom;

    /**
     * Az adott személy tárgylistája
     */
    private List<Item> itemInventory = new ArrayList<Item>();

    public Person(String name) {
        this.name = name;
    }

    /**
     * A Person osztály isPoisoned adattagjának getter függvénye
     * @return Az isPoisoned atrribútum értéke
     */
    public boolean isPoisoned() {
        return isPoisoned;
    }

    /**
     * A Person osztály isPoisoned adattagjának setter függvénye
     * @param isPoisoned : Az isPoisoned atrribútum értéke
     */
    public void setPoisoned(boolean isPoisoned) {
        this.isPoisoned = isPoisoned;
    }

    /**
     * A Person osztály name adattagjának getter függvénye
     * @return Az name atrribútum értéke
     */
    public String getName() {
        return name;
    }

    /**
     * A Person osztály name adattagjának setter függvénye
     * @param name : Az name atrribútum értéke
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A Person osztály currentRoom adattagjának getter függvénye
     * @return Az currentRoom atrribútum értéke
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * A Person osztály currentRoom adattagjának setter függvénye
     * @param currentRoom : Az currentRoom atrribútum értéke
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * A Person osztály itemInventory adattagjának getter függvénye
     * @return Az itemInventory lista
     */
    public List<Item> getItemInventory() {
        return itemInventory;
    }

    /**
     * A Person osztály itemInventory adattagjának setter függvénye
     * @param itemInventory : Az itemInventory lista
     */
    public void setItemInventory(List<Item> itemInventory) {
        this.itemInventory = itemInventory;
    }

    /**
     * Az adott személy inventoryjába egy tárgy felvétele
     * @param item : Az adott személy inventoryjába kerülő tárgy
     */
    public void addItem(Item item) {
        itemInventory.add(item);
    }

    /**
     * Az adott személy inventoryjából egy tárgy elvétele
     * @param item : Az adott személy inventoryjából elvétlre kerülő tárgy
     */
    public void removeItem(Item item) {
        Skeleton.log(this.name + ".removeItem(Item" + item.getId() + ")", true);
        itemInventory.remove(item);
        Skeleton.log("return", false);
    }

    /**
     *  A paraméterként átadott szobának meghívja az
        acceptNewPerson(Person) függvényét. A paraméterben az objektum magát adja át.
        Amennyiben ez a függvény igazzal tér vissza, a személy belép a szobába (a
        currentRoom-ot beállítjuk a paraméterben található szobára).
     * @param room : A szoba, ahova a személy be szeretne lépni
     */
    public void enter(Room room){
        Skeleton.log(this.name + ".enter(Room" + room.getId() + ")", true);
        if(!room.acceptNewPerson(this)) {
            Skeleton.log("return", false);
            return;
        }

        currentRoom.removePerson(this);
        currentRoom = room;

        if(currentRoom.isGassed() && !checkForMask()) {
            isPoisoned = true;
            dropItems();
            Skeleton.log("return", false);
            return;
        }

        for(Person person : currentRoom.getPeopleInRoom()){
            if(person != this)
                this.meet(person);
        }
        Skeleton.log("return", false);
    }

    /**
     * Ha egy gázzal teli szobában tartózkodik a személy, meghívódik
     * ez a függvény, ami megvizsgája hogy a személynél található-e 
     * maszk, amely megvédené a gáztól.
     * @return A személy rendelkezik-e olyan tárggyal ami megvédi a gáz hatásától
     */
    public boolean checkForMask(){
        Skeleton.log(this.name + ".checkForMask()", true);
        for(Item item : itemInventory){
            if(item.defendAgainstGas()){
                Skeleton.log("return true", false);
                return true;
            }
        }
        Skeleton.log("return false", false);
        return false;
    }

    /**
     *  A paraméterként kapott tárgy bekerül az itemInventory-ba, ha
        így annak a mérete nem haladja meg az ötöt. A tárgy a szobából
        eltávolításra kerül.
     * @param item : A tárgy, amelyet a személy fel szeretne venni
     */
    public void pickUp(Item item){
        if(itemInventory.size() >= 5) {
            return;
        }
        currentRoom.removeItem(item);
        itemInventory.add(item);
    }

    /**
     * A személynél levő tárgyak átkerülnek a currentRoom
        itemInventory-ába.
     */
    public void dropItems(){
        Skeleton.log(this.name + ".dropItems()", true);
        for(Item item : itemInventory){
            item.setActive(false);
            currentRoom.addItem(item);
        }
        itemInventory.clear();
        Skeleton.log("return", false);
    }

    /**
     * A függvény akkor hívódik meg amikor egy tanár belép egy olyan
        szobába, ahol egy diák tartózkodik. A függvény meghívja a paraméterben található diák
        checkForDefensiveItems() függvényét amely igazzal tér vissza ha a hallgatónál található
        egy védő tárgy ami aktív. Illetve meghívódik a paraméterben kapott diák
        checkForStunItems() metódusa amely, ha igazzal tér vissza, a tanár megbénul.
        Amennyiben nincs aktív védő illetve bénító tárgy a hallgatónál, meghívódik az
        eliminate(Student) függvény.     
     * @param student : A hallgató, akivel a tanár találkozott
     */
    public void meet(Student student){}

    /**
     * A függvény akkor hívódik meg amikor egy diák belép egy
        olyan szobába, ahol egy tanár tartózkodik. A függvény meghívja a diák
        checkForDefensiveItems() függvényét amely igazzal tér vissza ha a hallgatónál
        található egy védő tárgy ami aktív. Illetve meghívja a diák checkForStunItems()
        metódusát amely, ha igazzal tér vissza, a tanár megbénul. Amennyiben nincs aktív
        védő illetve bénító tárgy, meghívja a paraméterben található tanár eliminate(Student)
        függvényét ahol paraméterben átadja saját magát az objektum.     
     * @param teacher : A tanár, akivel a hallgató találkozott
     */
    public void meet(Teacher teacher){}

    /**
     * A személyek egymással való találkozását leíró függvény
     * @param person : A személy, akivel a másik személy találkozik
     */
    public void meet(Person person){}

    @Override
    public String toString() {
        StringBuilder itemInventoryString = new StringBuilder();
        for(Item item : itemInventory){
            itemInventoryString.append(item.getId()).append(",");
        }

        return this.getClass() + ", Name=" + name  + ", isPoisoned=" + isPoisoned + ", itemInventory=[" + itemInventoryString + "]";
    }
}
