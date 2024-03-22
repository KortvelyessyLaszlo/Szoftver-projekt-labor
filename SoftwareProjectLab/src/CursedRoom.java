import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CursedRoom extends Room{
    private List<Room> invisibleRooms = new ArrayList<Room>();

    public CursedRoom(int capacity) {
        super(capacity);
    }

    // Getters and setters
    public List<Room> getInvisibleRooms() {
        return invisibleRooms;
    }

    public void setInvisibleRooms(List<Room> invisibleRooms) {
        this.invisibleRooms = invisibleRooms;
    }

    // Add an invisible room to the cursed room
    public void addInvisibleRoom(Room room) {
        invisibleRooms.add(room);
    }

    // Remove an invisible room from the cursed room
    public void removeInvisibleRoom(Room room) {
        invisibleRooms.remove(room);
    }

    public void updateCursedDoors() {
        Skeleton.log("CursedRoom.updateCursedDoors()", true);
        getNeighbours().addAll(invisibleRooms);
        invisibleRooms.clear();

        Random random = new Random();
        Iterator<Room> iterator = getNeighbours().iterator();
        while (iterator.hasNext()) {
            Room neighbour = iterator.next();
            if(random.nextBoolean() && getNeighbours().size() > 1){
                invisibleRooms.add(neighbour);
                iterator.remove();
            }
        }
        Skeleton.log("return", false);
    }

    @Override
    public Room splitRoom() {
        Skeleton.log("CursedRoom.splitRoom()", true);
        getNeighbours().addAll(invisibleRooms);
        invisibleRooms.clear();
        Room newRoom = super.splitRoom();
        Skeleton.log("return" + newRoom, false);
        return newRoom;
    }

    @Override
    public Room combineRooms() {
        Skeleton.log("CursedRoom.combineRooms()", true);
        getNeighbours().addAll(invisibleRooms);
        invisibleRooms.clear();
        Room neighbour = super.combineRooms();
        Skeleton.log("return " + neighbour, false);
        return neighbour;
    }
}
