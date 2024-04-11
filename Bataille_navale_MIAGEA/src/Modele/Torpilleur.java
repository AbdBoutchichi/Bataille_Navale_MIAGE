package Modele;

public class Torpilleur extends Boat{
    public Torpilleur(int x, int y, char orientation) {
        super(x, y, orientation, 2, "Torpilleur");
    }

    public Torpilleur(Board brd, Player plr){
        super(brd, plr, 2, "Torpilleur");
    }
}
