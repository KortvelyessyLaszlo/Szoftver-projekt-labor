public class WetWipeCloth extends TimedItem{

        public WetWipeCloth(int id) {
            super(id);
        }

        @Override
        public void activate(Person person) {
            Skeleton.log("WetWipeCloth" + this.getId() + ".activate()", true);
            setActive(true);
            Skeleton.log("return", false);
        }

        @Override
        public void pickUp(Person person) {
            Skeleton.log("WetWipeCloth" + this.getId() + ".pickUp()", true);
            activate(person);
            Skeleton.log("return", false);
        }

        @Override
        public boolean stun() {
            Skeleton.log("WetWipeCloth" + this.getId() + ".stun()", true);
            boolean result = (isActive() && !isDestroyed());
            Skeleton.log("return " + result, false);
            return result;
        }
}
