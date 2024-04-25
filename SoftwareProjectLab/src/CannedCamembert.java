public class CannedCamembert extends Item {

    /**
     * A CannedCamembert osztály konstruktora
     * @param id : Az adott CannedCamembert azonosítója
     */
    public CannedCamembert(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        person.getCurrentRoom().setGassed(true);
        this.setDestroyed(true);
        person.removeItem(this);
    }
}
