public class AirFreshener extends Item {

    public AirFreshener(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        Skeleton.log("AirFreshener"+this.getId()+".activate(" + person.getName() + ")", true);
        person.getCurrentRoom().setGassed(false);
        this.setDestroyed(true);
        person.removeItem(this);
        Skeleton.log("return", false);
    }
}
