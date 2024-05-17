import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameView extends JFrame {
    private JButton save;
    private JButton pair;
    private JTextArea playerStats;
    private ImageIcon people;
    public GameView(){
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);
        this.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        save = new JButton("<html><span style=\"font-size:18px\">save</span></html>");
        save.setBounds(25, 25, 80, 50);
        save.setBackground(Color.LIGHT_GRAY);
        save.setBorder(new LineBorder(Color.BLACK, 8));
        panel.add(save);

        playerStats = new JTextArea();
        playerStats.setText("Name: Player\nPoisoned: false\nRoom: 2");
        playerStats.setFont(new Font("Arial", Font.BOLD, 16));
        playerStats.setSelectedTextColor(Color.BLACK);
        playerStats.setBounds(25,75,200,100);
        playerStats.setBorder(new LineBorder(Color.BLACK, 8));
        playerStats.setBackground(Color.LIGHT_GRAY);
        panel.add(playerStats);

        JPanel roomsPanel = new JPanel();
        roomsPanel.setLayout(new BoxLayout(roomsPanel, BoxLayout.Y_AXIS));
        roomsPanel.add(new JLabel("Rooms"));
        for (int i = 1; i < 11; i++) {
            roomsPanel.add(new JButton("Room "+1));
        }
        JScrollPane roomsScrollPane = new JScrollPane(roomsPanel);
        roomsScrollPane.setBounds(740,25,60,200);
        panel.add(roomsScrollPane);





        JLayeredPane layeredPane = new JLayeredPane();
        ImageIcon imageIcon = new ImageIcon("resources/image/terem.png"); // Replace with your image path
        JLabel backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0,0,800,600);
        panel.add(backgroundLabel);

    }
}
