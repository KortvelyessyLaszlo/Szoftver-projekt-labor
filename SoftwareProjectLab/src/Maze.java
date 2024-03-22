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
        Skeleton.log("Maze.getPlayerCount()", true);
        Skeleton.log("return " + playerCount, false);
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        Skeleton.log("Maze.setPlayerCount()", true);
        this.playerCount = playerCount;
        Skeleton.log("return", false);
    }

    public List<Room> getRooms() {
        Skeleton.log("Maze.getRooms()", true);
        Skeleton.log("return", false);
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        Skeleton.log("Maze.setRooms()", true);
        this.rooms = rooms;
        Skeleton.log("return", false);
    }

    // Add a room to the maze
    public void addRoom(Room room) {
        Skeleton.log("Maze.addRoom()", true);
        rooms.add(room);
        Skeleton.log("return", false);
    }

    // Remove a room from the maze
    public void removeRoom(Room room) {
        Skeleton.log("Maze.removeRoom()", true);
        rooms.remove(room);
        Skeleton.log("return", false);
    }

    public void startCombineRooms() {
        Skeleton.log("Maze.startCombineRooms()", true);
        Random random = new Random();
        Room pickedRoom = rooms.get(random.nextInt(rooms.size()));

        Room neighbour = pickedRoom.combineRooms();
        if(neighbour != null)
            rooms.remove(neighbour);
        Skeleton.log("return", false);
    }

    public void startSplitRooms() {
        Skeleton.log("Maze.startSplitRooms()", true);
        Random random = new Random();
        Room pickedRoom = rooms.get(random.nextInt(rooms.size()));

        Room newRoom = pickedRoom.splitRoom();
        if(newRoom != null)
            rooms.add(newRoom);
        Skeleton.log("return", false);
    }
}
