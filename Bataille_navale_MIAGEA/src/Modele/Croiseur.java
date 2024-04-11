package Modele;

public class Croiseur extends Boat{
    public Croiseur(int x, int y, char orientation){
        super(x,y,orientation,4,"Croiseur");
    }

    public Croiseur(Board brd, Player plr){
        super(brd, plr, 4,"Croiseur");
    }

}
