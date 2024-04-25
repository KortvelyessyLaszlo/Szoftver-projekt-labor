public abstract class TimedItem extends Item {
    /**
     * A megmaradó aktív idő 
     */
    private int remainingTime = 10;

    /**
     * A TimedItem osztály konstruktora
     * @param id : A tárgy azonosítója
     */
    public TimedItem(int id) {
        super(id);
    }

    /**
     * A tárgy aktív idejének csökkentése
     * Ha a tárgy aktív és nem megsemmisült, akkor a megmaradó időt csökkenti eggyel
     * Ha a megmaradó idő 0, akkor a tárgy inaktiválódik
     */
    @Override
    public void tick(){
        if(isActive() && !isDestroyed())
            remainingTime--;
        if(remainingTime == 0){
            setDestroyed(true);
            setActive(false);
        }
    }
}
