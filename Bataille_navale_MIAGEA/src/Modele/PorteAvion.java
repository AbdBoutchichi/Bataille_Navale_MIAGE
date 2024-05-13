package Modele;
import java.io.Serializable;

public class PorteAvion extends Boat implements Serializable{
    public PorteAvion(int x, int y, char orientation){
        super(x,y,orientation,5,"PorteAvion");
    }

    public PorteAvion(Player plr){
        super(plr, 5,"PorteAvion");
    }

}
