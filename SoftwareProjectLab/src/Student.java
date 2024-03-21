public class Student extends Person{

    public Student(String name) {
        super(name);
    }

    public void useItem(Item item){}

    public void dropItem(Item item){}

    public void meet(Teacher teacher){}

    @Override
    public void pickUp(Item item){}

    public boolean checkForDefensiveItems(){
        return false;
    }

    public void pairItems(){}

    public boolean checkForMask(){
        return false;
    }

    public boolean checkForStunItems(){
        return false;
    }
}
