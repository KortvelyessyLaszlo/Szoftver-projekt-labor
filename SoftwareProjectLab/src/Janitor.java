import java.util.Collections;
import java.util.List;

public class Janitor extends Person {

    public Janitor(String name) {
        super(name);
    }

    @Override
    public void enter(Room room) {
        if(!room.acceptNewPerson(this)) {
            Skeleton.log("return", false);
            return;
        }

        currentRoom.removePerson(this);
        currentRoom = room;

        currentRoom.setGassed(false);
        currentRoom.setSticky(false);
        currentRoom.setenterCounter(0);

        List<Room> temp = currentRoom.getNeighbours();
        temp.removeIf(temproom -> temproom.getPeopleInRoom().size() == temproom.getNeighbours().size());

        for(Person person : currentRoom.getPeopleInRoom()) {
            Collections.shuffle(temp);
            if(person != this && !temp.isEmpty()) {
                person.enter(temp.getFirst());
            }
        }
    }
}
