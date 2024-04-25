import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CursedRoom extends Room {
    /**
     * A nem látható szobákat tároló lista
     */
    private final List<Room> invisibleRooms = new ArrayList<>();

    /**
     * A CursedRoom osztály konstruktora
     * @param capacity : A szoba kapacitása
     * @param id : A szoba azonosítója
     */
    public CursedRoom(int capacity, int id) {
        super(capacity, id);
    }

    /**
     *  Az invisbleRooms listában levő szobákat hozzáadja a
        neighbours listához, majd véletlenszerűen kiválaszt szobákat a neigbours listából.
        Ezeket törli és hozzáadja az invisbleRooms listához.
     *  A szoba szomszédait frissíti
     *  A szoba szomszédait véletlenszerűen kiválasztja
     *  Ha a szoba szomszédai száma nagyobb mint 1, akkor a szoba szomszédai közül véletlenszerűen kiválaszt egyet
     *  A kiválasztott szomszédot hozzáadja az invisibleRooms listához
     *  A szoba szomszédait frissíti
     */
    public void updateCursedDoors() {
        Random random = new Random();
        List<Room> neighbours = new ArrayList<>(getNeighbours());

        neighbours.addAll(invisibleRooms);
        invisibleRooms.clear();

        Iterator<Room> iterator = neighbours.iterator();
        while (iterator.hasNext()) {
            Room neighbour = iterator.next();
            if(random.nextBoolean() && getNeighbours().size() > 1){
                invisibleRooms.add(neighbour);
                iterator.remove();
            }
        }
        setNeighbours(neighbours);
    }

    /**
     * A szoba szétválasztása
     * Az invisibleRooms listában levő szobákat hozzáadja a neighbours listához
     * @param newId : Az új szoba azonosítója
     * @return : Az új szoba
     */
    @Override
    public Room splitRoom(int newId) {
        getNeighbours().addAll(invisibleRooms);
        invisibleRooms.clear();
        return super.splitRoom(newId);
    }

    /**
     * A szobák összevonása
     * Az invisibleRooms listában levő szobákat hozzáadja a neighbours listához
     * Az invisibleRooms listát törli
     * @return : A kombinált szoba
     */
    @Override
    public Room combineRooms() {
        getNeighbours().addAll(invisibleRooms);
        invisibleRooms.clear();
        return super.combineRooms();
    }

    /**
     * A szoba frissítése
     */
    @Override
    public void tick() {
        updateCursedDoors();
    }
}
