import java.io.File;
import java.util.*;
import java.io.*;

public class GameController implements Serializable {

    /**
     * A játékosok számát tároló változó
     */
    private int playerCount;

    /**
     * A teszt mód beállításáért felelős változó
     */
    private boolean isTestMode;

    /**
     * A játék elindult-e
     */
    private boolean isGameStarted;

    /**
     * A játékban szereplő labirintust tároló változó
     */
    private Maze maze;

    /**
     * A játékban szereplő játékosokat tároló lista
     */
    private List<Student> players = new ArrayList<>();

    /**
     * A jelenlegi játékos
     */
    private Student currentPlayer;

    /**
     * A paramétrként kapott stringet szóközök mentén szétválasztja és egy listába gyűjti.
     * @param input : A bemeneti string
     * @return A bemeneti string szavainak listája
     */
    private List<String> parse(String input) {
        String[] parts = input.split(" ");
        return new ArrayList<>(Arrays.asList(parts));
    }

    /**
     * Ez a függvény felelős a játék objektumainak tickeléséért.
     * A játék objektumai közé tartoznak a játékosok, a tanárok, a szobák és az itemek.
     * A függvény végigmegy a játék objektumain és mindegyiknek meghívja a tick() függvényét.
     * A játékosok közül azokat, akik még élnek, egy listába gyűjti.
     * Ha egy játékos meghal, akkor azt a játékosok listájából törli.
     */
    private void tickAll(){
        List<Student> playersAlive = new ArrayList<>();

        maze.tick();
        List<Room> rooms = maze.getRooms();
        List<Person> people = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        for(Room room : rooms){
            people.addAll(room.getPeopleInRoom());
            items.addAll(room.getItemInventory());
            room.tick();
        }
        for(Person person : people){
            items.addAll(person.getItemInventory());
            person.tick();
        }
        for(Item item : items){
            item.tick();
        }
        for(Room room : rooms){
            for(Person person : room.getPeopleInRoom()){
                if(person instanceof Student)
                    playersAlive.add((Student) person);
            }
        }

        players.removeIf(player -> !playersAlive.contains(player));

        if(players.isEmpty()) {
            isGameStarted = false;
            System.out.println("The players lost!");
            processStart();
        }
    }

    /**
     * A játék indításáért felelős függvény
     * A függvény addig kér be parancsokat a felhasználótól, amíg a felhasználó nem adja meg a 'test' vagy 'user' parancsot.
     * Ha a felhasználó 'test' parancsot ad meg, akkor a teszt mód indul el.
     * Ha a felhasználó 'user' parancsot ad meg, akkor a játék mód indul el.
     */
    public void processStart(){
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while(!command.equals("test") && !command.equals("user")){
            System.out.println("Please enter 'test' or 'user'");
            command = scanner.nextLine();
            if(command.equals("test")){
                startTestMode();
            }
            else if(command.equals("user")){
                startGameMode();
            }
        }
    }

    /**
     * A teszt mód indításáért felelős függvény
     * A függvény addig kér be parancsokat a felhasználótól, amíg a felhasználó ki nem lép a teszt módból.
     * A függvény a felhasználó által megadott parancsokat értelmezi és végrehajtja.
     */
    private void startTestMode(){
        System.out.println();
        System.out.println();
        System.out.println("Available test cases:");
        System.out.println("1. Using TVSZ Batskin");
        System.out.println("2. Using CannedCamembert");
        System.out.println("3. Using FFP2 Mask");
        System.out.println("4. Using Air Freshener");
        System.out.println("5. Picking up Holy Beer Glass");
        System.out.println("6. Using Holy Beer Glass");
        System.out.println("7. Using Wet Wipe Cloth");
        System.out.println("8. Pairing Transistor");
        System.out.println("9. Using Transistor");
        System.out.println("10. Teleporting");
        System.out.println("11. Using Fake FFP2 Mask");
        System.out.println("12. Unsuccessful entry into a room");
        System.out.println("13. Entering a gassy room");
        System.out.println("14. Unsuccessful item pickup");
        System.out.println("15. Dropping an item");
        System.out.println("16. Meeting a teacher without an item");
        System.out.println("17. Picking up SlideRule");
        System.out.println("18. Meeting a stunned teacher");
        System.out.println("19. Combining rooms");
        System.out.println("20. Splitting rooms");
        System.out.println("21. Janitor entering a room");
        System.out.println();
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            List<String> parts = parse(command);
            if(parts.isEmpty()){
                System.out.println("Invalid command");
                return;
            }
            String commandName = parts.get(0);
            switch (commandName) {
                case "run" -> {
                    if (parts.size() != 2) {
                        System.out.println("Invalid command");
                        break;
                    }
                    try {
                        int testNumber = Integer.parseInt(parts.get(1));
                        if(testNumber > 0 && testNumber < 22)
                            runTest(testNumber);
                        else
                            System.out.println("Invalid command");
                    }catch (NumberFormatException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "help" -> {
                    System.out.println("Available commands: ");
                    System.out.println("run <testNumber> - Run the specified test");
                    System.out.println("exit - Exit test mode");
                }
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Invalid command");
            }
        }
    }

    /**
     * A tesztet lefuttató függvény
     * A függvény beolvassa a tesztesetet a megfelelő fájlból, majd végrehajtja a tesztesetet.
     * A teszteset végrehajtása után összehasonlítja a teszteset kimenetét az elvárt kimenettel.
     * Ha a kimenet megegyezik az elvárt kimenettel, akkor a teszt sikeres volt.
     * Ha a kimenet nem egyezik meg az elvárt kimenettel, akkor a teszt sikertelen volt.
     * Ha a teszt futtatása során kivétel keletkezik, akkor a teszt sikertelen volt.
     * A teszt eredményét kiírja a konzolra.
     * @param testNumber : A teszteset száma
     */
    private void runTest(int testNumber) {
        Maze testMaze = null;
        List<Room> testRooms = new ArrayList<>();
        List<Person> testPeople = new ArrayList<>();
        List<Item> testItems = new ArrayList<>();
        int nextRoomId = 0;
        int nextItemId = 0;
        try {
            File inputFile = new File("./resources/tests/input/" + testNumber + ".txt");
            Scanner inputScanner = new Scanner(inputFile);
            while (inputScanner.hasNextLine()) {
                String command = inputScanner.nextLine();
                List<String> parts = parse(command);
                if(parts.isEmpty()){
                    System.out.println("Invalid command");
                    return;
                }
                String commandName = parts.get(0);
                switch (commandName) {
                    case "createMaze" -> testMaze = new Maze();
                    case "createRoom" -> {
                        int capacity = Integer.parseInt(parts.get(1));
                        testRooms.add(new Room(capacity, nextRoomId++));
                    }
                    case "createTeacher" -> {
                        String name = parts.get(1);
                        testPeople.add(new Teacher(name));
                    }
                    case "createStudent" -> {
                        String name = parts.get(1);
                        testPeople.add(new Student(name));
                    }
                    case "createJanitor" -> {
                        String name = parts.get(1);
                        testPeople.add(new Janitor(name));
                    }
                    case "createItem" -> {
                        switch (parts.get(1)) {
                            case "AF" -> testItems.add(new AirFreshener(nextItemId++));
                            case "CC" -> testItems.add(new CannedCamembert(nextItemId++));
                            case "FFP2" -> {
                                boolean isFake = Boolean.parseBoolean(parts.get(2));
                                testItems.add(new FFP2Mask(nextItemId++, isFake));
                            }
                            case "HBG" -> testItems.add(new HolyBeerGlass(nextItemId++));
                            case "T" -> testItems.add(new Transistor(nextItemId++));
                            case "TVSZ" -> {
                                boolean isFake = Boolean.parseBoolean(parts.get(2));
                                testItems.add(new TVSZBatSkin(nextItemId++, isFake));
                            }
                            case "WWC" -> testItems.add(new WetWipeCloth(nextItemId++));
                            case "SR" -> {
                                boolean isFake = Boolean.parseBoolean(parts.get(2));
                                testItems.add(new SlideRule(nextItemId++, isFake));
                            }
                            default -> System.out.println("Unknown item type: " + parts.get(1));
                        }
                    }
                    case "addPerson" -> {
                        for (Room room : testRooms) {
                            if (room.getId() == Integer.parseInt(parts.get(1))) {
                                for (Person person : testPeople) {
                                    if (person.getName().equals(parts.get(2))) {
                                        room.addPerson(person);
                                        person.setCurrentRoom(room);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    case "addNeighbour" -> {
                        for (Room room1 : testRooms) {
                            if (room1.getId() == Integer.parseInt(parts.get(1))) {
                                for (Room room2 : testRooms) {
                                    if (room2.getId() == Integer.parseInt(parts.get(2))) {
                                        room1.addNeighbour(room2);
                                        room2.addNeighbour(room1);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    case "addItem" -> {
                        for (Item item : testItems) {
                            try {
                                if (item.getId() == Integer.parseInt(parts.get(2))) {
                                    for (Room room : testRooms) {
                                        if (room.getId() == Integer.parseInt(parts.get(1))) {
                                            room.addItem(item);
                                            break;
                                        }
                                    }
                                }
                            } catch (NumberFormatException ignored) {}
                        }
                        for (Item item : testItems) {
                            if (item.getId() == Integer.parseInt(parts.get(2))) {
                                for (Person person : testPeople) {
                                    if (person.getName().equals(parts.get(1))) {
                                        person.addItem(item);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    case "activate" -> {
                        for (Person person : testPeople) {
                            if (person.getName().equals(parts.get(2))) {
                                for (Item item : testItems) {
                                    if (item.getId() == Integer.parseInt(parts.get(1))) {
                                        item.activate(person);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    case "enter" -> {
                        for (Person person : testPeople) {
                            if (person.getName().equals(parts.get(1))) {
                                for (Room room : testRooms) {
                                    if (room.getId() == Integer.parseInt(parts.get(2))) {
                                        person.enter(room);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    case "useItem" -> {
                        for (Person person : testPeople) {
                            try {
                                if (person.getName().equals(parts.get(1))) {
                                    for (Item item : testItems) {
                                        if (item.getId() == Integer.parseInt(parts.get(2))) {
                                            ((Student) person).useItem(item);
                                            break;
                                        }
                                    }
                                }
                            } catch (ClassCastException ignored) {}
                        }
                    }
                    case "setGassed" -> {
                        for (Room room : testRooms) {
                            if (room.getId() == Integer.parseInt(parts.get(1))) {
                                room.setGassed(Boolean.parseBoolean(parts.get(2)));
                                break;
                            }
                        }
                    }
                    case "pickup" -> {
                        for (Person person : testPeople) {
                            if (person.getName().equals(parts.get(1))) {
                                for (Item item : testItems) {
                                    if (item.getId() == Integer.parseInt(parts.get(2))) {
                                        person.pickUp(item);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    case "pairItems" -> {
                        for (Person person : testPeople) {
                            try {
                                if (person.getName().equals(parts.get(1))) {
                                    ((Student) person).pairItems();
                                    break;
                                }
                            } catch (ClassCastException ignored) {}
                        }
                    }
                    case "pair" -> {
                        for (Item item : testItems) {
                            for (Item item2 : testItems) {
                                if (item.getId() == Integer.parseInt(parts.get(1)) && item2.getId() == Integer.parseInt(parts.get(2))) {
                                    item.pair(item2);
                                    break;
                                }
                            }
                        }
                    }
                    case "setActive" -> {
                        for (Item item : testItems) {
                            if (item.getId() == Integer.parseInt(parts.get(1))) {
                                item.setActive(Boolean.parseBoolean(parts.get(2)));
                                break;
                            }
                        }
                    }
                    case "setSticky" -> {
                        for (Room room : testRooms) {
                            if (room.getId() == Integer.parseInt(parts.get(1))) {
                                room.setSticky(Boolean.parseBoolean(parts.get(2)));
                                break;
                            }
                        }
                    }
                    case "combineRooms" -> {
                        for (Room room : testRooms) {
                            if (room.getId() == Integer.parseInt(parts.get(1))) {
                                room.combineRooms();
                                break;
                            }
                        }
                    }
                    case "split" -> {
                        if (testMaze != null) {
                            testMaze.startSplitRooms();
                        }
                    }
                    case "addRoom" -> {
                        for (Room room : testRooms) {
                            if (testMaze != null && room.getId() == Integer.parseInt(parts.get(1))) {
                                testMaze.addRoom(room);
                                break;
                            }
                        }
                    }
                    case "dropItem" -> {
                        for (Person person : testPeople) {
                            try {
                                if (person.getName().equals(parts.get(1))) {
                                    for (Item item : testItems) {
                                        if (item.getId() == Integer.parseInt(parts.get(2))) {
                                            ((Student) person).dropItem(item);
                                            break;
                                        }
                                    }
                                }
                            } catch (ClassCastException ignored) {}
                        }
                    }
                    case "setPlacedTransistorRoom" -> {
                        for (Item item : testItems) {
                            try {
                                if (item.getId() == Integer.parseInt(parts.get(1))) {
                                    for (Room room : testRooms) {
                                        if (room.getId() == Integer.parseInt(parts.get(2))) {
                                            ((Transistor) item).setPlacedTransistorRoom(room);
                                            break;
                                        }
                                    }
                                }
                            } catch (ClassCastException ignored) {}
                        }
                    }
                    default -> System.out.println("Unknown test command: " + command);
                }
            }

            StringBuilder testOutput = new StringBuilder();
            if (testMaze != null) {
                testOutput.append(testMaze).append("\n");
                for (Room room : testMaze.getRooms()) {
                    testOutput.append(room.toString()).append("\n");
                }
            }
            else for (Room room : testRooms) {
                testOutput.append(room.toString()).append("\n");
            }
            for (Person person : testPeople) {
                testOutput.append(person.toString()).append("\n");
            }
            for (Item item : testItems) {
                testOutput.append(item.toString()).append("\n");
            }

            System.out.println("Test output:");
            System.out.println(testOutput);
            System.out.println();
            System.out.println();

            File outputFile = new File("./resources/tests/output/" + testNumber + ".txt");
            Scanner outputScanner = new Scanner(outputFile);

            StringBuilder expectedOutput = new StringBuilder();
            while (outputScanner.hasNextLine()) {
                expectedOutput.append(outputScanner.nextLine()).append("\n");
            }

            System.out.println("Expected output:");
            System.out.println(expectedOutput);
            System.out.println();

            boolean testPassed = testOutput.toString().contentEquals(expectedOutput);
            System.out.println(testPassed ? "SUCCESS" : "FAIL");
        }
        catch (Exception e){
            System.out.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * A játék mód indításáért felelős függvény
     * A függvény addig kér be parancsokat a felhasználótól, amíg a játék véget nem ér.
     * A függvény a felhasználó által megadott parancsokat értelmezi és végrehajtja.
     * A függvény a játék működése során folyamatosan kiírja a játékosok statisztikáját.
     * Ha a játék véget ér, akkor a függvény kiírja a játék végeredményét.
     */
    private void startGameMode(){
        System.out.println("Available commands: ");
        System.out.println("start <playerCount> - Start the game");
        System.out.println("load - Load the last saved game");
        System.out.println("help - Display available commands");
        System.out.println("Commands only available after 'start' or 'load': ");
        System.out.println("save - Save the current game");
        System.out.println("enter <roomId> - Enter the specified room");
        System.out.println("pickup <itemId> - Pick up the specified item");
        System.out.println("drop <itemId> - Drop the specified item");
        System.out.println("pair - Pair the transistor in your inventory");
        System.out.println("use <itemId> - Use the specified item");

        int i = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            for(Student player : players) {
                if(player.isWinner()) {
                    player.setWinner(false);
                    isGameStarted = false;
                    processStart();
                    return;
                }
            }

            if(isGameStarted)
                showPlayerStat();

            if(!(isGameStarted && players.size() == 1 && currentPlayer.isPoisoned())) {
                String command = scanner.nextLine();
                processGameCommand(command);
            }

            if(isGameStarted)
                tickAll();

            if(isGameStarted){
                List<Student> notPoisonedPlayers = new ArrayList<>();
                for(Student player : players){
                    if(!player.isPoisoned()){
                        notPoisonedPlayers.add(player);
                    }
                }

                if(notPoisonedPlayers.isEmpty())
                    continue;

                currentPlayer = notPoisonedPlayers.get(i);
                if(i >= notPoisonedPlayers.size() - 1)
                    i = 0;
                else
                    i++;
            }

        }
    }

    /**
     * A játék parancsa alapján végrehajtja a megfelelő műveletet.
     * Ha a parancs nem értelmezhető, akkor a függvény kiírja, hogy az adott parancs érvénytelen.
     * Ha a parancs értelmezhető, akkor a függvény végrehajtja a parancsot.
     * @param command : A játék parancsa
     */
    private void processGameCommand(String command){
        List<String> parts = parse(command);
        if(parts.size() > 1 && !parts.get(1).matches("\\d+") || parts.size() > 2 || parts.isEmpty()){
            System.out.println("Invalid command");
            return;
        }
        String commandName = parts.get(0);
        switch (commandName) {
            case "start" -> {
                if (parts.size() != 2 || isGameStarted) {
                    System.out.println("Invalid command");
                    return;
                }
                isGameStarted = true;
                playerCount = Integer.parseInt(parts.get(1));
                initGame();
            }
            case "test" -> {
                if (parts.size() != 1 || isGameStarted) {
                    System.out.println("Invalid command");
                    return;
                }
                startTestMode();
            }
            case "load" -> {
                File f = new File("resources\\save.txt");
                if (isGameStarted || parts.size() != 1 || !f.exists()) {
                    System.out.println("Invalid command");
                    return;
                }
                isGameStarted = true;
                load();
            }
            case "help" -> {
                if(isGameStarted){
                    System.out.println("Available commands: ");
                    System.out.println("save - Save the current game");
                    System.out.println("enter <roomId> - Enter the specified room");
                    System.out.println("pickup <itemId> - Pick up the specified item");
                    System.out.println("drop <itemId> - Drop the specified item");
                    System.out.println("pair - Pair the transistor in your inventory");
                    System.out.println("use <itemId> - Use the specified item");
                }
                else{
                    System.out.println("Available commands: ");
                    System.out.println("start <playerCount> - Start the game");
                    System.out.println("load - Load the last saved game");
                    System.out.println("help - Display available commands");

                }
            }
            case "save" -> {
                if (!isGameStarted || parts.size() != 1) {
                    System.out.println("Invalid command");
                    return;
                }
                save();
            }
            case "enter" -> {
                if (!isGameStarted || parts.size() != 2) {
                    System.out.println("Invalid command");
                    return;
                }

                List<Room> rooms = currentPlayer.getCurrentRoom().getNeighbours();
                int roomId = Integer.parseInt(parts.get(1));
                for (Room room : rooms) {
                    if (room.getId() == roomId) {
                        currentPlayer.enter(room);
                        return;
                    }
                }
            }
            case "pickup" -> {
                if (!isGameStarted || parts.size() != 2) {
                    System.out.println("Invalid command");
                    return;
                }

                int itemId = Integer.parseInt(parts.get(1));
                List<Item> items = currentPlayer.getCurrentRoom().getItemInventory();
                for (Item item : items) {
                    if (item.getId() == itemId) {
                        currentPlayer.pickUp(item);
                        return;
                    }
                }
            }
            case "drop" -> {
                if (!isGameStarted || parts.size() != 2) {
                    System.out.println("Invalid command");
                    return;
                }

                int itemId = Integer.parseInt(parts.get(1));
                List<Item> items = currentPlayer.getItemInventory();
                for (Item item : items) {
                    if (item.getId() == itemId) {
                        currentPlayer.dropItem(item);
                        return;
                    }
                }
            }
            case "pair" -> {
                if (!isGameStarted || parts.size() != 1) {
                    System.out.println("Invalid command");
                    return;
                }
                currentPlayer.pairItems();
            }
            case "use" -> {
                if (!isGameStarted || parts.size() != 2) {
                    System.out.println("Invalid command");
                    return;
                }
                int itemId = Integer.parseInt(parts.get(1));
                List<Item> items = currentPlayer.getItemInventory();
                for (Item item : items) {
                    if (item.getId() == itemId) {
                        currentPlayer.useItem(item);
                        return;
                    }
                }
            }
            case "exit" -> System.exit(0);
            default -> System.out.println("Invalid command");
        }
    }

    /**
     * A soron következő játékos statisztikáját kiíró függvény
     * A függvény kiírja a játékos nevét, hogy mérgezett-e, a játékos jelenlegi szobáját és a játékos tárgyait.
     */
    private void showPlayerStat(){
        System.out.println("Player: " + currentPlayer.getName() + " isPoisoned= " + currentPlayer.isPoisoned());
        System.out.println("Room: " + currentPlayer.getCurrentRoom());
        System.out.println("Items: ");
        for(Item item : currentPlayer.getItemInventory()){
            System.out.println(item);
        }
    }

    /**
     * A játék inicializálásáért felelős függvény
     * A függvény inicializálja a játékot.
     * A függvény létrehozza a játékosokat, a szobákat és az itemeket.
     * A függvény beállítja a játék kezdeti állapotát.
     * A függvény beállítja a játék kezdeti játékosát.
     */
    private void initGame(){
        players.clear();
        Random random = new Random();
        this.maze = new Maze();
        for(int i = 0; i < playerCount * 5; i++){
            if(random.nextInt(10) > 0)
                maze.addRoom(new Room(random.nextInt(5) + 1, maze.getNextRoomId()));
            else{
                maze.addRoom(new CursedRoom(random.nextInt(5) + 1, maze.getNextRoomId()));
            }
        }

        maze.getRooms().get(0).addNeighbour(maze.getRooms().get(1));
        for(int i = 1; i < maze.getRooms().size() - 1; i++){
            maze.getRooms().get(i).addNeighbour(maze.getRooms().get(i - 1));
            maze.getRooms().get(i).addNeighbour(maze.getRooms().get(i + 1));
        }
        maze.getRooms().get(maze.getRooms().size() - 1).addNeighbour(maze.getRooms().get(maze.getRooms().size() - 2));

        for(int i = 0; i < maze.getRooms().size(); ++i) {
            int neighbourCount = random.nextInt(4);
            for(int j = 0; j < neighbourCount; ++j) {
                Room neighbour = maze.getRooms().get(random.nextInt(maze.getRooms().size()));
                if(maze.getRooms().get(i).getNeighbours().contains(neighbour)  || neighbour == maze.getRooms().get(i)){
                    continue;
                }
                maze.getRooms().get(i).addNeighbour(neighbour);
            }
        }

        int itemId = 0;
        for(int i = 0; i < playerCount; i++){
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new AirFreshener(itemId++));
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new CannedCamembert(itemId++));
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new FFP2Mask(itemId++, random.nextInt(4) == 0));
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new HolyBeerGlass(itemId++));
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new Transistor(itemId++));
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new Transistor(itemId++));
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new TVSZBatSkin(itemId++, random.nextInt(4) == 0));
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new WetWipeCloth(itemId++));
        }
        maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new SlideRule(itemId++, false));
        maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new SlideRule(itemId, true));

        for(int i = 0; i < playerCount; i++){
            Student student = new Student("Player" + i);
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addPerson(student);
            players.add(student);

            if(i == 0){
                currentPlayer = student;
            }
        }

        int teacherCountAndJanitorCount;
        if(playerCount % 2 == 1){
            teacherCountAndJanitorCount = playerCount / 2 + 1;
        }else {
            teacherCountAndJanitorCount = playerCount / 2;
        }
        for(int i = 0; i <  teacherCountAndJanitorCount; i++){
            Teacher teacher = new Teacher("Teacher" + i);
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addPerson(teacher);

            Janitor janitor = new Janitor("Janitor" + i);
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addPerson(janitor);
        }
    }

    /**
     * A játék állapotának mentéséért felelős függvény
     * A függvény elmenti a játék állapotát egy fájlba.
     * A függvény a játék állapotát a GameController objektum segítségével menti el.
     * A függvény a játék állapotát a resources/save.txt fájlba menti el.
     * Ha a mentés sikeres volt, akkor a függvény kiírja a konzolra, hogy a játék sikeresen el lett mentve.
     * Ha a mentés sikertelen volt, akkor a függvény kiírja a konzolra, hogy a játék mentése sikertelen volt.
     */
    private void save(){
        try {
            FileOutputStream file = new FileOutputStream("resources\\save.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
            System.out.println("Game Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A játék állapotának betöltéséért felelős függvény
     * A függvény betölti a játék állapotát egy fájlból.
     * A függvény a játék állapotát a GameController objektum segítségével tölti be.
     * A függvény a játék állapotát a resources/save.txt fájlból tölti be.
     * Ha a betöltés sikeres volt, akkor a függvény kiírja a konzolra, hogy a játék sikeresen betöltve lett.
     * Ha a betöltés sikertelen volt, akkor a függvény kiírja a konzolra, hogy a játék betöltése sikertelen volt.
     */
    private void load(){
        try {
            GameController temp;
            FileInputStream fileIn = new FileInputStream("resources\\save.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            temp = (GameController) in.readObject();
            this.playerCount = temp.playerCount;
            this.isTestMode = temp.isTestMode;
            this.isGameStarted = temp.isGameStarted;
            this.maze = temp.maze;
            this.players = temp.players;
            this.currentPlayer = temp.currentPlayer;
            in.close();
            fileIn.close();

            startGameMode();
        } catch (FileNotFoundException e) {
            System.out.println("No saved game found");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
