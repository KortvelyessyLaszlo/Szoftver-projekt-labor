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
        Skeleton.log(this.getName() + ".setStunned()", true);
        this.isStunned = isStunned;
        Skeleton.log("return", false);
    }
    @Override
    public void meet(Student student){
        Skeleton.log(this.getName() + ".meet()", true);
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
        Skeleton.log(this.getName() + ".eliminate()", true);
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
        return super.toString()+", isStunned: "+isStunned;
    }
}
