public class SlideRule extends Item{

    public SlideRule(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        victory(person);
    }

    @Override
    public void pickUp(Person person) {
        activate(person);
    }

    public void victory(Person person) {
        System.out.println(person + " has won the game!");
    }
}
