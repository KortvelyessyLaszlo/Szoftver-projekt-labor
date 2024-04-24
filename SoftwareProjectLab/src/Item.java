public abstract class Item implements ITickable {
    
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
        Skeleton.log("Item"+this.getId()+".setId(" + id + ")", true);
        Skeleton.log("return", false);
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
        Skeleton.log("Item"+this.getId()+".setActive(" + isActive + ")", true);
        Skeleton.log("return", false);
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
        Skeleton.log("Item"+this.getId()+".setDestroyed(" + isDestroyed + ")", true);
        Skeleton.log("return", false);
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
    public void activate(Person person){
        Skeleton.log("Item"+this.getId()+".activate(" + person.getName() + ")", true);
        Skeleton.log("return", false);
    }

    /**
     *  Ha egy személy felveszi ezt a tárgyat átadásra kerül,
        hogy ki vette fel. Ezt a függvényt valamennyi különböző tárgyat megvalósító osztály
        felüldefiniálja
     * @param person : A Person aki a tárgyat felvette
     */
    public void pickUp(Person person){
        Skeleton.log("Item"+this.getId()+".pickUp(" + person.getName() + ")", true);
        Skeleton.log("return", false);
    }

    /**
     *  A függvény igazzal tér vissza ha egy védőtárgy aktív
        állapotban van ezzel jelezve a védettséget. Alapértelmezetten hamis értékkel tér vissza
     * @return Az tárgy aktiválójának védettsége (alapértelmezésben hamis)
     */
    public boolean defend(){
        Skeleton.log("Item"+this.getId()+".defend()", true);
        Skeleton.log("return false", false);
        return false;
    }

    /**
     * A tárgyak párosítására szolgáló függvény
     * @param item : A párosítandó tárgy
     */
    public void pair(Item item){
        Skeleton.log("Item"+this.getId()+".pair(Item" + item.getId() + ")", true);
        Skeleton.log("return", false);
    }

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
        Skeleton.log("Item"+this.getId()+".defendAgainstGas()", true);
        Skeleton.log("return false", false);
        return false;
    }

    /**
     *  Ez a függvény igazzal tér vissza ha egy tárgy képes
        megbénítani oktatókat. Alapértelmezetten hamis értékkel tér vissza.
     * @return A tárgy képes-e megbénítani az oktatókat
     */
    public boolean stun(){
        Skeleton.log("Item"+this.getId()+".stun()", true);
        Skeleton.log("return false", false);
        return false;
    }
    
    @Override
    public String toString(){
        return this.getClass() + ", id=" + id + ", isActive=" + isActive + ", isDestroyed=" + isDestroyed;
    }

    @Override
    public void tick() {
        Skeleton.log("Item"+this.getId()+".tick()", true);
        Skeleton.log("return", false);
    }
}
