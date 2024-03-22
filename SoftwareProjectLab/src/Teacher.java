public class Teacher extends Person{
    private boolean isStunned;

    public Teacher(String name) {
        super(name);
    }

    // Getters and setters
    public boolean isStunned() {
        return isStunned;
    }

    public void setStunned(boolean isStunned) {
        this.isStunned = isStunned;
    }


    public void meet(Student student){
        if(isPoisoned() || isStunned())
            return;

        if(student.checkForStunItems()){
            isStunned = true;
            return;
        }
        if(student.checkForDefensiveItems()){
            return;
        }
        eliminate(student);
    }

    public void eliminate(Student student){
        student.dropItems();
        student.getCurrentRoom().removePerson(student);
    }
}
