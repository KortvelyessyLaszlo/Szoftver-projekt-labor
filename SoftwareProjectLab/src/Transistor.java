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
        Skeleton.log("Transistor"+this.getId()+".setPlacedTransistorRoom(" + placedTransistorRoom.getId() + ")", true);
        this.placedTransistorRoom = placedTransistorRoom;
        Skeleton.log("return", false);
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
        Skeleton.log("Transistor"+this.getId()+".setPair(Transistor" + pair.getId() + ")", true);
        this.pair = pair;
        Skeleton.log("return", false);
    }

    @Override
    public void activate(Person person) {
        Skeleton.log("Transistor"+this.getId()+".activate(" + person.getName() + ")", true);
        if(pair == null)
            return;
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
