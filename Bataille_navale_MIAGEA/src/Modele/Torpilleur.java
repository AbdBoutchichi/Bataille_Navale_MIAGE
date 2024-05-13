package Modele;
import java.io.Serializable;

public class Torpilleur extends Boat implements Serializable{
    public Torpilleur(int x, int y, char orientation) {
        super(x, y, orientation, 2, "Torpilleur");
    }

    public Torpilleur(Player plr){
        super(plr, 2, "Torpilleur");
    }
}
