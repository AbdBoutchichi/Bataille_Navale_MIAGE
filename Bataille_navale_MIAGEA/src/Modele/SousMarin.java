package Modele;
import java.io.Serializable;

public class SousMarin extends Boat implements Serializable{
    public SousMarin(int x, int y, char orientation) {
        super(x, y, orientation, 3, "SousMarin");
    }

    public SousMarin(Player plr){
        super(plr, 3, "SousMarin");
    }
}
