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
        Skeleton.log(this.getName() + ".setStunned(" + isStunned + ")", true);
        this.isStunned = isStunned;
        this.stunDuration = 10;
        Skeleton.log("return", false);
    }

    @Override
    public void enter(Room room){
        Skeleton.log(this.getName() + ".enter(Room" + room.getId() + ")", true);
        if(isStunned()){
            return;
        }
        super.enter(room);
        Skeleton.log("return", false);
    }

    @Override
    public void meet(Student student){
        Skeleton.log(this.getName() + ".meet(" + student.getName() + ")", true);
        if(isPoisoned() || isStunned()){
            Skeleton.log("return", false);
            return;
        }

        if(student.checkForStunItems()){
            isStunned = true;
            Skeleton.log("return", false);
            return;
        }
        if(student.checkForDefensiveItems()){
            Skeleton.log("return", false);
            return;
        }
        eliminate(student);
        Skeleton.log("return", false);
    }

    /**
     * Törli a hallgatót, annak a szobának a személy listájából, ahol a
        hallgató tartózkodott. A hallgatónál lévő tárgyak átkerülnek a szobába.
     * @param student : Az eliminált hallgató
     */
    public void eliminate(Student student){
        Skeleton.log(this.getName() + ".eliminate(" + student.getName() + ")", true);
        student.dropItems();
        student.getCurrentRoom().removePerson(student);
        Skeleton.log("return", false);
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
        Skeleton.log(this.getName() + ".tick()", true);
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
        if(isStunned() || isPoisoned()){
            Skeleton.log("return", false);
            return;
        }
        Random random = new Random();
        int randomValue = random.nextInt(4);
        if(randomValue == 0){
            if(!currentRoom.getItemInventory().isEmpty()) {
                Item item = currentRoom.getItemInventory().get(random.nextInt(currentRoom.getItemInventory().size()));
                pickUp(item);
            }
        }
        if(randomValue == 1){
            var temp = currentRoom.getNeighbours().get(random.nextInt(currentRoom.getNeighbours().size()));
            this.enter(temp);
        }
        Skeleton.log("return", false);
    }
}
