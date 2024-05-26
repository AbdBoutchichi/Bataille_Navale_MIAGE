package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import View.GridPanel;
import View.NewGameRadar;
import Modele.Player;


public class InteractRadar implements ActionListener{
    private int x;
    private int y;
    private Player joueur;
    private Player adversaire;
    private GridPanel grille1;
    private GridPanel grille2;
    private NewGameRadar page;

    public InteractRadar(int col, int row, Player plr, Player adv, GridPanel shot, GridPanel boat, NewGameRadar p){
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
                grille1.initGridPanelRadar(joueur, adversaire, grille2, page);

            } else {
                grille1.setBackground(null);
                grille1.removeAll();
                grille2.removeAll();
                grille1.initGridPanelInert(joueur, adversaire);
                grille2.initGridPanelRadar(adversaire, joueur, grille1, page);

                page.setActionlistener(joueur);
            }

                grille2.revalidate();
                grille2.repaint();
                grille1.revalidate();
                grille1.repaint();
                page.checkForWin();
        }
    }
}
