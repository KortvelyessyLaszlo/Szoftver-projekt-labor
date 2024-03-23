import java.util.ArrayList;
import java.util.Arrays;
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
        Skeleton.log("Room.setGassed()", true);
        this.isGassed = isGassed;
        Skeleton.log("return", false);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        Skeleton.log("Room.setCapacity()", true);
        this.capacity = capacity;
        Skeleton.log("return", false);
    }

    public List<Person> getPeopleInRoom() {
        return peopleInRoom;
    }

    public void setPeopleInRoom(List<Person> peopleInRoom) {
        Skeleton.log("Room.setPeopleInRoom()", true);
        this.peopleInRoom = peopleInRoom;
        Skeleton.log("return", false);
    }

    public List<Item> getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(List<Item> itemInventory) {
        Skeleton.log("Room.setItemInventory()", true);
        this.itemInventory = itemInventory;
        Skeleton.log("return", false);
    }

    public List<Room> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Room> neighbours) {
        Skeleton.log("Room.setNeighbours()", true);
        Skeleton.log("return", false);
        this.neighbours = neighbours;
    }

    // Add a person to the room
    public void addPerson(Person person) {
        Skeleton.log("Room.addPerson()", true);
        Skeleton.log("return", false);
        peopleInRoom.add(person);
    }

    // Remove a person from the room
    public void removePerson(Person person) {
        Skeleton.log("Room.removePerson()", true);
        peopleInRoom.remove(person);
        Skeleton.log("return", false);
    }

    // Add an item to the room
    public void addItem(Item item) {
        Skeleton.log("Room.addItem()", true);
        itemInventory.add(item);
        Skeleton.log("return", false);
    }

    // Remove an item from the room
    public void removeItem(Item item) {
        Skeleton.log("Room.removeItem()", true);
        itemInventory.remove(item);
        Skeleton.log("return", false);
    }

    // Add a neighbour to the room
    public void addNeighbour(Room neighbour) {
        Skeleton.log("Room.addNeighbour()", true);
        neighbours.add(neighbour);
        Skeleton.log("return", false);
    }

    // Remove a neighbour from the room
    public void removeNeighbour(Room neighbour) {
        Skeleton.log("Room.removeNeighbour()", true);
        neighbours.remove(neighbour);
        Skeleton.log("return", false);
    }

    public Room combineRooms(){
        Skeleton.log("Room.combineRooms()", true);
        Random random = new Random();
        Room neighbour = neighbours.get(random.nextInt(neighbours.size()));

        if(!neighbour.peopleInRoom.isEmpty() || !this.peopleInRoom.isEmpty()){
            Skeleton.log("return", false);
            return null;
        }

        if(neighbour.isGassed)
            this.isGassed = true;

        if(neighbour.capacity > this.capacity)
            this.capacity = neighbour.capacity;

        this.itemInventory.addAll(neighbour.itemInventory);

        for(Room room : neighbour.neighbours){
            if(room != this) {
                room.addNeighbour(this);
                this.neighbours.add(room);
            }
            room.removeNeighbour(neighbour);
        }

        Skeleton.log("return " + neighbour, false);
        return neighbour;
    }

    public Room splitRoom(){
        Skeleton.log("Room.splitRoom()", true);
        Random random = new Random();

        if(!this.peopleInRoom.isEmpty() || this.capacity <= 1){
            Skeleton.log("return", false);
            return null;
        }

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
        }

        this.neighbours.add(newRoom);
        newRoom.neighbours.add(this);

        Skeleton.log("return " + newRoom, false);
        return newRoom;
    }

    public boolean acceptNewPerson(Person person){
        Skeleton.log("Room.acceptNewPerson()", true);
        if(peopleInRoom.size() >= capacity){
            Skeleton.log("return false", false);
            return false;
        }
        peopleInRoom.add(person);
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
            itemInventoryString.append(item.getId()).append(",");
        }

        return "Type: " + this.getClass() + ", capacity=" + capacity + ", isGassed=" + isGassed + ", peopleInRoom= [" + peopleInRoomString
                + "], itemInventory= [" + itemInventoryString + "], neighbours=" + neighbours.size();
    }
}
