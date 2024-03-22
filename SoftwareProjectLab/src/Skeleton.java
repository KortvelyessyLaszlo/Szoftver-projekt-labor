public class Skeleton {

    private static int indentationLevel = 0;

    private static final String INDENTATION = "\t";

    public static void log(String methodName, boolean call) {
        if (call) {
            System.out.println(getIndentation() + "-> " + methodName); // Indentation for method call
            indentationLevel++;
        } else {
            indentationLevel--;
            System.out.println(getIndentation() + "<- " + methodName); // Indentation for method return
        }
    }

    private static String getIndentation() {
        return INDENTATION.repeat(Math.max(0, indentationLevel));
    }

    public void test1(){
        Room R1 = new Room(2);
        Room R2 = new Room(2);
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

        S.enter(R1);
    }

    public void test2(){
        Room R = new Room(1);
        Student S = new Student("S");
        CannedCamembert CC = new CannedCamembert(0);

        R.addPerson(S);
        S.setCurrentRoom(R);
        S.addItem(CC);

        S.useItem(CC);
    }

    public void test3(){
        Room R1 = new Room(2);
        Room R2 = new Room(2);
        Student S = new Student("S");
        FFP2Mask M = new FFP2Mask(0);

        R1.addNeighbour(R2);
        R1.setGassed(true);
        R2.addNeighbour(R1);
        R2.addPerson(S);
        S.setCurrentRoom(R2);
        S.addItem(M);
        M.activate(S);

        S.enter(R1);
    }

    public void test4(){
        Room R = new Room(1);
        Student S = new Student("S");
        HolyBeerGlass HBG = new HolyBeerGlass(0);

        R.addPerson(S);
        S.setCurrentRoom(R);
        R.addItem(HBG);

        S.pickUp(HBG);
    }

    public void test5(){
        Room R1 = new Room(2);
        Room R2 = new Room(2);
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

        S.enter(R1);
    }

    public void test6(){
        Student S = new Student("S");
        Transistor T1 = new Transistor(0);
        Transistor T2 = new Transistor(1);

        S.addItem(T1);
        S.addItem(T2);

        S.pairItems();
    }

    public void test7(){
        Room R = new Room(1);
        Student S = new Student("S");
        Transistor T1 = new Transistor(0);
        Transistor T2 = new Transistor(1);

        R.addPerson(S);
        S.setCurrentRoom(R);
        S.addItem(T1);
        S.addItem(T2);
        T1.pair(T2);
        T2.pair(T1);

        S.useItem(T1);
    }

    public void test8(){
        Room R1 = new Room(2);
        Room R2 = new Room(2);
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

        S.useItem(T2);
    }

    public void test9(){
        Room R1 = new Room(1);
        Room R2 = new Room(0);
        Student S = new Student("S");

        R1.addNeighbour(R2);
        R1.addPerson(S);
        R2.addNeighbour(R1);
        S.setCurrentRoom(R1);

        S.enter(R2);
    }

    public void test10(){
        Room R1 = new Room(1);
        Room R2 = new Room(1);
        Student S = new Student("S");
        TVSZBatSkin TVSZ = new TVSZBatSkin(0);

        R1.addNeighbour(R2);
        R1.setGassed(true);
        R2.addNeighbour(R1);
        R2.addPerson(S);
        S.setCurrentRoom(R1);
        S.addItem(TVSZ);

        S.enter(R2);
    }

    public void test11(){
        Room R = new Room(1);
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

        S.pickUp(HBG);
    }

    public void test12(){
        Room R = new Room(1);
        Student S = new Student("S");
        TVSZBatSkin TVSZ = new TVSZBatSkin(0);

        R.addPerson(S);
        S.setCurrentRoom(R);
        S.addItem(TVSZ);
        TVSZ.setActive(true);

        S.dropItem(TVSZ);
    }

    public void test13(){
        Person T = new Teacher("Teacher");
        Person S = new Student("Student");
        Room R1 = new Room(2);
        Room R2 = new Room(2);

        T.setCurrentRoom(R1);
        S.setCurrentRoom(R2);

        R1.addNeighbour(R2);
        R2.addNeighbour(R1);

        R1.addPerson(T);
        R2.addPerson(S);

        S.enter(R1);
    }

    public void test14(){
        Room R = new Room(1);
        Person S = new Student("Student");
        Item SR = new SlideRule(0);

        R.addItem(SR);
        R.addPerson(S);
        S.setCurrentRoom(R);

        S.pickUp(SR);
    }

    public void test15(){
        Room R1 = new Room(3);
        Room R2 = new Room(3);

        Person T = new Teacher("Teacher");
        Person S1 = new Student("Student1");
        Person S2 = new Student("Student2");

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

        S1.enter(R1);
        S2.enter(R1);
    }

    public void test16(){
        Room R1 = new Room(1);
        Room R2 = new Room(2);
        Room R3 = new Room(0);

        R1.addNeighbour(R2);
        R2.addNeighbour(R1);
        R2.addNeighbour(R3);
        R2.setGassed(true);
        R3.addNeighbour(R2);

        R1.combineRooms();
    }

    public void test17(){
        Maze M = new Maze(0);
        Room R = new Room(0);
        R.setGassed(true);
        M.addRoom(R);

        M.startSplitRooms();
    }
}