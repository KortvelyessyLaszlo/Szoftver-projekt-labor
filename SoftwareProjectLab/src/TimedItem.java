public abstract class TimedItem extends Item{
    /**
     * A megmaradó aktív idő 
     */
    private int remainingTime;

    /**
     * A TimedItem osztály konstruktora
     * @param id : A tárgy azonosítója
     */
    public TimedItem(int id) {
        super(id);
    }

    /**
     * A TimedItem remainingTime attribútumára vonatkozó getter függvény
     * @return A megmaradó aktív idő 
     */
    public int getRemainingTime() {
        return remainingTime;
    }

    /**
     * A TimedItem remainingTime attribútumára vonatkozó setter függvény
     * @param remainingTime : A megmaradó aktív idő 
     */
    public void setRemainingTime(int remainingTime) {
        Skeleton.log("TimedItem" + this.getId() + "setRemainingTime(" + remainingTime + ")", true);
        this.remainingTime = remainingTime;
        Skeleton.log("return", false);
    }
}
