import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    /**
     * A játék futtatásáért felelős main függvény
     * @param args : Esetleges argumentumlista
     */
    public static void main(String[] args) {
        /*Skeleton skeleton = new Skeleton();
        boolean run = true;
        while (run) {
            System.out.println();
            System.out.println();
            System.out.println("Valaszthato tesztesetek");
            System.out.println("1. TVSZ Batskin hasznalata");
            System.out.println("2. CannedCamembert hasznalata");
            System.out.println("3. FFP2 Mask hasznalata");
            System.out.println("4. Holy Beer Glass felvetele");
            System.out.println("5. Wet Wipe Cloth hasznalata");
            System.out.println("6. Transistor parositasa");
            System.out.println("7. Transistor hasznalata");
            System.out.println("8. Teleportalas");
            System.out.println("9. Sikertelen belepes egy szobaba");
            System.out.println("10. Belepes egy gazos szobaba");
            System.out.println("11. Sikertelen targy felvetel");
            System.out.println("12. Targy eldobasa");
            System.out.println("13. Tanarral valo talalkozas targy nelkul.");
            System.out.println("14. SlideRule felvetele");
            System.out.println("15. Talalkozas megbenult tanarral");
            System.out.println("16. Szobak egyesulese");
            System.out.println("17. Szobak szetvalasa");
            System.out.println();
            System.out.println();

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
        }*/
        GameController gameController = new GameController();
        gameController.processStart();
    }
}