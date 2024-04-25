public class AirFreshener extends Item {

    public AirFreshener(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        person.getCurrentRoom().setGassed(false);
        this.setDestroyed(true);
        person.removeItem(this);
    }
}
