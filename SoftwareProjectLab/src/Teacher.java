public class Teacher extends Person{
    private boolean isStunned;

    public Teacher(String name) {
        super(name);
    }

    // Getters and setters
    public boolean isStunned() {
        Skeleton.log("Teacher.isStunned()", true);
        Skeleton.log("return " + isStunned, false);
        return isStunned;
    }

    public void setStunned(boolean isStunned) {
        Skeleton.log("Teacher.setStunned()", true);
        this.isStunned = isStunned;
        Skeleton.log("return", false);
    }
    @Override
    public void meet(Student student){
        Skeleton.log("Teacher.meet()", true);
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

    public void eliminate(Student student){
        Skeleton.log("Teacher.eliminate()", true);
        student.dropItems();
        student.getCurrentRoom().removePerson(student);
        Skeleton.log("return", false);
    }

    @Override
    public void meet(Person p){
        Skeleton.log("Teacher.meet()", true);
        p.meet(this);

        Skeleton.log("return", false);
    }
    @Override
    public String toString(){
        return super.toString()+"\nisStunned: "+isStunned;
    }
}
