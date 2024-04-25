import java.util.Random;

public class Teacher extends Person {
    /**
     * A tanár bénult-e
     */
    private boolean isStunned;

    /**
     * A tanár bénulásának ideje
     */
    private int stunDuration = 0;

    /**
     * A Teacher osztály konstruktora
     * @param name : A tanár neve
     */
    public Teacher(String name) {
        super(name);
    }

    /**
     * A Teacher isStunned attribútumára vonatkozó getter függvény
     * @return A tanár bénult-e
     */
    public boolean isStunned() {
        return isStunned;
    }

    /**
     * A Teacher isStunned attribútumára vonatkozó setter függvény
     * @param isStunned : A tanár bénult-e
     */
    public void setStunned(boolean isStunned) {
        this.isStunned = isStunned;
        this.stunDuration = 10;
    }

    @Override
    public void enter(Room room){
        if(isStunned()){
            return;
        }
        super.enter(room);
    }

    @Override
    public void meet(Student student){
        if(isPoisoned() || isStunned())
            return;

        if(student.checkForStunItems()){
            isStunned = true;
            return;
        }
        if(student.checkForDefensiveItems())
            return;
        eliminate(student);
    }

    /**
     * Törli a hallgatót, annak a szobának a személy listájából, ahol a
        hallgató tartózkodott. A hallgatónál lévő tárgyak átkerülnek a szobába.
     * @param student : Az eliminált hallgató
     */
    public void eliminate(Student student){
        student.dropItems();
        student.getCurrentRoom().removePerson(student);
        System.out.println("\u001B[31m" + this.getName() + " has slayed " + student.getName() + "\u001B[0m");
    }

    @Override
    public void meet(Person p){
        p.meet(this);
    }
    @Override
    public String toString(){
        return super.toString()+", isStunned="+isStunned;
    }


    /**
     * A tanár cselekedeteinek leírása
     * A tanár véletlenszerűen választ egy cselekvést:
     * 0: Tárgy felvétele
     * 1: Szobaváltás
     */
    @Override
    public void tick() {
        if(isStunned){
            stunDuration--;
            if(stunDuration == 0)
                isStunned = false;
        }
        if(isPoisoned()){
            setPoisonDuration(getPoisonDuration()-1);
            if(getPoisonDuration() == 0)
                setPoisoned(false);
        }
        if(isStunned() || isPoisoned())
            return;

        Random random = new Random();
        int randomValue = random.nextInt(4);
        if(randomValue == 0){
            if(!currentRoom.getItemInventory().isEmpty()) {
                Item item = currentRoom.getItemInventory().get(random.nextInt(currentRoom.getItemInventory().size()));
                pickUp(item);
            }
        }
        if(randomValue == 1){
            if(!currentRoom.getNeighbours().isEmpty())
                enter(currentRoom.getNeighbours().get(random.nextInt(currentRoom.getNeighbours().size())));
        }
    }
}
