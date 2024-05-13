package Modele;
import java.io.Serializable;

public class ContreTorpilleur extends Boat implements Serializable{
    public ContreTorpilleur(int x, int y, char orientation) {
        super(x, y, orientation,3, "ContreTorpilleur" );
    }

    //generateur autonome et aléatoire du ContreTorpilleur
    public ContreTorpilleur(Player plr){
        super(plr, 3, "ContreTorpilleur");
    }

}
