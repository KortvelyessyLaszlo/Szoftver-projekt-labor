import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Janitor extends Person {

    /**
     * A Janitor osztály konstruktora
     * @param name : A Janitor neve
     */
    public Janitor(String name) {
        super(name);
    }

    /**
     * A Janitor belép egy szobába
     * Ha a szoba nem tudja fogadni a Janitort, akkor nem lép be
     * A Janitor kitakarítja a szobát, ahol tartózkodik
     * A szomszédos szobákba átkerülnek a szobában lévő személyek
     * @param room : Az adott szoba
     */
    @Override
    public void enter(Room room) {
        if (!room.acceptNewPerson(this))
            return;

        currentRoom.removePerson(this);
        currentRoom = room;

        currentRoom.setGassed(false);
        currentRoom.setSticky(false);
        currentRoom.setEnterCounter(0);

        List<Room> neighbours = currentRoom.getNeighbours();
        List<Person> people = currentRoom.getPeopleInRoom();

        for (Person person : people) {
            neighbours.removeIf(temproom -> temproom.getPeopleInRoom().size() == temproom.getCapacity());
            Collections.shuffle(neighbours);
            if (person != this && !neighbours.isEmpty()) {
                person.enter(neighbours.getFirst());
            }
        }
        System.out.println("\u001B[31m" + this.getName() + " has cleared Room "+ room.getId() + "\u001B[0m");
    }

    /**
     * A Janitor mozgatása
     * Ha a Janitor nem tudja elhagyni a szobát, akkor nem mozog
     * A Janitor véletlenszerűen választ egy szomszédos szobát és belép
     */
    @Override
    public void tick() {
        Random rand = new Random();
        if(rand.nextInt(4) == 0)
            if(!currentRoom.getNeighbours().isEmpty())
                this.enter(currentRoom.getNeighbours().get(rand.nextInt(currentRoom.getNeighbours().size())));
    }
}
