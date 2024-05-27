package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import View.EndGamePanel;
import View.GridPanel;
import View.NewGameOrdi;
import View.NewGameMenu;
import Modele.NormalMode;
import Modele.Player;
import Modele.PlayerComputer;


public class ShootFieldOrdi implements ActionListener{
    private String x;
    private String y;
    private Player joueur;
    private PlayerComputer adversaire;
    private GridPanel grille1;
    private GridPanel grille2;
    private NewGameOrdi page;
    private boolean firstPlayer;
    //private NewGameMenu masterPage;

    public ShootFieldOrdi(NewGameOrdi game, Player plr, PlayerComputer adv, GridPanel shot, GridPanel boat, boolean b){
        firstPlayer = b;
        joueur = plr;
        adversaire = adv;
        grille1 = shot;
        grille2 = boat;
        page = game;
    }

    public void actionPerformed(ActionEvent e){
        
        x = page.xFieldPlayer1.getText();
        y = page.yFieldPlayer1.getText();
        

        System.out.println("val: " + Integer.parseInt(x) + " ; " + Integer.parseInt(y));

        if(Integer.parseInt(x) > 0 || Integer.parseInt(x) < 9 || Integer.parseInt(y) > 0 || Integer.parseInt(y) < 9){
            System.out.println("val: " + Integer.parseInt(x) + " ; " + Integer.parseInt(y));
            if(joueur.canShoot(Integer.parseInt(x), Integer.parseInt(y))){

                joueur.shootAt(Integer.parseInt(x), Integer.parseInt(y), adversaire);
                if(adversaire.isTouch(Integer.parseInt(x), Integer.parseInt(y))){
                    grille1.setBackground(null);
                    grille1.removeAll();
                    grille2.removeAll();
                    grille2.initGridPanelInert(adversaire, joueur);
                    grille1.initGridPanelShotOrdi(joueur, adversaire, grille2, page);

                    
                
                
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

                page.updateShipPanel();
                page.revalidate();
                page.repaint();
                grille2.revalidate();
                grille2.repaint();
                grille1.revalidate();
                grille1.repaint();

                page.checkForWin();
            } 
            else{
                JOptionPane.showMessageDialog(null, "Vous avez déja tiré ici. Essayer ailleur", "Valeur déjà tiré", JOptionPane.INFORMATION_MESSAGE);}

        } else {
            JOptionPane.showMessageDialog(null, "Valeur invalide", "Valeur invalide", JOptionPane.INFORMATION_MESSAGE); }
    }
}

