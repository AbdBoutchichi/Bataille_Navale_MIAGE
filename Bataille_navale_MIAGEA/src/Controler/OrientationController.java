package Controler;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
    private PlacementPanel frame;

    //Constructeur

    public OrientationController(JComboBox orientationBox, String selectedOrientation, GridPanel grid, Player player, String boat, int size, PlacementPanel f){
        this.box = orientationBox;
        this.orientation = selectedOrientation;
        this.gridPanel = grid;
        this.player = player;
        this.selectedBoat = boat;
        this.selectedSize = size;
        this.frame = f;
    }

    public void actionPerformed(ActionEvent e){
        gridPanel.removeAll();
        orientation = (String) box.getSelectedItem();
        gridPanel.initGridPanelPlacement(player, (String) box.getSelectedItem(), frame.selectedSize, frame.selectedBoat);
        gridPanel.revalidate();
        gridPanel.repaint();
        System.out.println(orientation);
    }

}
