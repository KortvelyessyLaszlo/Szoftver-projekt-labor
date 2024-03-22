public class CannedCamembert extends Item {
    public CannedCamembert(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        // Activate the CannedCamembert
        person.getCurrentRoom().setGassed(true);
        this.setDestroyed(true);
        person.removeItem(this);
    }
}
