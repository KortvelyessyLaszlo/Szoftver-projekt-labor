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
    }

    @Override
    public Room splitRoom() {
        getNeighbours().addAll(invisibleRooms);
        invisibleRooms.clear();
        return super.splitRoom();
    }

    @Override
    public Room combineRooms() {
        getNeighbours().addAll(invisibleRooms);
        invisibleRooms.clear();
        return super.combineRooms();
    }
}
