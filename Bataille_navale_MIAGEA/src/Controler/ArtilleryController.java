package Controler;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Modele.Player;
import View.GridPanel;
import View.NewGameArtillerie;

public class ArtilleryController implements ActionListener {
    private Player player1;
    private Player player2;
    private NewGameArtillerie gameView;
    private int x, y;
    private boolean isPlayer1Turn;
    private boolean incrementXRunning;
    private boolean incrementYRunning;
    private javax.swing.Timer xTimer;
    private javax.swing.Timer yTimer;

    public ArtilleryController(Player player1, Player player2, NewGameArtillerie gameView) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameView = gameView;
        this.x = 0;
        this.y = 0;
        this.isPlayer1Turn = true;
        this.incrementXRunning = false;
        this.incrementYRunning = false;

        addListenersToControlPanel(gameView.controlPanelPlayer1);
        addListenersToControlPanel(gameView.controlPanelPlayer2);

        initTimers();
    }

    private void initTimers() {
        xTimer = new javax.swing.Timer(100, e -> {
            x = (x + 1) % 10;
            JLabel xLabel = gameView.getXLabel(isPlayer1Turn ? player1 : player2);
            JLabel yLabel = gameView.getYLabel(isPlayer1Turn ? player1 : player2);
            updateCoordinates(xLabel, yLabel);
        });

        yTimer = new javax.swing.Timer(100, e -> {
            y = (y + 1) % 10;
            JLabel xLabel = gameView.getXLabel(isPlayer1Turn ? player1 : player2);
            JLabel yLabel = gameView.getYLabel(isPlayer1Turn ? player1 : player2);
            updateCoordinates(xLabel, yLabel);
        });
    }

    private void addListenersToControlPanel(JPanel controlPanel) {
        for (Component component : controlPanel.getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).addActionListener(this);
            }
        }
    }

    private void updateCoordinates(JLabel xLabel, JLabel yLabel) {
        xLabel.setText("X: " + x);
        yLabel.setText("Y: " + y);
    }

    private void handleFire(Player shooter, Player target, GridPanel shooterGrid, GridPanel targetGrid) {
        if (shooter.canShoot(x, y)) {
            shooter.shootAt(x, y);
            if (target.isTouch(x, y)) {
                targetGrid.updateCell(x, y, Color.GREEN);
            } else {
                targetGrid.updateCell(x, y, Color.RED);
            }
            if (!target.isAlive()) {
                gameView.endGame(shooter.getName());
            } else {
                switchTurns();
            }
        }
        resetCoordinates();
    }

    private void resetCoordinates() {
        x = 0;
        y = 0;
        JLabel xLabel = gameView.getXLabel(isPlayer1Turn ? player1 : player2);
        JLabel yLabel = gameView.getYLabel(isPlayer1Turn ? player1 : player2);
        updateCoordinates(xLabel, yLabel);
    }

    private void switchTurns() {
        isPlayer1Turn = !isPlayer1Turn;
        updateTurnIndicators();
    }

    private void updateTurnIndicators() {
        // Mettre à jour les indicateurs visuels pour montrer quel joueur doit jouer
        if (isPlayer1Turn) {
            gameView.controlPanelPlayer1.setBackground(Color.LIGHT_GRAY);
            gameView.controlPanelPlayer2.setBackground(Color.DARK_GRAY);
        } else {
            gameView.controlPanelPlayer1.setBackground(Color.DARK_GRAY);
            gameView.controlPanelPlayer2.setBackground(Color.LIGHT_GRAY);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        Player currentPlayer = isPlayer1Turn ? player1 : player2;
        Player opponent = isPlayer1Turn ? player2 : player1;
        GridPanel currentPlayerGrid = isPlayer1Turn ? gameView.gridPanelPlayer1 : gameView.gridPanelPlayer2;
        GridPanel opponentGrid = isPlayer1Turn ? gameView.gridPanelPlayer2 : gameView.gridPanelPlayer1;
        
        JLabel xLabel = gameView.getXLabel(currentPlayer);
        JLabel yLabel = gameView.getYLabel(currentPlayer);

        switch (source.getText()) {
            case "Increment X":
                if (!incrementXRunning) {
                    xTimer.start();
                    incrementXRunning = true;
                }
                break;
            case "Increment Y":
                if (!incrementYRunning) {
                    yTimer.start();
                    incrementYRunning = true;
                }
                break;
            case "Stop X":
                if (incrementXRunning) {
                    xTimer.stop();
                    incrementXRunning = false;
                }
                break;
            case "Stop Y":
                if (incrementYRunning) {
                    yTimer.stop();
                    incrementYRunning = false;
                }
                break;
            case "Confirm Fire":
                handleFire(currentPlayer, opponent, currentPlayerGrid, opponentGrid);
                break;
            case "Cancel":
                resetCoordinates();
                break;
        }
    }
}
