import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameView extends JFrame {
    private JButton save;
    private JButton pair;
    private JTextArea playerStats;
    private GameController gameController;
    public void setGameController(GameController gameController){
        this.gameController = gameController;
    }
    public GameView(){
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void updateView() {
        this.getContentPane().removeAll();
        Student player = gameController.getCurrentPlayer();
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);

        save = new JButton("<html><span style=\"font-size:18px\">save</span></html>");
        save.addActionListener(e -> gameController.processGameCommand("save"));
        save.setBounds(25, 20, 80, 50);
        save.setBackground(Color.gray);
        save.setBorder(new LineBorder(Color.BLACK, 3));
        panel.add(save);

        playerStats = new JTextArea();
        playerStats.setEditable(false);
        playerStats.setText("Name: "+player.getName()+"\nPoisoned: "+player.isPoisoned()+"\nRoom: "+player.getCurrentRoom().getId());
        playerStats.setFont(new Font("Arial", Font.BOLD, 16));
        playerStats.setSelectedTextColor(Color.BLACK);
        playerStats.setBounds(25,75,200,100);
        playerStats.setBorder(new LineBorder(Color.BLACK, 3));
        playerStats.setBackground(Color.gray);
        panel.add(playerStats);

        JPanel roomsPanel = new JPanel();
        roomsPanel.setBackground(Color.gray);
        roomsPanel.setLayout(new BoxLayout(roomsPanel, BoxLayout.Y_AXIS));
        for (Room room : player.getCurrentRoom().getNeighbours()) {
            JButton newRoom = new JButton("Room "+room.getId());
            newRoom.addActionListener(e -> gameController.processGameCommand("enter " + room.getId()));
            newRoom.setBackground(Color.gray);
            newRoom.setAlignmentX(Component.CENTER_ALIGNMENT);
            roomsPanel.add(newRoom);
        }
        JScrollPane roomsScrollPane = new JScrollPane(roomsPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        roomsScrollPane.setBounds(700,50,100,200);
        roomsScrollPane.setBorder(new LineBorder(Color.BLACK, 3));
        panel.add(roomsScrollPane);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setBackground(Color.gray);
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        for (Item item : player.getCurrentRoom().getItemInventory()) {
            JButton newItem = new JButton(item.getClass().toString().split(" ")[1]);
            newItem.addActionListener(e -> gameController.processGameCommand("pickup " + item.getId()));
            newItem.setAlignmentX(Component.CENTER_ALIGNMENT);
            newItem.setBackground(Color.gray);
            itemsPanel.add(newItem);
        }
        JScrollPane itemsScrollPane = new JScrollPane(itemsPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemsScrollPane.setBounds(650,275,150,200);
        itemsScrollPane.setBorder(new LineBorder(Color.BLACK, 3));
        panel.add(itemsScrollPane);

        JLabel roomsLabel = new JLabel("<html><span style=\"font-size:18px\">Rooms</span></html>");
        roomsLabel.setBounds(700,25,100,25);
        panel.add(roomsLabel);

        JLabel itemsLabel = new JLabel("<html><span style=\"font-size:18px\">Items</span></html>");
        itemsLabel.setBounds(700,250,100,25);
        panel.add(itemsLabel);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setBounds(25,400,450,200);
        inventoryPanel.setBackground(Color.LIGHT_GRAY);
        inventoryPanel.setBorder(new LineBorder(Color.BLACK, 3));
        inventoryPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
        JTextArea inventory = new JTextArea("Inventory");
        inventory.setFont(new Font("Arial", Font.BOLD, 18 ));
        inventory.setEditable(false);
        inventory.setBackground(Color.LIGHT_GRAY);
        inventoryPanel.add(inventory);

        pair = new JButton("<html><span style=\"font-size:18px\">pair</span></html>");
        pair.addActionListener(e -> gameController.processGameCommand("pair"));
        pair.setBackground(Color.gray);
        inventoryPanel.add(pair);

        for (Item item : player.getItemInventory()) {
            JButton newItem = new JButton(item.toString());
            newItem.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if(SwingUtilities.isLeftMouseButton(evt))
                        gameController.processGameCommand("use " + item.getId());
                    else if(SwingUtilities.isRightMouseButton(evt))
                        gameController.processGameCommand("drop " + item.getId());

                }
            });
            newItem.setBorder(new LineBorder(Color.BLACK, 1));
            newItem.setBackground(Color.gray);
            inventoryPanel.add(newItem);
        }
        panel.add(inventoryPanel);

        boolean teacherInRoom = false;
        boolean teacherIsSick = false;
        boolean janitorInRoom = false;
        for(Person person : player.getCurrentRoom().getPeopleInRoom()){
            if(person.getName().contains("Teacher")){
                Teacher teacher = (Teacher) person;
                if(teacher.isPoisoned() || teacher.isStunned())
                    teacherIsSick = true;
                teacherInRoom = true;
            }
            else if (person.getName().contains("Janitor"))
                janitorInRoom = true;
        }

        ImageIcon playerIcon;
        if(player.isPoisoned()) {
            playerIcon = new ImageIcon("resources/image/betegdiak.png");
        } else {
            playerIcon = new ImageIcon("resources/image/hallgato.png");
        }
        JLabel playerLabel = new JLabel(playerIcon);
        playerLabel.setBounds(350,230, 97,180);
        panel.add(playerLabel);

        if(teacherInRoom){
            ImageIcon teacherIcon;
            if(teacherIsSick)
                teacherIcon = new ImageIcon("resources/image/betegtanar.png");
            else
                teacherIcon = new ImageIcon("resources/image/tanar.png");

            JLabel teacherLabel = new JLabel(teacherIcon);
            teacherLabel.setBounds(50,230, 168,180);
            panel.add(teacherLabel);
        }

        if(janitorInRoom){
            ImageIcon janitorIcon = new ImageIcon("resources/image/takarito.png");
            JLabel janitorLabel = new JLabel(janitorIcon);
            janitorLabel.setBounds(450,230, 131,180);
            panel.add(janitorLabel);
        }

        if(player.getCurrentRoom().isGassed()){
            ImageIcon mistIcon = new ImageIcon("resources/image/gaz.png");
            JLabel mistLabel = new JLabel(mistIcon);
            mistLabel.setBounds(0,150, 800,300);
            panel.add(mistLabel);
        }

        if(player.getCurrentRoom().isSticky()){
            ImageIcon mistIcon = new ImageIcon("resources/image/ragacs.png");
            JLabel mistLabel = new JLabel(mistIcon);
            mistLabel.setBounds(150,330, 800,300);
            panel.add(mistLabel);
        }

        ImageIcon imageIcon = new ImageIcon("resources/image/terem.png");
        JLabel backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0,0,800,600);
        panel.add(backgroundLabel);

        this.revalidate();
        this.repaint();
    }
}
