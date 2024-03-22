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

    public void test13(){}

    public void test14(){}

    public void test15(){}

    public void test16(){}

    public void test17(){}
}
