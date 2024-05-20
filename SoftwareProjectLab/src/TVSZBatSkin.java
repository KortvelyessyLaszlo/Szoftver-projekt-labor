public class TVSZBatSkin extends Item{

        /**
         * A TVSZBatSkin osztály adattagja, ami jelzi, hogy a bőr hamis-e
         */
        private final boolean isFake;

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

        /**
         * A TVSZBatSkin aktiválása
         * @param person : Az adott személy
         */
        @Override
        public void activate(Person person) {
                if(isFake)  return;
                setActive(true);
        }

        /**
         * A TVSZBatSkin felvétele
         * @param person : Az adott személy
         */
        @Override
        public void pickUp(Person person) {
                activate(person);
        }

        /**
         * A TVSZBatSkin védekezése
         * Ha a bőr aktív és nem megsemmisült, akkor a megmentések számát csökkenti eggyel
         * Ha a megmentések száma 0, akkor a bőr megsemmisül
         * @return Igaz, ha a védekezés sikeres, egyébként hamis
         */
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
                return "<html>" + this.getClass().toString().split(" ")[1] + "<br>isActive=" + isActive() +  "<br>isFake=" + isFake +  "<br>charges=" + charge + "</html>";
        }
}
