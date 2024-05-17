package Modele;

import java.util.List;
import java.io.Serializable;

public class BoardRadar extends Board implements Serializable{


    public BoardRadar(int dim, Player plr1, Player plr2){
        super(dim, plr1, plr2);
    }

    // Le nouveau systeme d'affichage pour le radar
    public void ShowBoardShot(Player joueur, Player adversaire) {
        List<Boat> adv = adversaire.getCellsBoats();
        Cell[] plr = joueur.getCellsShot();
        System.out.print(" Tires de " + joueur.getName() + ":\n  ");
        char ind = 'A';
        for (int index = 0; index < dimension; index++){
            System.out.print("  " + ind + " ");
            ind++;
        }
        for (int index = 0; index <= dimension; index++) {
            if (index == 0) {
                System.out.print("\n  ╔═══");
            } else if (index == dimension ) {
                System.out.print("╗\n");
            } else {System.out.print("╦═══");}
        }
        String ca = " ";
        for (int i = 0; i < dimension; i++) {
            System.out.print(i + " ║ ");
            for (int j = 0; j < dimension; j++) {
                for(int tire = 0; tire < plr.length; tire++){
                    if(j == plr[tire].getX() && i == plr[tire].getY()){
                        ca = "" + radar(i, j, joueur, adversaire);
                        for (int bat = 0; bat < adv.size(); bat++) {
                            if(adv.get(bat).isPosition(j, i)) {
                                ca = "X";
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

    //Cette methode recherche la cellule la plus proche avec un bateau enemie dessus
    public int radar(int x, int y,Player jr, Player adv){
        int count = dimension*2;
        List<Boat> boats = adv.getCellsBoats();
        Cell[] shoots = jr.getCellsShot();

        //il parcours toute les cellules  du plateau de jeu 
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.err.println(i + " ; " + j);
                
                for(int b = 0; b<boats.size(); b++){
                    if(boats.get(b).isPosition(i, j)){
                        if(count > (x-j > 0 ? x-j : j-x) + (y-i > 0 ? y-i : i-y)){
                            count = (x-j > 0 ? x-j : j-x) + (y-i > 0 ? y-i : i-y);
                        }
                    }
                }
                
            }
        }
        System.out.println("count: " + count);
        return count;
    }


}
