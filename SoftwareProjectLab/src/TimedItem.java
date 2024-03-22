public abstract class TimedItem extends Item{
    private int remainingTime;

    public TimedItem(int id) {
        super(id);
    }

    // Getters and setters
    public int getRemainingTime() {
        Skeleton.log("TimedItem.getRemainingTime()", true);
        Skeleton.log("return " + remainingTime, false);
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        Skeleton.log("TimedItem.setRemainingTime()", true);
        this.remainingTime = remainingTime;
        Skeleton.log("return", false);
    }
}
