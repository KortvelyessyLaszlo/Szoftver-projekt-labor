import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    private int playerCount;
    private List<Room> rooms = new ArrayList<Room>();

    public Maze(int playerCount) {
        this.playerCount = playerCount;
    }

    // Getters and setters
    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    // Add a room to the maze
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Remove a room from the maze
    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public void startCombineRooms() {
        Random random = new Random();
        Room pickedRoom = rooms.get(random.nextInt(rooms.size()));

        Room neighbour = pickedRoom.combineRooms();
        if(neighbour != null)
            rooms.remove(neighbour);
    }

    public void startSplitRooms() {
        Random random = new Random();
        Room pickedRoom = rooms.get(random.nextInt(rooms.size()));

        Room newRoom = pickedRoom.splitRoom();
        if(newRoom != null)
            rooms.add(newRoom);
    }
}
