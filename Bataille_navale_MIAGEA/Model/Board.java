package Modele;


import java.util.*;
import java.lang.*;

/**
 * 
 */
public class Board {

    /**
     * Default constructor
     */
    public Board(int dim, Player plr1, Player plr2) {
        dimension = dim;
        cellules = new Cell[dim*dim];
        joueur1 = plr1;
        joueur2 = plr2;
        
    }

    int dimension;

    public Cell[] cellules;

    public Player joueur1;
    
    public Player joueur2;

    public ArrayList<Boat> bateaux;

    
    /*public void getCell(int Zx, int Zy) {
        // TODO implement here
        return null;
    }*/

    /**
     * @return
     */
    public void ShowBoardPlayer(Player joueur, Player adversaire) {
        Cell[] adv = adversaire.getCellsShot();
        List<Boat> plr = joueur.getCellsBoats();
        System.out.print(" Tes bateaux :\n  ");
        char ind = 'A';
        for (int index = 0; index < dimension; index++){
            System.out.print("  " + ind + " ");
            ind++;
        }
        for (int index = 0; index <= dimension; index++) {
            if (index == 0) {
                System.out.print("\n  ╔═══");
                ind++;
            } else if (index == dimension) {
                System.out.print("╗\n");
            } else {System.out.print("╦═══");}
        }
        String ca = " ";
        for (int i = 0; i < dimension; i++) {
            System.out.print(i + " ║ ");
            for (int j = 0; j < dimension; j++) {
                
                for(int bat = 0; bat < plr.size(); bat++){
                    if(plr.get(bat).isPosition(i, j)){
                        ca = "B";
                        for (int tire = 0; tire < adv.length; tire++) {
                            if(j == adv[tire].getX() && i == adv[tire].getY()) {
                                ca = "C";
                            }
                        }
                    }
                }
            System.out.print( ca + " ║ ");
            ca = " ";
            }
            if(i< dimension-1){
                for (int index = 0; index <= dimension; index++) {
                    if (index == 0) {
                        System.out.print("\n  ╠═══");
                    } else if (index == dimension ) {
                        System.out.print("╣\n");
                    } else {System.out.print("╬═══");}
                }
            } else { 
                for (int index = 0; index <= dimension; index++) {
                    if (index == 0) {
                        System.out.print("\n  ╚═══");
                    } else if (index == dimension ) {
                        System.out.print("╝\n");
                    } else {System.out.print("╩═══");}
                }
            }
        }
        
    }

    /**
     * @param player 
     * @param enemie 
     * @return
     */
    public void ShowPlayBoard(Player joueur, Player adversaire) {
        List<Boat> adv = adversaire.getCellsBoats();
        Cell[] plr = joueur.getCellsShot();
        System.out.print(" Tes tires :");
        for (int index = 0; index <= dimension; index++) {
            if (index == 0) {
                System.out.print("\n  ╔═══");
            } else if (index == dimension ) {
                System.out.print("╗\n");
            } else {System.out.print("╦═══");}
        }
        char ca = ' ';
        for (int i = 0; i < dimension; i++) {
            System.out.print(i + " ║ ");
            for (int j = 0; j < dimension; j++) {
                for(int bat = 0; bat < plr.length; bat++){
                    if(adv.get(bat).isPosition(i, j)){
                        ca = 'O';
                        for (int tire = 0; tire < plr.length; tire++) {
                            if(j == plr[tire].getX() && i == plr[tire].getY()) {
                                ca = 'X';
                            }
                            
                        }
                    }
                }
            System.out.print( ca + " ║ ");
            ca = ' ';
            }
            if(i< dimension-1){ 
                for (int index = 0; index <= dimension; index++) {
                    if (index == 0) {
                        System.out.print("\n  ╠═══");
                    } else if (index == dimension ) {
                        System.out.print("╣\n");
                    } else {System.out.print("╬═══");}
                }
            }
                else {for (int index = 0; index <= dimension; index++) {
                    if (index == 0) {
                        System.out.print("\n  ╚═══");
                    } else if (index == dimension ) {
                        System.out.print("╝\n");
                    } else {System.out.print("╩═══");}
                }
            }
        }
    }

    public void turn(){
        Scanner  scanner = new Scanner(System.in);
        String tire;
        System.out.println("\n\n\n══════════════════════════════════════════════════════════════════════════════════════════════\n\nAu tour de "+ joueur1.name+"\n\n══════════════════════════════════════════════════════════════════════════════════════════════\n\n\n");
        ShowPlayBoard(joueur1, joueur2);
        ShowBoardPlayer(joueur1, joueur2);
        tire = scanner.nextLine();
        joueur1.shootAt((int)tire.charAt(0), convertPos(tire.charAt(1)) );
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



    public int convertPos(char c){
        switch (c) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            case 'i':
                return 8;
            case 'j':
                return 9;
            default:
                return 10;
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
        }
    }

}