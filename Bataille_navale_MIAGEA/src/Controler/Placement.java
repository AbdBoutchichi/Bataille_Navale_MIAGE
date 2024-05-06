package Controler;

import Modele.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import View.*;



public class Placement implements ActionListener{

    private int x;
    private int y;
    private String orientation;
    private Player player;
    private int size;
    private String name;
    private int dimension;
    private GridPanel grille;
    PlacementPage page;

    public Placement(Player player, GridPanel placement, int posX, int posY, int size, String name, String orientation, int dimension){
        this.x = posX;
        this.y = posY;
        this.orientation = orientation;
        this.size = size;
        this.player = player;
        this.name = name;
        this.dimension = dimension;
        this.grille = placement;
        
    }

    public void actionPerformed(ActionEvent e){

        if(player.canPlace(player.newPos(x, y, size, orientation.charAt(0), dimension)[0], player.newPos(x, y, size, orientation.charAt(0), dimension)[1], size, orientation.charAt(0))){
            grille.removeAll();
            
            player.placeBateau(player.newPos(x, y, size, orientation.charAt(0), dimension)[0], player.newPos(x, y, size, orientation.charAt(0), dimension)[1], orientation.charAt(0), size, name);
            grille.initGridPanelPlacement(player, orientation, 2, "Torpilleur");
            
            grille.repaint();
            grille.revalidate();
            
        } else 
            JOptionPane.showMessageDialog(null, "Placement invalide", "Placement invalide", JOptionPane.INFORMATION_MESSAGE); 
    }
}
