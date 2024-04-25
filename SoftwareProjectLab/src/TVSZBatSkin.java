public class TVSZBatSkin extends Item{

        public boolean isFake() {
                return isFake;
        }

        public void setFake(boolean fake) {
                isFake = fake;
        }

        /**
         * A TVSZBatSkin osztály adattagja, ami jelzi, hogy a bőr hamis-e
         */
        private boolean isFake = false;

        /**
         * A visszamaradó megmentések száma
         */
        int charge;

        /**
         * A TVSZBatSkin osztály konstruktora
         * @param id : A TVSZBatSkin azonosítója
         */
        public TVSZBatSkin(int id, boolean isFake) {
                super(id);
                this.isFake = isFake;
                charge = 5;
        }

        @Override
        public void activate(Person person) {
                if(isFake)  return;
                setActive(true);
        }

        @Override
        public void pickUp(Person person) {
                activate(person);
        }
        @Override
        public boolean defend() {
                if(this.isActive() && !this.isDestroyed()){
                    charge--;
                    if(charge == 0) setDestroyed(true);{
                            return true;
                        }
                }
                return false;
        }
        @Override
        public String toString(){
            return super.toString() + ", charge=" + charge + ", isFake=" + isFake;
        }
}
