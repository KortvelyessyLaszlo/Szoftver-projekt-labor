public class SlideRule extends Item{

    /**
     * Az adott logarléc hamis-e
     */
    private final boolean isFake;

    /**
     * A SlideRule osztály konstruktora
     * @param id : Az adott SlideRule azonosítója
     */
    public SlideRule(int id, boolean isFake) {
        super(id);
        this.isFake = isFake;
    }

    /**
     * A SlideRule aktiválása
     * @param person : Az adott személy
     */
    @Override
    public void activate(Person person) {
        if(isFake)
            return;
        victory(person);
    }

    /**
     * A SlideRule felvétele
     * @param person : Az adott személy
     */
    @Override
    public void pickUp(Person person) {
        activate(person);
    }

    /**
     *  Kiváltja a játék győzelmi viselkedését és megjeleníti a logarléc
        megtalálójának nevét.
     * @param person : A játékos, akinél a logarléc megtalálható
     */
    public void victory(Person person) {
        Student winner = (Student) person;
        winner.setWinner(true);
        System.out.println(person.getName() + " has won the game!");
    }

}
