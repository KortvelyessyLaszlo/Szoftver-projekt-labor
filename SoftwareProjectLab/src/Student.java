import java.util.List;

public class Student extends Person{

    public Student(String name) {
        super(name);
    }

    public void useItem(Item item){
        Skeleton.log(this.getName() + ".useItem(Item" + item.getId() + ")", true);
        item.activate(this);
        Skeleton.log("return", false);
    }

    public void dropItem(Item item){
        Skeleton.log(this.getName() + ".dropItem(Item" + item.getId() + ")", true);
        item.setActive(false);
        getCurrentRoom().addItem(item);
        this.removeItem(item);
        Skeleton.log("return", false);
    }
    @Override
    public void meet(Teacher teacher){
        Skeleton.log(this.getName() + ".meet(" + teacher.getName() + ")", true);
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
        Skeleton.log(this.getName() + ".pickUp(Item" + item.getId() + ")", true);
        super.pickUp(item);
        if(getItemInventory().size() < 5)
            item.pickUp(this);
        Skeleton.log("return", false);
    }

    public boolean checkForDefensiveItems(){
        Skeleton.log(this.getName() + ".checkForDefensiveItems()", true);
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
        Skeleton.log(this.getName() + ".pairItems()", true);
        List<Item> inventory = getItemInventory();
        for(int i = 0; i < inventory.size() - 1; i++){
            for(int j = i + 1; j < inventory.size(); j++){
                inventory.get(i).pair(inventory.get(j));
            }
        }
        Skeleton.log("return", false);
    }

    public boolean checkForStunItems(){
        Skeleton.log(this.getName() + ".checkForStunItems()", true);
        for(Item item : getItemInventory()){
            if(item.stun()){
                Skeleton.log("return true", false);
                return true;
            }
        }
        Skeleton.log("return false", false);
        return false;
    }
    @Override
    public void meet(Person p){
        p.meet(this);
    }
}
