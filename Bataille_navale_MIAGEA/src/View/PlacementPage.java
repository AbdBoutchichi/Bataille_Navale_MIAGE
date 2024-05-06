package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Controler.OrientationController;
import Controler.SelectBoat;
import Controler.Placement;
import Modele.Player;

public class PlacementPage extends JFrame {
    private GridPanel gridPanel;
    //private GameController gameController;
    private final static int CELL_SIZE = 30;
    private JTextField xField, yField;
    public JComboBox<String> orientationComboBox;
    private JButton placeShipButton;
    private JButton placeShipButtonRandomly;
    public JPanel shipsPanel;
    private JLabel statusLabel;
    private JLabel[] shipLabels;
    private boolean[] shipsPlaced;
    private String playerName;

    private Player player;

    public String orientation = "Horizontale";
    public int selectedSize = 2;
    public String selectedBoat = "Torpilleur";


    public PlacementPage(String playerName) {
        player = new Player(playerName);

        String orientation = "Horizontale";
        int selectedSize = 2;
        String selectedBoat = "Torpilleur";

        this.playerName = playerName;
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
        initializeController();
        setTitle("Naval Battle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
       
    }

    private void initializeComponents(ImageIcon[] shipIcons) {
        gridPanel = new GridPanel(10, player, orientation,selectedSize, selectedBoat);
        //gameController = new GameController(gridPanel);

        xField = new JTextField(5);
        yField = new JTextField(5);
        orientationComboBox = new JComboBox<>(new String[]{"Horizontal", "Vertical"});


        placeShipButton = new JButton("Place Ship");
        placeShipButton.addActionListener(e -> {
            int x = Integer.parseInt(xField.getText());
            int y = Integer.parseInt(yField.getText());
            String orientation = (String) orientationComboBox.getSelectedItem();
            //gameController.placeShip(x, y, orientation);
        });

        placeShipButtonRandomly = new JButton("Place Ship Randomly");

        for (int i = 0; i < shipIcons.length; i++) {



            shipLabels[i] = new JLabel(shipIcons[i]);
            shipLabels[i].setText("1");
            shipLabels[i].setHorizontalTextPosition(JLabel.CENTER);
            shipLabels[i].setVerticalTextPosition(JLabel.BOTTOM);
            shipsPlaced[i] = false; 
        }

        statusLabel = new JLabel("Place your ships!");
    }
    
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel trackingPanel1 = createTrackingPanel();
        shipsPanel = new JPanel();
        createShipsPanel();

        bottomPanel.add(trackingPanel1, BorderLayout.WEST);
        bottomPanel.add(this.shipsPanel, BorderLayout.CENTER);

        return bottomPanel;
    }

    public void createShipsPanel() {
        System.out.println("ship init");
        shipsPanel.setBorder(BorderFactory.createTitledBorder("Flottes"));
        shipsPanel.setLayout(new BoxLayout(shipsPanel, BoxLayout.LINE_AXIS));

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
            "sousMarin",
            "PorteAvion",
            "Croiseur",
            "contreTorpilleur"
        };

        for (int i = 0; i < shipSizes.length; i++) {
            
        
            ImageIcon shipIcon = new ImageIcon(getClass().getResource(shipImageFiles[i]));
            Image shipImage = shipIcon.getImage().getScaledInstance(CELL_SIZE, CELL_SIZE * shipSizes[i], Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(shipImage);

            JLabel shipLabel = new JLabel(resizedIcon);

            

            JPanel shipPanel = new JPanel(new BorderLayout());

            if(!(shipNames[i] == selectedBoat)){ 
            JButton shipCountLabel = new JButton("1");
            shipPanel.add(shipCountLabel, BorderLayout.SOUTH);
            shipCountLabel.addActionListener(new SelectBoat(this, shipPanel, shipImageFiles[i], gridPanel, player, orientationComboBox));
            }
            else {
            JPanel shipCountPanel = new JPanel();
            shipPanel.add(shipCountPanel, BorderLayout.SOUTH);
            }
            
            shipPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            shipPanel.add(shipLabel, BorderLayout.CENTER);
            System.out.println("ship size init");
            shipsPanel.add(shipPanel);
        }

        shipsPanel.add(Box.createHorizontalGlue());

        
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


    private void layoutComponents() {

        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Place Your Ships, Player: " + playerName));
    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        inputPanel.add(createInputFieldPanel("X:", xField));
        inputPanel.add(createInputFieldPanel("Y:", yField));
        inputPanel.add(createInputFieldPanel("Orientation:", orientationComboBox));
        
        placeShipButton.setMaximumSize(new Dimension(200, 50));
        placeShipButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeShipButtonRandomly.setMaximumSize(new Dimension(200, 50));
        placeShipButtonRandomly.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(placeShipButton);
        inputPanel.add(placeShipButtonRandomly);
    
        JPanel bottomPanel = createBottomPanel();
    
        getContentPane().setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.WEST);
        add(inputPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);
    }
    
    private JPanel createInputFieldPanel(String labelText, JComponent inputField) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(100, 20));
        fieldPanel.add(label);
        
        inputField.setMaximumSize(new Dimension(200, 20));
        fieldPanel.add(inputField);
        
        return fieldPanel;
    }
    
    

    

    private void initializeController() {
        OrientationController controllerComboBox = new OrientationController(orientationComboBox, orientation, gridPanel, player, selectedBoat, selectedSize, this);
        orientationComboBox.addActionListener(controllerComboBox);
        placeShipButtonRandomly.addActionListener(new Placement(player, gridPanel, (String) orientationComboBox.getSelectedItem(), selectedSize, selectedBoat));
    }

    

    public void updateShipPlacement(int shipIndex) {
        if (shipIndex >= 0 && shipIndex < shipsPlaced.length) {
            shipsPlaced[shipIndex] = true;
            shipLabels[shipIndex].setText("0");
            shipLabels[shipIndex].setEnabled(false);

        }
    }

    public void update(){
        ImageIcon[] shipIcons = {
            new ImageIcon("Images/Torpilleur.png"),
            new ImageIcon("Images/sousMarin.png"),
            new ImageIcon("Images/PorteAvion.png"),
            new ImageIcon("Images/Croiseur.png"),
            new ImageIcon("Images/contreTorpilleur.png")
        };

        initializeComponents(shipIcons);
        layoutComponents();
        initializeController();
        setTitle("Naval Battle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String playerName = JOptionPane.showInputDialog("Enter Player Name:");
            PlacementPage game = new PlacementPage(playerName);
            game.setVisible(true);
        });
    }
}
