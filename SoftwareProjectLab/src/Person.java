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
        Skeleton.log("Person.isPoisoned()", true);
        Skeleton.log("return " + isPoisoned, false);
        return isPoisoned;
    }

    public void setPoisoned(boolean isPoisoned) {
        Skeleton.log("Person.setPoisoned()", true);
        this.isPoisoned = isPoisoned;
        Skeleton.log("return", false);
    }

    public String getName() {
        Skeleton.log("Person.getName()", true);
        Skeleton.log("return " + name, false);
        return name;
    }

    public void setName(String name) {
        Skeleton.log("Person.setName()", true);
        this.name = name;
        Skeleton.log("return", false);
    }

    public Room getCurrentRoom() {
        Skeleton.log("Person.getCurrentRoom()", true);
        Skeleton.log("return " + currentRoom, false);
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        Skeleton.log("Person.setCurrentRoom()", true);
        this.currentRoom = currentRoom;
        Skeleton.log("return", false);
    }

    public List<Item> getItemInventory() {
        Skeleton.log("Person.getItemInventory()", true);
        Skeleton.log("return ", false);
        return itemInventory;
    }

    public void setItemInventory(List<Item> itemInventory) {
        Skeleton.log("Person.setItemInventory()", true);
        this.itemInventory = itemInventory;
        Skeleton.log("return", false);
    }

    // Add an item to the person's inventory
    public void addItem(Item item) {
        Skeleton.log("Person.addItem()", true);
        itemInventory.add(item);
        Skeleton.log("return", false);
    }

    // Remove an item from the person's inventory
    public void removeItem(Item item) {
        Skeleton.log("Person.removeItem()", true);
        itemInventory.remove(item);
        Skeleton.log("return", false);
    }

    public void enter(Room room){
        Skeleton.log("Person.enter()", true);
        if(!room.acceptNewPerson(this)) {
            Skeleton.log("return", false);
            return;
        }

        currentRoom.removePerson(this);
        currentRoom = room;

        if(currentRoom.isGassed() && !checkForMask()) {
            setPoisoned(true);
            dropItems();
            Skeleton.log("return", false);
            return;
        }

        for(Person person : currentRoom.getPeopleInRoom()){
            if(person != this)person.meet(this);
        }
        Skeleton.log("return", false);
    }

    public boolean checkForMask(){
        Skeleton.log("Person.checkForMask()", true);
        for(Item item : getItemInventory()){
            if(item.defendAgainstGas()){
                Skeleton.log("return true", false);
                return true;
            }
        }
        Skeleton.log("return false", false);
        return false;
    }

    public void pickUp(Item item){
        Skeleton.log("Person.pickUp()", true);
        if(itemInventory.size() >= 5) {
            Skeleton.log("return", false);
            return;
        }
        currentRoom.removeItem(item);
        itemInventory.add(item);
        Skeleton.log("return", false);
    }

    public void dropItems(){
        Skeleton.log("Person.dropItems()", true);
        for(Item item : itemInventory){
            item.setActive(false);
            currentRoom.addItem(item);
        }
        itemInventory.clear();
        Skeleton.log("return", false);
    }

    public void meet(Student person){
        Skeleton.log("Person.meet()", true);
        Skeleton.log("return", false);
    }

    public void meet(Teacher person){
        Skeleton.log("Person.meet()", true);
        Skeleton.log("return", false);
    }

    public void meet(Person p){

        Skeleton.log("Person.meet()", true);
        Skeleton.log("return", false);
    }

}
