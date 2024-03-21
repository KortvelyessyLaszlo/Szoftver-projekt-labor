import java.util.ArrayList;
import java.util.List;

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
        // Update cursed doors
    }

    @Override
    public Room splitRoom() {
        return this;
    }

    @Override
    public Room combineRooms() {
        return this;
    }
}
