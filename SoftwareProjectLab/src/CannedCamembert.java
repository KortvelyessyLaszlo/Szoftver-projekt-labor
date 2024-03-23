public class CannedCamembert extends Item {
    public CannedCamembert(int id) {
        super(id);
    }
    @Override
    public void activate(Person person) {
        Skeleton.log("CannedCamembert"+this.getId()+".activate()", true);
        person.getCurrentRoom().setGassed(true);
        this.setDestroyed(true);
        person.removeItem(this);
        Skeleton.log("return", false);
    }
}
