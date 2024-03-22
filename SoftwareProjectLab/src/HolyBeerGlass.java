public class HolyBeerGlass extends TimedItem{

    public HolyBeerGlass(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        setActive(true);
    }

    @Override
    public void pickUp(Person person) {
        Skeleton.log("HolyBeerGlass.pickUp()", true);
        activate(person);
        Skeleton.log("return", false);
    }

    @Override
    public boolean defend() {
        Skeleton.log("HolyBeerGlass.defend()", true);
        Skeleton.log("return " + (isActive() && !isDestroyed()), false);
        return isActive() && !isDestroyed();
    }
}
