package Modele;
import java.util.Random;




/**
 * 
 */
public class PlayerComputer extends Player {
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;

    private int difficultyLevel;
    private Random random = new Random();
    private boolean[][] hits = new boolean[10][10]; // Suivre les cases déjà attaquées
    private boolean[][] ships = new boolean[10][10]; // Suivre la position des bateaux de l'adversaire pour simulation
    private int lastHitX = -1; // Dernière position en X d'un tir réussi
    private int lastHitY = -1; // Dernière position en Y d'un tir réussi
    
    public PlayerComputer(int difficulty,  String n) {
        super(n);
        this.difficultyLevel = difficulty;
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
        
        shootAt(x, y);
        adv.isTouch(x, y);
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
                            adv.isTouch(newX, newY);
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
        // Pour cette implémentation, supposons une stratégie basique qui suit les tirs réussis
        if (lastHitX != -1 && lastHitY != -1) {
            // Tenter de suivre la direction d'un navire touché
            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : directions) {
                int newX = lastHitX + dir[0];
                int newY = lastHitY + dir[1];
                if (isValidMove(newX, newY)) {
                    shootAt(newX, newY);
                    adv.isTouch(newX, newY);
                    return;
                }
            }
        }
    }

    public void makeMove(Player adv) {
        chooseNextMove(adv);
    }
}
