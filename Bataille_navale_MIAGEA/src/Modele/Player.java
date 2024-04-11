package Modele;

//import java.io.BufferedReader;
import java.util.*;
import java.util.Random;

/**
 *
 */
public class Player {

    private Random random = new Random();

    public String name;
    //public Board board;
    public int NbreTotalShot;
    public int NbreShotSuccess;
    public int NbreBateauShot;
    public Cell LastCellShot = new Cell(-1, -1);
    //public BufferReader buffer;
    public List<Cell> cellsShot;
    public List<Boat> boats;

    public Player(String nom) {
        this.name = nom;
        //this.board = board;
        this.NbreTotalShot = 0;
        this.NbreBateauShot = 0;
        this.NbreShotSuccess = 0;
        this.LastCellShot = null;
        //this.buffer = new BufferedReader(new InputStreamReader(System.in));
        this.cellsShot = new ArrayList<Cell>();
        this.boats = new ArrayList<Boat>();
    }

    

    /**
     * @return
     */
    public void incrementNbreTotalShot() {
        NbreTotalShot++;
    }

    /**
     * @param cell
     * @param boat
     */
    public boolean placeBateau(Board brd, int x, int y, char orientation, int taille, String nom) {
        // Création du bateau selon le type spécifié
        Boat boat = createBoat(x, y, orientation, taille, nom);
        System.out.println("Position souhaitais :" + boat.posX +";"+ boat.posY + " avec l'orientation:"+boat.getOrientation());
        
        
        // Vérification si le bateau peut être placé
        if (canPlace(x, y, taille, orientation)) {
            System.out.println();
            boats.add(boat);
            return true;
        } else {
            return false;
        }
    }

    public boolean placeBateau(Board brd, Boat b) {
        
        System.out.println("Position souhaitais :" + b.getPosX() +";"+ b.getPosY() + " avec l'orientation:"+b.getOrientation());
        
        
        // Vérification si le bateau peut être placé
        if (this.canPlace(b.getPosX(), b.getPosY(), b.getSize(), b.getOrientation())) {
            System.out.println(b.getName());
            boats.add(b);
            return true;
        } else {
            return false;
        }
    }
    
    Boat createBoat(int x, int y, char orientation, int taille, String nom) {
        switch(nom.toLowerCase()) {
            case "PorteAvion":
                return new PorteAvion(x, y, orientation);
            case "Croiseur":
                return new Croiseur(x, y, orientation);
            case "ContreTorpilleur":
                return new ContreTorpilleur(x, y, orientation);
            case "SousMarin":
                return new SousMarin(x, y, orientation);
            case "Torpilleur":
                return new Torpilleur(x, y, orientation);
            default:
                return new Boat(x, y, orientation, taille, nom);
        }
    }
    

    /**
     * @return
     */
    public void incrementNbreShotSuccess() {
        NbreShotSuccess++;
    }

    /**
     * @return
     */
    public void incrementNbreBateauShot() {
        NbreBateauShot++;
    }

    /**
     * @return
     */
    /*public Board getBoard() {
        return this.board;
    }*/

    /**
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return
     */
    public void AfficherStats() {
        System.out.println("================================================================================================");
        System.out.println("║                                                                                               |");
        System.out.println("║ Statistiques de jeu de " + this.name + " :                                                      |");
        System.out.println("║                                                                                               |");
        System.out.println("║ Nombre de tirs réalisés : " + this.NbreTotalShot + "                                            |");
        System.out.println("║ Nombre de tirs réussis : " + this.NbreShotSuccess + "                                           |");
        System.out.println("║ Précision : "
                + (double) Math.round(((double) (NbreShotSuccess) / (double) (NbreTotalShot)) * 100.0) + "%                   |");
        // System.out.println("║ Nombre de bateaux adverses détruis : " + NbreBateauShot
        // + "/" + getNbBoats());
        System.out.println("║                                                                                               |");
        System.out.println("================================================================================================|");
    }

    /**
     * @return
     */
    public Cell getLastCellShot() {
        return this.LastCellShot;
    }

    /**
     * @param x
     * @param y
     * @return
     */
    public void setLastCellShot(int x, int y) {
        this.LastCellShot = new Cell(x, y);
    }

    /**
     * @param cells
     * @return
     */
    /*public void shootAt(int x, int y) {
        Cell target = board.getCell(x, y);
        if (target != null && !target.isShot()) {
            board.shoot(target); // Nous supposons que la classe Board a une méthode shoot(Cell cell)
            this.nbreTotalShots++;
            this.lastCellShot = target;

            if (target.hasBoat()) {
                this.nbreShotsSuccess++;
                if (target.getBoat().isSunk()) {
                    this.nbreBateauxShot++;
                }
            }
        }
    }*/

    //Ajoute la cellule sur laquelle le joueur tire a sa liste de tire
    public void shootAt(int x, int y){
        cellsShot.add(new Cell(x,y));
        System.out.println(name + "tire en " + x + ";" + y);
    }

    //renvoie la liste des cellules sur lesquelles il a tiré
    public Cell[] getCellsShot() {
        Cell[] shot = new Cell[cellsShot.size()];
        for (int i = 0; i < cellsShot.size(); i++) {
            shot[i] = cellsShot.get(i);
        }
        return shot;
    }

    public List<Boat> getCellsBoats(){
        return boats;
    }

    public boolean isTouch(int x, int y){
        for(Boat b : boats){
            //verifie si un bateau a été touché
            if (b.isPosition(y, x)) {
                System.out.println("Touché");
                b.touch();
                //verifie si il coule
                if(b.isSunk()){
                    System.out.println("Coulé");
                } 

                return true;
            }
        }

            System.out.println("Raté");
            return false;

        
    }


    //Verifie si un bateau est a cette position
    public boolean over(int x, int y, int taille, char o){
        if(o == 'H' || o == 'h'){ 
            for(int i = 0;  i < taille; i++){
                for(int j = 0; j < boats.size(); j++){
                    if(boats.get(j).isPosition(x+i, y)) 
                    {return true;}
                }
            }
        } 
        else if(o == 'V' || o == 'v'){
            for(int i = 0;  i < taille; i++){
                for(int j = 0; j < boats.size(); j++){
                    if(boats.get(j).isPosition(x, y+i)) 
                    {return true;}
                }
            }
        }
        return false;
    }

    //verifie si la position est hors du plateau
    public boolean out(int x, int y, int taille, char o, int bordure){
        int valX;
        int valY;
        for(int i = 0;  i < taille; i++){
            valY = y + (o=='V' || o=='v'?i:0);
            valX = x + (o=='H' || o=='h'?i:0);
            if(valX>=bordure || valY>=bordure) return true;
        }
        return false;
    }

    //verifie si la position n'a pas de bateau adjacent
    public boolean neighbor(int x, int y, int taille, char o){
        if(o == 'H' || o == 'h'){ 
            for(int i = 0;  i < taille; i++){
                for(int j = 0; j < boats.size(); j++){
                    if(boats.get(j).isNeighbor(x+i, y)) 
                    {return true;}
                }
            }
        } 
        else if(o == 'V' || o == 'v'){
            for(int i = 0;  i < taille; i++){
                for(int j = 0; j < boats.size(); j++){
                    if(boats.get(j).isNeighbor(x, y+i)) 
                    {return true;}
                }
            }
        }
        return false;
    }

    

    //verifie si on peut placer un bateau sur plateau a une position (x;y) en fonction d'un bateau
    public boolean canPlace(Boat b){
        
        if(!out(b.getPosX(), b.getPosY(), b.getSize(), b.getOrientation(), 10) && !over(b.getPosX(), b.getPosY(), b.getSize(), b.getOrientation()) && !neighbor(b.getPosX(), b.getPosY(), b.getSize(), b.getOrientation())){
            return true;
        }
        return false;
    }


    //verifie si on peut placer un bateau sur plateau a une position (x;y) en fonction des données d'un bateau
    public boolean canPlace(int x, int y, int taille, char orient){
        
        if(!out(x, y, taille, orient, 10) && !over(x, y, taille, orient) && !neighbor(x, y, taille, orient)){
            return true;
        }
        return false;
    }


    //Verifie si le joueur a encore des bateaux en vie 
    public boolean isAlive(){
        for(int i = 0; i<boats.size(); i++){
            if(!boats.get(i).isSunk()){return true;};
        }
        return false;
    }


    /**
     * @param brd
     */

    //Place les bateaux de façon aleatoire sur le plateau
    public void placeBoatsRandomly(Board brd) {
        char o;
        String[] boatNames = {"PorteAvions", "Croiseur", "ContreTorpilleur", "SousMarin", "Torpilleur"};
        int[] shipSizes = {5, 4, 3, 3, 2};
        for (int i = 0; i<5; i++) {
            boolean placed = false;
            while (!placed) {
                
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                boolean horizontal = random.nextBoolean();
                o = horizontal? 'H' : 'V';
                if (canPlace(x, y, shipSizes[i], o)) {
                    
                    
                   
                    placed = placeBateau(brd, x, y, o, shipSizes[i], boatNames[i]);;
                }
            }
        }
        brd.ShowBoardBoat(this, this);
    }

    public void placeBoatsRand(Board brd) {
        
        new PorteAvion(brd, this);
        new Torpilleur(brd, this);
        new ContreTorpilleur(brd, this);
        new Croiseur(brd, this);
        new SousMarin(brd, this);

        brd.ShowBoardBoat(this, this);
    }

    //Verifie que le joueur n'a pas deja tiré à la position
    public boolean canShoot(int x, int y){
        if(x > 9 || y > 9 || x < 0 || y < 0) return false;
        for (Cell c : cellsShot) {
            if(c.getX()==x && c.getY()==y) return false;
        }
        return true;
    }

}