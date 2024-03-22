public class Student extends Person{

    public Student(String name) {
        super(name);
    }

    public void useItem(Item item){
        item.activate(this);
    }

    public void dropItem(Item item){
        item.setActive(false);
        getCurrentRoom().addItem(item);
        this.removeItem(item);
    }

    public void meet(Teacher teacher){
        if(teacher.isPoisoned() || teacher.isStunned())
            return;

        if(checkForStunItems()){
            teacher.setStunned(true);
            return;
        }

        if(checkForDefensiveItems())
            return;

        teacher.eliminate(this);
    }

    @Override
    public void pickUp(Item item){
        super.pickUp(item);
        item.pickUp(this);
    }

    public boolean checkForDefensiveItems(){
        for(Item item : getItemInventory()){
            if(item.defend()){
                return true;
            }
        }
        return false;
    }

    public void pairItems(){
        for(Item item : getItemInventory()){
            for(Item item2 : getItemInventory()){
                item.pair(item2);
            }
        }
    }

    public boolean checkForStunItems(){
        for(Item item : getItemInventory()){
            if(item.stun()){
                return true;
            }
        }
        return false;
    }
}
