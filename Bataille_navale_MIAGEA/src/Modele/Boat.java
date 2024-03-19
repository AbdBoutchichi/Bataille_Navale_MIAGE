package Modele;



public class Boat {
    private String name;
    private Cell[] cells;
    // private BoatState state;
    // private int hits;

    public Boat(int x, int y, char o, int t, String nom){
        this.posX=x;
        this.posY=y;
        this.orientation = o;
        this.cellules = new Cell[taille];
        this.taille=t;
        this.life = t;
        this.name = nom;
        addPos();
    }


    public int taille;
    public int posX;
    public int posY;
    private char orientation;
    public Cell[] cellules;
    private int life;

    boolean isPosition(int x, int y){
        for (int i = 0; i < taille; i++) {
            if ((orientation == 'H' || orientation == 'h') && x == posX && y == posY+i || (orientation == 'V' || orientation == 'v') && x == posX+i && y == posY){
                return true;
            }
        }

        return false;
    }

    //Jusqu'à maintenant la méthode ne sert à rien 
    void addPos(){
        for(int i=0; i <  cellules.length; i++){
            if(orientation=='V' || orientation== 'v'){
                cellules[i] = new Cell(posX+i, posY);;
            } else if(orientation=='H' || orientation== 'h'){
                cellules[i] = new Cell(posX, posY+i);
            } else {
                System.out.println("y a un prb chef !! \n'" + orientation + "'n'est pas une valeur prise en compte");
            }
        }
    }

    /*
     * public void hit() {
     * hits++;
     * if (hits >= cells.length) {
     * state = BoatState.SUNK;
     * } else {
     * state = BoatState.DAMAGED;
     * }
     * }
     */

    // Check si un bateau a coulé ou pas
    public boolean isSunk() {
        for (int i = 0; i < this.cells.length; i++) {
            if (!this.cells[i].isShot()) {
                return false;
            }
        }
        return true;
    }

    // Retourne le tableau de cellule appartenant à un bateau
    public Cell[] getCells() {
        return cells;
    }

    /*
     * public Cell getCellsAtPosition(int cellPosition) {
     * if (cellPosition >= 0 && cellPosition < cells.length) {
     * return cells[cellPosition];
     * } else {
     * return null;
     * }
     * }
     */

    public Cell getCellByCoordinate(int x, int y) {
        for (Cell cell : cells) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }

        }
        return cells[0];
    }

    public int getSize() {
        return this.taille;
    }

    public String getName() {
        return name;
    }

    public boolean isDead(){
        if(life == 0){
            return true;
        }
        return false;
    }

    public Cell[] getCells(Cell[] list, int x){
        for (int i = 0; i < cellules.length; i++) {
            list[i+x]=cellules[i];
        }
        return list;
    }

    public void touch(){
        life--;
    }

    public char getOrientation() {
        return orientation;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    /*
     * public BoatState getState() {
     * return state;
     * }
     */

    /*
     * public void setState(BoatState state) {
     * this.state = state;
     * }
     */

    /*
     * public int getHits() {
     * return hits;
     * }
     */
}
