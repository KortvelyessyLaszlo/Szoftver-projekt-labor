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
        Skeleton.log("FFP2Mask"+this.getId()+".activate(" + person.getName() + ")", true);
        if(isFake){
            Skeleton.log("return", false);
            return;
        }
        setActive(true);
        Skeleton.log("return", false);
    }

    @Override
    public void pickUp(Person person) {
        Skeleton.log("FFP2Mask"+this.getId()+".pickUp(" + person.getName() + ")", true);
        activate(person);
        Skeleton.log("return", false);
    }

    @Override
    public boolean defendAgainstGas() {
        Skeleton.log("FFP2Mask"+this.getId()+".defendAgainstGas()", true);
        boolean result = (isActive() && !isDestroyed());
        Skeleton.log("return " + result, false);
        return result;
    }
}
