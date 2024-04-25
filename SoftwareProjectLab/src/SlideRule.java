public class SlideRule extends Item{

    public boolean isFake() {
        return isFake;
    }

    public void setFake(boolean fake) {
        isFake = fake;
    }

    /**
     * Az adott logarléc hamis-e
     */
    private boolean isFake = false;

    /**
     * A SlideRule osztály konstruktora
     * @param id : Az adott SlideRule azonosítója
     */
    public SlideRule(int id, boolean isFake) {
        super(id);
        this.isFake = isFake;
    }

    @Override
    public void activate(Person person) {
        Skeleton.log("SlideRule" + this.getId() + ".activate(" + person.getName() + ")", true);
        if(isFake){
            Skeleton.log("return", false);
            return;
        }
        victory(person);
        Skeleton.log("return", false);
    }

    @Override
    public void pickUp(Person person) {
        Skeleton.log("SlideRule" + this.getId() + ".pickUp(" + person.getName() + ")", true);
        activate(person);
        Skeleton.log("return", false);
    }

    /**
     *  Kiváltja a játék győzelmi viselkedését és megjeleníti a logarléc
        megtalálójának nevét.
     * @param person : A játékos, akinél a logarléc megtalálható
     */
    public void victory(Person person) {
        Skeleton.log("SlideRule" + this.getId() + ".victory(" + person.getName() + ")", true);
        Student winner = (Student) person;
        winner.setWinner(true);
        System.out.println("\u001B[31m" + person.getName() + " has won the game!\u001B[0m");
        Skeleton.log("return", false);
    }

    public String toString(){
        return super.toString() + ", isFake=" + isFake;
    }
}
