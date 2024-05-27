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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Controler.EndGameController;
import Modele.Boat;
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
    public EndGameController endGameController;

    public boolean firstPlayer;
    private JPanel bottomPanel;

    public NewGameOrdi(Player plr1, int difficulty) {
        this.player1 = plr1;
        this.player2 = new PlayerComputer(difficulty, "AI");
        this.player2.placeBoatsRand();
        this.endGameController = new EndGameController(plr1, new PlayerComputer(difficulty, "AI"), this);
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
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(THEME_COLOR);

        JPanel trackingPanel1 = createTrackingPanel("/Images/DecorDroite.png");
        JPanel trackingPanel2 = createTrackingPanel("/Images/DecorGauche.png");
        JPanel shipsPanel = createShipsPanel();

        bottomPanel.add(trackingPanel1, BorderLayout.WEST);
        bottomPanel.add(trackingPanel2, BorderLayout.EAST);
        bottomPanel.add(shipsPanel, BorderLayout.CENTER);

        return bottomPanel;
    }

    private JPanel createTrackingPanel(String gifPath) {
        JPanel trackingPanel = new JPanel(new BorderLayout());
        trackingPanel.setBorder(BorderFactory.createTitledBorder(""));
        trackingPanel.setBackground(THEME_COLOR);
        JLabel gifLabel = new JLabel();
        try {
            ImageIcon gifIcon = new ImageIcon(getClass().getResource(gifPath));
            Image gifImage = gifIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            gifLabel.setIcon(new ImageIcon(gifImage));
        } catch (Exception e) {
            e.printStackTrace();
            gifLabel.setText("GIF not found");
        }

        gifLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gifLabel.setVerticalAlignment(SwingConstants.CENTER);

        trackingPanel.add(gifLabel, BorderLayout.CENTER);

        return trackingPanel;
    }

    private void stylizeButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(new Color(199, 153, 119));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
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

        String[] shipNames= {
            "PorteAvion",
            "SousMarin",
            "Torpilleur",
            "Croiseur",
            "ContreTorpilleur"
        };

        for (int i = 0; i < shipSizes.length; i++) {
            Boat actual = player1.recupBoat(shipNames[i]);
            
            String state = "EN ETAT";
            if(actual.life < actual.taille){
                state = "ENDOMMAGE";
                if(actual.isSunk())
                    state = "COULE";
            }
            ImageIcon shipIcon = new ImageIcon(getClass().getResource(shipImageFiles[i]));
            Image shipImage = shipIcon.getImage().getScaledInstance(CELL_SIZE, CELL_SIZE * shipSizes[i], Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(shipImage);

            JLabel shipLabel = new JLabel(resizedIcon);
            JButton shipCountLabel = new JButton(state);
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

    public void updateShipPanel(){
        BorderLayout layout = (BorderLayout) bottomPanel.getLayout();
        bottomPanel.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        bottomPanel.add(createShipsPanel() , BorderLayout.CENTER);
    }

   public void checkForWin() {
    if (!player1.isAlive()) {
        SwingUtilities.invokeLater(() -> endGame(player2, player1));
    } else if (!player2.isAlive()) {
        SwingUtilities.invokeLater(() -> endGame(player1, player2));
    }
}

    
    public void endGame(Player winner, Player loser) {
        endGameController.endGame();
    }
    
    public int getElapsedTime() {
        return elapsedTime;
    }
    public EndGameController getEndGameController() {
        return endGameController;
    }

    // public static void main(String[] args) {
    //     Player plr1 = new Player("Bob");
    //     plr1.placeBoatsRand();
    //     int difficulty = PlayerComputer.MEDIUM;
    //     new NewGameOrdi(plr1, difficulty);
    // }
}
