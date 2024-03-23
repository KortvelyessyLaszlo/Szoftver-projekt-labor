public class Skeleton {

    private static int indentationLevel = 0;

    private static final String INDENTATION = "\t";

    private static boolean logEnabled;

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

    private static String getIndentation() {
        return INDENTATION.repeat(Math.max(0, indentationLevel));
    }

    public void test1(){
        logEnabled = false;
        Room R1 = new Room(2,0);
        Room R2 = new Room(2,1);
        Teacher T = new Teacher("T");
        Student S = new Student("S");
        TVSZBatSkin TVSZ = new TVSZBatSkin(0);

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

    public void test3(){
        logEnabled = false;
        Room R1 = new Room(2,0);
        Room R2 = new Room(2,1);
        Student S = new Student("S");
        FFP2Mask M = new FFP2Mask(0);

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

    public void test10(){
        logEnabled = false;
        Room R1 = new Room(1,0);
        Room R2 = new Room(1,1);
        Student S = new Student("S");
        TVSZBatSkin TVSZ = new TVSZBatSkin(0);

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

    public void test12(){
        logEnabled = false;
        Room R = new Room(1,0);
        Student S = new Student("S");
        TVSZBatSkin TVSZ = new TVSZBatSkin(0);

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

    public void test14(){
        logEnabled = false;
        Room R = new Room(1,0);
        Person S = new Student("S");
        Item SR = new SlideRule(0);

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

    public void test17(){
        logEnabled = false;
        Maze M = new Maze(0);
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
