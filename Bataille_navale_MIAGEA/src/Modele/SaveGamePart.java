package Modele;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */
public class SaveGamePart {

    /**
     * Default constructor
     */
    public SaveGamePart() {
    }

    /**
     * 
     */
   // public type field;

    /**
     * 
     */
    //public void Attribute1;

    /**
     * @return
     */
    public File createFile(Player plr1, Player plr2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = dateFormat.format(new Date()) + plr2.getName() + plr2.getName() + ".sav";
        File file = new File("Sauvegarde", fileName);
        return file;
    }

    /**
     * @return
     */
    public void updateFiles(File file) throws IOException {
        
    }

    /**
     * @return
     */
    public void loadFile() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    public void deleteFile() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    public void deleteAllFiles() {
        // TODO implement here
        return;
    }

}