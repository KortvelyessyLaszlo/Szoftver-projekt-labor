public class FFP2Mask extends TimedItem{

    /**
     * Az FFP2Mask osztály adattagja, ami jelzi, hogy a maszk hamis-e
     */
    private final boolean isFake;

    /**
     * Az FFP2Mask osztály konstruktora
     * @param id : Az FFP2Mask azonosítója
     */
    public FFP2Mask(int id, boolean isFake) {
        super(id);
        this.isFake = isFake;
    }

    /**
     * A maszk aktiválása
     * Ha a maszk hamis, akkor nem aktiválódik
     * @param person : Az adott személy
     */
    @Override
    public void activate(Person person) {
        if(isFake){
            return;
        }
        setActive(true);
    }

    /**
     * A maszk felvétele
     * @param person : Az adott személy
     */
    @Override
    public void pickUp(Person person) {
        activate(person);
    }

    /**
     * A maszk védekezése a gáz ellen
     * Ha a maszk aktív és nem megsemmisült, akkor védekezik a gáz ellen
     * @return : Igaz, ha a maszk védekezik a gáz ellen, egyébként hamis
     */
    @Override
    public boolean defendAgainstGas() {
        return (isActive() && !isDestroyed());
    }



}
