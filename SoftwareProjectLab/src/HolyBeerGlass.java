public class HolyBeerGlass extends TimedItem{

    public HolyBeerGlass(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        Skeleton.log("HolyBeerGlass.activate()", true);
        setActive(true);
        Skeleton.log("return", false);
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
        boolean result = (isActive() && !isDestroyed());
        Skeleton.log("return " + result, false);
        return result;
    }
}
