import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private boolean isGassed;

    private int capacity;

    private List<Person> peopleInRoom = new ArrayList<Person>();

    private List<Item> itemInventory = new ArrayList<Item>();

    private List<Room> neighbours = new ArrayList<Room>();


    public Room(int capacity) {
        this.capacity = capacity;
    }

    // Getters and setters
    public boolean isGassed() {
        return isGassed;
    }

    public void setGassed(boolean isGassed) {
        this.isGassed = isGassed;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Person> getPeopleInRoom() {
        return peopleInRoom;
    }

    public void setPeopleInRoom(List<Person> peopleInRoom) {
        this.peopleInRoom = peopleInRoom;
    }

    public List<Item> getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(List<Item> itemInventory) {
        this.itemInventory = itemInventory;
    }

    public List<Room> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Room> neighbours) {
        this.neighbours = neighbours;
    }

    // Add a person to the room
    public void addPerson(Person person) {
        peopleInRoom.add(person);
    }

    // Remove a person from the room
    public void removePerson(Person person) {
        peopleInRoom.remove(person);
    }

    // Add an item to the room
    public void addItem(Item item) {
        itemInventory.add(item);
    }

    // Remove an item from the room
    public void removeItem(Item item) {
        itemInventory.remove(item);
    }

    // Add a neighbour to the room
    public void addNeighbour(Room neighbour) {
        neighbours.add(neighbour);
    }

    // Remove a neighbour from the room
    public void removeNeighbour(Room neighbour) {
        neighbours.remove(neighbour);
    }

    public Room combineRooms(){
        Random random = new Random();
        Room neighbour = neighbours.get(random.nextInt(neighbours.size()));

        if(!neighbour.peopleInRoom.isEmpty() || !this.peopleInRoom.isEmpty())
            return null;

        if(neighbour.isGassed)
            this.isGassed = true;

        if(neighbour.capacity > this.capacity)
            this.capacity = neighbour.capacity;

        this.itemInventory.addAll(neighbour.itemInventory);

        this.neighbours.addAll(neighbour.neighbours);
        for(Room room : neighbour.neighbours){
            room.neighbours.remove(neighbour);
            room.neighbours.add(this);
        }
        this.neighbours.remove(this);

        return neighbour;
    }

    public Room splitRoom(){
        Random random = new Random();

        if(!this.peopleInRoom.isEmpty() || this.capacity <= 1)
            return null;

        Room newRoom = new Room(random.nextInt(this.capacity - 1) + 1);
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

            this.neighbours.add(newRoom);
            newRoom.neighbours.add(this);
        }

        return newRoom;
    }

    public boolean acceptNewPerson(Person person){
        if(peopleInRoom.size() >= capacity)
            return false;
        peopleInRoom.add(person);
        return true;
    }
}
