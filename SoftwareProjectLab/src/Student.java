public class Student extends Person{

    public Student(String name) {
        super(name);
    }

    public void useItem(Item item){
        Skeleton.log("Student.useItem()", true);
        item.activate(this);
        Skeleton.log("return", false);
    }

    public void dropItem(Item item){
        Skeleton.log("Student.dropItem()", true);
        item.setActive(false);
        getCurrentRoom().addItem(item);
        this.removeItem(item);
        Skeleton.log("return", false);
    }

    public void meet(Teacher teacher){
        Skeleton.log("Student.meet()", true);
        if(teacher.isPoisoned() || teacher.isStunned()){
            Skeleton.log("return", false);
            return;
        }

        if(checkForStunItems()){
            teacher.setStunned(true);
            Skeleton.log("return", false);
            return;
        }

        if(checkForDefensiveItems()){
            Skeleton.log("return", false);
            return;
        }

        teacher.eliminate(this);
        Skeleton.log("return", false);
    }

    @Override
    public void pickUp(Item item){
        Skeleton.log("Student.pickUp()", true);
        super.pickUp(item);
        item.pickUp(this);
        Skeleton.log("return", false);
    }

    public boolean checkForDefensiveItems(){
        Skeleton.log("Student.checkForDefensiveItems()", true);
        for(Item item : getItemInventory()){
            if(item.defend()){
                Skeleton.log("return true", false);
                return true;
            }
        }
        Skeleton.log("return false", false);
        return false;
    }

    public void pairItems(){
        Skeleton.log("Student.pairItems()", true);
        for(Item item : getItemInventory()){
            for(Item item2 : getItemInventory()){
                item.pair(item2);
            }
        }
        Skeleton.log("return", false);
    }

    public boolean checkForStunItems(){
        Skeleton.log("Student.checkForStunItems()", true);
        for(Item item : getItemInventory()){
            if(item.stun()){
                Skeleton.log("return true", false);
                return true;
            }
        }
        Skeleton.log("return false", false);
        return false;
    }
}
