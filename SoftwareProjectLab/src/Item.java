import java.io.Serializable;

public abstract class Item implements ITickable, Serializable {
    
    /**
     * Az adott tárgy azonosítója
     */
    private int id;

    /**
     * Az adott tárgy aktivált állapota
     */
    private boolean isActive = false;

    /**
     * Az adott tárgy megsemmisülésére vonatkozó tulajdonság
     */
    private boolean isDestroyed = false;

    /**
     * Az Item osztály konstruktora
     * @param id : Az adott Item azonosítója
     */
    public Item(int id) {
        this.id = id;
    }

    /**
     * Az Item osztály "id" adattagjához tartozó getter függvény
     * @return Az adott Item "id" adattagja
     */
    public int getId() {
        return id;
    }

    /**
     * Az Item osztály "id" adattagjához tartozó setter függvény
     * @param id : Egy Item "id" adattagja
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Az Item osztály "isActive" adattagjához tartozó getter függvény
     * @return Az adott Item "isActive" adattagja
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Az Item osztály "isActive" adattagjához tartozó setter függvény
     * @param isActive : Egy Item "isActive" adattagja
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Az Item osztály "isDestroyed" adattagjához tartozó getter függvény
     * @return Az adott Item "isDestroyed" adattagja
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Az Item osztály "isDestroyed" adattagjához tartozó setter függvény
     * @param isDestroyed : Egy Item "isDestroyed" adattagja
     */
    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    /**
     *  Amikor egy személy aktiválja a tárgyat átadásra kerül,
        hogy ki aktiválta. A useItem(Item) függvényhívás hívja meg ezt a függvényt, majd ez a
        függvény aktíválja a tárgy viselkedését, ezt a függvényt a különböző tárgyakat
        megvalósító osztályok felüldefiniálják. A függvény a tárgy isActive logikai változóját
        igazra állítja.
     * @param person : A Person aki a tárgyat aktiválta
     */
    public void activate(Person person){}

    /**
     *  Ha egy személy felveszi ezt a tárgyat átadásra kerül,
        hogy ki vette fel. Ezt a függvényt valamennyi különböző tárgyat megvalósító osztály
        felüldefiniálja
     * @param person : A Person aki a tárgyat felvette
     */
    public void pickUp(Person person){}

    /**
     *  A függvény igazzal tér vissza ha egy védőtárgy aktív
        állapotban van ezzel jelezve a védettséget. Alapértelmezetten hamis értékkel tér vissza
     * @return Az tárgy aktiválójának védettsége (alapértelmezésben hamis)
     */
    public boolean defend(){
        return false;
    }

    /**
     * A tárgyak párosítására szolgáló függvény
     * @param item : A párosítandó tárgy
     */
    public void pair(Item item){}

    /**
     * A tranzisztorok párosítására szolgáló függvény
     * @param transistor : A párosítandó tranzisztor
     */
    public void pair(Transistor transistor){}

    /**
     *  Ez a függvény igazzal tér vissza ha egy tárgy
        képes megvédeni a mérgező gázzal szemben illetve aktív állapotban van ezzel jelezve a
        gázzal szembeni védettséget. Alapértelmezetten hamis értékkel tér vissza.
     * @return A tárgy védelmet ad-e a mérgező gázzal szemben
     */
    public boolean defendAgainstGas(){
        return false;
    }

    /**
     *  Ez a függvény igazzal tér vissza ha egy tárgy képes
        megbénítani oktatókat. Alapértelmezetten hamis értékkel tér vissza.
     * @return A tárgy képes-e megbénítani az oktatókat
     */
    public boolean stun(){
        return false;
    }


    @Override
    public String toString(){
        return "<html>" + this.getClass().toString().split(" ")[1] + "<br>isActive=" + isActive + "</html>";
    }

    public void tick() {}
}
