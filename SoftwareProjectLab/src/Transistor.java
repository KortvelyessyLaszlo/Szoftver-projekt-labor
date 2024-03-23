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
        Skeleton.log("Transistor"+this.getId()+".setPlacedTransistorRoom(" + placedTransistorRoom.getId() + ")", true);
        this.placedTransistorRoom = placedTransistorRoom;
        Skeleton.log("return", false);
    }

    public Transistor getPair() {
        return pair;
    }

    public void setPair(Transistor pair) {
        Skeleton.log("Transistor"+this.getId()+".setPair(Transistor" + pair.getId() + ")", true);
        this.pair = pair;
        Skeleton.log("return", false);
    }

    @Override
    public void activate(Person person) {
        Skeleton.log("Transistor"+this.getId()+".activate(" + person.getName() + ")", true);
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
        Skeleton.log("Transistor"+this.getId()+".pair(Item" + item.getId() + ")", true);
        item.pair(this);
        Skeleton.log("return", false);
    }

    @Override
    public void pair(Transistor transistor){
        Skeleton.log("Transistor"+this.getId()+".pair(Transistor" + transistor.getId() + ")", true);
        if(transistor.pair == null && this.pair == null && this != transistor){
            this.pair = transistor;
            transistor.pair = this;
        }
        Skeleton.log("return", false);
    }
    @Override
    public String toString(){
        if(pair == null)return super.toString();
        return super.toString()  + ", pairId=" + pair.getId();
    }
}
