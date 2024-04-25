public class Transistor extends Item{

    /**
     * A szoba, ahol a tranzisztor le lett téve
     */
    private Room placedTransistorRoom;

    /**
     * A párosított tranzisztornak a párja (alapértelmezésként null)
     */
    private Transistor pair = null;

    /**
     * A Transistor osztály konstruktora
     * @param id : A tranzisztor azonosítója
     */
    public Transistor(int id) {
        super(id);
    }

    /**
     * A Transistor placedTransistorRoom attribútumára vonatkozó getter függvény
     * @return A szoba, ahol a tranzisztor le lett téve
     */
    public Room getPlacedTransistorRoom() {
        return placedTransistorRoom;
    }

    /**
     * A Transistor placedTransistorRoom attribútumára vonatkozó setter függvény
     * @param placedTransistorRoom : A szoba, ahol a tranzisztor le lett téve 
     */
    public void setPlacedTransistorRoom(Room placedTransistorRoom) {
        this.placedTransistorRoom = placedTransistorRoom;
    }

    /**
     * A Transistor pair attribútumára vonatkozó getter függvény
     * @return A párosított tranzisztornak a párja
     */
    public Transistor getPair() {
        return pair;
    }

    /**
     * A Transistor pair attribútumára vonatkozó setter függvény
     * @param pair : A párosított tranzisztornak a párja
     */
    public void setPair(Transistor pair) {
        this.pair = pair;
    }

    @Override
    public void activate(Person person) {
        if(pair == null)
            return;
        if(pair.isActive()){
            person.enter(pair.placedTransistorRoom);
        }else{
            setActive(true);
            person.getCurrentRoom().addItem(this);
            person.removeItem(this);
            this.placedTransistorRoom = person.getCurrentRoom();
        }
    }

    @Override
    public void pair(Item item){
        item.pair(this);
    }

    public void pickUp(Person person) {
        this.setActive(false);

        if(this.getPlacedTransistorRoom() != null)
            this.setPlacedTransistorRoom(null);

        if(this.getPair() != null) {
            this.getPair().setPair(null);
            this.setPair(null);
        }
    }

    @Override
    public void pair(Transistor transistor){
        if(transistor.pair == null && this.pair == null && this != transistor){
            this.pair = transistor;
            transistor.pair = this;
        }
    }
    @Override
    public String toString(){
        if(pair == null)return super.toString();
        return super.toString()  + ", pairId=" + pair.getId();
    }
}
