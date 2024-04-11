package Modele;



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
        //this.cible = false;//à revoir
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

    public Boat getBoat() {
        return this.boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public boolean hasBoat() {
        return boat != null;
    }

    /**
     * @return
     */

    public void shoot() {
        this.shot = true;
        if (this.boat != null) {
            this.boat.touch(); // Diminue la vie du bateau s'il est touché
        }
    }

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

    public boolean position(int posX, int posY){
        return x== posX && y == posY;
    }

}