public class TVSZBatSkin extends Item{
        int charge;
        public TVSZBatSkin(int id) {
            super(id);
            charge = 5;
        }

        @Override
        public void activate(Person person) {
            // Activate the TVSZ Bat Skin
                setActive(true);
        }

        @Override
        public void pickUp(Person person) {
            // Pick up the TVSZ Bat Skin
                activate(person);
        }

        @Override
        public boolean defend() {
            if(this.isActive() && !this.isDestroyed()){
                    charge--;
                    if(charge == 0) setDestroyed(true);
                    return true;
            }
            return false;
        }
}
