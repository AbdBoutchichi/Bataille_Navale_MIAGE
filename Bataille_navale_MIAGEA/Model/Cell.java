package Modele;

import java.util.*;

/**
 * 
 */
public class Cell {

    private int x;
    public int y;
    public Boat boat;
    public boolean shot;
    public boolean cible;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.shot = false;
        this.cible = false;//à revoir
        this.boat = null;
    }

    /**
     * @return
     */
    public void addBoat(Boat boat) {
        this.boat = boat;
    }

    /**
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * @return
     */
    // Marque la cellule comme eyant été tiré
    /*public void shoot() {
        this.shot = true;
        if (this.boat != null) {
            this.boat.hit();
        }
    }*/

    /**
     * @return
     */

    // Vérifie si la cellule a été attaquée
    public boolean isShot() {
        return this.shot;
    }

    /**
     * @return
     */

    // Vérifie si la cellule est actuellement une cible
    public boolean isCible() {
        return this.cible;
    }

    /**
     * @param valeur
     * @return
     */

    // Met à jour l'état de la cellule pour indiquer si elle est une cible ou non
    public void updateCible(boolean valeur) {
        this.cible = valeur;
    }

}