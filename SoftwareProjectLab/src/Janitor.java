import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Janitor extends Person {

    public Janitor(String name) {
        super(name);
    }

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

    @Override
    public void tick() {
        Random rand = new Random();
        if(rand.nextInt(4) == 0)
            this.enter(currentRoom.getNeighbours().get(rand.nextInt(currentRoom.getNeighbours().size())));
    }
}
