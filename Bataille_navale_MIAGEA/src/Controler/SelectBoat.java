package Controler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import View.GridPanel;
import View.PlacementPanel;
import Modele.Player;

public class SelectBoat implements ActionListener{
    private JPanel panel;
    private String path;
    private PlacementPanel frame;
    private GridPanel grille;
    public String selecteur;
    private Player player;
    private JComboBox combo;

    public SelectBoat(PlacementPanel f, JPanel p, String chemin, GridPanel g, Player plr, JComboBox c){
        this.panel = p;
        this.path = chemin;
        this.frame = f;
        this.grille = g;
        this.player = plr;
        this.combo = c;
    }

    public void actionPerformed(ActionEvent e){
        
        
        switch (path) {
            case "/Images/Torpilleur.png":
                
                frame.selectedBoat = "Torpilleur";
                frame.selectedSize = 2;
                System.out.println("ici y a " + frame.selectedBoat);
                break;
            case "/Images/sousMarin.png":
                
                frame.selectedBoat = "SousMarin";
                frame.selectedSize = 3;
                System.out.println("ici y a " + frame.selectedBoat);
                break;
            case "/Images/PorteAvion.png":
                
                frame.selectedBoat = "PorteAvion";
                frame.selectedSize = 5;
                System.out.println("ici y a " + frame.selectedBoat);
                break;
            case "/Images/Croiseur.png":
                
                frame.selectedBoat = "Croiseur";
                frame.selectedSize = 4;
                
                System.out.println("ici y a " + frame.selectedBoat);
                break;
            case "/Images/contreTorpilleur.png":
                
                frame.selectedBoat = "ContreTorpilleur";
                frame.selectedSize = 3;
                System.out.println("ici y a " + frame.selectedBoat);
                break;
            default:
                break;
        }

       
        System.out.println("controler selectBoat "+ frame.selectedBoat);
        grille.removeAll();
        grille.initGridPanelPlacement(player, (String) combo.getSelectedItem(), frame.selectedSize, frame.selectedBoat);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.shipsPanel.removeAll();
        frame.createShipsPanel();
        frame.shipsPanel.revalidate();
        frame.shipsPanel.repaint();
        frame.revalidate();
        frame.repaint();

    }      
}
