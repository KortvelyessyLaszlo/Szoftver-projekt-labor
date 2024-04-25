public class HolyBeerGlass extends TimedItem{

    /**
     * Az HolyBeerGlass osztály konstruktora
     * @param id : Az HolyBeerGlass azonosítója
     */
    public HolyBeerGlass(int id) {
        super(id);
    }

    /**
     * Az HolyBeerGlass aktiválása
     * @param person : Az adott személy
     */
    @Override
    public void activate(Person person) {
        setActive(true);
    }

    /**
     * Az HolyBeerGlass felvétele
     * @param person : Az adott személy
     */
    @Override
    public void pickUp(Person person) {
        activate(person);
    }

    /**
     * Az HolyBeerGlass védekezése
     * Ha az HolyBeerGlass aktív és nem megsemmisült, akkor védekezik
     * @return : Igaz, ha az HolyBeerGlass védekezik, egyébként hamis
     */
    @Override
    public boolean defend() {
        return (isActive() && !isDestroyed());
    }
}
