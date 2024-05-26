package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import View.GridPanel;
import View.NewGame;
import View.EndGamePanel; // Assurez-vous d'avoir un panneau pour la fin du jeu
import Modele.Player;

public class ShootField implements ActionListener{
    private String x;
    private String y;
    private Player joueur;
    private Player adversaire;
    private GridPanel grille1;
    private GridPanel grille2;
    private NewGame page;
    private boolean firstPlayer;

    public ShootField(NewGame game, Player plr, Player adv, GridPanel shot, GridPanel boat, boolean isFirstPlayer){
        this.firstPlayer = isFirstPlayer;
        this.joueur = plr;
        this.adversaire = adv;
        this.grille1 = shot;
        this.grille2 = boat;
        this.page = game;
    }

    public void actionPerformed(ActionEvent e){
        try {
            x = firstPlayer ? page.xFieldPlayer1.getText() : page.xFieldPlayer2.getText();
            y = firstPlayer ? page.yFieldPlayer1.getText() : page.yFieldPlayer2.getText();

            int posX = Integer.parseInt(x);
            int posY = Integer.parseInt(y);

            if (posX >= 0 && posX <= 9 && posY >= 0 && posY <= 9) {
                if(joueur.canShoot(posX, posY)){
                    joueur.shootAt(posX, posY);
                    boolean hit = adversaire.isTouch(posX, posY);
                    updateGameUI(hit);

                    if (!adversaire.isAlive()) {
                        endGame(joueur, adversaire);
                    } else {
                        page.setActionlistener(adversaire);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vous avez déjà tiré ici. Essayer ailleurs", "Valeur déjà tirée", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Valeur invalide", "Valeur invalide", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer des nombres valides", "Erreur de Format", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateGameUI(boolean hit) {
        grille1.setBackground(null);
        grille1.removeAll();
        grille2.removeAll();
        grille2.initGridPanelInert(adversaire, joueur);
        grille1.initGridPanelShot(joueur, adversaire, grille2, page);
        grille1.revalidate();
        grille1.repaint();
        grille2.revalidate();
        grille2.repaint();
    }

    private void endGame(Player winner, Player loser) {
        JFrame endGameFrame = new EndGamePanel(null,null,0, null, null);
        endGameFrame.setVisible(true);
        page.setVisible(false);  // Cache la fenêtre du jeu
    }
}
