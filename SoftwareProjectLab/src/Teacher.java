public class Teacher extends Person{
    /**
     * A tanár bénult-e
     */
    private boolean isStunned;

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
    }

    @Override
    public void meet(Person p){
        p.meet(this);
    }
    @Override
    public String toString(){
        return super.toString()+", isStunned="+isStunned;
    }
}
