package Controler;

import View.EndGamePanel;
import View.NewGame;
import View.NewGameOrdi;
import Modele.Player;
import java.lang.System;

public class EndGameController {
    private Player player1;
    private Player player2;
    private NewGame gameView;
    private NewGameOrdi gameOrdiView;
    private boolean isVsComputer;

    public EndGameController(Player p1, Player p2, NewGame game, boolean isVsComp) {
        this.player1 = p1;
        this.player2 = p2;
        this.gameView = game;
        this.isVsComputer = isVsComp;
    }

    public EndGameController(Player p1, Player p2, NewGameOrdi gameOrdi) {
        this.player1 = p1;
        this.player2 = p2;
        this.gameOrdiView = gameOrdi;
        this.isVsComputer = true;
    }

    public void checkGameStatus() {
        System.out.println("Checking game status...");
        System.out.println("Player 1 Alive: " + player1.isAlive());
        System.out.println("Player 2 Alive: " + player2.isAlive());
        if (!player1.isAlive() || !player2.isAlive()) {
            System.out.println("Ending game...");
            endGame();
        }
    }
    
    public void endGame() {
        Player winner = player1.isAlive() ? player1 : player2;
        Player loser = player1.isAlive() ? player2 : player1;

        int duration = isVsComputer ? gameOrdiView.getElapsedTime() : gameView.getElapsedTime();
        int[] statsWinner = {winner.incrementNbreBateauShot(), winner.incrementNbreShotSuccess(), winner.incrementNbreTotalShot()};
        int[] statsLoser = {loser.incrementNbreBateauShot(), loser.incrementNbreShotSuccess(), loser.incrementNbreTotalShot()};

        EndGamePanel endGamePanel = new EndGamePanel(winner.getName(), loser.getName(), duration, statsWinner, statsLoser);
        endGamePanel.setVisible(true);

        if (isVsComputer) {
            gameOrdiView.setVisible(false);
        } else {
            gameView.setVisible(false);
        }
    }

    public int getElapsedTime() {
        return isVsComputer ? gameOrdiView.getElapsedTime() : gameView.getElapsedTime();
    }
}
