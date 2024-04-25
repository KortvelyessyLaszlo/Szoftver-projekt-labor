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
                Skeleton.log("TVSZBatSkin" + this.getId() + ".activate(" + person.getName() + ")", true);
                if(isFake){
                        Skeleton.log("return", false);
                        return;
                }
                setActive(true);
                Skeleton.log("return", false);
        }

        @Override
        public void pickUp(Person person) {
                Skeleton.log("TVSZBatSkin" + this.getId() + ".pickUp(" + person.getName() + ")", true);
                activate(person);
                Skeleton.log("return", false);
        }

        @Override
        public boolean defend() {
                Skeleton.log("TVSZBatSkin" + this.getId() + ".defend()", true);
            if(this.isActive() && !this.isDestroyed()){
                    charge--;
                    if(charge == 0) setDestroyed(true);
                    Skeleton.log("return true", false);
                    return true;
            }
            Skeleton.log("return false", false);
            return false;
        }
        @Override
        public String toString(){
            return super.toString() + ", charge=" + charge + ", isFake=" + isFake;
        }
}
