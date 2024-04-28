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

    /**
     * A tanár belép egy szobába
     * Ha a tanár bénult, akkor nem lép be a szobába
     *
     * @param room : A szoba, amibe belép
     */
    @Override
    public void enter(Room room){
        if(isStunned()){
            return;
        }
        super.enter(room);
    }

    /**
     * A tanár találkozik egy hallgatóval. Ha a tanár mérgezve
        van, vagy bénult, akkor a függvény visszatér. Ellenkező esetben a hallgató megnézi,
        hogy van-e nála bénító tárgy, ha igen, akkor a tanár bénul. Ha nincs, akkor megnézi,
        hogy van-e nála védő tárgy, ha igen, akkor a függvény visszatér. Ha nincs, akkor a
        hallgató eliminálódik.
     * @param student : A hallgató, akivel a tanár találkozott
     */
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
        System.out.println(this.getName() + " has slayed " + student.getName());
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
