public class WetWipeCloth extends TimedItem{

        /**
         * A WetWipeCloth osztály konstruktora
         * @param id : A WetWipeCloth azonosítója
         */
        public WetWipeCloth(int id) {
            super(id);
        }

        /**
         * A WetWipeCloth aktiválása
         * @param person : Az adott személy
         */
        @Override
        public void activate(Person person) {
            setActive(true);
        }

        /**
         * A WetWipeCloth felvétele
         * @param person : Az adott személy
         */
        @Override
        public void pickUp(Person person) {
            activate(person);
        }

        /**
         * A WetWipeCloth védekezése
         * Ha a kendő aktív és nem megsemmisült, akkor a kendőt megsemmisíti
         * @return Igaz, ha a védekezés sikeres, egyébként hamis
         */
        @Override
        public boolean stun() {
            return (isActive() && !isDestroyed());
        }
}
