package Modele;
import java.io.Serializable;

public class Croiseur extends Boat implements Serializable{
    public Croiseur(int x, int y, char orientation){
        super(x,y,orientation,4,"Croiseur");
    }


    //generateur autonome et aléatoire du Croiseur
    public Croiseur(Player plr){
        super(plr, 4,"Croiseur");
    }

}
