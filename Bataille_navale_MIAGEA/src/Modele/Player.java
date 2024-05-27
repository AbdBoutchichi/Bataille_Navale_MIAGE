package Modele;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Player implements Serializable{

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
    public int incrementNbreTotalShot() {
        NbreTotalShot++;
        return NbreTotalShot;
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

    public boolean placeBateau(Boat b) {
        
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

    public boolean placeBateau(int x, int y, char orientation, int taille, String nom) {
        
        Boat b = createBoat(x, y, orientation, taille, nom);
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

    public boolean placeBateau(String x, String y, char orientation, int taille, String nom) {
        
        Boat b = createBoat(newPos(convertPos(x), convertPos(y), taille, orientation, 10)[0], newPos(convertPos(x), convertPos(y), taille, orientation, 10)[1], orientation, taille, nom);
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
    public int incrementNbreShotSuccess() {
        NbreShotSuccess++;
        return NbreShotSuccess;
    }

    /**
     * @return
     */
    public int incrementNbreBateauShot() {
        NbreBateauShot++;
        return NbreBateauShot;
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

    public boolean hasWon() {
        return !this.isAlive();
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
    public void shootAt(int x, int y, Player adv){
        cellsShot.add(new Cell(x,y));
        System.out.println(name + " tire en " + x + ";" + y);
        if(adv.verifTouch(x, y)){
            NbreShotSuccess++;
        }
        NbreTotalShot++;
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

    // public boolean isTouch(int x, int y){
    //     for(Boat b : boats){
    //         //verifie si un bateau a été touché
    //         if (b.isPosition(x, y)) {
    //             System.out.println("Touché");
    //             b.touch();
    //             //verifie si il coule
    //             if(b.isSunk()){
    //                 System.out.println("Coulé");
    //             } 

    //             return true;
    //         }
    //     }

        

    //         System.out.println("Raté");
    //         return false;

        
    // }

    public boolean isTouch(int x, int y) {
        for (Boat b : boats) {
            if (b.isPosition(x, y)) {
                b.touch();
                if (b.isSunk()) {
                    System.out.println("Le bateau " + b.getName() + " a coulé!");
                }
                System.out.println("Touché à " + x + "," + y);
                return true;
            }
        }
        System.out.println("Raté à " + x + "," + y);
        return false;
    }

    public boolean verifTouch(int x, int y) {
        for (Boat b : boats) {
            if (b.isPosition(x, y)) {
                
                
                return true;
            }
        }
        
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
        if(x < 0 || y < 0) return true;
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

        System.out.println(x+" ; "+y);
        return false;
    }

    public boolean canPlace(String posX, String posY, int taille, char orient){
        int x = newPos(convertPos(posX), convertPos(posY), taille, orient, 10)[0];
        int y = newPos(convertPos(posX), convertPos(posY), taille, orient, 10)[1];
        if(!out(x, y, taille, orient, 10) && !over(x, y, taille, orient) && !neighbor(x, y, taille, orient)){
            return true;
        }

        System.out.println(x+" ; "+y);
        return false;
    }


    //Verifie si le joueur a encore des bateaux en vie 
    public boolean isAlive(){
        for(int i = 0; i<boats.size(); i++){
            if(!boats.get(i).isSunk()){return true;};
        }
        return false;
    }

    public boolean isSunk(int x, int y) {
        for (Boat boat : boats) {
            if (boat.isPosition(x, y) && boat.isSunk()) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotDead() {
    for (Boat boat : boats) {
        if (!boat.isSunk()) {
            return true;
        }
    }
    return false; // All ships are sunk
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

    public void placeBoatsRand() {
        this.boats.clear();
        new PorteAvion(this);
        new Torpilleur(this);
        new ContreTorpilleur(this);
        new Croiseur(this);
        new SousMarin(this);

        
    }

    //Verifie que le joueur n'a pas deja tiré à la position
    public boolean canShoot(int x, int y){
        if(x > 9 || y > 9 || x < 0 || y < 0) return false;
        for (Cell c : cellsShot) {
            if(c.getX()==x && c.getY()==y) return false;
        }
        return true;
    }

    public int[] newPos(int x, int y, int size, char orientation, int dimension){
        int[] pos = {x, y};
        System.out.println("Dans newPos:"+pos[0]+" ; "+pos[1]);
        if (!out(x, y, size, orientation, dimension))
        
            return pos;
        else if(x < 0 || y < 0){
            pos[0] = -1;
            pos[1] = -1;}
        else if (orientation == 'H') 
            pos[0] = dimension - size;
        else 
            pos[1] =dimension - size;


        
        return pos;

    }

    public void removeBoat(String name){
        for (int i = 0; i< boats.size(); i++) {
            if (boats.get(i).getName()== name) boats.remove(i);
        }
        
    }

    public void removeAllShot(){
        List<Cell> suppression = new ArrayList<Cell>();
        for (int i = 0; i < cellsShot.size(); i++) {
            suppression.add(cellsShot.get(i));
        }
        cellsShot.removeAll(suppression);
    }

    public void removeAllBoat(){
        List<Boat> suppression = new ArrayList<Boat>();
        for (int i = 0; i < boats.size(); i++) {
            suppression.add(boats.get(i));
        }
        boats.removeAll(suppression);
    }

    public boolean exist(String name){
        for (Boat boat : boats) {
            if (boat.getName()== name) return true;
        }
        return false;
    }

    public Boat recupBoat(String name){
        for (Boat boat : boats) {
            if (boat.getName()== name) return boat;
        }
        return null;
    }

    public int convertPos(String pos){
        System.out.println("convertion de " + pos);
        if(pos.length()>1) return -1;
        switch (pos) {
            case "0":
                return 0;
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            default:
                return -1;
        }
    }



    public Object getShipName(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShipName'");
    }

}