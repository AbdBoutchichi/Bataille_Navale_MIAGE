package Modele;
import java.util.Scanner;

/**
 * 
 */
public class NormalMode {

    /**
     * Default constructor
     */
    private Scanner scanner;
    private Board board;
    private Player player1;
    private Player player2;
    private Menu menu;
    private boolean gameOver;
    private boolean isPlayer1Turn;


        public NormalMode() {
            this.scanner = new Scanner(System.in);
            this.menu = new Menu();
            initGame();
        }
    
        private void initGame() {
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
            } else if (choice == 2) {
                menu.getDifficultyChoice();
                int difficulty = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                player2 = new PlayerComputer(difficulty);
            }
    
            // Initialisation du plateau
            this.board = new Board(10, player1, player2);

            board.ShowBoardPlayer(player2, player1);
    
            // Placement des bateaux
            placeBoats(player1);
            placeBoats(player2);

            board.ShowBoardPlayer(player2, player1);
            // Début du jeu
            startGame();
        }
    
        public void startGame() {
            System.out.println("Début de la Bataille Navale!");
            isPlayer1Turn = true;
    
            while (!checkWinConditions()) {
                System.out.println((isPlayer1Turn ? player1.getName() : player2.getName()) + "'s turn");
                board.ShowBoardPlayer(isPlayer1Turn ? player1 : player2, isPlayer1Turn ? player2 : player1);
                
                playTurn(isPlayer1Turn ? player1 : player2);
                isPlayer1Turn = !isPlayer1Turn;
            }
    
            System.out.println("Le jeu est terminé.");
            System.out.println(player1.isAlive() ? player1.getName() + " gagne!" : player2.getName() + " gagne!");
        }

        private boolean checkWinConditions() {
            // Vérifie si tous les bateaux de player1 sont coulés
            boolean player1Lost = player1.getCellsBoats().stream().allMatch(Boat::isSunk);
            
            // Vérifie si tous les bateaux de player2 sont coulés
            boolean player2Lost = player2.getCellsBoats().stream().allMatch(Boat::isSunk);
    
            // Si l'un des joueurs a perdu tous ses bateaux, le jeu est terminé
            return player1Lost || player2Lost;
        }
    

    private void placeBoats(Player player) {

        if (player instanceof PlayerComputer){
            ((PlayerComputer) player).placeShipsRandomly();
        }else{
        int[] boatSizes = {5, 4, 3, 3, 2};
        String[] boatNames = {"Porte-avions", "Croiseur", "Contre-torpilleur", "Sous-marin", "Torpilleur"};
    
        for (int i = 0; i < boatSizes.length; i++) {
            boolean placed = false;
            while (!placed) {
                System.out.println(player.getName() + ", placez votre " + boatNames[i] + " (Taille: " + boatSizes[i] + ")");
                System.out.print("Entrez la position de départ x (par exemple, 0 à 9): ");
                int x = scanner.nextInt();
                scanner.nextLine();
    
                System.out.print("Entrez la position de départ y (par exemple, 0 à 9): ");
                int y = scanner.nextInt();
                scanner.nextLine();
    
                System.out.print("Entrez l'orientation (V pour verticale ou H pour horizontale): ");
                String orientationInput = scanner.nextLine(); // Lit la ligne entière pour l'orientation
                char orientation = orientationInput.charAt(0);
    
                 if (board.canPlaceBoat(new Boat(x, y, orientation, boatSizes[i], orientationInput))) {
                    player.placeBateau(board, x, y, orientation, boatSizes[i], orientationInput);
                     placed = true;
                 } else {
                     System.out.println("Placement invalide. Veuillez réessayer.");
                 }
                placed = true;
            }
        }
    }
}
    
    

    private void playTurn(Player player) {
        System.out.println("Au tour de " + player.getName() + ".");
        if (player instanceof PlayerComputer) {
            // Gère un tour automatique pour PlayerComputer
            ((PlayerComputer) player).chooseNextMove();
        } else {
            // Gère un tour pour un joueur humain
            System.out.println("Entrez les coordonnées de votre tir (x, y):");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            player.shootAt(x, y); // Supposons que cette méthode existe dans `Player`
        }
        
    }
    
    private void endGame() {
        menu.displayEndGameOptions();
        int endChoice = menu.getPlayerChoice();
        
        switch (endChoice) {
            case 1:
                initGame(); // Cela devrait fonctionner si initGame est défini dans la même classe
                break;
            case 2:
                gameOver = true; // Marque le jeu comme terminé
                break;
            case 3:
                System.exit(0); // Quitte l'application
                break;
        }
    }

}

