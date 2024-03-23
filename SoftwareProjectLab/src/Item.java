public abstract class Item {
    private int id;

    private boolean isActive;

    private boolean isDestroyed;

    public Item(int id) {
        this.id = id;
    }
     // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        Skeleton.log("Item"+this.getId()+".setId(" + id + ")", true);
        Skeleton.log("return", false);
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        Skeleton.log("Item"+this.getId()+".setActive(" + isActive + ")", true);
        Skeleton.log("return", false);
        this.isActive = isActive;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean isDestroyed) {
        Skeleton.log("Item"+this.getId()+".setDestroyed(" + isDestroyed + ")", true);
        Skeleton.log("return", false);
        this.isDestroyed = isDestroyed;
    }

    public void activate(Person person){
        Skeleton.log("Item"+this.getId()+".activate(" + person.getName() + ")", true);
        Skeleton.log("return", false);
    }

    public void pickUp(Person person){
        Skeleton.log("Item"+this.getId()+".pickUp(" + person.getName() + ")", true);
        Skeleton.log("return", false);
    }

    public boolean defend(){
        Skeleton.log("Item"+this.getId()+".defend()", true);
        Skeleton.log("return false", false);
        return false;
    }

    public void pair(Item item){
        Skeleton.log("Item"+this.getId()+".pair(Item" + item.getId() + ")", true);
        Skeleton.log("return", false);
    }

    public void pair(Transistor transistor){}

    public boolean defendAgainstGas(){
        Skeleton.log("Item"+this.getId()+".defendAgainstGas()", true);
        Skeleton.log("return false", false);
        return false;
    }

    public boolean stun(){
        Skeleton.log("Item"+this.getId()+".stun()", true);
        Skeleton.log("return false", false);
        return false;
    }
    @Override
    public String toString(){
        return this.getClass() + ", id=" + id + ", isActive=" + isActive + ", isDestroyed=" + isDestroyed;
    }
}
