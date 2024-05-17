import javax.swing.*;
import java.io.File;
import java.util.*;
import java.io.*;

public class GameController implements Serializable {

    /**
     * A GameController osztályhoz tartozó GameView objektum
     */
    private GameView gameView;

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
    public Student getCurrentPlayer(){return currentPlayer;}

    /**
     *
     */
    private int currentPlayerIndex = 0;


    /**
     * A GameController osztály konstruktora
     * @param gameView : A GameController osztályhoz tartozó GameView objektum
     */
    public GameController(GameView gameView) {
        this.gameView = gameView;
    }

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
            JOptionPane.showMessageDialog(gameView, "All players are dead", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            gameView.setVisible(false);
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        }

        if(isGameStarted){
            List<Student> notPoisonedPlayers = new ArrayList<>();
            for(Student player : players){
                if(!player.isPoisoned()){
                    notPoisonedPlayers.add(player);
                }
            }

            if(notPoisonedPlayers.isEmpty())
                return;

            currentPlayer = notPoisonedPlayers.get(currentPlayerIndex);
            if(currentPlayerIndex >= notPoisonedPlayers.size() - 1)
                currentPlayerIndex = 0;
            else
                currentPlayerIndex++;
        }
        gameView.placeComponents();
    }

    /**
     * A játék parancsa alapján végrehajtja a megfelelő műveletet.
     * Ha a parancs nem értelmezhető, akkor a függvény kiírja, hogy az adott parancs érvénytelen.
     * Ha a parancs értelmezhető, akkor a függvény végrehajtja a parancsot.
     * @param command : A játék parancsa
     */
    public void processGameCommand(String command){
        for(Student player : players) {
            if(player.isWinner()) {
                isGameStarted = false;
                JOptionPane.showMessageDialog(gameView, "The winner is: " + player.getName(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
                gameView.setVisible(false);
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
                return;
            }
        }

        List<String> parts = parse(command);

        String commandName = parts.get(0);
        switch (commandName) {
            case "start" -> {
                isGameStarted = true;
                try{
                    playerCount = Integer.parseInt(parts.get(1));
                } catch (Exception e){
                    playerCount = 1;
                }
                initGame();
            }
            case "load" -> {
                isGameStarted = true;
                load();
            }
            case "save" -> {
                save();
            }
            case "enter" -> {

                List<Room> rooms = currentPlayer.getCurrentRoom().getNeighbours();
                int roomId = Integer.parseInt(parts.get(1));
                for (Room room : rooms) {
                    if (room.getId() == roomId) {
                        currentPlayer.enter(room);
                        break;
                    }
                }
            }
            case "pickup" -> {
                int itemId = Integer.parseInt(parts.get(1));
                List<Item> items = currentPlayer.getCurrentRoom().getItemInventory();
                for (Item item : items) {
                    if (item.getId() == itemId) {
                        currentPlayer.pickUp(item);
                        break;
                    }
                }
            }
            case "drop" -> {
                int itemId = Integer.parseInt(parts.get(1));
                List<Item> items = currentPlayer.getItemInventory();
                for (Item item : items) {
                    if (item.getId() == itemId) {
                        currentPlayer.dropItem(item);
                        break;
                    }
                }
            }
            case "pair" -> {
                currentPlayer.pairItems();
            }
            case "use" -> {
                int itemId = Integer.parseInt(parts.get(1));
                List<Item> items = currentPlayer.getItemInventory();
                for (Item item : items) {
                    if (item.getId() == itemId) {
                        currentPlayer.useItem(item);
                        break;
                    }
                }
            }
        }
        if(isGameStarted)
            tickAll();
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

        } catch (FileNotFoundException e) {
            System.out.println("No saved game found");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
