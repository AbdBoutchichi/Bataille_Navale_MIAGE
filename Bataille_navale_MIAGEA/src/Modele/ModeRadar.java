package Modele;
import java.io.Serializable;
import java.util.Collection;
import java.util.Scanner;
/**
 * 
 */
public class ModeRadar implements Serializable{

    /**
     * Default constructor
     */
    private transient Scanner scanner;
    private Board board;
    private Menu menu;
    public boolean gameOver;
    private boolean isPlayer1Turn;
    private int choice;
    private Player player1;
    private Player player2;
    private PlayerComputer bot;

    

        public ModeRadar() {

            scanner = new Scanner(System.in);
            this.menu = new Menu();

           
            // Création du joueur 1
            System.out.println("Entrez le nom du Joueur 1:");
            
            player1 = new Player(scanner.nextLine());
            
            System.out.println("Entrez le nom du Joueur 2:");
            
            player2 = new Player(scanner.nextLine());
            initGame(player1, player2);
                
        }
    
        private void initGame(Player jr1, Player jr2) {
            
            // Initialisation du plateau
            this.board = new BoardRadar(10, jr1, jr2);
    
            // Placement des bateaux (automatique ou manuelle)
            //choix du joueur 1 
            if(menu.modePlacement()==1) placeBoats(jr1, jr2);
            else jr1.placeBoatsRandomly(board);
            //choix du joueur 2
            if(menu.modePlacement()==1) placeBoats(jr2, jr1);
            else jr2.placeBoatsRandomly(board);
    
            // Début du jeu
            startGame(jr1, jr2);
        }

        private Object readResolve() {
            this.scanner = new Scanner(System.in);
            return this;
        }

        public void resumeGame() {
            // Assurez-vous que les joueurs sont non null avant de reprendre
            if (player1 == null || (player2 == null && bot == null)) {
                System.out.println("Erreur: Les données de joueur ne sont pas chargées correctement.");
                return; // Sortir de la méthode si les joueurs ne sont pas chargés
            }
            // Reprendre avec les joueurs existants
            if (bot != null) {
                startGame(player1, bot); // Reprendre avec le joueur et l'ordinateur si c'est un jeu PvC
            } else if (player2 != null) {
                startGame(player1, player2); // Reprendre avec deux joueurs si c'est un jeu PvP
            }
        }
        
    
        public void startGame(Player jr1, Player jr2) {
            System.out.println("Début de la Bataille Navale!");
            isPlayer1Turn = true;
    
            while (checkWinConditions(jr1, jr2)) {
                System.out.println((isPlayer1Turn ? jr1.getName() : jr2.getName()) + "'s turn");
                board.ShowBoardBoat(isPlayer1Turn ? jr1 : jr2, isPlayer1Turn ? jr2 : jr1);
                
                if(isPlayer1Turn){
                    while(isPlayer1Turn && checkWinConditions(jr1, jr2)){
                    playTurn(jr1,jr2);
                    System.out.println("est ce au joueur 1:" + isPlayer1Turn);}
                } else {
                    while(!isPlayer1Turn && checkWinConditions(jr1, jr2)){playTurn(jr2,jr1);
                        System.out.println("est ce au joueur 2:" + !isPlayer1Turn);}
                }
                
                
            }
    
            System.out.println("Le jeu est terminé.");
            System.out.println(player1.isAlive() ? jr1.getName() + " gagne!" : jr2.getName() + " gagne!");
            endGame();
        }

        public boolean checkWinConditions(Player jr1, Player jr2) {
            if (jr1 == null || jr2 == null) {
                System.out.println("Un des joueurs est null, impossible de vérifier les conditions de victoire.");
                return false;
            }
            return jr1.isAlive() && jr2.isAlive();
        }

        // Fin du jeu
        //System.out.println("Le jeu est terminé. " + (player1.isAlive() ? player1.getName() : player2.getName()) + " a gagné !");
    //}

    public void placeBoats(Player plr1, Player plr2) {
        int[] boatSizes = {5, 4, 3, 3, 2};
        String[] boatNames = {"PorteAvions", "Croiseur", "ContreTorpilleur", "SousMarin", "Torpilleur"};
    
        for (int i = 0; i < boatSizes.length; i++) {
            boolean placed = false;
            this.board.ShowBoardBoat(plr1, plr2);
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
    
    

    // private void playTurn(Player jr1, Player jr2) {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.println("Au tour de " + jr1.getName() + ".");
    //     int x = -1;
    //     int y = -1;
    
    //     board.ShowBoardShot(jr1, jr2);
    
    //     // Gère un tour pour un joueur humain ou automatique
    //     if (jr1 instanceof PlayerComputer) {
    //         System.out.println("Tour automatique de l'ordinateur.");
    //         ((PlayerComputer) jr1).chooseNextMove(jr2);
    //     } else {
    //         boolean validShot = false;
    //         while (!validShot) {
    //             System.out.println("Entrez les coordonnées de votre tir (x, y):");
    //             x = scanner.nextInt();
    //             y = scanner.nextInt();
    //             scanner.nextLine(); // nettoyer le buffer d'entrée
    //             validShot = jr1.canShoot(x, y);
    //             if (!validShot) {
    //                 System.out.println("Vous avez déjà tiré sur cette case ou elle sort du plateau. Veuillez donner de nouvelles coordonnées.");
    //             }
    //         }
    //         jr1.shootAt(x, y); // Effectuer le tir
    //         System.out.println("Tir en " + x + ";" + y);
    //         if (jr2.isTouch(y, x)) {
    //             System.out.println("Touché");
    //         } else {
    //             System.out.println("Manqué");
    //         }
    //     }
    
    //     //askForSave();
    
    //     // Changement de tour
    //     if (!jr2.isTouch(y, x)) {
    //         isPlayer1Turn = !isPlayer1Turn;
    //     }
    //     System.out.println("Au tour de " + (isPlayer1Turn ? jr1.getName() : jr2.getName()) + ".");
    // }

    private void playTurn(Player jr1, Player jr2) {
        System.out.println("Au tour de " + jr1.getName() + ".");
        int x = -1;
        int y = -1;
    
        board.ShowBoardShot(jr1, jr2);
    
        if (jr1 instanceof PlayerComputer) {
            // Si le joueur est un ordinateur, exécutez son mouvement automatiquement.
            ((PlayerComputer) jr1).makeMove(jr2);
        } else {
            // Interaction avec l'utilisateur pour un joueur humain.
            boolean validShot = false;
            while (!validShot) {
                System.out.println("Entrez les coordonnées de votre tir (colonne x, ligne y):");
                x = scanner.nextInt();  // D'abord x, la coordonnée de la colonne.
                y = scanner.nextInt();  // Ensuite y, la coordonnée de la ligne.
                scanner.nextLine();  // Nettoyer le buffer d'entrée.
                validShot = jr1.canShoot(x, y);
                if (!validShot) {
                    System.out.println("Vous avez déjà tiré sur cette case, ou elle sort du plateau. Veuillez donner de nouvelles coordonnées.");
                }
            }
            jr1.shootAt(x, y, jr2); // Enregistrement du tir effectué par le joueur.
        }
    
        // Vérifier si le tir est réussi.
        boolean hit = jr2.isTouch(x, y);
        System.out.println(hit ? "Touché" : "Manqué");
    
        // Changement de tour seulement si le tir est manqué.
        if (!hit) {
            isPlayer1Turn = !isPlayer1Turn;
        }
    
        System.out.println("Au tour de " + (isPlayer1Turn ? jr1.getName() : jr2.getName()) + ".");
    }
    
    
    
    
    
    
    
    
    public void endGame() {
        menu.displayEndGameOptions();
        int endChoice = menu.getPlayerChoice();

        player1.removeAllBoat();
        player2.removeAllBoat();
        player1.removeAllShot();
        player2.removeAllShot();
        
        switch (endChoice) {
            case 1:
                
                    
                this.initGame(player1, player2);
                
            case 2:
                gameOver = true;
                break;
            case 3:
                System.exit(0);
                break;
        }
    }



}