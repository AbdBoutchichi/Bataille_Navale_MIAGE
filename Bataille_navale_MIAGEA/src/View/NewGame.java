package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controler.*;
import Modele.Player;

public class NewGame extends JFrame {

    private static final Color THEME_COLOR = new Color(249, 246, 233);
    private static final Color TEXT_COLOR = new Color(123, 85, 74);
    private static final Font BUTTON_FONT = new Font("Stencil", Font.BOLD, 13);
    private static final int CELL_SIZE = 30;

    private Player player1;
    private Player player2;
    private JLabel welcomeLabel;
    private JLabel timerLabel;
    private int elapsedTime = 0; // Temps écoulé en secondes
    private Timer timer;

    public NewGame(Player plr1, Player plr2) {
        this.player1 = plr1;
        this.player2 = plr2;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bataille Navale");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width * 75 / 100, screenSize.height * 75 / 100);
        setLocationRelativeTo(null);
        getContentPane().setBackground(THEME_COLOR);
        setLayout(new BorderLayout(10, 10));

        add(createTimerPanel(), BorderLayout.NORTH);
        add(createPlayerPanel(player1, player2), BorderLayout.WEST);
        add(createWelcomePanel(), BorderLayout.CENTER);
        add(createPlayerPanel(player2, player1), BorderLayout.EAST);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setupTimer(); // Initialize and start the timer
        setVisible(true);
    }

    private JPanel createTimerPanel() {
        JPanel timerPanel = new JPanel();
        timerPanel.setBackground(THEME_COLOR);
        timerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        timerLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        timerLabel.setForeground(TEXT_COLOR);

        timerPanel.add(timerLabel);
        return timerPanel;
    }

    private void setupTimer() {
        timer = new Timer(1000, e -> updateTimer());
        timer.start();
    }

    private void updateTimer() {
        elapsedTime++;
        int hours = elapsedTime / 3600;
        int minutes = (elapsedTime % 3600) / 60;
        int seconds = elapsedTime % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerLabel.setText(timeString);
    }

    private JPanel createPlayerPanel(Player player, Player enemy) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(THEME_COLOR);

        JLabel nameLabel = new JLabel(player.getName(), SwingConstants.CENTER);
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setFont(BUTTON_FONT);

        GridPanel gridPanel = new GridPanel(10, player, enemy, this);
        JPanel controlPanel = createControlPanel();

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(gridPanel, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new GridLayout(4, 2));
        controlPanel.setBackground(THEME_COLOR);

        JTextField xField = new JTextField();
        JTextField yField = new JTextField();
        JButton fireButton = new JButton("Tirer");
        JButton cancelButton = new JButton("Annuler");

        stylizeButton(fireButton);
        stylizeButton(cancelButton);

        controlPanel.add(new JLabel("X:"));
        controlPanel.add(xField);
        controlPanel.add(new JLabel("Y:"));
        controlPanel.add(yField);
        controlPanel.add(fireButton);
        controlPanel.add(cancelButton);

        return controlPanel;
    }

    private JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(THEME_COLOR);

        welcomeLabel = new JLabel("<html><center>Bienvenue à la Bataille Navale!<br>Que le meilleur gagne!</center></html>", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 18));
        welcomeLabel.setForeground(TEXT_COLOR);

        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        return welcomePanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(THEME_COLOR);

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
        trackingPanel.setBackground(THEME_COLOR);

        JTextArea trackingArea = new JTextArea(10, 30);
        trackingArea.setEditable(false);
        trackingArea.setForeground(TEXT_COLOR);
        trackingArea.setBackground(THEME_COLOR);

        JScrollPane scrollPane = new JScrollPane(trackingArea);
        scrollPane.setBackground(THEME_COLOR);
        scrollPane.getViewport().setBackground(THEME_COLOR);
        trackingPanel.add(scrollPane);

        return trackingPanel;
    }

    private JPanel createShipsPanel() {
        JPanel shipsPanel = new JPanel();
        shipsPanel.setBorder(BorderFactory.createTitledBorder("Flottes"));
        shipsPanel.setBackground(THEME_COLOR);
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
            Image shipImage = shipIcon.getImage().getScaledInstance(CELL_SIZE, CELL_SIZE * shipSizes[i], Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(shipImage);

            JLabel shipLabel = new JLabel(resizedIcon);
            JButton shipCountLabel = new JButton("1");
            stylizeButton(shipCountLabel);

            JPanel shipPanel = new JPanel(new BorderLayout());
            shipPanel.setBackground(THEME_COLOR);
            shipPanel.add(shipLabel, BorderLayout.CENTER);
            shipPanel.add(shipCountLabel, BorderLayout.SOUTH);
            shipsPanel.add(shipPanel);
        }
        shipsPanel.add(Box.createHorizontalGlue()); // Add glue to space out the ships
        
        return shipsPanel;
    }

    private void stylizeButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(new Color(199, 153, 119));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    public static void main(String[] args) {
        Player plr1 = new Player("Bob");
        Player plr2 = new Player("Bill");
        new NewGame(plr1, plr2);
    }
}
