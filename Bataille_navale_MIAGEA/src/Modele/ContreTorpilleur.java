package Modele;

public class ContreTorpilleur extends Boat{
    public ContreTorpilleur(int x, int y, char orientation) {
        super(x, y, orientation,3, "ContreTorpilleur" );
    }

    //generateur autonome et aléatoire du ContreTorpilleur
    public ContreTorpilleur(Player plr){
        super(plr, 3, "ContreTorpilleur");
    }

}
