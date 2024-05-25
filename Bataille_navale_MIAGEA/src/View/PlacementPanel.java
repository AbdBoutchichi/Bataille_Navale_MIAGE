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
import javax.swing.JOptionPane;
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

    private Color themeColor;
    private Color textColor;
    private Color buttonColor;
    private Font buttonFont;

    public String orientation = "Horizontale";
    public int selectedSize = 2;
    public String selectedBoat = "Torpilleur";

    private boolean firstPlayer;
    private NewGameMenu page;

    public PlacementPanel(String playerName, NewGameMenu page, ImageIcon avatar, GameMode mode) {
        this.playerName = playerName;
        this.page = page;
        this.avatarIcon = avatar;
        player = new Player(playerName);
        if (page == null) {
            throw new IllegalArgumentException("Page parameter cannot be null");
        }

        // Set theme based on game mode
        setTheme(mode);

        ImageIcon[] shipIcons = {
            new ImageIcon(getClass().getResource("/Images/Torpilleur.png")),
            new ImageIcon(getClass().getResource("/Images/sousMarin.png")),
            new ImageIcon(getClass().getResource("/Images/PorteAvion.png")),
            new ImageIcon(getClass().getResource("/Images/Croiseur.png")),
            new ImageIcon(getClass().getResource("/Images/contreTorpilleur.png"))
        };
        this.shipLabels = new JLabel[shipIcons.length];
        this.shipsPlaced = new boolean[shipIcons.length];
        initializeComponents(shipIcons);
        layoutComponents();
        initializeController();
    }

    private void setTheme(GameMode mode) {
        switch (mode) {
            case NORMAL:
                themeColor = new Color(249, 246, 233);
                textColor = new Color(123, 85, 74);
                buttonFont = new Font("Stencil", Font.BOLD, 13);
                buttonColor = new Color(199, 153, 119);
                break;
            case RADAR:
                themeColor = Color.BLACK;
                textColor = Color.GREEN;
                buttonFont = new Font("Monospaced", Font.BOLD, 13);
                buttonColor = Color.GRAY;
                break;
            case ARTILLERY:
                themeColor = new Color(10, 25, 48);
                textColor = Color.WHITE;
                buttonFont = new Font("Stencil", Font.BOLD, 12);
                buttonColor = new Color(30, 144, 255);
                break;
        }
    }

    private void initializeComponents(ImageIcon[] shipIcons) {
        gridPanel = new GridPanel(10, player, "Horizontale", 2, "Torpilleur");
        xField = new JTextField("0", 5);
        yField = new JTextField("0", 5);
        orientationComboBox = new JComboBox<>(new String[]{"Horizontal", "Vertical"});

        xField.setFont(buttonFont);
        yField.setFont(buttonFont);
        xField.setForeground(textColor);
        yField.setForeground(textColor);
        xField.setBackground(themeColor);
        yField.setBackground(themeColor);

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
        statusLabel.setForeground(textColor);
        statusLabel.setFont(new Font("Stencil", Font.BOLD, 15));
    }

    private void stylizeButton(JButton button) {
        button.setFont(buttonFont);
        button.setBackground(buttonColor);
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
        JPanel trackingPanel = new JPanel(new BorderLayout());
        trackingPanel.setBorder(BorderFactory.createTitledBorder(" "));
        trackingPanel.setBackground(themeColor);
        ImageIcon gifIcon = new ImageIcon(getClass().getResource("/Images/décorSHip.png"));
        Image gifImage = gifIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        gifIcon = new ImageIcon(gifImage);

        JLabel gifLabel = new JLabel(gifIcon);
        gifLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gifLabel.setVerticalAlignment(SwingConstants.CENTER);

        trackingPanel.add(gifLabel, BorderLayout.CENTER);

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
        JPanel titlePanel = new JPanel(new GridBagLayout()); 
        titlePanel.setBackground(themeColor);
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  
    
        avatarLabel = new JLabel();
        if (avatarIcon != null) {
            avatarLabel.setIcon(new ImageIcon(avatarIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))); 
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        titlePanel.add(avatarLabel, gbc);
    
        JLabel titleLabel = new JLabel("Place Your Ships, Player: " + playerName);
        titleLabel.setForeground(textColor);
        titleLabel.setFont(new Font("Stencil", Font.BOLD, 20));
        gbc.gridx = 1; 
        gbc.anchor = GridBagConstraints.WEST; 
        titlePanel.add(titleLabel, gbc);
    
        return titlePanel;
    }
    
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(themeColor);

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
        fieldPanel.setBackground(themeColor);

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(130, 30));
        label.setForeground(textColor);
        label.setFont(buttonFont);

        inputField.setPreferredSize(new Dimension(width, height));
        inputField.setMaximumSize(new Dimension(width, height));
        inputField.setFont(buttonFont);
        inputField.setForeground(textColor);
        inputField.setBackground(themeColor);

        fieldPanel.add(label);
        fieldPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        fieldPanel.add(inputField);

        return fieldPanel;
    }

    public void createShipsPanel() {
        shipsPanel.setBorder(BorderFactory.createTitledBorder("Flottes"));
        shipsPanel.setLayout(new BoxLayout(shipsPanel, BoxLayout.LINE_AXIS));
        shipsPanel.setBackground(themeColor);
    
        int[] shipSizes = {5, 4, 3, 3, 2};
        String[] shipImageFiles = {
            "/Images/PorteAvion.png",
            "/Images/sousMarin.png",
            "/Images/Torpilleur.png",
            "/Images/Croiseur.png",
            "/Images/contreTorpilleur.png"
        };
    
        String[] shipNames = {
            "PorteAvion",
            "SousMarin",
            "Torpilleur",
            "Croiseur",
            "ContreTorpilleur"
        };
    
        for (int i = 0; i < shipSizes.length; i++) {
            ImageIcon shipIcon = new ImageIcon(getClass().getResource(shipImageFiles[i]));
            Image shipImage = shipIcon.getImage().getScaledInstance(CELL_SIZE * 2, CELL_SIZE * shipSizes[i], Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(shipImage);
    
            JLabel shipLabel = new JLabel(resizedIcon);
    
            JPanel shipPanel = new JPanel(new BorderLayout());
            shipPanel.setBackground(themeColor);
    
            if (!(shipNames[i].equals(selectedBoat))) {
                JButton shipCountLabel = new JButton("EN ETAT");
                stylizeButton(shipCountLabel);
                shipPanel.add(shipCountLabel, BorderLayout.SOUTH);
                shipCountLabel.addActionListener(new SelectBoat(this, shipPanel, shipImageFiles[i], gridPanel, player, orientationComboBox));
            } else {
                JLabel shipCountPanel = new JLabel("Place le", SwingConstants.CENTER);
                shipCountPanel.setForeground(textColor);
                shipCountPanel.setBackground(themeColor);
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
        buttonPanel.setBackground(themeColor);
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
        if (page == null) {
            System.err.println("Error: Page reference is null");
            return;
        }
        if (player.boats.size() != 5) {
            JOptionPane.showMessageDialog(this, "Vous devez placer tous les bateaux avant de continuer.", "Placement incomplet", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (page.singlePlayerMode) {
            NewGameOrdi gameOrdi = new NewGameOrdi(player, page.selectedDifficulty);
            gameOrdi.setVisible(true);
            page.setVisible(false);
        } else {
            if (page.firstPlayer) {
                page.firstPlayer = false;
                page.player1 = player;
                page.showProfileMenu(page.PROFILE_MENU);
                player = new Player("");
            } else {
                if (page.selectedGameMode == GameMode.RADAR) {
                    NewGameRadar gameRadar = new NewGameRadar(page.player1, this.player);
                    gameRadar.setVisible(true);
                } else if (page.selectedGameMode == GameMode.ARTILLERY) {
                    NewGameArtillerie gameArtillerie = new NewGameArtillerie(page.player1, this.player);
                    gameArtillerie.setVisible(true);
                } else {
                    NewGame game = new NewGame(page.player1, this.player);
                    game.setVisible(true);
                }
                page.setVisible(false);
            }
        }
    }
    

    public void updateShipPlacement(int shipIndex, String shipName) {
        if (shipIndex >= 0 && shipIndex < shipsPlaced.length) {
            shipsPlaced[shipIndex] = true;
            shipLabels[shipIndex].setText("0");
            shipLabels[shipIndex].setEnabled(false);
            String message = shipName + " has been placed.";
            ((JTextArea) ((JScrollPane) ((JPanel) getComponent(0)).getComponent(0)).getViewport().getView()).append(message + "\n");
        }

        // Check if all ships are placed
        boolean allPlaced = true;
        for (boolean placed : shipsPlaced) {
            if (!placed) {
                allPlaced = false;
                break;
            }
        }

        if (allPlaced) {
            String message = "All ships have been placed. Click Validate to proceed.";
            ((JTextArea) ((JScrollPane) ((JPanel) getComponent(0)).getComponent(0)).getViewport().getView()).append(message + "\n");
        }
    }
}
