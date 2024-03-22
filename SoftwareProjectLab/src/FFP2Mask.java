public class FFP2Mask extends TimedItem{

    public FFP2Mask(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        Skeleton.log("FFP2Mask.activate()", true);
        setActive(true);
        Skeleton.log("return", false);
    }

    @Override
    public void pickUp(Person person) {
        Skeleton.log("FFP2Mask.pickUp()", true);
        activate(person);
        Skeleton.log("return", false);
    }

    @Override
    public boolean defendAgainstGas() {
        Skeleton.log("FFP2Mask.defendAgainstGas()", true);
        boolean result = (isActive() && !isDestroyed());
        Skeleton.log("return " + result, false);
        return result;
    }
}
