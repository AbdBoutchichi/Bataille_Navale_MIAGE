package Modele;

import java.util.Scanner;
import java.io.Serializable;

public class Menu implements Serializable{

    private static Scanner lecteur;

    public Menu(){
        this.lecteur = new Scanner(System.in);
    }

    /**
* __        _______ _     ____ ___  __  __ _____   _____ ___
* \ \      / / ____| |   / ___/ _ \|  \/  | ____| |_   _/ _ \
*  \ \ /\ / /|  _| | |  | |  | | | | |\/| |  _|     | || | | |
*   \ V  V / | |___| |__| |__| |_| | |  | | |___    | || |_| |
*  __\_/\_/_ |_____|_____\____\___/|_|__|_|_____| __|_|_\___/
* | __ )  / \|_   _|_   _| |   | ____/ ___|| | | |_ _|  _ \
* |  _ \ / _ \ | |   | | | |   |  _| \___ \| |_| || || |_) |
* | |_) / ___ \| |   | | | |___| |___ ___) |  _  || ||  __/
* |____/_/   \_\_|   |_| |_____|_____|____/|_| |_|___|_|
*/
    public static void ShowMenu(){

System.out.println("            _        ooo                                     ");
System.out.println("                     | |                                  ");
System.out.println("                     '.|                                   ");
System.out.println("     _-   _-    _-  _-||    _-    _-  _-   _-    _-    _-  ");
System.out.println("       _-    _-   - __||___    _-       _-    _-    _-     ");
System.out.println("    _-   _-    _-  |   _   |       _-   _-    _-           ");
System.out.println("      _-    _-    /_) (_) (_|        _-    _-       _-     ");
System.out.println("              _.-'           `-._      ________       _-   ");
System.out.println("        _..--`                   `-..'       .'            ");
System.out.println("    _.-'  o/o                     o/o`-..__.'        ~  ~  ");
System.out.println(" .-'      o|o                     o|o      `.._.  // ~  ~  ");
System.out.println(" `-._     o|o                     o|o        |||<|||~  ~   ");
System.out.println("     `-.__o|o                     o|o       .'-'  \\\\ ~  ~  ");
System.out.println("LGB       `-.______________________|_...-``'.       ~  ~   ");
System.out.println("                                    `._______`.            ");



                                    
System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
System.out.println("║                       Bataille navale : règles du jeu                        ║");
System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void displayMainMenu() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║      1. Jouer contre un autre joueur       ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║       2. Jouer contre l'ordinateur         ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║          3. Jouer en Artillerie            ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║             4. Jouer en Radar              ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║           5. Charger une partie            ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║               6. Quitter                   ║");
        System.out.println("╚════════════════════════════════════════════╝");
    }

    public static int getPlayerChoice() {
        int choice = lecteur.nextInt();
        lecteur.nextLine();
        return choice;
    }

    public static int getDifficultyChoice() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║            Choisir la difficulté  :        ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                 1. Facile                  ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                 2. Moyen                   ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                 3. Difficile               ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        //demande au joueur la difficulté du bot jusqu'a ce que la valeur données soir correcte.
        int difficulty = lecteur.nextInt();
        while (difficulty < 1 || difficulty > 3) {
            difficulty = lecteur.nextInt();
            valeurNonConforme();
        }
        return difficulty;
    }

    public static void displayEndGameOptions() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║     Jeu terminé. Choisissez une option :   ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                 1. Redémarrer              ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║          2. Retour au menu principal       ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                 3. Quitter                 ║");
        System.out.println("╚════════════════════════════════════════════╝");
    }

    public static void valeurNonConforme(){
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║     La valeur entrée n'est pas conforme !     ║");
        System.out.println("║       Veuillez saisir une valeur valide       ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");
    }

    public static int modePlacement() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║          Choisir le mode de placement :        ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║                  1. Manuelle                   ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║                 2. Automatique                 ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        
        //demande au joueur la difficulté du bot jusqu'a ce que la valeur données soir correcte.
        int placement = 0;
        while (placement <1 || placement > 2) {
            placement = lecteur.nextInt();
            System.out.println(""+placement);
            if(placement <1 || placement > 2) valeurNonConforme();
        }
        return placement;
    }

    public static void main(String[] args) {
        ShowMenu();
        displayMainMenu();
        getPlayerChoice();
        getDifficultyChoice();
        displayEndGameOptions();
        
    }
    
}
