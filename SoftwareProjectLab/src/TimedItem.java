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
        Skeleton.log("TimedItem" + this.getId() + "setRemainingTime(" + remainingTime + ")", true);
        this.remainingTime = remainingTime;
        Skeleton.log("return", false);
    }
}
