package Controler;

import View.EndGamePanel;
import View.NewGame;
import View.NewGameOrdi;
import View.NewGameRadar;
import Modele.Player;
import java.lang.System;

public class EndGameController {
    private Player player1;
    private Player player2;
    private NewGame gameView;
    private NewGameOrdi gameOrdiView;
    public NewGameRadar gameRadar;
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

    public EndGameController(Player p1, Player p2, NewGameRadar gameRadar, boolean isVsComp) {
        this.player1 = p1;
        this.player2 = p2;
        this.gameRadar = gameRadar;
        this.isVsComputer = isVsComp;
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

    int duration;
    if (isVsComputer) {
        duration = gameOrdiView != null ? gameOrdiView.getElapsedTime() : 0;
    } else if (gameRadar != null) {
        duration = gameRadar.getElapsedTime();
    } else {
        duration = gameView != null ? gameView.getElapsedTime() : 0;
    }

    int[] statsWinner = {winner.NbreShotSuccess, winner.NbreTotalShot - winner.NbreShotSuccess, winner.NbreTotalShot};
    int[] statsLoser = {loser.NbreShotSuccess, loser.NbreTotalShot - loser.NbreShotSuccess, loser.NbreTotalShot};

    EndGamePanel endGamePanel = new EndGamePanel(winner.getName(), loser.getName(), duration, statsWinner, statsLoser);
    endGamePanel.setVisible(true);

    if (gameOrdiView != null && isVsComputer) {
        gameOrdiView.setVisible(false);
    } else if (gameRadar != null) {
        gameRadar.setVisible(false);
    } else if (gameView != null) {
        gameView.setVisible(false);
    }
}


public int getElapsedTime() {
    if (gameOrdiView != null && isVsComputer) {
        return gameOrdiView.getElapsedTime();
    } else if (gameRadar != null) {
        return gameRadar.getElapsedTime();
    } else if (gameView != null) {
        return gameView.getElapsedTime();
    }
    return 0;
}

}
