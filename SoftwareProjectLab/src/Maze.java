import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze implements ITickable, Serializable {

    /**
     * A következő még nem kiosztott szoba id
     */
    private int nextRoomId = 0;

    /**
     * A szobákat tároló lista
     */
    private final List<Room> rooms = new ArrayList<>();

    /**
     * A következő szoba id-jét visszaadó függvény
     * @return A következő szoba id-je
     */
    public int getNextRoomId() {
        return nextRoomId;
    }

    /**
     * A Maze osztály rooms attribútumához tartozó getter függvény
     * @return A labirintusban lévő szobák listája
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * A laborintushoz egy szoba hozzáadásáért felelős függvény
     * @param room : A hozzáadandó szoba
     */
    public void addRoom(Room room) {
        rooms.add(room);
        nextRoomId++;
    }

    /**
     *  Véletlenszerűen kiválaszt egy olyan szobát a
        labirintusban, amelyben nincs játékos, és meghívja ennek a combineRooms()
        függvényét, amikor ez visszatér egy szobával, azt törli a szoba listájából.
     */
    public void startCombineRooms() {
        Random random = new Random();
        Room pickedRoom = rooms.get(random.nextInt(rooms.size()));

        Room neighbour = pickedRoom.combineRooms();
        if(neighbour != null)
            rooms.remove(neighbour);
    }

    /**
     *  Véletlenszerűen kiválaszt egy olyan szobát a labirintusban,
        amelyben nincs játékos, és meghívja ennek a splitRoom() függvényét, amikor ez
        visszatér egy szobával, a visszakapott szobát elhelyezi a szobák listájába.
     */
    public void startSplitRooms() {
        Random random = new Random();
        Room pickedRoom = rooms.get(random.nextInt(rooms.size()));

        Room newRoom = pickedRoom.splitRoom(nextRoomId);
        if(newRoom != null) {
            rooms.add(newRoom);
            nextRoomId++;
        }
    }
    @Override
    public String toString(){
        return this.getClass() + ", roomCount=" + rooms.size();
    }


    /**
     * A labirintus tickeléséért felelős függvény
     * A függvény véletlenszerűen választ egy számot 0 és 3 között.
     * Ha a szám nagyobb mint 0, akkor a függvény visszatér.
     * Ha a szám páros, akkor a startCombineRooms() függvényt hívja meg.
     * Ha a szám páratlan, akkor a startSplitRooms() függvényt hívja meg.
     */
    public void tick() {
        Random random = new Random();
        int randomValue = random.nextInt(4);
        if(randomValue > 0)
            return;

        if(random.nextBoolean())
            startCombineRooms();
        else
            startSplitRooms();

    }
}
