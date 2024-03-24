public class FFP2Mask extends TimedItem{

    /**
     * Az FFP2Mask osztály konstruktora
     * @param id : Az FFP2Mask azonosítója
     */
    public FFP2Mask(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        Skeleton.log("FFP2Mask"+this.getId()+".activate(" + person.getName() + ")", true);
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
