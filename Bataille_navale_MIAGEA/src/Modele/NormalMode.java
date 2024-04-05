package Modele;
import java.util.Scanner;
import java.util.Random;
/**
 * 
 */
public class NormalMode {

    /**
     * Default constructor
     */
    private Scanner scanner;
    private Board board;
    private Menu menu;
    private boolean gameOver;
    private boolean isPlayer1Turn;
    private int choice;
    private Player player1;
    private Player player2;
    private PlayerComputer bot;


        public NormalMode() {
            this.scanner = new Scanner(System.in);
            this.menu = new Menu();
            menu.ShowMenu();
            menu.displayMainMenu();
            int choice = menu.getPlayerChoice();

            // Création du joueur 1
            System.out.println("Entrez le nom du Joueur 1:");
            String name1 = scanner.nextLine();
            
            player1 = new Player(name1);
            
            
            

            

            // Sélection du mode de jeu et création du joueur 2
            if (choice == 1) {
                System.out.println("Entrez le nom du Joueur 2:");
                String name2 = scanner.nextLine();
                player2 = new Player(name2);
                initGame(player1, player2);

            } else if (choice == 2) {
                //defini la difficulté de l'ordinateur
                int difficulty = menu.getDifficultyChoice();
                System.out.println("La difficulté de l'ordinateur sera :" + difficulty);

                bot = new PlayerComputer(difficulty, "Computer");
                
                initGameComputer(player1, bot);
            }
        }
    
        private void initGame(Player jr1, Player jr2) {
            
            // Initialisation du plateau
            this.board = new Board(10, jr1, jr2);
    
            // Placement des bateaux (automatique ou manuelle)
            //choix du joueur 1 
            if(menu.modePlacement()==1) placeBoats(jr1, jr2);
            else jr1.placeBoatsRandomly(board);
            //choix du joueur 2
            if(menu.modePlacement()==1) placeBoats(jr1, jr2);
            else jr1.placeBoatsRandomly(board);
    
            // Début du jeu
            startGame(jr1, jr2);
        }

        private void initGameComputer(Player jr1, PlayerComputer ordi) {
            
            
    
            
    
            System.out.println("le placement");
    
            // Initialisation du plateau
            this.board = new Board(10, jr1, ordi);
            
            // Placement des bateaux du joueur humain
            if(menu.modePlacement()==1)
            placeBoats(jr1, bot);
            else jr1.placeBoatsRandomly(board);

            bot.placeShipsRandomly(board);

            board.ShowBoardBoat(ordi, jr1);
    
            // Début du jeu
            startGame(jr1, ordi);
        }
    
        public void startGame(Player jr1, Player jr2) {
            System.out.println("Début de la Bataille Navale!");
            isPlayer1Turn = true;
    
            while (checkWinConditions(jr1, jr2)) {
                System.out.println((isPlayer1Turn ? jr1.getName() : jr2.getName()) + "'s turn");
                board.ShowBoardBoat(isPlayer1Turn ? jr1 : jr2, isPlayer1Turn ? jr2 : jr1);
                
                if(isPlayer1Turn){
                    while(isPlayer1Turn){
                    playTurn(jr1,jr2, isPlayer1Turn);}
                } else {
                    while(!isPlayer1Turn){playTurn(jr2,jr1, isPlayer1Turn);}
                }
            }
    
            System.out.println("Le jeu est terminé.");
            System.out.println(player1.isAlive() ? jr1.getName() + " gagne!" : jr2.getName() + " gagne!");
        }

        private boolean checkWinConditions(Player jr1, Player jr2) {
            // Si l'un des joueurs a perdu tous ses bateaux, le jeu est terminé
            return jr1.isAlive() && jr2.isAlive();
        }
    

        // Fin du jeu
        //System.out.println("Le jeu est terminé. " + (player1.isAlive() ? player1.getName() : player2.getName()) + " a gagné !");
    //}

    private void placeBoats(Player plr1, Player plr2) {
        int[] boatSizes = {5, 4, 3, 3, 2};
        String[] boatNames = {"PorteAvions", "Croiseur", "ContreTorpilleur", "SousMarin", "Torpilleur"};
    
        for (int i = 0; i < boatSizes.length; i++) {
            boolean placed = false;
            board.ShowBoardBoat(plr1, plr2);
            while (!placed) {
                System.out.println(plr1.getName() + ", placez votre " + boatNames[i] + " (Taille: " + boatSizes[i] + ")");
                System.out.print("Entrez la position de départ x (par exemple, 0 à 9): ");
                int x = scanner.nextInt();
                System.out.println("le x vaut : "+x);
                scanner.nextLine();
    
                System.out.print("Entrez la position de départ y (par exemple, 0 à 9): ");
                int y = scanner.nextInt();
                scanner.nextLine();
    
                System.out.print("Entrez l'orientation (V pour verticale ou H pour horizontale): ");
                String orientationInput = scanner.nextLine(); // Lit la ligne entière pour l'orientation
                char orientation = orientationInput.charAt(0);
    
                 if (plr1.canPlace(new Boat(x, y, orientation, boatSizes[i], orientationInput))) {
                    plr1.placeBateau(board, x, y, orientation, boatSizes[i],boatNames[i]);
                    placed = true;
                 } else {
                    System.out.println("Placement invalide. Veuillez réessayer.");
                 }
    
                
                
            }
        }
    }
    
    

    private void playTurn(Player jr1,Player jr2, boolean turnOf) {
        System.out.println("Au tour de " + jr1.getName() + ".");
        int x = -1;
        int y = -1;
        if (jr1 instanceof PlayerComputer) {
            // Gère un tour automatique pour PlayerComputer
            System.out.println("tour NPC");
            ((PlayerComputer) jr1).chooseNextMove();

        } else {
            board.ShowBoardShot(jr1, jr2);
            

            // Gère un tour pour un joueur humain
            while (!jr1.canShoot(x, y)) {
                System.out.println("Entrez les coordonnées de votre tir (x, y):");
                x = scanner.nextInt();
                y = scanner.nextInt();
            if (!jr1.canShoot(x, y)) {
                System.out.println("Vous avez deja tiré sur cette case ou elle sort du plateau. \nVeuillez donner de nouvelle coordonnées.");
            }
            }
            
            jr1.shootAt(x, y); // Supposons que cette méthode existe dans `Player`
            
        }
        if(!jr2.isTouch(y, x)){turnOf= !turnOf;}
        
    }
    
    private void endGame() {
        menu.displayEndGameOptions();
        int endChoice = menu.getPlayerChoice();
        
        switch (endChoice) {
            case 1:
                initGame(player1, player2); // Cela devrait fonctionner si initGame est défini dans la même classe
            case 2:
                gameOver = true; // Marque le jeu comme terminé
                break;
            case 3:
                System.exit(0); // Quitte l'application
                break;
        }
    }



}