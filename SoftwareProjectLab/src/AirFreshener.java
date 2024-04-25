public class AirFreshener extends Item {

    ˛/**
     * Az AirFreshener osztály konstruktora
     * @param id : Az AirFreshener azonosítója
     */
    public AirFreshener(int id) {
        super(id);
    }

    /**
     * A szoba gázosításának megszüntetése
     * A személy által eldobott AirFreshener eltávolítja a gázosítást a szobából
     * A személy eltávolítja a tárgyat a tárgyai közül
     * @param person : Az adott személy
     */
    @Override
    public void activate(Person person) {
        person.getCurrentRoom().setGassed(false);
        this.setDestroyed(true);
        person.removeItem(this);
    }
}
