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
            System.out.println("A tanulók vesztettek");
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

            String command = scanner.nextLine();
            processGameCommand(command);

            if(isGameStarted)
                tickAll();

            if (i >= players.size()) {
                i = 0;
            }

            currentPlayer = players.get(i);

            if (i == players.size() - 1) {
                i = 0;
            } else {
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
        System.out.println("Player: \u001B[34m" + currentPlayer.getName() + "\u001B[0m");
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
;
        for(int i = 0; i < maze.getRooms().size(); ++i) {
            int neighbourCount = random.nextInt(4);
            for(int j = 0; j < neighbourCount; ++j) {
                Room neighbour = maze.getRooms().get(random.nextInt(maze.getRooms().size()));
                if(maze.getRooms().get(i).getNeighbours().contains(neighbour)  || neighbour == maze.getRooms().get(i)){
                    //j--;
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
            teacherCountAndJanitorCount = playerCount ;
        }else {
            teacherCountAndJanitorCount = playerCount ;
        }
        for(int i = 0; i <  teacherCountAndJanitorCount; i++){
            Teacher teacher = new Teacher("Teacher" + i);
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addPerson(teacher);

            Janitor janitor = new Janitor("Janitor" + i);
            maze.getRooms().get(random.nextInt(maze.getRooms().size())).addPerson(janitor);
        }
    }

    private void save(){

    }

    private void load(){

    }
}
