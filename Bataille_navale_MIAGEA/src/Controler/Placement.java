package Controler;

import Modele.Player;
import Modele.Boat;

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
    private PlacementPage page;
    private boolean random;

    public Placement(Player player, GridPanel placement, int posX, int posY, int size, String name, String orientation, int dimension){
        this.x = posX;
        this.y = posY;
        this.orientation = orientation;
        this.size = size;
        this.player = player;
        this.name = name;
        this.dimension = dimension;
        this.grille = placement;
        this.random = false;
    }

    public Placement(Player player, GridPanel placement, String o, int s, String n){
        this.player = player;
        this.grille = placement;
        this.random = true;
        this.size = s;
        this.orientation = o;
        this.name = n;
    }

    public void actionPerformed(ActionEvent e){
        if(!random){
            Boat b = player.recupBoat(name);
            player.removeBoat(name);
            if(player.canPlace(player.newPos(x, y, size, orientation.charAt(0), dimension)[0], player.newPos(x, y, size, orientation.charAt(0), dimension)[1], size, orientation.charAt(0))){
                System.out.println("controler placement " + name);
                grille.removeAll();

                
                player.placeBateau(player.newPos(x, y, size, orientation.charAt(0), dimension)[0], player.newPos(x, y, size, orientation.charAt(0), dimension)[1], orientation.charAt(0), size, name);
                grille.initGridPanelPlacement(player, orientation, size, name);

                grille.repaint();
                grille.revalidate();

            } else {
                JOptionPane.showMessageDialog(null, "Placement invalide", "Placement invalide", JOptionPane.INFORMATION_MESSAGE); 
                if(b != null) player.boats.add(b);
            }
        } else {
            player.placeBoatsRand();
            grille.removeAll();
            grille.initGridPanelPlacement(player, (String) orientation, size, name);
            grille.revalidate();
            grille.repaint();
        }
    }
}
