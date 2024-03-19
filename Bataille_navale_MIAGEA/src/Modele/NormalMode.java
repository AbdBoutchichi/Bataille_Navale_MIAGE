package Modele;
import java.util.*;

/**
 * 
 */
public class NormalMode {

    /**
     * Default constructor
     */
    Scanner scanner;
    
    public NormalMode() {
        scanner = new Scanner(System.in);
        startNormalMode(10,5);
    }

    public void startNormalMode(int dimension, int nbrBateau){

        System.out.println("Nom du joueur 1 :");
        String nom1 = scanner.nextLine();
        System.out.println("Nom du joueur 2 :");
        String nom2 = scanner.nextLine();

        Player plr1 = new Player(nom1);
        Player plr2 = new Player(nom2);
        Board brd = new Board(dimension, plr1, plr2);


        brd.ShowBoardPlayer(brd.joueur1, brd.joueur2);

        System.out.println(brd.joueur1.name + " place tes bateaux");

        int bat = 5;
        int dim = 5;
        String lecteur;


        while (bat !=0) {
            brd.ShowBoardPlayer(plr1, plr2);
            if (bat == 2) {
                dim++;
            }

    
            lecteur = scanner.nextLine();

//cette partie pourrait être plus optimisable********************************************************/
            if (brd.joueur1.out((int)lecteur.charAt(0)-48, brd.convertPos(lecteur.charAt(1)),dim,lecteur.charAt(2), brd.dimension)) {

                System.out.println("Ton bateaux sors du plateaux !!!");

            } else if(brd.joueur1.over((int)lecteur.charAt(0)-48, brd.convertPos(lecteur.charAt(1)),dim,lecteur.charAt(2))){

                System.out.println("Ton bateaux passe sur la position d'un autre !!!");

            } else if(lecteur.length() > 3){

                System.out.println("Coordonnées non conforme !!!");

            } else{
                if(bat == 5){
                    System.out.println("\nDonne le placement de tes bateau en 3 caractere XAO\n\nX : un chiffre entre 0 et 9\n\nA : une lettre entre A et J\n\nO: son orientation V pour verticale et H pour horizontale\n\nOù poser le Porte-avions (longueur = 5)?");
                    dim = 5;
                    brd.joueur1.placeBateau(null, (int)lecteur.charAt(0)-48, brd.convertPos(lecteur.charAt(1)),lecteur.charAt(2),dim,"Porte-avions");
                    bat--;
                } else if (bat == 4) {
                    System.out.println("\n\nOù poser le Croiseur (longueur = 4)?");
                    dim = 4;
                    brd.joueur1.placeBateau(null, (int)lecteur.charAt(0)-48, brd.convertPos(lecteur.charAt(1)),lecteur.charAt(2),dim,"Croiseur");
                    bat--;
                } else if (bat == 2 || bat == 3) {
                    System.out.println("\n\nOù poser le Contre-torpilleur (longueur = 4)?");
                    dim = 3;
                    brd.joueur1.placeBateau(null, (int)lecteur.charAt(0)-48, brd.convertPos(lecteur.charAt(1)),lecteur.charAt(2),dim,"Contre-torpilleur");
                    bat--;
                } else {
                    System.out.println("\n\nOù poser le Torpilleur (longueur = 4)?");
                    dim = 2;
                    brd.joueur1.placeBateau(null, (int)lecteur.charAt(0)-48, brd.convertPos(lecteur.charAt(1)),lecteur.charAt(2),dim,"Torpilleur");
                    bat--;
                }
            }
            dim--;
            
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+brd.joueur2.name + " place tes bateaux\n\nDonne le placement de tes bateau en 3 caractere XAO\n\nX : un chiffre entre 0 et 9\n\nA : une lettre entre A et J\n\nO: son orientation V pour verticale et H pour horizontale\n\nOù poser le Porte-avions (longueur = 5)?");
        bat = 5;
        dim = 5;
        while (bat !=0) {
            brd.ShowBoardPlayer(plr2, plr1);
            
            if (bat == 2) {
                dim++;
            }
            lecteur = scanner.nextLine();
            if (brd.joueur2.over((int)lecteur.charAt(0)-49, brd.convertPos(lecteur.charAt(1)),dim,lecteur.charAt(2)) || brd.joueur2.out((int)lecteur.charAt(0)-48, brd.convertPos(lecteur.charAt(1)),dim,lecteur.charAt(2), 10)) {
                System.out.println("ta mere la pute choisis une autre position !!!");
            } else {
                if(bat == 5){
                    System.out.println("\n\nOù poser le Croiseur (longueur = 4)?");
                    dim = 5;
                    brd.joueur2.placeBateau(brd, (int)lecteur.charAt(0)-48, brd.convertPos(lecteur.charAt(1)),lecteur.charAt(2),dim,"Porte avion");
                    bat--;
                } else if (bat == 4) {
                    System.out.println("\n\nOù poser le Contre-torpilleur (longueur = 3)?");
                    dim = 4;
                    brd.joueur2.placeBateau(null, (int)lecteur.charAt(0)-48, brd.convertPos(lecteur.charAt(1)),lecteur.charAt(2),dim,"Croiseur");
                    bat--;
                } else if (bat == 2 || bat == 3) {
                    System.out.println("\n\nOù poser le Torpilleur (longueur = 2)?");
                    dim = 3;
                    brd.joueur2.placeBateau(null, (int)lecteur.charAt(0)-48, brd.convertPos(lecteur.charAt(1)),lecteur.charAt(2),dim,"Contre-torpilleur");
                    bat--;
                } else {
                    
                    dim = 2;
                    brd.joueur2.placeBateau(brd, (int)lecteur.charAt(0)-48, brd.convertPos(lecteur.charAt(1)),lecteur.charAt(2),dim,"Torpilleur");
                    bat--;
                }
            }
            dim--;
        }
//************************************************************************************/
        int tour = 0;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nDonnez le placement de vos tires en 2 caractere XA\n\nX : un chiffre entre 0 et 9\n\nA : une lettre entre A et J\n\nO: son orientation V pour verticale et H pour horizontale\n\nAppuyez sur entrée pour continuer");
        lecteur = scanner.nextLine();
        while(brd.joueur1.isAlive() || brd.joueur2.isAlive()) {
            tour++;
            if(tour % 2 == 1) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nc'est a " + brd.joueur1.getName()+ " de jouer : ");
                brd.ShowBoardPlayer(brd.joueur1, brd.joueur2);
                brd.ShowPlayBoard(brd.joueur1, brd.joueur2);
                lecteur = scanner.nextLine();
                brd.joueur1.shootAt((int)lecteur.charAt(0)-48, lecteur.charAt(1));
                brd.joueur2.isTouch((int)lecteur.charAt(0)-48, lecteur.charAt(1));
            } else {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nc'est a" + brd.joueur2.getName()+ "de jouer : ");
                brd.ShowBoardPlayer(brd.joueur2, brd.joueur1);
                brd.ShowPlayBoard(brd.joueur2, brd.joueur1);
                lecteur = scanner.nextLine();
                brd.joueur2.shootAt((int)lecteur.charAt(0)-48, lecteur.charAt(1));
                brd.joueur1.isTouch((int)lecteur.charAt(0)-48, lecteur.charAt(1));
            }
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n changement de joueur !!\n\n Appuyez sur entrée pour changer de joueur");
            lecteur = scanner.nextLine();
        }

        if(brd.joueur1.isAlive()){
            System.out.println("╔══════════════════════════════════════════════════════════\n║\n║ "+brd.joueur1+" a gagné !!! Gloire a notre nouveau Heros\n║\n║ Il a fait"+brd.joueur1.NbreTotalShot+" tires\n║\n╚══════════════════════════════════════════════════════════");
        } else {
            System.out.println("╔══════════════════════════════════════════════════════════\n║\n║ "+brd.joueur2+" a gagné !!! Gloire a notre nouveau Heros\n║\n║ Il a fait"+brd.joueur2.NbreTotalShot+" tires\n║\n╚══════════════════════════════════════════════════════════");
        }
    }

    

}