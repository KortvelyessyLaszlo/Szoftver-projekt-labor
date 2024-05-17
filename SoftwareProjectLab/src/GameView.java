import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameView extends JFrame {
    private JButton save;
    private JButton pair;
    private JTextArea playerStats;
    private ImageIcon people;
    private GameController gameController;
    public void setGameController(GameController gameController){
        this.gameController = gameController;
    }
    public GameView(){
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);
        this.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        Student player = gameController.getCurrentPlayer();

        panel.setLayout(null);

        save = new JButton("<html><span style=\"font-size:18px\">save</span></html>");
        save.setBounds(25, 25, 80, 50);
        save.setBackground(Color.gray);
        save.setBorder(new LineBorder(Color.BLACK, 8));
        panel.add(save);

        playerStats = new JTextArea();
        playerStats.setText("Name: "+player.getName()+"\nPoisoned: "+player.isPoisoned()+"\nRoom: "+player.getCurrentRoom().getId());
        playerStats.setFont(new Font("Arial", Font.BOLD, 16));
        playerStats.setSelectedTextColor(Color.BLACK);
        playerStats.setBounds(25,75,200,100);
        playerStats.setBorder(new LineBorder(Color.BLACK, 8));
        playerStats.setBackground(Color.gray);
        panel.add(playerStats);

        JPanel roomsPanel = new JPanel();
        roomsPanel.setLayout(new BoxLayout(roomsPanel, BoxLayout.Y_AXIS));
        for (Room room : player.getCurrentRoom().getNeighbours()) {
            JButton newRoom = new JButton("Room "+room.getId());
            newRoom.setBorder(new LineBorder(Color.BLACK, 3));
            newRoom.setBackground(Color.gray);
            roomsPanel.add(newRoom);
        }
        JScrollPane roomsScrollPane = new JScrollPane(roomsPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        roomsScrollPane.setBounds(700,50,100,200);
        roomsScrollPane.setBorder(new LineBorder(Color.BLACK, 5));
        panel.add(roomsScrollPane);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        for (Item item : player.getCurrentRoom().getItemInventory()) {
            JButton newItem = new JButton(item.getClass().toString());
            newItem.setBorder(new LineBorder(Color.BLACK, 3));
            newItem.setBackground(Color.gray);
            itemsPanel.add(newItem);
        }
        JScrollPane itemsScrollPane = new JScrollPane(itemsPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemsScrollPane.setBounds(700,275,100,200);
        itemsScrollPane.setBorder(new LineBorder(Color.BLACK, 5));
        panel.add(itemsScrollPane);

        JLabel roomsLabel = new JLabel("<html><span style=\"font-size:18px\">Rooms</span></html>");
        roomsLabel.setBounds(700,25,100,25);
        panel.add(roomsLabel);

        JLabel itemsLabel = new JLabel("<html><span style=\"font-size:18px\">Items</span></html>");
        itemsLabel.setBounds(700,250,100,25);
        panel.add(itemsLabel);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setBounds(25,400,200,200);
        inventoryPanel.setBackground(Color.LIGHT_GRAY);
        inventoryPanel.setBorder(new LineBorder(Color.BLACK, 10));
        inventoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        JTextArea inventory = new JTextArea("Inventory");
        inventory.setFont(new Font("Arial", Font.BOLD, 18 ));
        inventory.setEditable(false);
        inventory.setBackground(Color.LIGHT_GRAY);
        inventoryPanel.add(inventory);

        pair = new JButton("<html><span style=\"font-size:18px\">pair</span></html>");
        pair.setBackground(Color.gray);
        pair.setBorder(new LineBorder(Color.BLACK, 5));
        inventoryPanel.add(pair);

        for (Item item : player.getItemInventory()) {
            JButton newItem = new JButton(item.toString());
            newItem.setBorder(new LineBorder(Color.BLACK, 3));
            newItem.setBackground(Color.gray);
            inventoryPanel.add(newItem);
        }
        panel.add(inventoryPanel);



        JLayeredPane layeredPane = new JLayeredPane();
        ImageIcon imageIcon = new ImageIcon("resources/image/terem.png"); // Replace with your image path
        JLabel backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0,0,800,600);
        panel.add(backgroundLabel);

    }
}
