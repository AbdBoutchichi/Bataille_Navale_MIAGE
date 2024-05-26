package Modele;

import java.io.Serializable;
import java.util.Random;

public class Boat implements Serializable{
    //public enum nom {PorteAvion, SousMarin, Torpilleur, Croiseur, ContreTorpilleur};


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
    

    //creation d'un bateau de façon autonome et aléatoire 
    public Boat(Player plr, int t, String nom){
        this.cellules = new Cell[taille];
        this.taille=t;
        this.life = t;
        this.name = nom;
        placeRandom(plr);
        addPos();
    }


    public int taille;
    public int posX;
    public int posY;
    private char orientation;
    public Cell[] cellules;
    private int life;


    //Cette methode confirme si une position (x;y) appartient bien au bateau
    public boolean isPosition(int x, int y){
        for (int i = 0; i < taille; i++) {
            if ((orientation == 'H' || orientation == 'h') && x == posX+i && y == posY || (orientation == 'V' || orientation == 'v') && x == posX && y == posY+i){
                return true;
            }
        }

        return false;
    }


    //Cette methode confirme si une position (x;y) est mitoyenne au bateau
    boolean isNeighbor(int x, int y){
        if ((orientation == 'H' || orientation == 'h') && x == posX-1 && y == posY || (orientation == 'V' || orientation == 'v') && x == posX && y == posY-1) {
            return true;
        }

        if ((orientation == 'H' || orientation == 'h') && x == posX+taille && y == posY || (orientation == 'V' || orientation == 'v') && x == posX && y == posY+taille) {
            return true;
        }

        for (int i = 0; i < taille; i++) {
            if ((orientation == 'H' || orientation == 'h') && x == posX+i && (y+1 == posY || y-1 == posY) || (orientation == 'V' || orientation == 'v') && (x-1 == posX || x+1 == posX) && y == posY+i){
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

    

    // Check si un bateau a coulé ou pas
    // public boolean isSunk() {
    //     if(life == 0)
    //         return true;
    //     else 
    //         return false;
    // }
    // Check si un bateau a coulé ou pas
    public boolean isSunk() {
        return this.life <= 0; // Vérifie si la vie du bateau est à 0 ou moins
    }
    

    // Retourne le tableau de cellule appartenant à un bateau
    public Cell[] getCells() {
        return cells;
    }



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

    public Cell[] getCells(Cell[] list, int x){
        for (int i = 0; i < cellules.length; i++) {
            list[i+x]=cellules[i];
        }
        return list;
    }


    //retire de la vie au le bateau a chaque fois qu'il est touché
    public void touch() {
        this.life--; // Décrémenter la vie du bateau
        System.out.println("Vie restante du bateau: " + this.life);
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

    public int fragment(int x, int y){
        for (int i = 0; i < taille; i++) {
            if ((orientation == 'H' || orientation == 'h') && x == posX+i && y == posY || (orientation == 'V' || orientation == 'v') && x == posX && y == posY+i){
                return i;
            }
        }

        return 0;
    }

    //Renvoie en fonction de d'une position l'image correspondante à la partie du bateau
    public String part(int x, int y){
        switch (name) {
            case "Torpilleur":
                switch (orientation) {
                    case 'h':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/TorpilleurH1.png";
                            case 1:
                                return "/Images/TorpilleurH2.png";
                            default:
                                return "";
                        }
                    case 'H':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/TorpilleurH1.png";
                            case 1:
                                return "/Images/TorpilleurH2.png";
                            default:
                                return "";
                        }

                    case 'v':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/TorpilleurV1.png";
                            case 1:
                                return "/Images/TorpilleurV2.png";
                            default:
                                return "";
                        }
                    case 'V':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/TorpilleurV1.png";
                            case 1:
                                return "/Images/TorpilleurV2.png";
                            default:
                                return "";
                        }
                    default:
                        return "";
                }
            
            case "ContreTorpilleur":
                
                switch (orientation) {
                    case 'h':
                    
                        switch (this.fragment(x, y)) {
                            case 0:
                            System.out.println("ici");
                                return "/Images/ContreTorpilleurH1.png";
                            case 1:
                                return "/Images/ContreTorpilleurH2.png";
                            case 2:
                                return "/Images/ContreTorpilleurH3.png";
                            default:
                                return "";
                        }
                    case 'H':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/ContreTorpilleurH1.png";
                            case 1:
                                return "/Images/ContreTorpilleurH2.png";
                            case 2:
                                return "/Images/ContreTorpilleurH3.png";
                            default:
                                return "";
                        }

                    case 'v':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/ContreTorpilleurV1.png";
                            case 1:
                                return "/Images/ContreTorpilleurV2.png";
                            case 2:
                                return "/Images/ContreTorpilleurV3.png";
                            default:
                                return "";
                        }
                    case 'V':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/ContreTorpilleurV1.png";
                            case 1:
                                return "/Images/ContreTorpilleurV2.png";
                            case 2:
                                return "/Images/ContreTorpilleurV3.png";
                            default:
                                return "";
                        }
                    default:
                    return "";
                        }
            case "SousMarin":
                switch (orientation) {
                    case 'h':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/SousMarinH1.png";
                            case 1:
                                return "/Images/SousMarinH2.png";
                            case 2:
                                return "/Images/SousMarinH3.png";
                            default:
                                return "";
                        }
                    case 'H':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/SousMarinH1.png";
                            case 1:
                                return "/Images/SousMarinH2.png";
                            case 2:
                                return "/Images/SousMarinH3.png";
                            default:
                                return "";
                        }

                    case 'v':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/SousMarinV1.png";
                            case 1:
                                return "/Images/SousMarinV2.png";
                            case 2:
                                return "/Images/SousMarinV3.png";
                            default:
                                return "";
                        }
                    case 'V':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/SousMarinV1.png";
                            case 1:
                                return "/Images/SousMarinV2.png";
                            case 2:
                                return "/Images/SousMarinV3.png";
                            default:
                                return "";
                        }
                }

            case "Croiseur":
                switch (orientation) {
                    case 'h':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/CroiseurH1.png";
                            case 1:
                                return "/Images/CroiseurH2.png";
                            case 2:
                                return "/Images/CroiseurH3.png";
                            case 3:
                                return "/Images/CroiseurH4.png";
                            default:
                                return "";
                        }
                    case 'H':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/CroiseurH1.png";
                            case 1:
                                return "/Images/CroiseurH2.png";
                            case 2:
                                return "/Images/CroiseurH3.png";
                            case 3:
                                return "/Images/CroiseurH4.png";
                            default:
                                return "";
                        }

                    case 'v':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/CroiseurV1.png";
                            case 1:
                                return "/Images/CroiseurV2.png";
                            case 2:
                                return "/Images/CroiseurV3.png";
                            case 3:
                                return "/Images/CroiseurV4.png";
                            default:
                                return "";
                        }
                    case 'V':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/CroiseurV1.png";
                            case 1:
                                return "/Images/CroiseurV2.png";
                            case 2:
                                return "/Images/CroiseurV3.png";
                            case 3:
                                return "/Images/CroiseurV4.png";
                            default:
                                return "";
                        }
                }

            case "PorteAvion":
                switch (orientation) {
                    case 'h':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/PorteAvionH1.png";
                            case 1:
                                return "/Images/PorteAvionH2.png";
                            case 2:
                                return "/Images/PorteAvionH3.png";
                            case 3:
                                return "/Images/PorteAvionH4.png";
                            case 4:
                                return "/Images/PorteAvionH5.png";
                            default:
                                return "";
                        }
                    case 'H':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/PorteAvionH1.png";
                            case 1:
                                return "/Images/PorteAvionH2.png";
                            case 2:
                                return "/Images/PorteAvionH3.png";
                            case 3:
                                return "/Images/PorteAvionH4.png";
                            case 4:
                                return "/Images/PorteAvionH5.png";
                            default:
                                return "";
                        }

                    case 'v':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/PorteAvionV1.png";
                            case 1:
                                return "/Images/PorteAvionV2.png";
                            case 2:
                                return "/Images/PorteAvionV3.png";
                            case 3:
                                return "/Images/PorteAvionV4.png";
                            case 4:
                                return "/Images/PorteAvionV5.png";
                            default:
                                return "";
                        }
                    case 'V':
                        switch (this.fragment(x, y)) {
                            case 0:
                                return "/Images/PorteAvionV1.png";
                            case 1:
                                return "/Images/PorteAvionV2.png";
                            case 2:
                                return "/Images/PorteAvionV3.png";
                            case 3:
                                return "/Images/PorteAvionV4.png";
                            case 4:
                                return "/Images/PorteAvionV5.png";
                            default:
                                return "";
                        }
                }
                
            default:
                return "";
        }

    }
    private void placeRandom(Player plr){
    
        Random rand = new Random();
        boolean horizontal;
        boolean placed = false;
        this.posX = -1;
        this.posY=-1;
        this.orientation = 'H';
        while (!placed) {
            this.posX = rand.nextInt(10);
            this.posY = rand.nextInt(10);
            horizontal = rand.nextBoolean();
            orientation = horizontal? 'H' : 'V';
            if(plr.canPlace(this)){
                placed = plr.placeBateau(this);
            }
        }
    }
}
    
    



