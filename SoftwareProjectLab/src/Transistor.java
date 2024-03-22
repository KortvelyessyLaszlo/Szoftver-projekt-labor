public class Transistor extends Item{

    private Room placedTransistorRoom;

    private Transistor pair = null;

    public Transistor(int id) {
        super(id);
    }

    // Getters and setters
    public Room getPlacedTransistorRoom() {
        Skeleton.log("Transistor.getPlacedTransistorRoom()", true);
        Skeleton.log("return", false);
        return placedTransistorRoom;
    }

    public void setPlacedTransistorRoom(Room placedTransistorRoom) {
        Skeleton.log("Transistor.setPlacedTransistorRoom()", true);
        this.placedTransistorRoom = placedTransistorRoom;
        Skeleton.log("return", false);
    }

    public Transistor getPair() {
        Skeleton.log("Transistor.getPair()", true);
        Skeleton.log("return", false);
        return pair;
    }

    public void setPair(Transistor pair) {
        Skeleton.log("Transistor.setPair()", true);
        this.pair = pair;
        Skeleton.log("return", false);
    }

    @Override
    public void activate(Person person) {
        Skeleton.log("Transistor.activate()", true);
        if(pair.isActive()){
            person.enter(pair.placedTransistorRoom);
        }else{
            setActive(true);
            person.getCurrentRoom().addItem(this);
            person.removeItem(this);
        }
        Skeleton.log("return", false);
    }

    @Override
    public void pair(Item item){
        Skeleton.log("Transistor.pair()", true);
        item.pair(this);
        Skeleton.log("return", false);
    }

    @Override
    public void pair(Transistor transistor){
        Skeleton.log("Transistor.pair()", true);
        if(transistor.pair == null && this.pair == null && this != transistor){
            this.pair = transistor;
            transistor.pair = this;
        }
        Skeleton.log("return", false);
    }
}
