import java.util.List;

public class Student extends Person implements ITickable{

    private boolean isWinner;

    public boolean isWinner() { return isWinner; }

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
        Skeleton.log(this.getName() + ".useItem(Item" + item.getId() + ")", true);
        item.activate(this);
        Skeleton.log("return", false);
    }

    /**
     * A hallgató eldob egy tárgyat. A paraméterben átadott tárgy
        törlődik a hallgató itemInventorty-ából, és bele kerül a currentRoom itemInventoryába.
        A tárgy deaktiválódik.
     * @param item : Az eldobandó tárgy
     */
    public void dropItem(Item item){
        Skeleton.log(this.getName() + ".dropItem(Item" + item.getId() + ")", true);
        item.setActive(false);
        getCurrentRoom().addItem(item);
        this.removeItem(item);
        Skeleton.log("return", false);
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
        System.out.println("TEACHER MEET");
        Skeleton.log(this.getName() + ".meet(" + teacher.getName() + ")", true);
        if(teacher.isPoisoned() || teacher.isStunned()){
            Skeleton.log("return", false);
            return;
        }

        if(checkForStunItems()){
            teacher.setStunned(true);
            Skeleton.log("return", false);
            return;
        }

        if(checkForDefensiveItems()){
            Skeleton.log("return", false);
            return;
        }

        teacher.eliminate(this);
        Skeleton.log("return", false);
    }

    @Override
    public void pickUp(Item item){
        Skeleton.log(this.getName() + ".pickUp(Item" + item.getId() + ")", true);
        super.pickUp(item);
        if(getItemInventory().size() < 5)
            item.pickUp(this);
        Skeleton.log("return", false);
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
        Skeleton.log(this.getName() + ".checkForDefensiveItems()", true);
        for(Item item : getItemInventory()){
            if(item.defend()){
                Skeleton.log("return true", false);
                return true;
            }
        }
        Skeleton.log("return false", false);
        return false;
    }

    /**
     * A hallgatónál lévő tárgyakra páronként meghívja a pair()
        függvényt.
     */
    public void pairItems(){
        Skeleton.log(this.getName() + ".pairItems()", true);
        List<Item> inventory = getItemInventory();
        for(int i = 0; i < inventory.size() - 1; i++){
            for(int j = i + 1; j < inventory.size(); j++){
                inventory.get(i).pair(inventory.get(j));
            }
        }
        Skeleton.log("return", false);
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
        Skeleton.log(this.getName() + ".checkForStunItems()", true);
        for(Item item : getItemInventory()){
            if(item.stun()){
                Skeleton.log("return true", false);
                return true;
            }
        }
        Skeleton.log("return false", false);
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
        Skeleton.log(this.getName() + ".tick()", true);
        if(!isPoisoned()) {
            Skeleton.log("return", false);
            return;
        }
        setPoisonDuration(getPoisonDuration() - 1);
        if(getPoisonDuration() == 0)
            setPoisoned(false);
        Skeleton.log("return", false);
    }
}
