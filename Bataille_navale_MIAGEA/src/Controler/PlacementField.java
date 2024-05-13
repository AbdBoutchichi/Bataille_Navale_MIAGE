package Controler;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Modele.Boat;
import Modele.Player;
import View.GridPanel;
//import View.PlacementPage;
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

    public PlacementField(Player player, GridPanel placement, JTextField posX, JTextField posY, String orientation, PlacementPanel placementPanel){
        
        this.player = player;
        this.x = posX;
        this.y = posY;
        this.size = placementPanel.selectedSize;
        this.name = placementPanel.selectedBoat;
        this.grille = placement;
        this.page = placementPanel;
        this.orientation = orientation;
    }

    public void actionPerformed(ActionEvent e){
        
        Boat b = player.recupBoat(page.selectedBoat);
        player.removeBoat(page.selectedBoat);
        if(player.canPlace(x.getText(), y.getText(), page.selectedSize, ((String) page.orientationComboBox.getSelectedItem()).charAt(0))){
            System.out.println("les Field valent exactement " + x.getText() + " et " + y.getText());
            grille.removeAll();
            
            player.placeBateau(x.getText(), y.getText(), ((String) page.orientationComboBox.getSelectedItem()).charAt(0), page.selectedSize , page.selectedBoat);
            grille.initGridPanelPlacement(player, orientation, page.selectedSize, page.selectedBoat);

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
