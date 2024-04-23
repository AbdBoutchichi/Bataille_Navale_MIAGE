package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import View.Grille;

import Modele.NormalMode;
import Modele.Player;


public class CarreauxInteract implements ActionListener{
    private int x;
    private int y;
    private Player joueur;
    private Player adversaire;
    private Grille grille1;
    private Grille grille2;

    public CarreauxInteract(int col, int row, Player plr, Player adv, Grille shot, Grille boat){
        x = col;
        y = row;
        joueur = plr;
        adversaire = adv;
        grille1 = shot;
        grille2 = boat;
    }

    public void actionPerformed(ActionEvent e){
        if(joueur.canShoot(x, y)){
            joueur.shootAt(x, y);
            if(adversaire.isTouch(x, y)){
                grille1.removeAll();
                grille2.removeAll();
                grille2.initGrilleBoat(joueur, adversaire);
                grille1.initGrilleShot(joueur, adversaire, grille2);
                
                grille1.revalidate();
                grille1.repaint();
            } else {
                grille1.removeAll();
                grille2.removeAll();
                grille2.initGrilleBoat(adversaire, joueur);
                grille1.initGrilleShot(adversaire, joueur, grille2);
                
                grille1.revalidate();
                grille1.repaint();
            }

        }
    }
}
