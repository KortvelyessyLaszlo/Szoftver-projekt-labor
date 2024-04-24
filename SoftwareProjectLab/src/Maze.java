import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Maze implements ITickable, Serializable {
    /**
     * A labirintusban lévő játékosok száma
     */
    private int playerCount;

    /**
     * A következő még nem kiosztott szoba id
     */
    private int nextRoomId = 0;

    /**
     * A szobákat tároló lista
     */
    private List<Room> rooms = new ArrayList<Room>();

    /**
     * A Maze osztály konstruktora
     * @param playerCount : A játékosok száma
     */
    public Maze(int playerCount) {
        this.playerCount = playerCount;
    }

    /**
     * A Maze osztály playerCount attribútumához tartozó getter függvény
     * @return A játékosok száma
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * A Maze osztály playerCount attribútumához tartozó setter függvény
     * @param playerCount : A játékosok száma
     */
    public void setPlayerCount(int playerCount) {
        Skeleton.log("Maze.setPlayerCount(" + playerCount + ")", true);
        this.playerCount = playerCount;
        Skeleton.log("return", false);
    }

    /**
     * A Maze osztály rooms attribútumához tartozó getter függvény
     * @return A labirintusban lévő szobák listája
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * A Maze osztály rooms attribútumához tartozó setter függvény
     * @param rooms : A labirintusban lévő szobák listája
     */
    public void setRooms(List<Room> rooms) {
        Skeleton.log("Maze.setRooms(" + rooms + ")", true);
        this.rooms = rooms;
        Skeleton.log("return", false);
    }

    /**
     * A laborintushoz egy szoba hozzáadásáért felelős függvény
     * @param room : A hozzáadandó szoba
     */
    public void addRoom(Room room) {
        Skeleton.log("Maze.addRoom(Room" + room.getId() + ")", true);
        rooms.add(room);
        nextRoomId++;
        Skeleton.log("return", false);
    }

    /**
     * A laborintusból egy szoba eltávolításáért felelős függvény
     * @param room : Eltávolítandó szoba
     */
    public void removeRoom(Room room) {
        Skeleton.log("Maze.removeRoom(Room" + room.getId() + ")", true);
        rooms.remove(room);
        Skeleton.log("return", false);
    }

    /**
     *  Véletlenszerűen kiválaszt egy olyan szobát a
        labirintusban, amelyben nincs játékos, és meghívja ennek a combineRooms()
        függvényét, amikor ez visszatér egy szobával, azt törli a szoba listájából.
     */
    public void startCombineRooms() {
        Skeleton.log("Maze.startCombineRooms()", true);
        Random random = new Random();
        Room pickedRoom = rooms.get(random.nextInt(rooms.size()));

        Room neighbour = pickedRoom.combineRooms();
        if(neighbour != null)
            rooms.remove(neighbour);
        Skeleton.log("return", false);
    }

    /**
     *  Véletlenszerűen kiválaszt egy olyan szobát a labirintusban,
        amelyben nincs játékos, és meghívja ennek a splitRoom() függvényét, amikor ez
        visszatér egy szobával, a visszakapott szobát elhelyezi a szobák listájába.
     */
    public void startSplitRooms() {
        Skeleton.log("Maze.startSplitRooms()", true);
        Random random = new Random();
        Room pickedRoom = rooms.get(random.nextInt(rooms.size()));

        Room newRoom = pickedRoom.splitRoom(nextRoomId);
        if(newRoom != null) {
            rooms.add(newRoom);
            nextRoomId++;
        }
        Skeleton.log("return", false);
    }
    @Override
    public String toString(){
        return this.getClass() + ", playerCount=" + playerCount + ", roomCount=" + rooms.size();
    }

    @Override
    public void tick() {
        Skeleton.log("Maze.tick()", true);
        Random random = new Random();
        int randomValue = random.nextInt(4);
        if(randomValue > 0)
            return;

        if(random.nextBoolean())
            startCombineRooms();
        else
            startSplitRooms();
        Skeleton.log("return", false);
    }
}
