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
import Controler.ShootField;
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
    private GridPanel gridPanelPlayer1;
    private GridPanel gridPanelPlayer2;

    public JTextField xFieldPlayer1;
    public JTextField yFieldPlayer1;
    public JButton fireButtonPlayer1;
    public JButton cancelButtonPlayer1;
    public JPanel controlPanelPlayer1;

    public JTextField xFieldPlayer2;
    public JTextField yFieldPlayer2;
    public JButton fireButtonPlayer2;
    public JButton cancelButtonPlayer2;
    public JPanel controlPanelPlayer2;

    private ShootField shootFieldPlayer1;
    private ShootField shootFieldPlayer2;
    public EndGameController endGameController;



    public NewGame(Player plr1, Player plr2) {
        this.player1 = plr1;
        this.player2 = plr2;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bataille Navale");
        this.endGameController = new EndGameController(plr1, plr2, this, false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width * 75 / 100, screenSize.height * 75 / 100);
        setLocationRelativeTo(null);
        getContentPane().setBackground(THEME_COLOR);
        setLayout(new BorderLayout(10, 10));

        add(createTimerPanel(), BorderLayout.NORTH);
        add(createPlayerPanel2(player2, player1), BorderLayout.EAST);
        
        add(createPlayerPanel1(player1, player2), BorderLayout.WEST);
        
        add(createWelcomePanel(), BorderLayout.CENTER);
        
        add(createBottomPanel(), BorderLayout.SOUTH);

        initActionListener(player1, player2);

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

    private JPanel createPlayerPanel2(Player player, Player enemy) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(THEME_COLOR);

        JLabel nameLabel = new JLabel(player.getName(), SwingConstants.CENTER);
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setFont(BUTTON_FONT);

        gridPanelPlayer2 = new GridPanel(player, enemy);
        controlPanelPlayer2 = createControlPanel(player, enemy);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(gridPanelPlayer2, BorderLayout.CENTER);
        panel.add(controlPanelPlayer2, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPlayerPanel1(Player player, Player enemy) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(THEME_COLOR);

        JLabel nameLabel = new JLabel(player.getName(), SwingConstants.CENTER);
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setFont(BUTTON_FONT);

        gridPanelPlayer1 = new GridPanel(10, player, enemy, gridPanelPlayer2, this);
        controlPanelPlayer1 = createControlPanel(player, enemy);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(gridPanelPlayer1, BorderLayout.CENTER);
        panel.add(controlPanelPlayer1, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createControlPanel(Player player, Player enemy) {
        
        JPanel controlPanel = new JPanel(new GridLayout(4, 2));

        if(player1 == player){
            controlPanel.setBackground(THEME_COLOR);

            xFieldPlayer1 = new JTextField();
            yFieldPlayer1 = new JTextField();
            fireButtonPlayer1 = new JButton("Tirer");

            
            //fireButtonPlayer1.addActionListener(new ShootField(this, player, enemy, gridPanelPlayer1, gridPanelPlayer2, true));
            



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
            controlPanel.setBackground(THEME_COLOR);

            xFieldPlayer2 = new JTextField();
            yFieldPlayer2 = new JTextField();
            fireButtonPlayer2 = new JButton("Tirer");

            
            //fireButtonPlayer2.addActionListener(new ShootField(this, player, enemy, gridPanelPlayer2, gridPanelPlayer1, false));
            



            cancelButtonPlayer2 = new JButton("Annuler");
            cancelButtonPlayer2.addActionListener(e -> {
                xFieldPlayer2.setText("");
                yFieldPlayer2.setText("");
            });

            stylizeButton(fireButtonPlayer2);
            stylizeButton(cancelButtonPlayer2);

            controlPanel.add(new JLabel("X:"));
            controlPanel.add(xFieldPlayer2);
            controlPanel.add(new JLabel("Y:"));
            controlPanel.add(yFieldPlayer2);
            controlPanel.add(fireButtonPlayer2);
            controlPanel.add(cancelButtonPlayer2);
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

        // Load and resize GIF
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

    private JPanel createShipsPanel() {
        JPanel shipsPanel = new JPanel();
        shipsPanel.setBorder(BorderFactory.createTitledBorder("Flottes"));
        shipsPanel.setBackground(THEME_COLOR);
        shipsPanel.setLayout(new BoxLayout(shipsPanel, BoxLayout.LINE_AXIS));


        int[] shipSizes = {5, 4, 3, 3, 2};
        String[] shipImageFiles = {
            "/Images/PorteAvion.png",
            "/Images/sousMarin.png",
            "/Images/Torpilleur.png",
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

    private void initActionListener(Player joueur, Player adversaire){
        shootFieldPlayer1 = new ShootField(this, player1, player2, gridPanelPlayer1, gridPanelPlayer2, true);
        shootFieldPlayer2 = new ShootField(this, player2, player1, gridPanelPlayer2, gridPanelPlayer1, false);
        fireButtonPlayer1.addActionListener(shootFieldPlayer1);
        fireButtonPlayer2.setBackground(Color.GRAY);
    }

    public void setActionlistener(Player joueur){
        if(joueur == player1){
            fireButtonPlayer1.removeActionListener(shootFieldPlayer1);
            fireButtonPlayer2.addActionListener(shootFieldPlayer2);
            fireButtonPlayer2.setBackground(new Color(199, 153, 119));
            fireButtonPlayer1.setBackground(Color.GRAY);
        } else {
            fireButtonPlayer2.removeActionListener(shootFieldPlayer2);
            fireButtonPlayer1.addActionListener(shootFieldPlayer1);
            fireButtonPlayer1.setBackground(new Color(199, 153, 119));
            fireButtonPlayer2.setBackground(Color.GRAY);
        }
    }

    private void stylizeButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(new Color(199, 153, 119));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
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
    public static void main(String[] args) {
        Player plr1 = new Player("Bob");
        Player plr2 = new Player("Bill");
        new NewGame(plr1, plr2);
    }
}
