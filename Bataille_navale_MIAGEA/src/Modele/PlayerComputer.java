package Modele;
import java.util.Random;
import java.util.Scanner;
import java.io.Serializable;




/**
 * 
 */
public class PlayerComputer extends Player implements Serializable{
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;

    private int difficultyLevel;
    private Random random = new Random();
    private boolean[][] hits = new boolean[10][10];
    private boolean[][] ships = new boolean[10][10];
    private int lastHitX = -1;
    private int lastHitY = -1;
    public boolean playAgain;
    public int[][] map = new int[10][10];
    
    public PlayerComputer(int difficulty,  String n) {
        super(n);
        this.difficultyLevel = difficulty;
        initMap();
    }

    private void initMap(){
        for(int i = 0; i < 10; i++){
            for(int y = 0; y< 10; y++){
                map[i][y] = 0;
            }
        }
    }

    // Placement aléatoire des navires
        public void placeShipsRandomly(Board brd) {
            char o;
            String[] boatNames = {"PorteAvions", "Croiseur", "ContreTorpilleur", "SousMarin", "Torpilleur"};
            int[] shipSizes = {5, 4, 3, 3, 2};
            for (int i = 0; i<5; i++) {
                boolean placed = false;
                while (!placed) {
                    
                    int x = random.nextInt(9);
                    int y = random.nextInt(9);
                    boolean horizontal = random.nextBoolean();
                    if (horizontal == true) o = 'H';
                    else o = 'V';
                    if (canPlace(new Boat(x, y, o, shipSizes[i], boatNames[i]))) {
                        placeBateau(brd, x, y, o, shipSizes[i], boatNames[i]);
                       
                        placed = true;
                    }
                }
            }
            brd.ShowBoardBoat(this, this);
        }
    

    private boolean canPlaceShip(int x, int y, int size, boolean horizontal) {
        for (int i = 0; i < size; i++) {
            if (horizontal) {
                if (x + i >= 10 || ships[x + i][y]) {
                    return false;
                }
            } else {
                if (y + i >= 10 || ships[x][y + i]) {
                    return false;
                }
            }
        }
        return true;
    }


    

    // Décision du prochain mouvement
    public void chooseNextMove(Player adv) {
        playAgain = true;
        switch (this.difficultyLevel) {
            case EASY:
                chooseRandom(adv);
                break;
            case MEDIUM:
                chooseNearHit(adv);
                break;
            case HARD:
                chooseStrategically(adv);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.difficultyLevel);
        }
    }

    private void chooseRandom(Player adv) {
        int x, y;
        do {
            x = random.nextInt(10);
            y = random.nextInt(10);
        } while (hits[x][y]);
        lastHitX = x;
        lastHitY = y;
        shootAt(x, y);
        
        if(adv.isTouch(x, y)){
            hits[x][y] = true;
            playAgain = true;
            map[lastHitX][lastHitY] = 2;
        } else {
            playAgain = false;
            map[lastHitX][lastHitY] = 1;
        }
        
    }

    private void chooseNearHit(Player adv) {
        // Logique améliorée pour choisir à proximité d'un coup réussi
        for (int i = 0; i < hits.length; i++) {
            for (int j = 0; j < hits[i].length; j++) {
                if (hits[i][j] && !allDirectionsChecked(i, j)) {
                    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                    for (int[] dir : directions) {
                        int newX = i + dir[0];
                        int newY = j + dir[1];
                        if (isValidMove(newX, newY)) {
                            shootAt(newX, newY);
                            hits[newX][newY] = true;
                            playAgain = adv.isTouch(newX, newY);
                            return;
                        }
                    }
                }
            }
        }
        chooseRandom(adv);
    }

    private boolean allDirectionsChecked(int x, int y) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (isValidMove(newX, newY)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10 && !hits[x][y];
    }

    private void chooseStrategically(Player adv) {
        if(lastHitX == -1 && lastHitY == -1){
            chooseRandom(adv);
            return;
        }

        int[] newShot = findShoot(lastHitX, lastHitY);
        // Pour cette implémentation, supposons une stratégie basique qui suit les tirs réussis
        if (map[newShot[0]][newShot[1]]==2) {
            
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int i = 0; i<4 ; i++) {
                System.out.println("pour " + newShot[0] +" ; "+ newShot[1] + "la dire possible " + (newShot[0] + directions[i][0]) + " ; " + (newShot[1] + directions[i][1]));
                if((newShot[0] + directions[i][0] == 10 || newShot[1] + directions[i][1] == 10 || newShot[1] + directions[i][1] == -1 || newShot[0] + directions[i][0] == -1)){
                    if(map[newShot[0] - directions[i][0]][ newShot[1] - directions[i][1]] == 0){
                    shootAt(newShot[0] - directions[i][0], newShot[1] - directions[i][1]);
                    lastHitX = newShot[0] - directions[i][0];
                    lastHitY = newShot[1] - directions[i][1];
                    
                    if(adv.isTouch(lastHitX, lastHitY)){
                        playAgain = true;
                        map[lastHitX][lastHitY] = 2;
                        return;
                    } else {
                        playAgain = false;
                        map[lastHitX][lastHitY] = 1;
                        return;
                    }
                    }
                }
                else if(map[newShot[0] + directions[i][0]][ newShot[1] + directions[i][1]] == 2 && map[newShot[1] - directions[i][0]][ newShot[1] - directions[i][1]] == 0){
                    Scanner scan = new Scanner(System.in);
                    //scan.nextLine();
                    shootAt(newShot[0] - directions[i][0], newShot[1] - directions[i][1]);
                    lastHitX = newShot[0] - directions[i][0];
                    lastHitY = newShot[1] - directions[i][1];
                    //scan.nextLine();
                    if(adv.isTouch(lastHitX, lastHitY)){
                        playAgain = true;
                        map[lastHitX][lastHitY] = 2;
                        return;
                    } else {
                        playAgain = false;
                        map[lastHitX][lastHitY] = 1;
                        return;
                    }
                }
            
            }
            
            //int newDir;
            for(int newDir = 0; newDir < 4; newDir++){
                System.out.println("ça mouline !!");
                //newDir = random.nextInt(4);
                if((newShot[0] + directions[newDir][0] ==10 || newShot[1] + directions[newDir][1] ==10 || newShot[1] + directions[newDir][1] ==-1 || newShot[0] + directions[newDir][0] ==-1)){}
                else if(map[newShot[0] + directions[newDir][0]][ newShot[1] + directions[newDir][1]] == 0){
                    shootAt(newShot[0] + directions[newDir][0], newShot[1] + directions[newDir][1]);
                    lastHitX = newShot[0] + directions[newDir][0];
                    lastHitY = newShot[1] + directions[newDir][1];
                    if(adv.isTouch(lastHitX, lastHitY)){
                        playAgain = true;
                        map[lastHitX][lastHitY] = 2;
                        return;
                    } else {
                        playAgain = false;
                        map[lastHitX][lastHitY] = 1;
                        return;
                    }
                }
            }

        } 
        chooseRandom(adv);
    }



    private int[] findShoot(int x, int y){
        if(lockedShot(x, y) && !(map[x][y]==2)){
            for(int i = 0; i< 10; i++){
                for(int j = 0; j< 10; j++){
                    
                    if(map[i][j] == 2 && lockedShot(i, j)){
                        int[] newPos = {i, j};
                        return newPos;
                    }
                }   
            }
        }
        boolean unit = true;
        int newX= random.nextInt(10);
        int newY= random.nextInt(10);
        while(canShoot(newX, newY)){
            newX = random.nextInt(10);
            newY = random.nextInt(10);
        }
        int[] newPos = {newX, newY};
        return newPos;
    }

    private boolean lockedShot(int x, int y){
        System.out.println("val rentré dans lockedShot " + x + " ; " + y);
        if(x == 0 && y == 0){
            System.out.println("val de lockedShot " + (map[x + 1][y] == 0 || map[x][y + 1] == 0));
            return map[x + 1][y] == 0 || map[x][y + 1] == 0;}

        if(x == 9 && y == 9){
            System.out.println("val de lockedShot " + (map[x - 1][y] == 0 || map[x][y - 1] == 0));
            return map[x - 1][y] == 0 || map[x][y - 1] == 0;}
        
        if(x == 0 && y == 9){
            System.out.println("val de lockedShot " + (map[x + 1][y] == 0 || map[x][y - 1] == 0));
            return map[x + 1][y] == 0 || map[x][y - 1] == 0;}
        
        if(x == 9 && y == 0){
            System.out.println("val de lockedShot " + (map[x - 1][y] == 0 || map[x][y + 1] == 0));
            return map[x - 1][y] == 0 || map[x][y + 1] == 0;}

        if(x == 0 && y != 0 || x == 0 && y != 9){
            System.out.println("val de lockedShot " + (map[x + 1][y] == 0 || map[x][y + 1] == 0 || map[x][y - 1] == 0 && !(map[x][y + 1] == 2 && map[x][y - 1]==2) && !(map[x][y + 1] == 2 && map[x][y - 1]==1) && !(map[x][y + 1] == 1 && map[x][y - 1]==2)));
            return map[x + 1][y] == 0 || map[x][y + 1] == 0 || map[x][y - 1] == 0 && !(map[x][y + 1] == 2 && map[x][y - 1]==2) && !(map[x][y + 1] == 2 && map[x][y - 1]==1) && !(map[x][y + 1] == 1 && map[x][y - 1]==2);}

        if(x == 9 && y != 0 || x == 9 && y != 9){
            System.out.println("val de lockedShot " + (map[x - 1][y] == 0 || map[x][y + 1] == 0 || map[x][y - 1] == 0 && !(map[x][y + 1] == 2 && map[x][y - 1]==2) && !(map[x][y + 1] == 2 && map[x][y - 1]==1) && !(map[x][y + 1] == 1 && map[x][y - 1]==2)));
            return map[x - 1][y] == 0 || map[x][y + 1] == 0 || map[x][y - 1] == 0 && !(map[x][y + 1] == 2 && map[x][y - 1]==2) && !(map[x][y + 1] == 2 && map[x][y - 1]==1) && !(map[x][y + 1] == 1 && map[x][y - 1]==2);}

        if(y == 0 && x != 0 || y == 0 && x != 9){
            System.out.println("val de lockedShot " + (map[x][y + 1] == 0 || map[x + 1][y] == 0 || map[x - 1][y] == 0 && !(map[x + 1][y] == 2 && map[x - 1][y]==2) && !(map[x + 1][y] == 1 && map[x - 1][y]==2) && !(map[x + 1][y] == 2 && map[x - 1][y]==1)));
            return map[x][y + 1] == 0 || map[x + 1][y] == 0 || map[x - 1][y] == 0 && !(map[x + 1][y] == 2 && map[x - 1][y]==2) && !(map[x + 1][y] == 1 && map[x - 1][y]==2) && !(map[x + 1][y] == 2 && map[x - 1][y]==1);}

        if(y == 9 && x != 0 || y == 9 && x != 9){
            System.out.println("val de lockedShot " + (map[x][y - 1] == 0 || map[x + 1][y] == 0 || map[x - 1][y] == 0 && !(map[x + 1][y] == 2 && map[x - 1][y]==2) && !(map[x + 1][y] == 1 && map[x - 1][y]==2) && !(map[x + 1][y] == 2 && map[x - 1][y]==1)));
            return map[x][y - 1] == 0 || map[x + 1][y] == 0 || map[x - 1][y] == 0 && !(map[x + 1][y] == 2 && map[x - 1][y]==2) && !(map[x + 1][y] == 1 && map[x - 1][y]==2) && !(map[x + 1][y] == 2 && map[x - 1][y]==1);}

        
            
        
        return (map[x + 1][y] == 0 || map[x - 1][y]==0 || map[x][y + 1]==0 || map[x][y - 1] == 0) && !(map[x + 1][y] == 2 && map[x - 1][y]==2) && !(map[x][y + 1] == 2 && map[x][y - 1]==2) && !(map[x + 1][y] == 2 && map[x - 1][y]==1) && !(map[x][y + 1] == 2 && map[x][y - 1]==1) && !(map[x + 1][y] == 1 && map[x - 1][y]==2) && !(map[x][y + 1] == 1 && map[x][y - 1]==2);
    }                                                                                   

    public void makeMove(Player adv) {
        chooseNextMove(adv);
    }
}
