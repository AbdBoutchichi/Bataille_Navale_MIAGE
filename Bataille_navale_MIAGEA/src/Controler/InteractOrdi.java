package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import View.GridPanel;
import View.NewGameOrdi;
import Modele.Player;
import Modele.PlayerComputer;


public class InteractOrdi implements ActionListener{
    private int x;
    private int y;
    private Player joueur;
    private PlayerComputer adversaire;
    private GridPanel grille1;
    private GridPanel grille2;
    private NewGameOrdi page;

    public InteractOrdi(int col, int row, Player plr, PlayerComputer adv, GridPanel shot, GridPanel ordi, NewGameOrdi p){
        x = col;
        y = row;
        joueur = plr;
        adversaire = adv;
        grille1 = shot;
        grille2 = ordi;
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
                grille1.initGridPanelShotOrdi(joueur, adversaire, grille2, page);
                page.checkForWin();
            } else {
                grille1.removeAll();
                grille2.removeAll();
                grille1.initGridPanelInert(adversaire, joueur);
                grille2.initGridPanelInert(adversaire, joueur);

                grille2.revalidate();
                grille2.repaint();
                grille1.revalidate();
                grille1.repaint();

                

                boolean nextPlayer = true;
                while (nextPlayer) {
                    grille1.removeAll();
                    grille2.removeAll();

                    adversaire.chooseNextMove(joueur);
                    nextPlayer = adversaire.playAgain;

                    grille1.initGridPanelInert(joueur, adversaire);
                    grille2.initGridPanelInert(adversaire, joueur);

                    

                    grille2.revalidate();
                    grille2.repaint();
                    grille1.revalidate();
                    grille1.repaint();
                    page.checkForWin();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException a){
                        a.printStackTrace();
                    }
                }

                grille1.setBackground(null);
                grille1.removeAll();
                grille2.removeAll();
                grille2.initGridPanelInert(adversaire, joueur);
                grille1.initGridPanelShotOrdi(joueur, adversaire, grille2, page);

                grille2.revalidate();
                grille2.repaint();
                grille1.revalidate();
                grille1.repaint();
                page.checkForWin();
                
            }

                grille2.revalidate();
                grille2.repaint();
                grille1.revalidate();
                grille1.repaint();
                page.checkForWin();

        }
    }
}
