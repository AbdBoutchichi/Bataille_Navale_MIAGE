package Modele;

import java.util.List;

public class BoardRadar extends Board{


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
        int count = dimension;
        List<Boat> boats = adv.getCellsBoats();
        Cell[] shoots = jr.getCellsShot();

        //il parcours toute les cellules  du plateau de jeu 
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {

                //Il verifie que chacune d'entre elle ne soit pas deja une cellule sur laquelle le joueur a tiré et si il y a un bateau
                for (Boat b : boats) {
                    for (Cell s : shoots) {
                        if (b.isPosition(x, y) && !s.position(x, y))
                        count = (count > Math.abs(x-i) + Math.abs(y-j) ? Math.abs(x-i) + Math.abs(y-j) : count);
                    }
                }
            }
        }
        return count;
    }


}
