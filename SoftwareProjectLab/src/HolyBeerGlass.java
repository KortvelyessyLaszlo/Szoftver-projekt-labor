public class HolyBeerGlass extends TimedItem{

    public HolyBeerGlass(int id) {
        super(id);
    }

    @Override
    public void activate(Person person) {
        Skeleton.log("HolyBeerGlass"+this.getId()+".activate(" + person.getName() + ")", true);
        setActive(true);
        Skeleton.log("return", false);
    }

    @Override
    public void pickUp(Person person) {
        Skeleton.log("HolyBeerGlass"+this.getId()+".pickUp(" + person.getName() + ")", true);
        activate(person);
        Skeleton.log("return", false);
    }

    @Override
    public boolean defend() {
        Skeleton.log("HolyBeerGlass"+this.getId()+".defend()", true);
        boolean result = (isActive() && !isDestroyed());
        Skeleton.log("return " + result, false);
        return result;
    }
}
