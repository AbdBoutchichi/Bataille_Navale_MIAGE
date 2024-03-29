package Modele;

import java.util.Scanner;

public class Menu {

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
        System.out.println("║          3. Charger une partie             ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║               4. Quitter                   ║");
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
        int difficulty = lecteur.nextInt();
        lecteur.nextLine();
        return difficulty;
    }

    public static void displayEndGameOptions() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║     Jeu terminé. Choisissez une option :   ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("");
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

    public static void main(String[] args) {
        ShowMenu();
        displayMainMenu();
        getPlayerChoice();
        getDifficultyChoice();
        displayEndGameOptions();
        
    }
    
}
