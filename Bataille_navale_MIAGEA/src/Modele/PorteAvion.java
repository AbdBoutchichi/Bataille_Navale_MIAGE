package Modele;

public class PorteAvion extends Boat {
    public PorteAvion(int x, int y, char orientation){
        super(x,y,orientation,5,"PorteAvion");
    }

    public PorteAvion(Player plr){
        super(plr, 5,"PorteAvion");
    }

}
