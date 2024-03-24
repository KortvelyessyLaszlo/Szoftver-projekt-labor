public class TVSZBatSkin extends Item{
        /**
         * A visszamaradó megmentések száma
         */
        int charge;

        /**
         * A TVSZBatSkin osztály konstruktora
         * @param id : A TVSZBatSkin azonosítója
         */
        public TVSZBatSkin(int id) {
            super(id);
            charge = 5;
        }

        @Override
        public void activate(Person person) {
                Skeleton.log("TVSZBatSkin" + this.getId() + ".activate(" + person.getName() + ")", true);
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
            return super.toString() + ", charge=" + charge;
        }
}
