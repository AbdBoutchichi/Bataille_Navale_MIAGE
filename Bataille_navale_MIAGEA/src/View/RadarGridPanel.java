package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import Modele.Player;
import Controler.CarreauxInteract;
//import Controler.CarreauxInteractRadar;
import Controler.Placement;
//import Controler.PlacementRadar;

public class RadarGridPanel extends JPanel {

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int CELL_SIZE = 45;

    public RadarGridPanel(int dim, Player jr, Player adv, JFrame frame) {
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setLayout(new GridLayout(ROWS, COLS));
        setBackground(Color.BLACK);
        setOpaque(true);

        initRadarGridPanelBoat(jr, adv);
    }

    public RadarGridPanel(int dim, Player jr, Player adv, RadarGridPanel grilleBoat, JFrame frame) {
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setLayout(new GridLayout(ROWS, COLS));
        setBackground(Color.BLACK);
        setOpaque(true);

        initRadarGridPanelShot(jr, adv, grilleBoat);
    }

    public RadarGridPanel(int dim, Player jr, String orientation, int size, String name) {
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setLayout(new GridLayout(ROWS, COLS));
        setBackground(Color.BLACK);
        setOpaque(true);

        initRadarGridPanelPlacement(jr, orientation, size, name);
    }

    // Generates a button grid for boat placement
    public void initRadarGridPanelBoat(Player jr, Player adv) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JButton cell = new JButton();
                cell.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 1)); // Green border for each cell
                cell.setOpaque(false);
                cell.setContentAreaFilled(false);
                add(cell);
            }
        }
    }

    // Generates a button grid for shooting interaction
    public void initRadarGridPanelShot(Player jr, Player adv, RadarGridPanel boat) {
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                JButton cell = new JButton();
                cell.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 1));
                cell.setOpaque(false);
                cell.setContentAreaFilled(false);
                //cell.addActionListener(new CarreauxInteract(col, row, jr, adv, this, boat));
                add(cell);
            }
        }
    }

    // Initializes a button grid for placing boats with specific orientation and size
    public void initRadarGridPanelPlacement(Player jr, String orientation, int size, String name) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JButton cell = new JButton();
                cell.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 1));
                cell.setOpaque(false);
                cell.setContentAreaFilled(false);
                //cell.addActionListener((ActionListener) new Placement(jr, this, col, row, size, name, orientation, 10));
                add(cell);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Since the background is set to black and each button is already styled, there is no need to draw a background image here.
    }

    public static void main(String[] arg) {
        JFrame frame = new JFrame();
        frame.setSize(2000, 1000);

        Player jr = new Player("Bob");
        Player adv = new Player("Bill");

        jr.placeBoatsRand();
        adv.placeBoatsRand();

        RadarGridPanel grille1 = new RadarGridPanel(10, jr, adv, frame);
        RadarGridPanel grille2 = new RadarGridPanel(10, jr, adv, grille1, frame);
        
        frame.add(grille1, BorderLayout.WEST);
        frame.add(grille2, BorderLayout.EAST);

        frame.pack();
        frame.setVisible(true);
    }
}
