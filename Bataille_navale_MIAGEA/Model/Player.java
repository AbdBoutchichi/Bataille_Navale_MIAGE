import java.io.BufferedReader;
import java.util.*;

/**
 *
 */
public class Player {

    public String name;
    public Board board;
    public int NbreTotalShot;
    public int NbreShotSuccess;
    public int NbreBateauShot;
    public Cell LastCellShot = new Cell(-1,-1);
    public BufferReader buffer;


    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        this.NbreTotalShot = 0;
        this.NbreBateauShot = 0;
        this.NbreShotSuccess = 0;
        this.LastCellShot = null;
        this.buffer = new BufferedReader(new InputStreamReader(System.in));
    }

   

    /**
     * @return
     */
    public void incrementNbreTotalShot() {
        NbreTotalShot++;
    }

    /**
     * @param cell 
     * @param boat
     */
    public void placeBateau(Cell cell, Boat boat) {
        // TODO implement here
    }

    /**
     * @return
     */
    public void incrementNbreShotSuccess() {
        NbreShotSuccess++;
    }

    /**
     * @return
     */
    public void incrementNbreBateauShot() {
        NbreBateauShot++;
    }

    /**
     * @return
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return
     */
    public void AfficherStats() {
        System.out.println("============================================================");
        System.out.println("║");
        System.out.println("║ Statistiques de jeu de " + this.Name + " :");
        System.out.println("║");
        System.out.println("║ Nombre de tirs réalisés : " + this.NbreTotalShot);
        System.out.println("║ Nombre de tirs réussis : " + this.NbreShotSuccess);
        System.out.println("║ Précision : " + (double) Math.round(((double) (NbreShotSuccess) / (double) (NbreTotalShot)) * 100.0) + "%");
        //System.out.println("║ Nombre de bateaux adverses détruis : " + NbreBateauShot + "/" + Config.getNbBoats());
        System.out.println("║");
        System.out.println("============================================================");
    }

    /**
     * @return
     */
    public Cell getLastCellShot() {
        return this.LastCellShot;
    }

    /**
     * @param x 
     * @param y 
     * @return
     */
    public void setLastCellShot(int x, int y) {
        this.LastCellShot =  new Cell(x, y);
    }

    /**
     * @param cells 
     * @return
     */
    public void shootAt(int x, int y) {
        Cell target = board.getCell(x, y);
        if (target != null && !target.isShot()) {
            board.shoot(target); // Nous supposons que la classe Board a une méthode shoot(Cell cell)
            this.nbreTotalShots++;
            this.lastCellShot = target;

            if (target.hasBoat()) {
                this.nbreShotsSuccess++;
                if (target.getBoat().isSunk()) {
                    this.nbreBateauxShot++;
                }
            }
        }
    }

}