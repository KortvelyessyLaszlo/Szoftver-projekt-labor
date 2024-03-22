public class SlideRule extends Item{

    public SlideRule(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        Skeleton.log("SlideRule.activate()", true);
        victory(person);
        Skeleton.log("return", false);
    }

    @Override
    public void pickUp(Person person) {
        Skeleton.log("SlideRule.pickUp()", true);
        activate(person);
        Skeleton.log("return", false);
    }

    public void victory(Person person) {
        Skeleton.log("SlideRule.victory()", true);
        System.out.println(person + " has won the game!");
        Skeleton.log("return", false);
    }
}
