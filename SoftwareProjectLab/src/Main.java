import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Skeleton skeleton = new Skeleton();
        boolean run = true;
        while (run) {
            System.out.println("Választható teszesetek");
            System.out.println("1. TVSZ Batskin használata");
            System.out.println("2. CannedCamembert használata");
            System.out.println("3. FFP2 Mask használata");
            System.out.println("4. Holy Beer Glass felvétele");
            System.out.println("5. Wet Wipe Cloth használata");
            System.out.println("6. Transistor párosítása");
            System.out.println("7. Transistor használata");
            System.out.println("8. Teleportálás");
            System.out.println("9. Sikertelen belépés egy szobába");
            System.out.println("10. Belépés egy gázos szobába");
            System.out.println("11. Sikertelen tárgy felvétel");
            System.out.println("12. Tárgy eldobása");
            System.out.println("13. Tanárral való találkozás tárgy nélkül.");
            System.out.println("14. SlideRule felvétele");
            System.out.println("15. Találkozás megbénult tanárral");
            System.out.println("16. Szobák egyesülése");
            System.out.println("17. Szobák szétválása");

            Scanner scan = new Scanner(System.in);
            String myLine = scan.nextLine();

            switch (myLine) {
                case "1":
                    skeleton.test1();
                    break;
                case "2":
                    skeleton.test2();
                    break;
                case "3":
                    skeleton.test3();
                    break;
                case "4":
                    skeleton.test4();
                    break;
                case "5":
                    skeleton.test5();
                    break;
                case "6":
                    skeleton.test6();
                    break;
                case "7":
                    skeleton.test7();
                    break;
                case "8":
                    skeleton.test8();
                    break;
                case "9":
                    skeleton.test9();
                    break;
                case "10":
                    skeleton.test10();
                    break;
                case "11":
                    skeleton.test11();
                    break;
                case "12":
                    skeleton.test12();
                    break;
                case "13":
                    skeleton.test13();
                    break;
                case "14":
                    skeleton.test14();
                    break;
                case "15":
                    skeleton.test15();
                    break;
                case "16":
                    skeleton.test16();
                    break;
                case "17":
                    skeleton.test17();
                    break;
                default:
                    run = false;
                    break;

            }

        }


    }
}