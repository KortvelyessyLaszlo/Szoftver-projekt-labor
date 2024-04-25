import java.io.Serializable;
import java.util.*;

public class Room implements ITickable, Serializable {

    /**
     * A szoba egyedi azonosítója
     */
    private final int id;

    /**
     * A szoba gázosságára vonatkozó attribútum
     */
    private boolean isGassed;

    /**
     * A szoba ragadós tulajdonságára vonatkozó attribútum
     */
    private boolean isSticky;

    /**
     * A szoba belépések számát tároló attribútum
     */
    private int enterCounter;

    /**
     * A szoba kapacitása
     */
    private int capacity;

    /**
     * A szobában tartózkodó személyek listája
     */
    private List<Person> peopleInRoom = new ArrayList<>();

    /**
     * A szobában lévő tárgyak listája
     */
    private List<Item> itemInventory = new ArrayList<>();

    /**
     * A szobának a szomszédos szobáinak listája
     */
    private List<Room> neighbours = new ArrayList<>();


    /**
     * A Room osztály konstruktora
     * @param capacity : A szoba kapacitása
     * @param id : A szoba egyedi azonosítója
     */
    public Room(int capacity, int id) {
        this.capacity = capacity;
        this.id = id;
    }

    /**
     * A szoba gázosságára vonatkozó attribútum getter függvénye
     * @return A szoba gázossága
     */
    public boolean isGassed() {
        return isGassed;
    }

    /**
     * A szoba ragadós tulajdonságára vonatkozó attribútum getter függvénye
     * @return A szoba ragadós tulajdonsága
     */
    public boolean isSticky() { return isSticky; }

    /**A szoba gázosságára vonatkozó attribútum setter függvénye
     * @param isGassed : A szoba gázossága
     */
    public void setGassed(boolean isGassed) {
        this.isGassed = isGassed;
    }

    /**
     * A szoba ragadós tulajdonságára vonatkozó attribútum setter függvénye
     * @param isSticky : A szoba ragadós tulajdonsága
     */
    public void setSticky(boolean isSticky) {
        this.isSticky = isSticky;
    }

    /**
     * A szoba egyedi azonosítójához tartozó getter függvény
     * @return : A szoba egyedi azonosítója
     */
    public int getId() {
        return id;
    }

    /**
     * A szoba kapacitására vonatkozó attribútum getter függvénye
     * @return A szoba kapacitása
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * A szoba belépések számát tároló attribútum getter függvénye
     * @return A szoba belépések száma
     */
    public int getEnterCounter() {
        return enterCounter;
    }

    /**
     * A szoba kapacitására vonatkozó attribútum setter függvénye
     * @param capacity : A szoba kapacitása
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * A szoba belépések számát tároló attribútum setter függvénye
     * @param enterCounter : A szoba belépések száma
     */
    public void setEnterCounter(int enterCounter) {
        this.enterCounter = enterCounter;
    }

    /**
     * A szobában tartózkodó személyek listájának getter függvénye
     * @return A szobában tartózkodó személyek listája
     */
    public List<Person> getPeopleInRoom() {
        return peopleInRoom;
    }

    /**
     * A szobában tartózkodó személyek listájának getter függvénye
     * @param peopleInRoom : A szobában tartózkodó személyek listája
     */
    public void setPeopleInRoom(List<Person> peopleInRoom) {
        this.peopleInRoom = peopleInRoom;
    }

    /**
     * A szobában lévő tárgyak listájának getter függvénye
     * @return A szobában lévő tárgyak listája
     */
    public List<Item> getItemInventory() {
        return itemInventory;
    }

    /**
     * A szobában lévő tárgyak listájának getter függvénye
     * @param itemInventory : A szobában lévő tárgyak listája
     */
    public void setItemInventory(List<Item> itemInventory) {
        this.itemInventory = itemInventory;
    }

    /**
     * A szobának a szomszédos szobáinak listájának getter függvénye
     * @return A szobának a szomszédos szobáinak listája
     */
    public List<Room> getNeighbours() {
        return neighbours;
    }

    /**
     * A szobának a szomszédos szobáinak listájának getter függvénye
     * @param neighbours : A szobának a szomszédos szobáinak listája
     */
    public void setNeighbours(List<Room> neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * A szobához új személy hozzáadása
     * A személyt a szoba peopleInRoom listájához adja hozzá
     * és beállítja a személy jelenlegi szobáját erre a szobára
     * @param person : A hozzáadandó személy
     */
    public void addPerson(Person person) {
        peopleInRoom.add(person);
        person.setCurrentRoom(this);
    }

    /**
     * A szobából egy meglévő személy eltávolítása
     * @param person : Az eltávolítandó személy
     */
    public void removePerson(Person person) {
        peopleInRoom.remove(person);
    }

    /**
     * A szobához új tárgy hozzáadása
     * @param item : A A hozzáadandó tárgy
     */
    public void addItem(Item item) {
        itemInventory.add(item);
    }

    /**
     * A szobából egy meglévő tárgy eltávolítása
     * @param item : Az eltávolítandó tárgy
     */
    public void removeItem(Item item) {
        itemInventory.remove(item);
    }

    /**
     * A szobához új szomszédos szoba hozzáadása
     * @param neighbour : A hozzáadandó szomszédos szoba
     */
    public void addNeighbour(Room neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * Ezt a függvényt a Maze startCombineRooms() függvénye
        váltja ki. Kiválaszt a szomszédjai közül véletlenszerűen egy olyan szobát, amelyben nem
        található játékos. Amelynek átveszi tulajdonságait és szomszédjait, ha a szomszédos
        szoba kapacitása nagyobb, akkor átveszi a kapacitását is. Illetve visszatér a szomszédos
        szobával.
     * @return Az eredetileg szomszédos szoba
     */
    public Room combineRooms(){
        Random random = new Random();
        Room neighbour = neighbours.get(random.nextInt(neighbours.size()));

        if(!neighbour.peopleInRoom.isEmpty() || !this.peopleInRoom.isEmpty())
            return null;

        if(neighbour.isGassed)
            this.isGassed = true;

        if(neighbour.isSticky)
            this.isSticky = true;

        if(neighbour.capacity > this.capacity)
            this.capacity = neighbour.capacity;

        this.itemInventory.addAll(neighbour.itemInventory);

        for(var item : this.itemInventory) {
            if(item instanceof Transistor && ((Transistor) item).getPlacedTransistorRoom() != null)
                ((Transistor) item).setPlacedTransistorRoom(this);
        }

        for (Room room : neighbour.neighbours) {
            if (room != this) {
                if(!room.getNeighbours().contains(this))
                    room.neighbours.add(this);
                if(!this.getNeighbours().contains(room))
                    this.neighbours.add(room);
            }
            room.neighbours.remove(neighbour);
        }
        neighbour.neighbours.clear();
        return neighbour;
    }

    /**
     * Ezt a függvényt a Maze startSplitRooms() függvénye váltja ki.
        Létrehoz egy új szobát, amelynek tulajdonságai megegyeznek ezzel a szobával. A szoba
        kapacitása és szomszédjai szétoszlanak a két szoba között véletlenszerűen. Visszatér az
        újonnan létrehozott szobával.
     * @param newId : A létrehozott szobának az azonosítója
     * @return A létrehozott szoba
     */
    public Room splitRoom(int newId){
        Random random = new Random();

        if(!this.peopleInRoom.isEmpty() || this.capacity <= 1){
            return null;
        }

        Room newRoom = new Room(random.nextInt(this.capacity - 1) + 1, newId);
        this.capacity -= newRoom.capacity;

        newRoom.isGassed = this.isGassed;
        newRoom.isSticky = this.isSticky;

        if(!this.itemInventory.isEmpty()){
            int splitIndex = random.nextInt(this.itemInventory.size());
            newRoom.itemInventory.addAll(this.itemInventory.subList(splitIndex, this.itemInventory.size()));
            this.itemInventory = this.itemInventory.subList(0, splitIndex);
        }

        for(var item : newRoom.itemInventory) {
            if(item instanceof Transistor && ((Transistor) item).getPlacedTransistorRoom() != null) {
                ((Transistor) item).setPlacedTransistorRoom(newRoom);
            }
        }

        if(!this.neighbours.isEmpty()){
            int splitIndex = random.nextInt(this.neighbours.size());
            newRoom.neighbours.addAll(this.neighbours.subList(splitIndex, this.neighbours.size()));
            this.neighbours = this.neighbours.subList(0, splitIndex);

            for (Room room : newRoom.neighbours) {
                room.neighbours.remove(this);
                room.neighbours.add(newRoom);
            }
        }

        this.neighbours.add(newRoom);
        newRoom.neighbours.add(this);

        return newRoom;
    }

    /**
     * Igaz, ha a szobában tartózkodó személyek száma
        kisebb mint a szoba kapacitása. Ezt a függvény a Person.enter(Room) függvénye hívja
        meg. A paraméterként kapott személyt elhelyezi a szobában (peopleInRoom), ha van elég
        hely.
     * @param person : A személy, aki be szeretne lépni a szobába
     * @return Sikerült-e belépni a szobába
     */
    public boolean acceptNewPerson(Person person){
        if(peopleInRoom.size() >= capacity){
            if(person instanceof Student)
                System.out.println("\u001B[31mRoom is full. You can't enter.\u001B[0m");
            return false;
        }
        peopleInRoom.add(person);
        this.setEnterCounter(this.getEnterCounter() + 1);
        if(this.getEnterCounter() >= 10)
            this.setSticky(true);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder peopleInRoomString = new StringBuilder();
        for(Person person : peopleInRoom){
            peopleInRoomString.append(person.getName()).append(",");
        }

        StringBuilder itemInventoryString = new StringBuilder();
        for(Item item : itemInventory){
            itemInventoryString.append(item.getId()).append(" ").append(item.getClass().getName()).append(",");
        }

        StringBuilder neighboursString = new StringBuilder();
        for(Room neighbour : neighbours){
            neighboursString.append(neighbour.getId()).append(",");
        }

        return this.getClass() + ", id=" + this.getId() + ", capacity=" + capacity + ", enterCounter=" + enterCounter + ", isGassed=" + isGassed
                + ", isSticky=" + isSticky + ", peopleInRoom=[" + peopleInRoomString
                + "], itemInventory=[" + itemInventoryString + "], neighbours=[" + neighboursString + "]";
    }

    public void tick() {}
}
