public class HolyBeerGlass extends TimedItem{

    public HolyBeerGlass(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        // Activate the Holy Beer Glass
    }

    @Override
    public void pickUp(Person person) {
        // Pick up the Holy Beer Glass
    }

    @Override
    public boolean defend() {
        // Defend against gas
        return true;
    }
}
