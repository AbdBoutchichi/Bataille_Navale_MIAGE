package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controler.OrientationController;
import Controler.Placement;
import Controler.PlacementField;
import Controler.SelectBoat;
import Modele.Player;

public class PlacementPanel extends JPanel {
    private GridPanel gridPanel;
    //private GameController gameController;
    private final static int CELL_SIZE = 30;
    private JTextField xField, yField;
    public JComboBox<String> orientationComboBox;
    private JButton placeShipButton, placeShipButtonRandomly, validateButton, backButton;
    public JPanel shipsPanel;
    private JLabel statusLabel;
    private JLabel[] shipLabels;
    private boolean[] shipsPlaced;
    private String playerName;
    private JLabel avatarLabel; 
    private ImageIcon avatarIcon;

    private Player player;

    private static final Color THEME_COLOR = new Color(249, 246, 233);
    private static final Color TEXT_COLOR = new Color(123, 85, 74);
    private static final Font BUTTON_FONT = new Font("Stencil", Font.BOLD, 13);


    public String orientation = "Horizontale";
    public int selectedSize = 2;
    public String selectedBoat = "Torpilleur";

<<<<<<< HEAD
    public PlacementPanel(String playerName, ImageIcon avatar) {
=======
    private boolean firstPlayer;
    private NewGameMenu page;

    public PlacementPanel(String playerName, NewGameMenu page) {

        this.firstPlayer = firstPlayer;
        this.page = page;

>>>>>>> a1a4995f9ce915a16c13d5dd82daa93911184578
        this.playerName = playerName;
        player = new Player(playerName);
        ImageIcon[] shipIcons = {
            new ImageIcon("Images/Torpilleur.png"),
            new ImageIcon("Images/sousMarin.png"),
            new ImageIcon("Images/PorteAvion.png"),
            new ImageIcon("Images/Croiseur.png"),
            new ImageIcon("Images/contreTorpilleur.png")
        };
        this.avatarIcon = avatar;
        this.shipLabels = new JLabel[shipIcons.length];
        this.shipsPlaced = new boolean[shipIcons.length];
        initializeComponents(shipIcons);
        layoutComponents();
        initializeController();
    }

    
    private void initializeComponents(ImageIcon[] shipIcons) {
        gridPanel = new GridPanel(10, player, "Horizontale", 2, "Torpilleur");
        xField = new JTextField("0", 5);
        yField = new JTextField("0", 5);
        orientationComboBox = new JComboBox<>(new String[]{"Horizontal", "Vertical"});

        xField.setFont(BUTTON_FONT);
        yField.setFont(BUTTON_FONT);
        xField.setForeground(TEXT_COLOR);
        yField.setForeground(TEXT_COLOR);
        xField.setBackground(THEME_COLOR);
        yField.setBackground(THEME_COLOR);

        placeShipButton = new JButton("Place Ship");
        placeShipButtonRandomly = new JButton("Place Ship Randomly");
        validateButton = new JButton("Validate");
        backButton = new JButton("Back");

        stylizeButton(placeShipButton);
        stylizeButton(placeShipButtonRandomly);
        stylizeButton(validateButton);
        stylizeButton(backButton);

        for (int i = 0; i < shipIcons.length; i++) {
            shipLabels[i] = new JLabel(shipIcons[i]);
            shipLabels[i].setText("1");
            shipLabels[i].setHorizontalTextPosition(JLabel.CENTER);
            shipLabels[i].setVerticalTextPosition(JLabel.BOTTOM);
            shipsPlaced[i] = false;
        }

        statusLabel = new JLabel("Place your ships!");
        statusLabel.setForeground(TEXT_COLOR);
        statusLabel.setFont(new Font("Stencil", Font.BOLD, 15));
    }

    private void stylizeButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(new Color(199, 153, 119));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel trackingPanel = createTrackingPanel();
        shipsPanel = new JPanel();
        createShipsPanel();

        bottomPanel.add(trackingPanel, BorderLayout.WEST);
        bottomPanel.add(this.shipsPanel, BorderLayout.CENTER);

        return bottomPanel;
    }

    private JPanel createTrackingPanel() {
        JPanel trackingPanel = new JPanel();
        trackingPanel.setBorder(BorderFactory.createTitledBorder("Game Tracking"));
        trackingPanel.setBackground(THEME_COLOR);

        JTextArea trackingArea = new JTextArea(10, 30);
        trackingArea.setEditable(false);
        trackingArea.setFont(BUTTON_FONT);
        trackingArea.setBackground(THEME_COLOR);
        trackingArea.setForeground(TEXT_COLOR);

        JScrollPane scrollPane = new JScrollPane(trackingArea);
        scrollPane.setBackground(THEME_COLOR);
        scrollPane.getViewport().setBackground(THEME_COLOR);
        trackingPanel.add(scrollPane);

        return trackingPanel;
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(createTitlePanel(), BorderLayout.NORTH);
        add(createInputPanel(), BorderLayout.WEST);
        add(gridPanel, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setPreferredSize(new Dimension(800, 650));
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new GridBagLayout()); // Utilisation de GridBagLayout pour un placement plus flexible
        titlePanel.setBackground(THEME_COLOR);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Ajoute des marges autour des composants
    
        // Avatar Label
        avatarLabel = new JLabel();
        if (avatarIcon != null) {
            avatarLabel.setIcon(new ImageIcon(avatarIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))); // Redimensionnement et affichage de l'avatar
        }
        gbc.gridx = 0; // Première colonne
        gbc.gridy = 0; // Première ligne
        gbc.anchor = GridBagConstraints.WEST;  // Ancrage à l'ouest
        titlePanel.add(avatarLabel, gbc);
    
        // Title Label
        JLabel titleLabel = new JLabel("Place Your Ships, Player: " + playerName);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setFont(new Font("Stencil", Font.BOLD, 20));
        gbc.gridx = 1; // Seconde colonne, à côté de l'avatar
        gbc.anchor = GridBagConstraints.WEST;  // Ancrage à l'ouest
        titlePanel.add(titleLabel, gbc);
    
        return titlePanel;
    }
    
    

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(THEME_COLOR);

        inputPanel.add(createInputFieldPanel("X:", xField, 200, 40));
        inputPanel.add(createInputFieldPanel("Y:", yField, 200, 40));
        inputPanel.add(createInputFieldPanel("Orientation:", orientationComboBox, 200, 30));
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(createButtonPanel(placeShipButton, 200, 40));
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(createButtonPanel(placeShipButtonRandomly, 200, 40));
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(createButtonPanel(validateButton, 200, 40));
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(createButtonPanel(backButton, 200, 40));

        return inputPanel;
    }

    private JPanel createInputFieldPanel(String labelText, JComponent inputField, int width, int height) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        fieldPanel.setBackground(THEME_COLOR);

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(130, 30));
        label.setForeground(TEXT_COLOR);
        label.setFont(BUTTON_FONT);

        inputField.setPreferredSize(new Dimension(width, height));
        inputField.setMaximumSize(new Dimension(width, height));
        inputField.setFont(BUTTON_FONT);
        inputField.setForeground(TEXT_COLOR);
        inputField.setBackground(THEME_COLOR);

        fieldPanel.add(label);
        fieldPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        fieldPanel.add(inputField);

        return fieldPanel;
    }

    public void createShipsPanel() {
        shipsPanel.setBorder(BorderFactory.createTitledBorder("Flottes"));
        shipsPanel.setLayout(new BoxLayout(shipsPanel, BoxLayout.LINE_AXIS));
        shipsPanel.setBackground(THEME_COLOR);
    
        int[] shipSizes = {5, 4, 3, 3, 2};
        String[] shipImageFiles = {
            "/Images/Torpilleur.png",
            "/Images/sousMarin.png",
            "/Images/PorteAvion.png",
            "/Images/Croiseur.png",
            "/Images/contreTorpilleur.png"
        };
    
        String[] shipNames = {
            "Torpilleur",
            "SousMarin",
            "PorteAvion",
            "Croiseur",
            "ContreTorpilleur"
        };
    
        for (int i = 0; i < shipSizes.length; i++) {
            ImageIcon shipIcon = new ImageIcon(getClass().getResource(shipImageFiles[i]));
            Image shipImage = shipIcon.getImage().getScaledInstance(CELL_SIZE * 2, CELL_SIZE * shipSizes[i], Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(shipImage);
    
            JLabel shipLabel = new JLabel(resizedIcon);
    
            JPanel shipPanel = new JPanel(new BorderLayout());
            shipPanel.setBackground(THEME_COLOR);
    
            if (!(shipNames[i].equals(selectedBoat))) {
                JButton shipCountLabel = new JButton("1");
                stylizeButton(shipCountLabel);
                shipPanel.add(shipCountLabel, BorderLayout.SOUTH);
                shipCountLabel.addActionListener(new SelectBoat(this, shipPanel, shipImageFiles[i], gridPanel, player, orientationComboBox));
            } else {
                JLabel shipCountPanel = new JLabel("Place le", SwingConstants.CENTER);
                shipCountPanel.setForeground(TEXT_COLOR);
                shipCountPanel.setBackground(THEME_COLOR);
                shipCountPanel.setFont(new Font("Stencil", Font.BOLD, 16));
                shipPanel.add(shipCountPanel, BorderLayout.SOUTH);
            }
    
            shipPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            shipPanel.add(shipLabel, BorderLayout.CENTER);
            shipsPanel.add(shipPanel);
    
            shipsPanel.add(Box.createHorizontalStrut(10));
        }
    
        shipsPanel.add(Box.createHorizontalGlue()); 
    }

    private JPanel createButtonPanel(JButton button, int width, int height) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(THEME_COLOR);
        button.setPreferredSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        stylizeButton(button);
        buttonPanel.add(button);
        return buttonPanel;
    }

    private void initializeController() {
        OrientationController controllerComboBox = new OrientationController(orientationComboBox, orientation, gridPanel, player, selectedBoat, selectedSize, this);
        orientationComboBox.addActionListener(controllerComboBox);
        placeShipButtonRandomly.addActionListener(new Placement(player, gridPanel, (String) orientationComboBox.getSelectedItem(), selectedSize, selectedBoat));
        placeShipButton.addActionListener(new PlacementField(player, gridPanel, xField, yField, (String) orientationComboBox.getSelectedItem(), this));
        
        validateButton.addActionListener(e -> validateAndProceed());
        //backButton.addActionListener(e -> revertLastAction());
    }
    
    private void validateAndProceed() {
        if(player.boats.size() == 5){
            System.out.println("firstPlayer de page: "+ page.firstPlayer);
            if(page.firstPlayer){
                page.firstPlayer = false;
                page.player1 = player;
                page.showProfileMenu(page.PROFILE_MENU);
                player = new Player(page.firstNameField.getText());
                
                System.out.println("firstPlayer: "+firstPlayer);
            } else {
                System.out.println("firstPlayer: "+firstPlayer);
                NewGame game = new NewGame(page.player1, this.player);
                game.setVisible(true);

                page.setVisible(false);
                page = null;
            }
        }
    }

    public void updateShipPlacement(int shipIndex) {
        if (shipIndex >= 0 && shipIndex < shipsPlaced.length) {
            shipsPlaced[shipIndex] = true;
            shipLabels[shipIndex].setText("0");
            shipLabels[shipIndex].setEnabled(false);
        }
    }
}
