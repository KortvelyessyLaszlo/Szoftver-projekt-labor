public class CannedCamembert extends Item {

    /**
     * A CannedCamembert osztály konstruktora
     * @param id : Az adott CannedCamembert azonosítója
     */
    public CannedCamembert(int id) {
        super(id);
    }

    /**
     * A szoba gázosításának elindítása
     * A személy által eldobott CannedCamembert gázosítja a szobát
     * A személy eltávolítja a tárgyat a tárgyai közül
     * @param person : Az adott személy
     */
    @Override
    public void activate(Person person) {
        person.getCurrentRoom().setGassed(true);
        this.setDestroyed(true);
        person.removeItem(this);
    }
}
