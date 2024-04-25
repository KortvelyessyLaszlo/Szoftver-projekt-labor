import java.util.*;

public class Room implements ITickable{

    /**
     * A szoba egyedi azonosítója
     */
    private final int id;

    /**
     * A szoba gázosságára vonatkozó attribútum
     */
    private boolean isGassed;

    private boolean isSticky;

    private int enterCounter;

    /**
     * A szoba kapacitása
     */
    private int capacity;

    /**
     * A szobában tartózkodó személyek listája
     */
    private List<Person> peopleInRoom = new ArrayList<Person>();

    /**
     * A szobában lévő tárgyak listája
     */
    private List<Item> itemInventory = new ArrayList<Item>();

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

    public boolean isSticky() { return isSticky; }

    /**A szoba gázosságára vonatkozó attribútum setter függvénye
     * @param isGassed : A szoba gázossága
     */
    public void setGassed(boolean isGassed) {
        Skeleton.log("Room" + this.getId() + ".setGassed(" + isGassed + ")", true);
        this.isGassed = isGassed;
        Skeleton.log("return", false);
    }

    public void setSticky(boolean isSticky) {
        Skeleton.log("Room" + this.getId() + ".setSticky(" + isSticky + ")", true);
        this.isSticky = isSticky;
        Skeleton.log("return", false);
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

    public int getenterCounter() {
        return enterCounter;
    }

    /**
     * A szoba kapacitására vonatkozó attribútum setter függvénye
     * @param capacity : A szoba kapacitása
     */
    public void setCapacity(int capacity) {
        Skeleton.log("Room" + this.getId() + ".setCapacity(" + capacity + ")", true);
        this.capacity = capacity;
        Skeleton.log("return", false);
    }

    public void setenterCounter(int enterCounter) {
        Skeleton.log("Room" + this.getId() + ".setenterCounter(" + enterCounter + ")", true);
        this.enterCounter = enterCounter;
        Skeleton.log("return", false);
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
        Skeleton.log("Room" + this.getId() + ".setPeopleInRoom(" + peopleInRoom + ")", true);
        this.peopleInRoom = peopleInRoom;
        Skeleton.log("return", false);
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
        Skeleton.log("Room" + this.getId() + ".setItemInventory(" + itemInventory + ")", true);
        this.itemInventory = itemInventory;
        Skeleton.log("return", false);
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
        Skeleton.log("Room" + this.getId() + ".setNeighbours(" + neighbours + ")", true);
        Skeleton.log("return", false);
        this.neighbours = neighbours;
    }

    /**
     * A szobához új személy hozzáadása
     * A személyt a szoba peopleInRoom listájához adja hozzá
     * és beállítja a személy jelenlegi szobáját erre a szobára
     * @param person : A hozzáadandó személy
     */
    public void addPerson(Person person) {
        Skeleton.log("Room" + this.getId() + ".addPerson(" + person.getName() + ")", true);
        Skeleton.log("return", false);
        peopleInRoom.add(person);
        person.setCurrentRoom(this);
    }

    /**
     * A szobából egy meglévő személy eltávolítása
     * @param person : Az eltávolítandó személy
     */
    public void removePerson(Person person) {
        Skeleton.log("Room" + this.getId() + ".removePerson(" + person.getName() + ")", true);
        peopleInRoom.remove(person);
        Skeleton.log("return", false);
    }

    /**
     * A szobához új tárgy hozzáadása
     * @param item : A A hozzáadandó tárgy
     */
    public void addItem(Item item) {
        Skeleton.log("Room" + this.getId() + ".addItem(Item" + item.getId() + ")", true);
        itemInventory.add(item);
        Skeleton.log("return", false);
    }

    /**
     * A szobából egy meglévő tárgy eltávolítása
     * @param item : Az eltávolítandó tárgy
     */
    public void removeItem(Item item) {
        Skeleton.log("Room" + this.getId() + ".removeItem(Item" + item.getId() + ")", true);
        itemInventory.remove(item);
        Skeleton.log("return", false);
    }

    /**
     * A szobához új szomszédos szoba hozzáadása
     * @param neighbour : A hozzáadandó szomszédos szoba
     */
    public void addNeighbour(Room neighbour) {
        Skeleton.log("Room" + this.getId() + ".addNeighbour(Room" + neighbour.getId() + ")", true);
        neighbours.add(neighbour);
        Skeleton.log("return", false);
    }

    /**
     * A szobából egy meglévő szomszédos szoba eltávolítása
     * @param neighbour : Az eltávolítandó szomszédos szoba
     */
    public void removeNeighbour(Room neighbour) {
        Skeleton.log("Room" + this.getId() + ".removeNeighbour(Room" + neighbour.getId() + ")", true);
        neighbours.remove(neighbour);
        Skeleton.log("return", false);
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
        Skeleton.log("Room" + this.getId() + ".combineRooms()", true);
        Random random = new Random();
        Room neighbour = neighbours.get(random.nextInt(neighbours.size()));

        if(!neighbour.peopleInRoom.isEmpty() || !this.peopleInRoom.isEmpty()){
            Skeleton.log("return null", false);
            return null;
        }

        if(neighbour.isGassed)
            this.isGassed = true;

        if(neighbour.capacity > this.capacity)
            this.capacity = neighbour.capacity;

        this.itemInventory.addAll(neighbour.itemInventory);

        Iterator<Room> neighbourIterator = neighbour.neighbours.iterator();
        while(neighbourIterator.hasNext()){
            Room room = neighbourIterator.next();
            if(room != this) {
                room.neighbours.add(this);
                this.neighbours.add(room);
            }
            room.neighbours.remove(neighbour);
        }
        neighbour.neighbours.clear();
        Skeleton.log("return Room" + neighbour.getId(), false);
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
        Skeleton.log("Room" + this.getId() + ".splitRoom(" + newId + ")", true);
        Random random = new Random();

        if(!this.peopleInRoom.isEmpty() || this.capacity <= 1){
            Skeleton.log("return null", false);
            return null;
        }

        Room newRoom = new Room(random.nextInt(this.capacity - 1) + 1, newId);
        this.capacity -= newRoom.capacity;

        newRoom.isGassed = this.isGassed;

        if(!this.itemInventory.isEmpty()){
            int splitIndex = random.nextInt(this.itemInventory.size());
            newRoom.itemInventory.addAll(this.itemInventory.subList(splitIndex, this.itemInventory.size()));
            this.itemInventory = this.itemInventory.subList(0, splitIndex);
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

        Skeleton.log("return Room" + newRoom.getId(), false);
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
        Skeleton.log("Room" + this.getId() + ".acceptNewPerson(" + person.getName() + ")", true);
        if(peopleInRoom.size() >= capacity){
            Skeleton.log("return false", false);
            if(person instanceof Student)
                System.out.println("\u001B[31mRoom is full. You can't enter.\u001B[0m");
            return false;
        }
        peopleInRoom.add(person);
        this.setenterCounter(this.getenterCounter() + 1);
        if(this.getenterCounter() >= 2)
            this.setSticky(true);
        Skeleton.log("return true", false);
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
            itemInventoryString.append(item.getId()).append(" " + item.getClass().getName()).append(",");
        }

        StringBuilder neighboursString = new StringBuilder();
        for(Room neighbour : neighbours){
            neighboursString.append(neighbour.getId()).append(",");
        }

        return this.getClass() + ", id=" + this.getId() + ", capacity=" + capacity + ", isGassed=" + isGassed
                + ", isSticky=" + isSticky + ", peopleInRoom=[" + peopleInRoomString
                + "], itemInventory=[" + itemInventoryString + "], neighbours=[" + neighboursString + "]";
    }

    public void tick() {

    }
}
