import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Janitor extends Person implements ITickable{

    public Janitor(String name) {
        super(name);
    }

    @Override
    public void enter(Room room) {
        Skeleton.log(this.getName() + ".enter(" + room.getId() + ")", true);
        if (!room.acceptNewPerson(this)) {
            Skeleton.log("return", false);
            return;
        }

        currentRoom.removePerson(this);
        currentRoom = room;

        currentRoom.setGassed(false);
        currentRoom.setSticky(false);
        currentRoom.setenterCounter(0);

        List<Room> temp = currentRoom.getNeighbours();
        temp.removeIf(temproom -> temproom.getPeopleInRoom().size() == temproom.getCapacity());

        for (Person person : currentRoom.getPeopleInRoom()) {
            Collections.shuffle(temp);
            if (person != this && !temp.isEmpty()) {
                person.enter(temp.getFirst());
            }
        }
        Skeleton.log("return", false);
    }

    @Override
    public void tick() {
        Skeleton.log(this.getName() + ".tick()", true);
        Random rand = new Random();
        if(rand.nextInt(4) == 0)
            this.enter(currentRoom.getNeighbours().get(rand.nextInt(currentRoom.getNeighbours().size())));
        Skeleton.log("return", false);
    }
}
