package View;

import java.awt.*;
import javax.swing.*;

public class NewGame extends JFrame {

    private static final int CELL_SIZE = 30;

    public NewGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bataille Navale");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width * 75 / 100, screenSize.height * 75 / 100);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        add(createPlayerPanel("Joueur 1"), BorderLayout.WEST);
        add(createTimerPanel(), BorderLayout.CENTER);
        add(createPlayerPanel("Joueur 2"), BorderLayout.EAST);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createPlayerPanel(String playerName) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel(playerName, SwingConstants.CENTER);
        JPanel gridPanel = new GridPanel();
        JPanel controlPanel = createControlPanel();

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(gridPanel, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new GridLayout(4, 2));

        JTextField xField = new JTextField();
        JTextField yField = new JTextField();

        String[] orientations = {"Horizontal", "Vertical"};
        JComboBox<String> orientationBox = new JComboBox<>(orientations);

        JButton validateButton = new JButton("Valider");
        JButton modifyButton = new JButton("Modifier");

        controlPanel.add(new JLabel("X:"));
        controlPanel.add(xField);
        controlPanel.add(new JLabel("Y:"));
        controlPanel.add(yField);
        controlPanel.add(new JLabel("Orientation:"));
        controlPanel.add(orientationBox);
        controlPanel.add(validateButton);
        controlPanel.add(modifyButton);

        return controlPanel;
    }

    private JPanel createTimerPanel() {
        JPanel timerPanel = new JPanel(new GridBagLayout());
        timerPanel.setBorder(BorderFactory.createTitledBorder("Timer"));

        JLabel timerLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        timerPanel.add(timerLabel, gbc);

        return timerPanel;
    }

     private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel trackingPanel1 = createTrackingPanel();
        JPanel trackingPanel2 = createTrackingPanel();
        JPanel shipsPanel = createShipsPanel();

        bottomPanel.add(trackingPanel1, BorderLayout.WEST);
        bottomPanel.add(trackingPanel2, BorderLayout.EAST);
        bottomPanel.add(shipsPanel, BorderLayout.CENTER);

        return bottomPanel;
    }

    private JPanel createTrackingPanel() {
        JPanel trackingPanel = new JPanel();
        trackingPanel.setBorder(BorderFactory.createTitledBorder("Suivi du jeu"));
        JTextArea trackingArea = new JTextArea(10, 30);
        trackingArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(trackingArea);
        trackingPanel.add(scrollPane);
        return trackingPanel;
    }

    private JPanel createShipsPanel() {
        JPanel shipsPanel = new JPanel();
        shipsPanel.setBorder(BorderFactory.createTitledBorder("Flottes"));
        shipsPanel.setLayout(new BoxLayout(shipsPanel, BoxLayout.LINE_AXIS));
    
        // Assuming you have ship sizes and corresponding images
        int[] shipSizes = {5, 4, 3, 3, 2};
        String[] shipImageFiles = {
            "/Images/Torpilleur.png",
            "/Images/sousMarin.png",
            "/Images/PorteAvion.png",
            "/Images/Croiseur.png",
            "/Images/contreTorpilleur.png"
        };
    
        for (int i = 0; i < shipSizes.length; i++) {
            ImageIcon shipIcon = new ImageIcon(getClass().getResource(shipImageFiles[i]));
            Image shipImage = shipIcon.getImage().getScaledInstance(CELL_SIZE , CELL_SIZE * shipSizes[i], Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(shipImage);


    
            JLabel shipLabel = new JLabel(resizedIcon);
            JLabel shipCountLabel = new JLabel("1", SwingConstants.CENTER);
    
            JPanel shipPanel = new JPanel(new BorderLayout());
            shipPanel.add(shipLabel, BorderLayout.CENTER);
            shipPanel.add(shipCountLabel, BorderLayout.SOUTH);
            shipsPanel.add(shipPanel);
        }
        shipsPanel.add(Box.createHorizontalGlue()); // Add glue to space out the ships
        
        return shipsPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewGame::new);
    }
}