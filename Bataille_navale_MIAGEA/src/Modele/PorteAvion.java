package Modele;

public class PorteAvion extends Boat {
    public PorteAvion(int x, int y, char orientation){
        super(x,y,orientation,5,"PorteAvion");
    }

    public PorteAvion(Board brd, Player plr){
        super(brd, plr, 5,"PorteAvion");
    }

}
