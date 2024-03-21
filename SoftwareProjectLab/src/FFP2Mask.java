public class FFP2Mask extends TimedItem{

    public FFP2Mask(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        // Activate the FFP2 mask
    }

    @Override
    public void pickUp(Person person) {
        // Pick up the FFP2 mask
    }

    @Override
    public boolean defendAgainstGas() {
        // Defend against gas
        return true;
    }
}
