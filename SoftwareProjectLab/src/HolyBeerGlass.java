public class HolyBeerGlass extends TimedItem{

    /**
     * Az HolyBeerGlass osztály konstruktora
     * @param id : Az HolyBeerGlass azonosítója
     */
    public HolyBeerGlass(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        setActive(true);
    }

    @Override
    public void pickUp(Person person) {
        activate(person);
    }

    @Override
    public boolean defend() {
        return (isActive() && !isDestroyed());
    }
}
