package Controler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import View.GridPanel;
import View.PlacementPanel;
import View.RadarPlacementPanel;
import Modele.Player;

public class SelectBoat implements ActionListener{
    private JPanel panel;
    private String path1;
    private String path2;
    private PlacementPanel frame1;
    private RadarPlacementPanel frame2;
    private GridPanel grille;
    private String selecteur;
    private Player player;
    private JComboBox combo;

    public SelectBoat(PlacementPanel f, JPanel p, String chemin, GridPanel g, Player plr, JComboBox c){
        this.panel = p;
        this.path1 = chemin;
        this.frame1 = f;
        this.grille = g;
        this.player = plr;
        this.combo = c;
    }
    public SelectBoat(RadarPlacementPanel R, JPanel p, String chemin, GridPanel g, Player plr, JComboBox c){
        this.panel = p;
        this.path2 = chemin;
        this.frame2 = R;
        this.grille = g;
        this.player = plr;
        this.combo = c;
    }

    public void actionPerformed(ActionEvent e){
        
        
        switch (path1) {
            case "/Images/Torpilleur.png":
                
                frame1.selectedBoat = "Torpilleur";
                frame1.selectedSize = 2;
                System.out.println("ici y a " + frame1.selectedBoat);
                break;
            case "/Images/sousMarin.png":
                
                frame1.selectedBoat = "SousMarin";
                frame1.selectedSize = 3;
                System.out.println("ici y a " + frame1.selectedBoat);
                break;
            case "/Images/PorteAvion.png":
                
                frame1.selectedBoat = "PorteAvion";
                frame1.selectedSize = 5;
                System.out.println("ici y a " + frame1.selectedBoat);
                break;
            case "/Images/Croiseur.png":
                
                frame1.selectedBoat = "Croiseur";
                frame1.selectedSize = 4;
                
                System.out.println("ici y a " + frame1.selectedBoat);
                break;
            case "/Images/contreTorpilleur.png":
                
                frame1.selectedBoat = "ContreTorpilleur";
                frame1.selectedSize = 3;
                System.out.println("ici y a " + frame1.selectedBoat);
                break;
            default:
                break;
        }

        switch (path2) {
            case "/Images/Torpilleur.png":
                
                frame1.selectedBoat = "Torpilleur";
                frame1.selectedSize = 2;
                System.out.println("ici y a " + frame1.selectedBoat);
                break;
            case "/Images/sousMarin.png":
                
                frame1.selectedBoat = "SousMarin";
                frame1.selectedSize = 3;
                System.out.println("ici y a " + frame1.selectedBoat);
                break;
            case "/Images/PorteAvion.png":
                
                frame1.selectedBoat = "PorteAvion";
                frame1.selectedSize = 5;
                System.out.println("ici y a " + frame1.selectedBoat);
                break;
            case "/Images/Croiseur.png":
                
                frame1.selectedBoat = "Croiseur";
                frame1.selectedSize = 4;
                
                System.out.println("ici y a " + frame1.selectedBoat);
                break;
            case "/Images/contreTorpilleur.png":
                
                frame1.selectedBoat = "ContreTorpilleur";
                frame1.selectedSize = 3;
                System.out.println("ici y a " + frame1.selectedBoat);
                break;
            default:
                break;
        }

        System.out.println("controler selectBoat "+ frame1.selectedBoat);
        grille.removeAll();
        grille.initGridPanelPlacement(player, (String) combo.getSelectedItem(), frame1.selectedSize, frame1.selectedBoat);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame1.shipsPanel.removeAll();
        frame1.createShipsPanel();
        frame1.shipsPanel.revalidate();
        frame1.shipsPanel.repaint();
        frame1.revalidate();
        frame1.repaint();

        System.out.println("controler selectBoat "+ frame1.selectedBoat);
        grille.removeAll();
        grille.initGridPanelPlacement(player, (String) combo.getSelectedItem(), frame2.selectedSize, frame2.selectedBoat);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame2.shipsPanel.removeAll();
        frame2.createShipsPanel();
        frame2.shipsPanel.revalidate();
        frame2.shipsPanel.repaint();
        frame2.revalidate();
        frame2.repaint();
    }
}
