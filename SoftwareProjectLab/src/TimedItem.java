public abstract class TimedItem extends Item{
    private int remainingTime;

    public TimedItem(int id) {
        super(id);
    }

    // Getters and setters
    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }
}
