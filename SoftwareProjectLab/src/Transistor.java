public class Transistor extends Item{

    private Room placedTransistorRoom;

    private Transistor pair = null;

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
        if(pair.isActive()){
            person.enter(pair.getPlacedTransistorRoom());
        }else{
            setActive(true);
            person.getCurrentRoom().addItem(this);
            person.removeItem(this);
        }
    }

    public void pair(Transistor transistor){
        if(this.pair != null)
            return;

        if(transistor.pair == null){
            this.pair = transistor;
            transistor.pair(this);
        }
    }
}
