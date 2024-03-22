public abstract class Item {
    private int id;

    private boolean isActive;

    private boolean isDestroyed;

    public Item(int id) {
        this.id = id;
    }
     // Getters and setters
    public int getId() {
        Skeleton.log("Item.getId()", true);
        Skeleton.log("return " + id, false);
        return id;
    }

    public void setId(int id) {
        Skeleton.log("Item.setId()", true);
        Skeleton.log("return", false);
        this.id = id;
    }

    public boolean isActive() {
        Skeleton.log("Item.isActive()", true);
        Skeleton.log("return " + isActive, false);
        return isActive;
    }

    public void setActive(boolean isActive) {
        Skeleton.log("Item.setActive()", true);
        Skeleton.log("return", false);
        this.isActive = isActive;
    }

    public boolean isDestroyed() {
        Skeleton.log("Item.isDestroyed()", true);
        Skeleton.log("return " + isDestroyed, false);
        return isDestroyed;
    }

    public void setDestroyed(boolean isDestroyed) {
        Skeleton.log("Item.setDestroyed()", true);
        Skeleton.log("return", false);
        this.isDestroyed = isDestroyed;
    }

    public void activate(Person person){
        Skeleton.log("Item.activate()", true);
        Skeleton.log("return", false);
    }

    public void pickUp(Person person){
        Skeleton.log("Item.pickUp()", true);
        Skeleton.log("return", false);
    }

    public boolean defend(){
        Skeleton.log("Item.defend()", true);
        Skeleton.log("return false", false);
        return false;
    }

    public void pair(Item item){
        Skeleton.log("Item.pair()", true);
        Skeleton.log("return", false);
    }

    public void pair(Transistor transistor){
        Skeleton.log("Item.pair()", true);
        Skeleton.log("return", false);
    }

    public boolean defendAgainstGas(){
        Skeleton.log("Item.defendAgainstGas()", true);
        Skeleton.log("return false", false);
        return false;
    }

    public boolean stun(){
        Skeleton.log("Item.stun()", true);
        Skeleton.log("return false", false);
        return false;
    }
}
