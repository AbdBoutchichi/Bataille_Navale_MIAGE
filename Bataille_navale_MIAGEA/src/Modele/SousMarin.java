package Modele;

public class SousMarin extends Boat{
    public SousMarin(int x, int y, char orientation) {
        super(x, y, orientation, 3, "SousMarin");
    }

    public SousMarin(Player plr){
        super(plr, 3, "SousMarin");
    }
}
