package Modele;

public class SousMarin extends Boat{
    public SousMarin(int x, int y, char orientation) {
        super(x, y, orientation, 3, "SousMarin");
    }

    public SousMarin(Board brd, Player plr){
        super(brd, plr, 3, "SousMarin");
    }
}
