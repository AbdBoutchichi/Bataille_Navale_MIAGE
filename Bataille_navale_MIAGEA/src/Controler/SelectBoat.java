package Controler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SelectBoat implements ActionListener{
    private JPanel panel;
    private String path;
    private JFrame frame;
    private String selecteur;

    public SelectBoat(JFrame f, JPanel p, String chemin, String s){
        this.panel = p;
        this.path = chemin;
        this.frame = f;
        this.selecteur = s;
    }

    public void actionPerformed(ActionEvent e){
        frame.remove(panel);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        switch (path) {
            case "/Images/Torpilleur.png":
                
                selecteur = "Torpilleur";

            case "/Images/sousMarin.png":
                
                selecteur = "SousMarin";
            case "/Images/PorteAvion.png":
                
                selecteur = "PorteAvion";
            case "/Images/Croiseur.png":
                
                selecteur = "Croiseur";
            case "/Images/contreTorpilleur.png":
                
                selecteur = "ContreTorpilleur";
            default:
                break;
        }
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
}
