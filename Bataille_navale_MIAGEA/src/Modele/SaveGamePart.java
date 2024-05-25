package Modele;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe pour gérer la sauvegarde et le chargement de parties du jeu.
 */
public class SaveGamePart {

    private static final String SAVE_DIRECTORY = "C:\\Users\\lenovo\\git\\Bataille_Navale_MIAGE\\"; 

    /**
     * Sauvegarde une partie dans un fichier unique basé sur un timestamp.
     * @param gamePartie L'état du jeu à sauvegarder.
     */
    public static void sauvegarderPartie(NormalMode gamePartie) {
        String fileName = generateFileName(); // Génère un nom de fichier unique
        try {
            File file = new File(SAVE_DIRECTORY + fileName);
            file.getParentFile().mkdirs(); // Crée les dossiers nécessaires si non existants
            try (FileOutputStream fileOut = new FileOutputStream(file);
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(gamePartie);
                System.out.println("La partie a été sauvegardée dans : " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde du fichier: " + e.getMessage());
        }
    }

    /**
     * Génère un nom de fichier unique pour chaque sauvegarde.
     * @return Le nom du fichier généré.
     */
    private static String generateFileName() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return "game_save_" + df.format(new Date()) + ".sav";
    }

    /**
     * Liste tous les fichiers de sauvegarde disponibles dans le répertoire de sauvegarde.
     * @return Un tableau des fichiers de sauvegarde.
     */
    public static File[] listSavedGames() {
        File dir = new File(SAVE_DIRECTORY);
        return dir.listFiles((dir1, name) -> name.startsWith("game_save_") && name.endsWith(".sav"));
    }

    /**
     * Charge une partie spécifique depuis un fichier de sauvegarde.
     * @param fileName Le nom du fichier à charger.
     * @return L'objet NormalMode chargé, ou null en cas d'erreur.
     */
    public static NormalMode chargerPartie(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_DIRECTORY + fileName))) {
            return (NormalMode) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la partie : " + e.getMessage());
            return null;
        }
    }
}
