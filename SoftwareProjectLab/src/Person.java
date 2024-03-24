import java.util.ArrayList;
import java.util.Arrays;
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
        Skeleton.log(this.name + ".removeItem(Item" + item.getId() + ")", true);
        itemInventory.remove(item);
        Skeleton.log("return", false);
    }

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

    public void pickUp(Item item){
        if(itemInventory.size() >= 5) {
            return;
        }
        currentRoom.removeItem(item);
        itemInventory.add(item);
    }

    public void dropItems(){
        Skeleton.log(this.name + ".dropItems()", true);
        for(Item item : itemInventory){
            item.setActive(false);
            currentRoom.addItem(item);
        }
        itemInventory.clear();
        Skeleton.log("return", false);
    }

    public void meet(Student student){}

    public void meet(Teacher teacher){}

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
