public class Skeleton {

    /**
     * Tabulátorok száma
     */
    private static int indentationLevel = 0;

    /**
     * Behúzás
     */
    private static final String INDENTATION = "\t";

    /**
     * Logolás engedélyezésére szolgáló attribútum
     */
    private static boolean logEnabled;

    /**
     * A logolásért felelős függvény
     * @param methodName : A futtatandó metódus neve
     * @param call : Függvényhívas vagy visszatérésről van szó
     */
    public static void log(String methodName, boolean call) {
        if(logEnabled) {
            if (call) {
                System.out.println(getIndentation() + "-> " + methodName); // Indentation for method call
                indentationLevel++;
            } else {
                indentationLevel--;
                System.out.println(getIndentation() + "<- " + methodName); // Indentation for method return
            }
        }
    }

    /**
     * A sor behúzásáért felelős függvény
     * @return Annyi tabulátort egymás után fűzve, amennyi indentationLevel-be van írva
     */
    private static String getIndentation() {
        return INDENTATION.repeat(Math.max(0, indentationLevel));
    }

    /**
     * A felhasználó használja a nála lévő TVSZ Batskin-t.
     * <pre>
     *  1. A karakter rendelkezik TVSZ Batskin tárgyal. 
     *2. Találkozik egy tanárral.
     *3. A tárgy megvédi őt és csökkenti a töltései számát.
     * </pre>
     */
    public void test1(){
        logEnabled = false;
        Room R1 = new Room(2,0);
        Room R2 = new Room(2,1);
        Teacher T = new Teacher("T");
        Student S = new Student("S");;
        TVSZBatSkin TVSZ = new TVSZBatSkin(0, false);

        R1.addNeighbour(R2);
        R1.addPerson(T);
        R2.addNeighbour(R1);
        R2.addPerson(S);
        T.setCurrentRoom(R1);
        S.setCurrentRoom(R2);
        S.addItem(TVSZ);
        TVSZ.activate(S);

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(T);
        System.out.println(S);
        System.out.println(TVSZ);
        System.out.println();

        logEnabled = true;
        S.enter(R1);
        logEnabled = false;

        System.out.println();
        System.out.println(R1);
        System.out.println(R2);
        System.out.println(T);
        System.out.println(S);
        System.out.println(TVSZ);
    }

    /**
     * A felhasználó használja a nála lévő Canned Camembert-et
     * <pre>
     *1. A karakter aktiválja a nála lévő Canned Camembert tárgyat
     *2. A szoba, amelyben a karakter tartózkodik gázzal teli lesz.
     *3. A Canned Camembert tárgy eltűnik a User inventory-jából
     * </pre>
     */
    public void test2(){
        logEnabled = false;
        Room R = new Room(1,0);
        Student S = new Student("S");
        CannedCamembert CC = new CannedCamembert(0);

        R.addPerson(S);
        S.setCurrentRoom(R);
        S.addItem(CC);

        System.out.println(R);
        System.out.println(S);
        System.out.println(CC);
        System.out.println();

        logEnabled = true;
        S.useItem(CC);
        logEnabled = false;

        System.out.println();
        System.out.println(R);
        System.out.println(S);
        System.out.println(CC);
    }

    /**
     * A felhasználó használja a nála lévő FFP2 Mask-ot
     * <pre>
1. A karakter rendelkezik egy aktív FFP2 Mask tárgyal
2. A karakter belép egy gázzal teli szobába.
3. A gáz hatástalan lesz a karakter ellen.
     * </pre>
     */
    public void test3(){
        logEnabled = false;
        Room R1 = new Room(2,0);
        Room R2 = new Room(2,1);
        Student S = new Student("S");
        FFP2Mask M = new FFP2Mask(0, false);

        R1.addNeighbour(R2);
        R1.setGassed(true);
        R2.addNeighbour(R1);
        R2.addPerson(S);
        S.setCurrentRoom(R2);
        S.addItem(M);
        M.activate(S);

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S);
        System.out.println(M);
        System.out.println();

        logEnabled = true;
        S.enter(R1);
        logEnabled = false;

        System.out.println();
        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S);
        System.out.println(M);
    }

    /**
     * A felhasználó használja a nála lévő Holy Beer Glass-t.
     * <pre>
1. A karakter aktiválja felvételkor a Holy Beer Glass-t.
2. A Holy Beer Glass hátralévő ideje beállítódik.
     * </pre>
     */
    public void test4(){
        logEnabled = false;
        Room R = new Room(1,0);
        Student S = new Student("S");
        HolyBeerGlass HBG = new HolyBeerGlass(0);

        R.addPerson(S);
        S.setCurrentRoom(R);
        R.addItem(HBG);

        System.out.println(R);
        System.out.println(S);
        System.out.println(HBG);
        System.out.println();

        logEnabled = true;
        S.pickUp(HBG);
        logEnabled = false;

        System.out.println();
        System.out.println(R);
        System.out.println(S);
        System.out.println(HBG);
    }

    /**
     * A felhasználó használja a nála lévő Wet Wipe Cloth-ot.
     * <pre>
1. A karakter rendelkezik egy aktív wet wipe cloth tárgyal.
2. A karakter egy olyan szobába lép be, amelyben található tanár.
3. Találkozik a szobában lévő tanárral
4. A tárgy megbénítja az oktatót, aki ezért nem támad rá az hallgatóra
     * </pre>
     */
    public void test5(){
        logEnabled = false;
        Room R1 = new Room(2,0);
        Room R2 = new Room(2,1);
        Teacher T = new Teacher("T");
        Student S = new Student("S");
        WetWipeCloth WWC = new WetWipeCloth(0);

        R1.addNeighbour(R2);
        R1.addPerson(T);
        R2.addNeighbour(R1);
        R2.addPerson(S);
        T.setCurrentRoom(R1);
        S.setCurrentRoom(R2);
        S.addItem(WWC);
        WWC.activate(S);

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(T);
        System.out.println(S);
        System.out.println(WWC);
        System.out.println();

        logEnabled = true;
        S.enter(R1);
        logEnabled = false;

        System.out.println();
        System.out.println(R1);
        System.out.println(R2);
        System.out.println(T);
        System.out.println(S);
        System.out.println(WWC);
    }

    /**
     * A felhasználó párosít két nála található tranzisztort.
     * <pre>
1. A karakternél található két tranzisztor-tárgy.
2. A tranzisztorokat ezután párosítja egymással.
3. A két tranzisztor ezután párosítva lesz egymással.
     * </pre>
     */
    public void test6(){
        logEnabled = false;
        Student S = new Student("S");
        Transistor T1 = new Transistor(0);
        Transistor T2 = new Transistor(1);

        S.addItem(T1);
        S.addItem(T2);

        System.out.println(S);
        System.out.println(T1);
        System.out.println(T2);
        System.out.println();

        logEnabled = true;
        S.pairItems();
        logEnabled = false;

        System.out.println();
        System.out.println(S);
        System.out.println(T1);
        System.out.println(T2);
    }

    /**
     * A felhasználó aktiválja a nála lévő tranzisztort, ha annak már van párja.
     * <pre>
1. A felhasználó aktiválja a nála lévő tranzisztort.
2. Mivel a tranzisztor párja még nem aktív, akkor az aktivált tranzisztor a szoba inventory-jába kerül.
     * </pre>
     */
    public void test7(){
        logEnabled = false;
        Room R = new Room(1,0);
        Student S = new Student("S");
        Transistor T1 = new Transistor(0);
        Transistor T2 = new Transistor(1);

        R.addPerson(S);
        S.setCurrentRoom(R);
        S.addItem(T1);
        S.addItem(T2);
        T1.pair(T2);
        T2.pair(T1);

        System.out.println(R);
        System.out.println(S);
        System.out.println(T1);
        System.out.println(T2);
        System.out.println();

        logEnabled = true;
        S.useItem(T1);
        logEnabled = false;

        System.out.println();
        System.out.println(R);
        System.out.println(S);
        System.out.println(T1);
        System.out.println(T2);
    }

    /**
     * A felhasználó aktiválja a nála lévő tranzisztort, és a vele párosított tranzisztorhoz teleportál.
     * <pre>
1. A karakternél található tranzisztort aktiváljuk.
2. Mivel a tranzisztor párja aktív, a karakter a tranzisztorral párosított másik tranzisztor szobájába kerül.
     * </pre>
     */
    public void test8(){
        logEnabled = false;
        Room R1 = new Room(2,0);
        Room R2 = new Room(2,1);
        Student S = new Student("S");
        Transistor T1 = new Transistor(0);
        Transistor T2 = new Transistor(1);

        R1.addPerson(S);
        R2.addItem(T1);
        S.setCurrentRoom(R1);
        S.addItem(T2);
        T1.pair(T2);
        T1.setActive(true);
        T1.setPlacedTransistorRoom(R2);
        T2.pair(T1);

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S);
        System.out.println(T1);
        System.out.println(T2);
        System.out.println();

        logEnabled = true;
        S.useItem(T2);
        logEnabled = false;

        System.out.println();
        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S);
        System.out.println(T1);
        System.out.println(T2);
    }

    /**
     * A felhasználó megpróbál belépni egy megtelt szobába.
     * <pre>
1. A felhasználó megpróbál belépni egy másik szobába.
2. A szoba megtelt állapotban van ezért a felhasználó az eredeti szobában marad.

     * </pre>
     */
    public void test9(){
        logEnabled = false;
        Room R1 = new Room(1,0);
        Room R2 = new Room(0,1);
        Student S = new Student("S");

        R1.addNeighbour(R2);
        R1.addPerson(S);
        R2.addNeighbour(R1);
        S.setCurrentRoom(R1);

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S);
        System.out.println();

        logEnabled = true;
        S.enter(R2);
        logEnabled = false;

        System.out.println();
        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S);
    }

    /**
     * A felhasználónál található egy TVSZ Batskin. A gázos szobába való belépés után a felhasználó elkábul és kidobja a tárgyat.
     * <pre>
1. A felhasználó rendelkezik egy TVSZ Batskin tárgyal.
2. A felhasználó belép egy gázos szobába.
3. A szoba gázos ezért a felhasználó belépés után elkábul, és kidobja a tágyat.

     * </pre>
     */
    public void test10(){
        logEnabled = false;
        Room R1 = new Room(1,0);
        Room R2 = new Room(1,1);
        Student S = new Student("S");
        TVSZBatSkin TVSZ = new TVSZBatSkin(0, false);

        R1.addNeighbour(R2);
        R2.setGassed(true);
        R2.addNeighbour(R1);
        R1.addPerson(S);
        S.setCurrentRoom(R1);
        S.addItem(TVSZ);

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S);
        System.out.println(TVSZ);
        System.out.println();

        logEnabled = true;
        S.enter(R2);
        logEnabled = false;

        System.out.println();
        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S);
        System.out.println(TVSZ);
    }

    /**
     * A felhasználó megpróbál felvenni egy Holy Beer Glass tárgyat.
     * <pre>
1. A felhsználó megpróbál felvenni egy Holy Beer Glass tárgyat.
2. A felhasználónál már található 5 darab tárgy, így nem tudja felvenni a kiválasztott tárgyat.

     * </pre>
     */
    public void test11(){
        logEnabled = false;
        Room R = new Room(1,0);
        Student S = new Student("S");
        HolyBeerGlass HBG = new HolyBeerGlass(0);
        Transistor T1 = new Transistor(1);
        Transistor T2 = new Transistor(2);
        Transistor T3 = new Transistor(3);
        Transistor T4 = new Transistor(4);
        Transistor T5 = new Transistor(5);

        R.addPerson(S);
        R.addItem(HBG);
        S.setCurrentRoom(R);
        S.addItem(T1);
        S.addItem(T2);
        S.addItem(T3);
        S.addItem(T4);
        S.addItem(T5);

        System.out.println(R);
        System.out.println(S);
        System.out.println(HBG);
        System.out.println(T1);
        System.out.println(T2);
        System.out.println(T3);
        System.out.println(T4);
        System.out.println(T5);
        System.out.println();

        logEnabled = true;
        S.pickUp(HBG);
        logEnabled = false;

        System.out.println();
        System.out.println(R);
        System.out.println(S);
        System.out.println(HBG);
        System.out.println(T1);
        System.out.println(T2);
        System.out.println(T3);
        System.out.println(T4);
        System.out.println(T5);
    }

    /**
     * A felhasználó letesz egy aktív TVSZ Batskin tárgyat.
     * <pre>
1. A felhasználó letesz egy aktív TVSZ Batskin tárgyat.
2. A tárgy a szoba inventory-jába kerül.
3. A játékos inventory-ából törlődik a tárgy.

     * </pre>
     */
    public void test12(){
        logEnabled = false;
        Room R = new Room(1,0);
        Student S = new Student("S");
        TVSZBatSkin TVSZ = new TVSZBatSkin(0, false);

        R.addPerson(S);
        S.setCurrentRoom(R);
        S.addItem(TVSZ);
        TVSZ.setActive(true);

        System.out.println(R);
        System.out.println(S);
        System.out.println(TVSZ);
        System.out.println();

        logEnabled = true;
        S.dropItem(TVSZ);
        logEnabled = false;

        System.out.println();
        System.out.println(R);
        System.out.println(S);
        System.out.println(TVSZ);
    }

    /**
     * A felhasználó találkozik egy oktatóval, és nincsenek nála tárgyak.
     * <pre>
1.	A felhasználó találkozik egy tanárral, és nem rendelkezik semmilyen tárggyal.
2.	A tanár elveszi a hallgató lelkét.

     * </pre>
     */
    public void test13(){
        logEnabled = false;
        Person T = new Teacher("T");
        Person S = new Student("S");
        Room R1 = new Room(2,0);
        Room R2 = new Room(2,1);

        T.setCurrentRoom(R1);
        S.setCurrentRoom(R2);

        R1.addNeighbour(R2);
        R2.addNeighbour(R1);

        R1.addPerson(T);
        R2.addPerson(S);

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S);
        System.out.println(T);
        System.out.println();

        logEnabled = true;
        S.enter(R1);
        logEnabled = false;

        System.out.println();
        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S);
        System.out.println(T);
    }

    /**
     * A felhasználó felveszi a Logarlécet
     * <pre>
1. A felhasználó felveszi a Logarlécet
2. A játékot a hallgatók megnyerik.

     * </pre>
     */
    public void test14(){
        logEnabled = false;
        Room R = new Room(1,0);
        Person S = new Student("S");
        Item SR = new SlideRule(0, false);

        R.addItem(SR);
        R.addPerson(S);
        S.setCurrentRoom(R);

        System.out.println(R);
        System.out.println(S);
        System.out.println(SR);
        System.out.println();

        logEnabled = true;
        S.pickUp(SR);
        logEnabled = false;

        System.out.println();
        System.out.println(R);
        System.out.println(S);
        System.out.println(SR);
    }

    /**
     * Két felhasználó is találkozik ugyanazzal a tanárral, egymás után. Az első felhasználó rendelkezik nedves táblatörlő ronggyal, amivel elkábítja a tanárt. Ezután a második felhasználó is belép a szobába, akit nem támad meg a tanár mert meg van bénulva.
     * <pre>
1. Az első felhasználó találkozik egy tanárral. A felhasználó rendelkezik aktív nedves táblatörlő ronggyal, amivel megbénítja a tanárt.
2. A második hallgató is belép ugyanebbe a szobába és találkozik a tanárral.
3. Mivel tanár meg van bénítva ezért nem támadja meg a hallgatót.

     * </pre>
     */
    public void test15(){
        logEnabled = false;
        Room R1 = new Room(3,0);
        Room R2 = new Room(3,1);

        Person T = new Teacher("T");
        Person S1 = new Student("S1");
        Person S2 = new Student("S2");

        Item WWC = new WetWipeCloth(0);

        R1.addNeighbour(R2);
        R2.addNeighbour(R1);

        R1.addPerson(T);
        T.setCurrentRoom(R1);

        R2.addPerson(S1);
        R2.addPerson(S2);
        S1.setCurrentRoom(R2);
        S2.setCurrentRoom(R2);

        WWC.setActive(true);
        S1.addItem(WWC);

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S1);
        System.out.println(S2);
        System.out.println(T);
        System.out.println();

        logEnabled = true;
        S1.enter(R1);
        S2.enter(R1);
        logEnabled = false;

        System.out.println();
        System.out.println(R1);
        System.out.println(R2);
        System.out.println(S1);
        System.out.println(S2);
        System.out.println(T);
    }

    /**
     * Két szoba egyesülése
     * <pre>
1. A szoba kiválaszt a szomszédjai közül véletlenszerűen egyet, amelyben nem található játékos, gázos, illetve van egy olyan szomszédja, akivel ő nem szomszédos.
2. A szoba átveszi a kiválasztott szoba tulajdonságait és szomszédjait.
3. A szoba megszünteti a kiválasztott szoba szomszédsági kapcsolatait.

     * </pre>
     */
    public void test16(){
        logEnabled = false;
        Room R1 = new Room(1,0);
        Room R2 = new Room(2,1);
        Room R3 = new Room(0,2);

        R1.addNeighbour(R2);
        R2.addNeighbour(R1);
        R2.addNeighbour(R3);
        R2.setGassed(true);
        R3.addNeighbour(R2);


        System.out.println(R1);
        System.out.println(R2);
        System.out.println(R3);
        System.out.println();

        logEnabled = true;
        R1.combineRooms();
        logEnabled = false;

        System.out.println();
        System.out.println(R1);
        System.out.println(R2);
        System.out.println(R3);
    }

    /**
     * Két szoba szétválása
     * <pre>
1. A labirintus egy véletlenszerűen kiválasztott szobának megkezdi a szétválasztó viselkedését.
2. A kiválasztott szoba gázos, nincsenek szomszédjai és a kapacitása nulla.
3. A szoba létrehoz egy új szobát, amelynek tulajdonságai megegyeznek az övével.
4. A szoba elosztja a kapacitását, és szomszédjait kettejük között.
5. A labirintus eltárolja az új szobát. 

     * </pre>
     */
    public void test17(){
        logEnabled = false;
        Maze M = new Maze();
        Room R = new Room(2,0);
        R.setGassed(true);
        M.addRoom(R);

        System.out.println(M);
        for (Room room : M.getRooms()) {
            System.out.println(room);
        }
        System.out.println();

        logEnabled = true;
        M.startSplitRooms();
        logEnabled = false;

        System.out.println();
        System.out.println(M);
        for (Room room : M.getRooms()) {
            System.out.println(room);
        }
    }
}
