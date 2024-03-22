public class FFP2Mask extends TimedItem{

    public FFP2Mask(int id) {
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
    public boolean defendAgainstGas() {
        return !isDestroyed() && isActive() && getRemainingTime()>0;
    }
}
