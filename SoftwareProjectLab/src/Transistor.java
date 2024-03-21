public class Transistor extends Item{

    private Room placedTransistorRoom;

    private Transistor pair;

    public Transistor(int id) {
        super(id);
    }

    // Getters and setters
    public Room getPlacedTransistorRoom() {
        return placedTransistorRoom;
    }

    public void setPlacedTransistorRoom(Room placedTransistorRoom) {
        this.placedTransistorRoom = placedTransistorRoom;
    }

    public Transistor getPair() {
        return pair;
    }

    public void setPair(Transistor pair) {
        this.pair = pair;
    }

    @Override
    public void activate(Person person) {
        // Activate the transistor
    }

    public void pair(Transistor transistor){
        this.pair = transistor;
        transistor.pair = this;
    }
}
