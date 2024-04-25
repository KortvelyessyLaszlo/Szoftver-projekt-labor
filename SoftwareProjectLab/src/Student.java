import java.util.List;

public class Student extends Person {

    /**
     * Az adott hallgató győztes-e
     */
    private boolean isWinner;

    /**
     * A hallgatő mérgezésének beállítása
     * @param value : A mérgezés állapota
     */
    @Override
    public void setPoisoned(boolean value) {
        super.setPoisoned(value);
        if(value)
            System.out.println("\u001B[31m" + this.getName() + " got poisoned \u001B[0m");
    }

    /**
     * A hallgató győztes állapotának getter függvénye
     * @return A hallgató győztes-e
     */
    public boolean isWinner() { return isWinner; }

    /**
     * A hallgató győztes állapotának setter függvénye
     * @param value : A hallgató győztes állapota
     */
    public void setWinner(boolean value) { isWinner = value; }

    /**
     * A Student osztály konstruktora
     * @param name : Az adott hallgató neve
     */
    public Student(String name) {
        super(name);
    }

    /**
     * A hallgató használ egy tárgyat. A paraméterben átadott
        tárgynak meghívja az activate() metódusát.
     * @param item : A használandó tárgy
     */
    public void useItem(Item item){
        item.activate(this);
    }

    /**
     * A hallgató eldob egy tárgyat. A paraméterben átadott tárgy
        törlődik a hallgató itemInventorty-ából, és bele kerül a currentRoom itemInventoryába.
        A tárgy deaktiválódik.
     * @param item : Az eldobandó tárgy
     */
    public void dropItem(Item item){
        item.setActive(false);
        getCurrentRoom().addItem(item);
        this.removeItem(item);
    }

    /**
     * A hallgató találkozik egy tanárral. Ha a tanár mérgezve
        van, vagy bénult, akkor a függvény visszatér. Ellenkező esetben a hallgató megnézi,
        hogy van-e nála bénító tárgy, ha igen, akkor a tanár bénul. Ha nincs, akkor megnézi,
        hogy van-e nála védő tárgy, ha igen, akkor a függvény visszatér. Ha nincs, akkor a
        diák eliminálódik.
     * @param teacher : A tanár, akivel a hallgató találkozott
     */
    @Override
    public void meet(Teacher teacher){
        if(teacher.isPoisoned() || teacher.isStunned())
            return;

        if(checkForStunItems()){
            teacher.setStunned(true);
            return;
        }

        if(checkForDefensiveItems())
            return;

        teacher.eliminate(this);
    }

    /**
     * A hallgató felvesz egy tárgyat. Ha a szoba ragacsos, akkor
        a függvény visszatér. Ha a hallgató tárgyai száma eléri az 5-öt, akkor a függvény
        visszatér. Ellenkező esetben a hallgató felveszi a tárgyat.
     */
    @Override
    public void pickUp(Item item){
        super.pickUp(item);

        if(this.getCurrentRoom().isSticky()) {
            System.out.println("\u001B[31m Pickup fail, the floor is sticky \u001B[0m");
            return;
        }
        if(this.getItemInventory().size() >= 5) {
            System.out.println("\u001B[31m Pickup fail, inventory is full \u001B[0m");
            return;
        }

        item.pickUp(this);
    }

    /**
     * Végig megyünk a hallgatónál található tárgyakon
        (itemInventory) mindegyiknek meghívjuk a defend() függvényét amely igazzal tér
        vissza ha egy védő tárgy aktív. Amennyiben az egyik tárgy defend() függvénye
        igazzal tér vissza, azaz van nála aktív védő tárgy a tanárok ellen, akkor a függvény
        igazzal tér vissza.
     * @return A hallgató rendelkezik-e olyan tárggyal, amely megvédi őt
     */
    public boolean checkForDefensiveItems(){
        for(Item item : getItemInventory()){
            if(item.defend())
                return true;
        }
        return false;
    }

    /**
     * A hallgatónál lévő tárgyakra páronként meghívja a pair()
        függvényt.
     */
    public void pairItems(){
        List<Item> inventory = getItemInventory();
        for(int i = 0; i < inventory.size() - 1; i++){
            for(int j = i + 1; j < inventory.size(); j++)
                inventory.get(i).pair(inventory.get(j));
        }
    }

    /**
     * Végig megyünk a hallgatónál található tárgyakon
        (itemInventory) mindegyiknek meghívjuk a stun() függvényét amely igazzal tér vissza
        ha egy bénító tárgy aktív. Amennyiben az egyik tárgy stun() függvénye igazzal tér
        vissza, azaz van nála aktív bénító tárgy a tanárok ellen, akkor a függvény igazzal tér
        vissza.
     * @return A hallgató rendelkezik-e olyan tárggyal, amely tud bénítani
     */
    public boolean checkForStunItems(){
        for(Item item : getItemInventory()){
            if(item.stun())
                return true;
        }
        return false;
    }
    
    @Override
    public void meet(Person p){
        p.meet(this);
    }

    /**
     * A hallgató tick() függvénye, amely a hallgató
        mérgezésének idejét csökkenti eggyel, ha a hallgató mérgezve van.
     */
    @Override
    public void tick(){
        if(isPoisoned()) {
            setPoisonDuration(getPoisonDuration() - 1);
            if(getPoisonDuration() == 0)
                setPoisoned(false);
        }

        this.getItemInventory().removeIf(item -> item instanceof TimedItem && item.isDestroyed());
    }
}
