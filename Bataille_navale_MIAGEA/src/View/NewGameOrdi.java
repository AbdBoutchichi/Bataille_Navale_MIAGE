package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Modele.Player;
import Modele.PlayerComputer;

public class NewGameOrdi extends JFrame {

    private static final Color THEME_COLOR = new Color(249, 246, 233);
    private static final Color TEXT_COLOR = new Color(123, 85, 74);
    private static final Font BUTTON_FONT = new Font("Stencil", Font.BOLD, 13);
    private static final int CELL_SIZE = 30;

    private Player player1;
    private PlayerComputer player2;
    private JLabel welcomeLabel;
    private JLabel timerLabel;
    private int elapsedTime = 0;
    private Timer timer;
    private GridPanel gridPanelPlayer1;
    private GridPanel gridPanelPlayer2;

    public JTextField xFieldPlayer1;
    public JTextField yFieldPlayer1;
    public JButton fireButtonPlayer1;
    public JButton cancelButtonPlayer1;
    public JPanel controlPanelPlayer1;

    public NewGameOrdi(Player plr1, int difficulty) {
        this.player1 = plr1;
        this.player2 = new PlayerComputer(difficulty, "AI");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bataille Navale");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width * 75 / 100, screenSize.height * 95 / 100);
        setLocationRelativeTo(null);
        getContentPane().setBackground(THEME_COLOR);
        setLayout(new BorderLayout(10, 10));

        add(createTimerPanel(), BorderLayout.NORTH);
        add(createComputerPanel(player2, player1, false), BorderLayout.EAST);
        add(createPlayerPanel(player1, player2, true), BorderLayout.WEST);
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

    private JPanel createPlayerPanel(Player player, PlayerComputer enemy, boolean isHuman) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(THEME_COLOR);

        JLabel nameLabel = new JLabel(player.getName(), SwingConstants.CENTER);
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setFont(BUTTON_FONT);

        gridPanelPlayer1 = new GridPanel(10, player, enemy, gridPanelPlayer2, this);
        JPanel controlPanel = createControlPanel(player, enemy, isHuman);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(gridPanelPlayer1, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createComputerPanel(PlayerComputer player, Player enemy, boolean isHuman) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(THEME_COLOR);

        JLabel nameLabel = new JLabel(player.getName(), SwingConstants.CENTER);
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setFont(BUTTON_FONT);

        gridPanelPlayer2 = new GridPanel(player, enemy);
        JPanel controlPanel = createControlPanel(player, enemy, isHuman);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(gridPanelPlayer2, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createControlPanel(Player player, Player enemy, boolean isHuman) {
        JPanel controlPanel = new JPanel(new GridLayout(4, 2));
        controlPanel.setBackground(THEME_COLOR);

        if (isHuman) {
            xFieldPlayer1 = new JTextField();
            yFieldPlayer1 = new JTextField();
            fireButtonPlayer1 = new JButton("Tirer");
            fireButtonPlayer1.addActionListener(e -> {
                int x = Integer.parseInt(xFieldPlayer1.getText());
                int y = Integer.parseInt(yFieldPlayer1.getText());
                player.shootAt(x, y);
                enemy.isTouch(x, y);
                xFieldPlayer1.setText("");
                yFieldPlayer1.setText("");
            });

            cancelButtonPlayer1 = new JButton("Annuler");
            cancelButtonPlayer1.addActionListener(e -> {
                xFieldPlayer1.setText("");
                yFieldPlayer1.setText("");
            });

            stylizeButton(fireButtonPlayer1);
            stylizeButton(cancelButtonPlayer1);

            controlPanel.add(new JLabel("X:"));
            controlPanel.add(xFieldPlayer1);
            controlPanel.add(new JLabel("Y:"));
            controlPanel.add(yFieldPlayer1);
            controlPanel.add(fireButtonPlayer1);
            controlPanel.add(cancelButtonPlayer1);
        } else {
            controlPanel.add(new JLabel("L'ordinateur joue..."));
        }

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

    private void stylizeButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(new Color(199, 153, 119));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
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
            JButton shipCountLabel = new JButton("EN ETAT");
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

    public static void main(String[] args) {
        Player plr1 = new Player("Bob");
        plr1.placeBoatsRand();
        int difficulty = PlayerComputer.MEDIUM; // Choisissez la difficulté ici
        new NewGameOrdi(plr1, difficulty);
    }
}
