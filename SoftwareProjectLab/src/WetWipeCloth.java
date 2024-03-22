public class WetWipeCloth extends TimedItem{

        public WetWipeCloth(int id) {
            super(id);
        }

        @Override
        public void activate(Person person) {
            setActive(true);
        }

        @Override
        public void pickUp(Person person) {
            activate(person);
        }

        @Override
        public boolean stun() {
            return isActive() && !isDestroyed();
        }
}
