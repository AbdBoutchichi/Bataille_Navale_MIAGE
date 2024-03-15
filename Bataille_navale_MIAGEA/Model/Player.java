package Modele;

//import java.io.BufferedReader;
import java.util.*;

/**
 *
 */
public class Player {

    public String name;
    //public Board board;
    public int NbreTotalShot;
    public int NbreShotSuccess;
    public int NbreBateauShot;
    public Cell LastCellShot = new Cell(-1, -1);
    //public BufferReader buffer;
    public List<Cell> cellsShot;
    public List<Boat> boats;

    public Player(String name) {
        this.name = name;
        //this.board = board;
        this.NbreTotalShot = 0;
        this.NbreBateauShot = 0;
        this.NbreShotSuccess = 0;
        this.LastCellShot = null;
        //this.buffer = new BufferedReader(new InputStreamReader(System.in));
        this.cellsShot = new ArrayList<Cell>();
        this.boats = new ArrayList<Boat>();
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
    public void placeBateau(int x, int y, char orientation, int dim, String nom) {
        boats.add(new Boat(x, y, orientation, dim, nom));
        System.out.println("le "+ nom +" a été ajouté !");
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
    /*public Board getBoard() {
        return this.board;
    }*/

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
        System.out.println("║ Statistiques de jeu de " + this.name + " :");
        System.out.println("║");
        System.out.println("║ Nombre de tirs réalisés : " + this.NbreTotalShot);
        System.out.println("║ Nombre de tirs réussis : " + this.NbreShotSuccess);
        System.out.println("║ Précision : "
                + (double) Math.round(((double) (NbreShotSuccess) / (double) (NbreTotalShot)) * 100.0) + "%");
        // System.out.println("║ Nombre de bateaux adverses détruis : " + NbreBateauShot
        // + "/" + getNbBoats());
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
        this.LastCellShot = new Cell(x, y);
    }

    /**
     * @param cells
     * @return
     */
    /*public void shootAt(int x, int y) {
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
    }*/

    public void shootAt(int x, int y){
        cellsShot.add(new Cell(x,y));
    }

    public Cell[] getCellsShot() {
        Cell[] shot = new Cell[cellsShot.size()];
        for (int i = 0; i < cellsShot.size(); i++) {
            shot[i] = cellsShot.get(i);
        }
        return shot;
    }

    public List<Boat> getCellsBoats(){
        return boats;
    }

    public void isTouch(int x, int y){
        boolean t=false;
        for(int i = 0; i < boats.size(); i++){
            if (boats.get(i).isPosition(x, y)) {
                System.out.println("Touché");
                boats.get(i).touch();
                t= true;
                if(boats.get(i).isDead()){
                    System.out.println("Coulé");
                } 
            }
        }

        if(!t){System.out.println("Raté");}

        
    }

    public boolean over(int x, int y, int taille, char o){
        if(o == 'H' || o == 'h'){ 
            for(int i = 0;  i < taille; i++){
                for(int j = 0; j < boats.size(); j++){
                    if(boats.get(j).isPosition(x+i, y)) 
                    {return true;}
                }
            }
        } 
        else if(o == 'V' || o == 'v'){
            for(int i = 0;  i < taille; i++){
                for(int j = 0; j < boats.size(); j++){
                    if(boats.get(j).isPosition(x, y+i)) 
                    {return true;}
                }
            }
        }
        return false;
    }

    public boolean out(int x, int y, int taille, char o, int bordure){
        if(o == 'V' || o == 'v'){ 
            for(int i = 0;  i < taille; i++){
                    if(x+i>=bordure){return true;}
            }
        } 
        else if(o == 'H' || o == 'h'){
            for(int i = 0;  i < taille; i++){
                    if(y+i>=bordure) {return true;}
            }
        }
        return false;
    }

    public boolean isAlive(){
        for(int i = 0; i<boats.size(); i++){
            if(!boats.get(i).isDead()){return true;};
        }
        return false;
    }

}