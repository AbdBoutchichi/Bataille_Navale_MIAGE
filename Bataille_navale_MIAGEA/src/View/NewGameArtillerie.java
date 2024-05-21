package View;

import java.awt.*;
import javax.swing.*;
import Modele.Player;

public class NewGameArtillerie extends JFrame {

    private static final Color THEME_COLOR = new Color(10, 25, 48);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color BUTTON_COLOR = new Color(30, 144, 255);
    private static final Font BUTTON_FONT = new Font("Stencil", Font.BOLD, 12);
    private static final int CELL_SIZE = 30;

    private Player player1;
    private Player player2;
    private JLabel welcomeLabel;
    private JLabel timerLabel;
    private int elapsedTime = 0;
    private Timer timer;
    private GridPanel gridPanelPlayer1;
    private GridPanel gridPanelPlayer2;
    public JPanel controlPanelPlayer2;
    public JPanel controlPanelPlayer1;

    public NewGameArtillerie(Player plr1, Player plr2) {
        this.player1 = plr1;
        this.player2 = plr2;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mode Artillerie - Bataille navale");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width * 75 / 100, screenSize.height * 95 / 100);
        setLocationRelativeTo(null);
        getContentPane().setBackground(THEME_COLOR);
        setLayout(new BorderLayout(10, 10));

        add(createTimerPanel(), BorderLayout.NORTH);
        add(createPlayerPanel1(player1, player2), BorderLayout.WEST);
        add(createPlayerPanel2(player2, player1), BorderLayout.EAST);
        add(createWelcomePanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setupTimer();
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

    private JPanel createPlayerPanel1(Player player, Player enemy) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(THEME_COLOR);

        JLabel nameLabel = new JLabel(player.getName(), SwingConstants.CENTER);
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setFont(BUTTON_FONT);

        gridPanelPlayer1 = new GridPanel(player, enemy);
        controlPanelPlayer1 = createControlPanelArtillery(player, enemy);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(gridPanelPlayer1, BorderLayout.CENTER);
        panel.add(controlPanelPlayer1, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPlayerPanel2(Player player, Player enemy) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(THEME_COLOR);

        JLabel nameLabel = new JLabel(player.getName(), SwingConstants.CENTER);
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setFont(BUTTON_FONT);

        gridPanelPlayer2 = new GridPanel(player, enemy);
        controlPanelPlayer2 = createControlPanelArtillery(player, enemy);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(gridPanelPlayer2, BorderLayout.CENTER);
        panel.add(controlPanelPlayer2, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createControlPanelArtillery(Player player, Player enemy) {
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        controlPanel.setBackground(THEME_COLOR);

        JLabel xValueLabel = new JLabel("X: 0", SwingConstants.CENTER);
        JLabel yValueLabel = new JLabel("Y: 0", SwingConstants.CENTER);
        JButton incrementXButton = new JButton("Increment X");
        JButton incrementYButton = new JButton("Increment Y");
        JButton stopXButton = new JButton("Stop X");
        JButton stopYButton = new JButton("Stop Y");
        JButton confirmFireButton = new JButton("Confirm Fire");
        JButton cancelButton = new JButton("Cancel");

        setLabelStyles(xValueLabel, yValueLabel);
        setButtonStyles(incrementXButton, incrementYButton, stopXButton, stopYButton, confirmFireButton, cancelButton);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("X:"), gbc);
        gbc.gridy = 1;
        controlPanel.add(xValueLabel, gbc);
        gbc.gridy = 2;
        controlPanel.add(incrementXButton, gbc);
        gbc.gridy = 3;
        controlPanel.add(stopXButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("Y:"), gbc);
        gbc.gridy = 1;
        controlPanel.add(yValueLabel, gbc);
        gbc.gridy = 2;
        controlPanel.add(incrementYButton, gbc);
        gbc.gridy = 3;
        controlPanel.add(stopYButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        controlPanel.add(confirmFireButton, gbc);
        gbc.gridy = 5;
        controlPanel.add(cancelButton, gbc);

        return controlPanel;
    }

    private void setLabelStyles(JLabel... labels) {
        for (JLabel label : labels) {
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setForeground(TEXT_COLOR);
        }
    }

    private void setButtonStyles(JButton... buttons) {
        for (JButton button : buttons) {
            button.setFont(BUTTON_FONT);
            button.setBackground(BUTTON_COLOR);
            button.setForeground(TEXT_COLOR);
            button.setFocusPainted(false);
            button.setBorderPainted(true);
            button.setOpaque(true);
        }
    }

    private JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(THEME_COLOR);

        welcomeLabel = new JLabel("<html><center>Welcome to Artillery Mode Naval Battle!<br>May the best strategist win!</center></html>", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
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
        trackingPanel.setBorder(BorderFactory.createTitledBorder("Game Tracking"));
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
        shipsPanel.setBorder(BorderFactory.createTitledBorder("Fleets"));
        shipsPanel.setBackground(THEME_COLOR);
        shipsPanel.setLayout(new BoxLayout(shipsPanel, BoxLayout.LINE_AXIS));

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
        shipsPanel.add(Box.createHorizontalGlue());

        return shipsPanel;
    }

    private void stylizeButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    public static void main(String[] args) {
        Player plr1 = new Player("Bob");
        Player plr2 = new Player("Bill");
        new NewGameArtillerie(plr1, plr2);
    }
}
