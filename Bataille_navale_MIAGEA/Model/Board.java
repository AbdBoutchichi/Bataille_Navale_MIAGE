
import java.util.*;
import java.lang.*;

/**
 * 
 */
public class Board {

    /**
     * Default constructor
     */
    public Board(int dim) {
        cellules = new Cell[dim*dim];
        
    }

    public Cell[] cellules;

    public ArrayList<Boat> bateaux;

    
    public void getCell(int Zx, int Zy) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void ShowBoardPlayer(Player joueur, Player adversaire) {
        Cell[] adv = adversaire.getCellsShot();
        Cell[] plr = joueur.getBoats();
        System.out.println(" Tes bateaux:\n _ _ _ _ _ _ _ _ _ _ \n");
        char ca = ' ';
        for (int i = 0; i < cellules.length/cellules.length; i++) {
            System.out.println("|");
            for (int j = 0; j < bateaux.size(); j++) {
                for(int bat = 0; bat < plr.length; bat++){
                    if(plr[bat].getX()==j && plr[bat].getY() == i){
                        for (int tire = 0; tire < adv.length; tire++) {
                            if(j == adv[tire].getX() && i == adv[tire].getY()) {
                                ca = 'C';
                            }
                            if(ca != 'C'){
                                ca = 'B';
                            }
                        }
                    }
                }
            System.out.println(ca);
            ca = ' ';
            }
            System.out.println("|\n _ _ _ _ _ _ _ _ _ _ \n");
        }
        
    }

    /**
     * @param player 
     * @param enemie 
     * @return
     */
    public void ShowPlayBoard(Player joueur, Player adversaire) {
        Cell[] adv = adversaire.getBoats();
        Cell[] plr = joueur.getCellsShot();
        System.out.println(" Tes tires:\n _ _ _ _ _ _ _ _ _ _ \n");
        char ca = ' ';
        for (int i = 0; i < cellules.length/cellules.length; i++) {
            System.out.println("|");
            for (int j = 0; j < cellules.length/cellules.length; j++) {
                for(int bat = 0; bat < plr.length; bat++){
                    if(adv[bat].getX()==j && adv[bat].getY()== i){
                        for (int tire = 0; tire < plr.length; tire++) {
                            if(j == plr[tire].getX() && i == plr[tire].getY()) {
                                ca = 'X';
                            }
                            if(ca != 'X'){
                                ca = 'O';
                            }
                        }
                    }
                }
            System.out.println(ca);
            ca = ' ';
            }
            System.out.println("|\n _ _ _ _ _ _ _ _ _ _ \n");
        }
    }

    /**
     * @param boat 
     * @return
     */
    public void addBoat(Boat boat) {
        bateaux.add(boat);
    }

    /**
     * @param cells  
     * @return
     */
    public boolean isThereNeighbors(Cell cells ) {
        // TODO implement here
        return false;
    }

    /**
     * @param cells 
     * @return
     */
    public boolean isboatInBoard(Cell cells) {
        // TODO implement here
        return false;
    }

    /**
     * @param hauteur 
     * @param largeur
     */
    public void generateBoard(int hauteur, int largeur) {
        // TODO implement here
    }

    /**
     * @param x 
     * @param y 
     * @param size 
     * @param position 
     * @return
     */
    public Cell generateBoat(int x, int y, int size, String position) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Boat getBoats() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Cell getCells() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public int getNbreBoats() {
        // TODO implement here
        return 0;
    }

}