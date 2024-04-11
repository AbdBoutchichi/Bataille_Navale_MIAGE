package Modele;

public class ContreTorpilleur extends Boat{
    public ContreTorpilleur(int x, int y, char orientation) {
        super(x, y, orientation,3, "ContreTorpilleur" );
    }

    public ContreTorpilleur(Board brd, Player plr){
        super(brd, plr, 3, "ContreTorpilleur");
    }

}
