public class WetWipeCloth extends TimedItem{

        public WetWipeCloth(int id) {
            super(id);
        }

        @Override
        public void activate(Person person) {
            Skeleton.log("WetWipeCloth.activate()", true);
            setActive(true);
            Skeleton.log("return", false);
        }

        @Override
        public void pickUp(Person person) {
            Skeleton.log("WetWipeCloth.pickUp()", true);
            activate(person);
            Skeleton.log("return", false);
        }

        @Override
        public boolean stun() {
            Skeleton.log("WetWipeCloth.stun()", true);
            boolean result = (isActive() && !isDestroyed());
            Skeleton.log("return " + result, false);
            return result;
        }
}
