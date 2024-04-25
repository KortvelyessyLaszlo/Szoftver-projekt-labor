public class WetWipeCloth extends TimedItem{

        /**
         * A WetWipeCloth osztály konstruktora
         * @param id : A WetWipeCloth azonosítója
         */
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
            return (isActive() && !isDestroyed());
        }
}
