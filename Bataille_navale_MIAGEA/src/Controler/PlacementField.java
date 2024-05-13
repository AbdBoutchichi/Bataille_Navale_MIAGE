package Controler;


import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Modele.Boat;
import Modele.Player;
import View.GridPanel;
import View.PlacementPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlacementField  implements ActionListener{
    private JTextField x;
    private JTextField y;
    private String orientation;
    private Player player;
    private int size;
    private String name;
    private int dimension;
    private GridPanel grille;
    private PlacementPanel page;
    private boolean random;

    public PlacementField(Player player, GridPanel placement, JTextField posX, JTextField posY, String orientation, PlacementPanel p){
        
        this.player = player;
        this.x = posX;
        this.y = posY;
        this.size = p.selectedSize;
        this.name = p.selectedBoat;
        this.grille = placement;
        this.page = p;
        this.orientation = orientation;
    }

    public void actionPerformed(ActionEvent e){
        
        Boat b = player.recupBoat(name);
        player.removeBoat(name);
        if(player.canPlace(x.getText(), y.getText(), size, orientation.charAt(0))){
            System.out.println("les Field valent exactement " + x.getText() + " et " + y.getText());
            grille.removeAll();
            
            player.placeBateau(x.getText(), y.getText(), orientation.charAt(0), size, name);
            grille.initGridPanelPlacement(player, orientation, size, name);

            y.repaint();
            y.revalidate();
            x.repaint();
            x.revalidate();
            
            grille.repaint();
            grille.revalidate();
        } else {
            JOptionPane.showMessageDialog(null, "Placement invalide", "Placement invalide", JOptionPane.INFORMATION_MESSAGE); 
            if(b != null) player.boats.add(b);
        }
    }
}
