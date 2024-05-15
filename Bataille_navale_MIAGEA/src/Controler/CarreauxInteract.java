package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import View.GridPanel;
import View.NewGame;
import Modele.NormalMode;
import Modele.Player;


public class CarreauxInteract implements ActionListener{
    private int x;
    private int y;
    private Player joueur;
    private Player adversaire;
    private GridPanel grille1;
    private GridPanel grille2;
    private NewGame page;

    public CarreauxInteract(int col, int row, Player plr, Player adv, GridPanel shot, GridPanel boat, NewGame p){
        x = col;
        y = row;
        joueur = plr;
        adversaire = adv;
        grille1 = shot;
        grille2 = boat;
        page = p;
    }

    public void actionPerformed(ActionEvent e){
        if(joueur.canShoot(x, y)){
            joueur.shootAt(x, y);
            if(adversaire.isTouch(x, y)){
                grille1.setBackground(null);
                grille1.removeAll();
                grille2.removeAll();
                grille2.initGridPanelInert(adversaire, joueur);
                grille1.initGridPanelShot(joueur, adversaire, grille2, page);

            } else {
                grille1.setBackground(null);
                grille1.removeAll();
                grille2.removeAll();
                grille1.initGridPanelInert(joueur, adversaire);
                grille2.initGridPanelShot(adversaire, joueur, grille1, page);

                page.setActionlistener(joueur);
            }

                grille2.revalidate();
                grille2.repaint();
                grille1.revalidate();
                grille1.repaint();

        }
    }
}
