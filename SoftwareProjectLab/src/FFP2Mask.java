public class FFP2Mask extends TimedItem{

    public boolean isFake() {
        return isFake;
    }

    public void setFake(boolean fake) {
        isFake = fake;
    }

    /**
     * Az FFP2Mask osztály adattagja, ami jelzi, hogy a maszk hamis-e
     */
    private boolean isFake = false;

    /**
     * Az FFP2Mask osztály konstruktora
     * @param id : Az FFP2Mask azonosítója
     */
    public FFP2Mask(int id, boolean isFake) {
        super(id);
        this.isFake = isFake;
    }

    @Override
    public void activate(Person person) {
        if(isFake){
            return;
        }
        setActive(true);
    }

    @Override
    public void pickUp(Person person) {
        activate(person);
    }

    @Override
    public boolean defendAgainstGas() {
        return (isActive() && !isDestroyed());
    }

    public String toString(){
        return super.toString() + ", isFake=" + isFake;
    }
}
