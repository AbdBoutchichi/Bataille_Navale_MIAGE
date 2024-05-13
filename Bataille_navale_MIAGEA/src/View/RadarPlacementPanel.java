package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

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
//import Controler.OrientationControllerRadar;
import Controler.Placement;
import Controler.PlacementField;
//import Controler.PlacementFieldRadar;
//import Controler.PlacementRadar;
import Controler.SelectBoat;
//import Controler.selectBoatRadar;
import Modele.Player;

public class RadarPlacementPanel extends JPanel {
    private RadarGridPanel gridPanel;
    private JTextField xField, yField;
    public JComboBox<String> orientationComboBox;
    private JButton placeShipButton, placeShipButtonRandomly, validateButton, backButton;
    public JPanel shipsPanel;
    private JLabel statusLabel;
    private JLabel[] shipLabels;
    private boolean[] shipsPlaced;
    private String playerName;
    private final static int CELL_SIZE = 30;

    private Player player;
    public int selectedSize;
    public String selectedBoat;
    public String orientation = "Horizontale";

    private static final Color THEME_COLOR = new Color(0, 100, 0); // Dark green typical of radar screens
    private static final Color TEXT_COLOR = new Color(255, 255, 255); // White text for better readability
    private static final Font BUTTON_FONT = new Font("Monospaced", Font.BOLD, 12); // Monospaced font for a digital look
    

    public RadarPlacementPanel(String playerName) {
        this.playerName = playerName;
        player = new Player(playerName);
        ImageIcon[] shipIcons = {
            new ImageIcon("Images/Torpilleur.png"),
            new ImageIcon("Images/sousMarin.png"),
            new ImageIcon("Images/PorteAvion.png"),
            new ImageIcon("Images/Croiseur.png"),
            new ImageIcon("Images/contreTorpilleur.png")
        };
        this.shipLabels = new JLabel[shipIcons.length];
        this.shipsPlaced = new boolean[shipIcons.length];
        initializeComponents(shipIcons);
        layoutComponents();
        //initializeControllerRadar();
    }

    private void initializeComponents(ImageIcon[] shipIcons) {
        gridPanel = new RadarGridPanel(10, player, "Horizontal", 2, "Torpilleur"); // Adjust parameters as needed
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
        placeShipButtonRandomly = new JButton("Random Placement");
        validateButton = new JButton("Confirm");
        backButton = new JButton("Return");

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

        statusLabel = new JLabel("Radar Deployment");
        statusLabel.setForeground(TEXT_COLOR);
        statusLabel.setFont(new Font("Monospaced", Font.BOLD, 15));
    }

    private void stylizeButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(new Color(0, 128, 0)); // Slightly brighter green for buttons
        button.setForeground(TEXT_COLOR);
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
        trackingPanel.setBorder(BorderFactory.createTitledBorder("Radar Monitoring"));
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
    private JPanel createButtonPanel(JButton button, int width, int height) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(THEME_COLOR);
        button.setPreferredSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        stylizeButton(button);
        buttonPanel.add(button);
        return buttonPanel;
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
                //shipCountLabel.addActionListener((ActionListener) new selectBoat(this, shipPanel, shipImageFiles[i], gridPanel, player, orientationComboBox));
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

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(createTitlePanel(), BorderLayout.NORTH);
        add(createInputPanel(), BorderLayout.WEST);
        add(gridPanel, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        setPreferredSize(new Dimension(800, 650));
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(THEME_COLOR);
        JLabel titleLabel = new JLabel("Deploy Radar, Player: " + playerName);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        return titlePanel;
    }

    /*private void initializeControllerRadar() {
        
    }*/

    public void updateShipPlacement(int shipIndex) {
        if (shipIndex >= 0 && shipIndex < shipsPlaced.length) {
            shipsPlaced[shipIndex] = true;
            shipLabels[shipIndex].setText("0");
            shipLabels[shipIndex].setEnabled(false);
        }
}
}