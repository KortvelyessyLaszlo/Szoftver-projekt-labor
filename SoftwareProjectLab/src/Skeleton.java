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

    public void test1(){}

    public void test2(){}

    public void test3(){}

    public void test4(){}

    public void test5(){}

    public void test6(){}

    public void test7(){}

    public void test8(){}

    public void test9(){}

    public void test10(){}

    public void test11(){}

    public void test12(){}

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
