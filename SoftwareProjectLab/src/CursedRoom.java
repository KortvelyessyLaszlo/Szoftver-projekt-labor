import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class CursedRoom extends Room{
    /**
     * A nem látható szobákat tároló lista
     */
    private List<Room> invisibleRooms = new ArrayList<Room>();

    /**
     * A CursedRoom osztály konstruktora
     * @param capacity : A szoba kapacitása
     * @param id : A szoba azonosítója
     */
    public CursedRoom(int capacity, int id) {
        super(capacity, id);
    }

    /**
     * A CursedRoom osztály getter függvénye
     * @return A nem látható szobákat tároló lista
     */
    public List<Room> getInvisibleRooms() {
        return invisibleRooms;
    }

    /**
     * A CursedRoom osztály setter függvénye
     * @param invisibleRooms : Egy nem látható szobákat tároló lista
     */
    public void setInvisibleRooms(List<Room> invisibleRooms) {
        this.invisibleRooms = invisibleRooms;
    }

    /**
     * A nem látható szobákat tároló listához egy új szoba hozzáadása
     * @param room : A hozzáadandó szoba
     */
    public void addInvisibleRoom(Room room) {
        invisibleRooms.add(room);
    }

    /**
     * A nem látható szobákat tároló listából egy meglévő szoba eltávolítása
     * @param room : Az eltávolítandó szoba
     */
    public void removeInvisibleRoom(Room room) {
        invisibleRooms.remove(room);
    }

    /**
     *  Az invisbleRooms listában levő szobákat hozzáadja a
        neighbours listához, majd véletlenszerűen kiválaszt szobákat a neigbours listából.
        Ezeket törli és hozzáadja az invisbleRooms listához.
     */
    public void updateCursedDoors() {
        Skeleton.log("CursedRoom" + this.getId() + ".updateCursedDoors()", true);
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
    public Room splitRoom(int newId) {
        Skeleton.log("CursedRoom" + this.getId() + ".splitRoom(" + newId + ")", true);
        getNeighbours().addAll(invisibleRooms);
        invisibleRooms.clear();
        Room newRoom = super.splitRoom(newId);
        Skeleton.log("return" + newRoom, false);
        return newRoom;
    }

    @Override
    public Room combineRooms() {
        Skeleton.log("CursedRoom" + this.getId() + ".combineRooms()", true);
        getNeighbours().addAll(invisibleRooms);
        invisibleRooms.clear();
        Room neighbour = super.combineRooms();
        Skeleton.log("return " + neighbour, false);
        return neighbour;
    }
}
