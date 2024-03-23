public class SlideRule extends Item{

    public SlideRule(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        Skeleton.log("SlideRule" + this.getId() + ".activate(" + person.getName() + ")", true);
        victory(person);
        Skeleton.log("return", false);
    }

    @Override
    public void pickUp(Person person) {
        Skeleton.log("SlideRule" + this.getId() + ".pickUp(" + person.getName() + ")", true);
        activate(person);
        Skeleton.log("return", false);
    }

    public void victory(Person person) {
        Skeleton.log("SlideRule" + this.getId() + ".victory(" + person.getName() + ")", true);
        System.out.println(person.getName() + " has won the game!");
        Skeleton.log("return", false);
    }
}
