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
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    public void activate(Person person){}

    public void pickUp(Person person){}

    public boolean defend(){
        return false;
    }

    public void pair(Item item){}

    public boolean defendAgainstGas(){
        return false;
    }

    public boolean stun(){
        return false;
    }
}
