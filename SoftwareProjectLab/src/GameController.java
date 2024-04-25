import java.io.File;
import java.util.*;

public class GameController {

    private int playerCount;

    private boolean isTestMode;

    private boolean isGameStarted;

    private Maze maze;

    private List<Student> players = new ArrayList<Student>();

    private Student currentPlayer;

    /**
     * A paramétrként kapott stringet szóközök mentén szétválasztja és egy listába gyűjti.
     * @param input : A bemeneti string
     * @return A bemeneti string szavainak listája
     */
    private List<String> parse(String input) {
        String[] parts = input.split(" ");
        return new ArrayList<String>(Arrays.asList(parts));
    }

    /**
     * Ez a függvény felelős a játék objektumainak tickeléséért.
     * A játék objektumai közé tartoznak a játékosok, a tanárok, a szobák és az itemek.
     * A függvény végigmegy a játék objektumain és mindegyiknek meghívja a tick() függvényét.
     * A játékosok közül azokat, akik még élnek, egy listába gyűjti.
     * Ha egy játékos meghal, akkor azt a játékosok listájából törli.
     */
    private void tickAll(){
        List<Student> playersAlive = new ArrayList<Student>();

        maze.tick();
        List<Room> rooms = maze.getRooms();
        List<Person> people = new ArrayList<Person>();
        List<Item> items = new ArrayList<Item>();
        for(Room room : rooms){
            people.addAll(room.getPeopleInRoom());
            items.addAll(room.getItemInventory());
        }
        for(Person person : people){
            if(person instanceof Student)
                playersAlive.add((Student) person);

            items.addAll(person.getItemInventory());
        }
        for(Item item : items){
            item.tick();
        }
        for (Person person : people) {
            person.tick();
        }
        for (Student player : playersAlive) {
            player.tick();
        }

        players.removeIf(player -> !playersAlive.contains(player));


        if(players.isEmpty()) {
            isGameStarted = false;
            System.out.println("\u001B[31mThe players lost!\u001B[0m");
            processStart();
        }
    }

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

    private void startTestMode(){
        System.out.println();
        System.out.println();
        System.out.println("Valaszthato tesztesetek");
        System.out.println("1. TVSZ Batskin hasznalata");
        System.out.println("2. CannedCamembert hasznalata");
        System.out.println("3. FFP2 Mask hasznalata");
        System.out.println("4. Air Freshener hasznalata");
        System.out.println("5. Holy Beer Glass felvetele");
        System.out.println("6. Holy Beer Glass hasznalata");
        System.out.println("7. Wet Wipe Cloth hasznalata");
        System.out.println("8. Transistor parositasa");
        System.out.println("9. Transistor hasznalata");
        System.out.println("10. Teleportalas");
        System.out.println("11. Fake FFP2 Mask hasznalata");
        System.out.println("12. Sikertelen belepes egy szobaba");
        System.out.println("13. Belepes egy gazos szobaba");
        System.out.println("14. Sikertelen targy felvetel");
        System.out.println("15. Targy eldobasa");
        System.out.println("16. Tanarral valo talalkozas targy nelkul.");
        System.out.println("17. SlideRule felvetele");
        System.out.println("18. Talalkozas megbenult tanarral");
        System.out.println("19. Szobak egyesulese");
        System.out.println("20. Szobak szetvalasa");
        System.out.println("21. Takarito szobaba lepese");
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
            String commandName = parts.getFirst();
            switch (commandName) {
                case "run" -> {
                    if (parts.size() != 2) {
                        System.out.println("Invalid command");
                        return;
                    }
                    int testNumber = Integer.parseInt(parts.get(1));
                    runTest(testNumber);
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
                String commandName = parts.getFirst();
                switch (commandName) {
                    case "createMaze" -> {
                        testMaze = new Maze();
                    }
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
                            case "AF" -> {
                                testItems.add(new AirFreshener(nextItemId++));
                            }
                            case "CC" -> {
                                testItems.add(new CannedCamembert(nextItemId++));
                            }
                            case "FFP2" -> {
                                boolean isFake = Boolean.parseBoolean(parts.get(2));
                                testItems.add(new FFP2Mask(nextItemId++, isFake));
                            }
                            case "HBG" -> {
                                testItems.add(new HolyBeerGlass(nextItemId++));
                            }
                            case "T" -> {
                                testItems.add(new Transistor(nextItemId++));
                            }
                            case "TVSZ" -> {
                                boolean isFake = Boolean.parseBoolean(parts.get(2));
                                testItems.add(new TVSZBatSkin(nextItemId++, isFake));
                            }
                            case "WWC" -> {
                                testItems.add(new WetWipeCloth(nextItemId++));
                            }
                            case "SR" -> {
                                boolean isFake = Boolean.parseBoolean(parts.get(2));
                                testItems.add(new SlideRule(nextItemId++, isFake));
                            }
                            default -> {
                                System.out.println("Unknown item type: " + parts.get(1));
                            }
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
                    default -> {
                        System.out.println("Unknown test command: " + command);
                    }
                }
            }

            StringBuilder testOutput = new StringBuilder();
            if (testMaze != null) {
                testOutput.append(testMaze.toString()).append("\n");
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
                List<Student> notPoisonedPlayers = new ArrayList<Student>();
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

    private void processGameCommand(String command){
        List<String> parts = parse(command);
        if(parts.isEmpty()){
            System.out.println("Invalid command");
            return;
        }
        String commandName = parts.getFirst();
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
                if (isGameStarted || parts.size() != 1) {
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
            default -> System.out.println("Invalid command");
        }
    }

    private void showPlayerStat(){
        System.out.println("Player: \u001B[34m" + currentPlayer.getName() + "\u001B[0m isPoisoned= " + currentPlayer.isPoisoned());
        System.out.println("Room: " + currentPlayer.getCurrentRoom());
        System.out.println("Items: ");
        for(Item item : currentPlayer.getItemInventory()){
            System.out.println(item);
        }
    }

    private void initGame(){
        players.clear();
        Random random = new Random();
        this.maze = new Maze();
        for(int i = 0; i < playerCount * 5; i++){
            maze.addRoom(new Room(random.nextInt(5) + 1, maze.getNextRoomId()));
        }

        maze.getRooms().get(0).addNeighbour(maze.getRooms().get(1));
        for(int i = 1; i < maze.getRooms().size() - 1; i++){
            maze.getRooms().get(i).addNeighbour(maze.getRooms().get(i - 1));
            maze.getRooms().get(i).addNeighbour(maze.getRooms().get(i + 1));
        }
        maze.getRooms().getLast().addNeighbour(maze.getRooms().get(maze.getRooms().size() - 2));

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
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new TVSZBatSkin(itemId++, random.nextInt(4) == 0));
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new WetWipeCloth(itemId++));
        }
        maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new SlideRule(itemId++, false));
        maze.getRooms().get(random.nextInt(maze.getRooms().size())).addItem(new SlideRule(itemId++, true));

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
            //maze.getRooms().get(random.nextInt(maze.getRooms().size())).addPerson(teacher);

            Janitor janitor = new Janitor("Janitor" + i);
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addPerson(janitor);
        }
    }

    private void save(){

    }

    private void load(){

    }
}
