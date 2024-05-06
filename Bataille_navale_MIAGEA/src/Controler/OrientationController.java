package Controler;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import View.PlacementPage;
import View.GridPanel;
import Modele.*;

public class OrientationController implements ActionListener{
    private String orientation;
    private JComboBox box;
    private GridPanel gridPanel;
    private Player player;

    public OrientationController(JComboBox orientationBox, String selectedOrientation, GridPanel grid, Player player){
        this.box = orientationBox;
        this.orientation = selectedOrientation;
        this.gridPanel = grid;
        this.player = player;
    }

    public void actionPerformed(ActionEvent e){
        gridPanel.removeAll();
        orientation = (String) box.getSelectedItem();
        gridPanel.initGridPanelPlacement(player, (String) box.getSelectedItem(), 2, "Torpilleur");
        gridPanel.revalidate();
        gridPanel.repaint();
        System.out.println(orientation);
    }

}
