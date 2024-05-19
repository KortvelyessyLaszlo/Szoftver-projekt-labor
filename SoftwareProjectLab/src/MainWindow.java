import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;

public class MainWindow extends JFrame {
    private JButton start;
    private JButton load;
    private JTextField playerCount;
    public MainWindow(){
        this.setSize(300, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);
        this.setVisible(true);
        setResizable(false);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);

        // Létrehoz egy gombot
        start = new JButton("Start");
        start.setBounds(80, 25, 140, 50);
        start.setBackground(Color.LIGHT_GRAY);
        start.setBorder(new LineBorder(Color.BLACK, 3));
        panel.add(start);

        load = new JButton("Load");
        load.setBounds(80, 125, 140, 50);
        load.setBackground(Color.LIGHT_GRAY);
        load.setBorder(new LineBorder(Color.BLACK, 3));
        File f = new File("resources\\save.txt");
        if (!f.exists())
            load.setEnabled(false);
        panel.add(load);

        start.addActionListener(e -> {
            GameView gameView = new GameView();
            GameController gameController = new GameController(gameView);
            gameView.setGameController(gameController);
            setVisible(false);
            gameController.processGameCommand("start "+ playerCount.getText());
        });

        load.addActionListener(e -> {
            GameView gameView = new GameView();
            GameController gameController = new GameController(gameView);
            gameView.setGameController(gameController);
            setVisible(false);
            gameController.processGameCommand("load");
        });

        playerCount = new JTextField();
        playerCount.setBounds(225, 25,50,50);
        playerCount.setBackground(Color.LIGHT_GRAY);
        playerCount.setBorder(new LineBorder(Color.BLACK, 3));
        playerCount.setHorizontalAlignment(JTextField.CENTER);
        panel.add(playerCount);
    }
}