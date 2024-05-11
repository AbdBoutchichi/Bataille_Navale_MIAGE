package Controler;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//import View.PlacementPage;
import View.PlacementPanel;
import View.GridPanel;
import Modele.*;

public class OrientationController implements ActionListener{
    private String orientation;
    private JComboBox box;
    private GridPanel gridPanel;
    private Player player;
    private String selectedBoat;
    private int selectedSize;
    //private PlacementPage frame;
    private PlacementPanel placementPanel;

    //Constructeur

    public OrientationController(JComboBox orientationBox, String selectedOrientation, GridPanel grid, Player player, String boat, int size, PlacementPanel placementPanel){
        this.box = orientationBox;
        this.orientation = selectedOrientation;
        this.gridPanel = grid;
        this.player = player;
        this.selectedBoat = boat;
        this.selectedSize = size;
        this.placementPanel = placementPanel;
    }

    public void actionPerformed(ActionEvent e){
        gridPanel.removeAll();
        orientation = (String) box.getSelectedItem();
        gridPanel.initGridPanelPlacement(player, (String) box.getSelectedItem(), placementPanel.selectedSize, placementPanel.selectedBoat);
        gridPanel.revalidate();
        gridPanel.repaint();
        System.out.println(orientation);
    }

}
