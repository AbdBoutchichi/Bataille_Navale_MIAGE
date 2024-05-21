package Modele;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

public class Jeu_Bataillenavale implements Serializable {

    private NormalMode normalMode;
    private ModeArtillerie modeArtillerie;
    private Menu menu;
    private transient Scanner scanner;

    public Jeu_Bataillenavale() {
        scanner = new Scanner(System.in);
        menu = new Menu();
        start();
    }

    public void displaySavedGames() {
        File[] savedGames = SaveGamePart.listSavedGames(); // Récupère les fichiers de sauvegarde
        if (savedGames == null || savedGames.length == 0) {
            System.out.println("Aucune partie sauvegardée trouvée.");
            return;
        }
        System.out.println("Parties sauvegardées disponibles:");
        for (File file : savedGames) {
            System.out.println("Fichier: " + file.getName());
        }
        System.out.println("Entrez le nom de fichier de la partie que vous souhaitez charger:");
        String fileName = scanner.nextLine();
        loadGame(fileName);
    }

    public void start() {
        menu.ShowMenu();
        menu.displayMainMenu();
        int choice = menu.getPlayerChoice();
        switch (choice) {
            case 1:
                normalMode = new NormalMode(1);
                break;
            case 2:
                normalMode = new NormalMode(2);
                break;
            case 3:
                modeArtillerie = new ModeArtillerie();
                break;
            case 4:
                displaySavedGames();
                break;
            default:
                System.exit(0);
                break;
        }
    }

    public void saveGame() {
        if (normalMode != null) {
            SaveGamePart.sauvegarderPartie(normalMode);
        } else if (modeArtillerie != null) {
            SaveGamePart.sauvegarderPartie(modeArtillerie);
        }
        System.out.println("État du jeu sauvegardé.");
    }

    public void loadGame(String fileName) {
        Object loadedGame = SaveGamePart.chargerPartie(fileName);
        if (loadedGame instanceof NormalMode) {
            this.normalMode = (NormalMode) loadedGame;
            System.out.println("Normal Mode game loaded.");
            normalMode.resumeGame();
        } else if (loadedGame instanceof ModeArtillerie) {
            this.modeArtillerie = (ModeArtillerie) loadedGame;
            System.out.println("Artillery Mode game loaded.");
        }
    }
}
