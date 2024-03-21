import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    private boolean isPoisoned;
    private String name;
    private Room currentRoom;
    private List<Item> itemInventory = new ArrayList<Item>();

    public Person(String name) {
        this.name = name;
    }

    // Getters and setters
    public boolean isPoisoned() {
        return isPoisoned;
    }

    public void setPoisoned(boolean isPoisoned) {
        this.isPoisoned = isPoisoned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<Item> getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(List<Item> itemInventory) {
        this.itemInventory = itemInventory;
    }

    // Add an item to the person's inventory
    public void addItem(Item item) {
        itemInventory.add(item);
    }

    // Remove an item from the person's inventory
    public void removeItem(Item item) {
        itemInventory.remove(item);
    }

    public void enter(Room room){}

    public void pickUp(Item item){}

    public void dropItems(){}

    public void meet(Person person){}
}
