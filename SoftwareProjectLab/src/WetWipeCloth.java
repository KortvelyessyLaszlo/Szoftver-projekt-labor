public class WetWipeCloth extends TimedItem{

        public WetWipeCloth(int id) {
            super(id);
        }

        @Override
        public void activate(Person person) {
            // Activate the Wet Wipe Cloth
        }

        @Override
        public void pickUp(Person person) {
            // Pick up the Wet Wipe Cloth
        }

        @Override
        public boolean stun() {
            return true;
        }
}
