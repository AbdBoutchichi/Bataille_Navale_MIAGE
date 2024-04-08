package Modele;
import java.util.Scanner;

public class ModeArtillerie extends NormalMode {
    
    private Scanner scanner;
    private Board board;
    private Menu menu;
    private boolean gameOver;
    private boolean isPlayer1Turn;
    private int choice;
    private Player player1;
    private Player player2;
    private PlayerComputer bot;

    private Threader th;


    private boolean lifeOfThread1;
    private boolean lifeOfThread2;
    private int y;
    private int x;
    private boolean val;

    private Thread counterX = new Thread(() -> {
        try {
            System.out.println("Le compteur se lance");
            while (lifeOfThread1) { // Défilement automatique jusqu'à ce que le thread soit interrompu
                System.out.println("" + x);
                Thread.sleep(100); // Défilement toutes les 1 seconde
                x++; // Incrémentation de la valeur de la coordonnée
                if(x==10) x=0; // Si on atteint 10, on revient a 0
            }
        } catch (InterruptedException e) {
            System.err.println("X vaut :" + x);
        }
        System.out.println ("Fin du décompte Y");
    });

    Thread counterY = new Thread(() -> {
        try {
            System.out.println("Le compteur se lance");
            while (lifeOfThread2) {
             // Défilement automatique jusqu'à ce que le thread soit interrompu
                System.out.println("" + y);
                Thread.sleep(100); // Défilement toutes les 1 seconde
                y++; // Incrémentation de la valeur de la coordonnée
                if(y==10) y=0;
            }
            
        } catch (InterruptedException e) {
            System.err.println("Y vaut :" + y);
            
        }
        System.out.println ("Fin du décompte Y");
    });

    
    public ModeArtillerie() {
        super();
        this.scanner = new Scanner(System.in);
        this.menu = new Menu();

        x = 0;
        y = 0;


        
        
        

        // Création des joueurs
        System.out.println("Entrez le nom du Joueur 1:");
        String name1 = scanner.nextLine();
        player1 = new Player(name1);

        System.out.println("Entrez le nom du Joueur 2:");
        String name2 = scanner.nextLine();
        player2 = new Player(name2);
        initGameArtillerie(player1, player2);
    }

    

    private void initGameArtillerie(Player jr1, Player jr2) {
            
        // Initialisation du plateau
        this.board = new Board(10, jr1, jr2);

        // Placement des bateaux (automatique ou manuelle)
        //choix du joueur 1 
        if(menu.modePlacement()==1) this.placeBoats(jr1, jr2);
        else jr1.placeBoatsRandomly(this.board);
        //choix du joueur 2
        if(menu.modePlacement()==1) this.placeBoats(jr2, jr1);
        else jr2.placeBoatsRandomly(this.board);

        // Début du jeu
        startGameArtillerie(jr1, jr2);
    }
    

    

    private void playTurnArtillerie(Player jr1, Player jr2) {
        System.out.println("Au tour de " + jr1.getName() + ".");
        th = new Threader();
        x = th.roue();

        th = new Threader();
        
        y = th.roue();
        
        System.out.println("tire souhaité en " + x + ";" + y);
        if(jr1.canShoot(x, y)){
            jr1.shootAt(x, y);
            if(!jr2.isTouch(y, x)){
                isPlayer1Turn = !isPlayer1Turn;
            }
            
        }else{
            System.out.println("Raté !");
        }
        
        
    }

    public void startGameArtillerie(Player jr1, Player jr2) {
        System.out.println("Début de la Bataille Navale!");
        isPlayer1Turn = true;

        while (super.checkWinConditions(jr1, jr2)) {
            System.out.println((isPlayer1Turn ? jr1.getName() : jr2.getName()) + "'s turn");
            board.ShowBoardBoat(isPlayer1Turn ? jr1 : jr2, isPlayer1Turn ? jr2 : jr1);
            
            if(isPlayer1Turn){
                while(isPlayer1Turn && super.checkWinConditions(jr1, jr2)){
                    board.ShowBoardShot(jr1, jr2);
                    System.out.println(jr1.getName() + "Presse entrer pour lancer les roues");
                    scanner.nextLine();
                    playTurnArtillerie(jr1,jr2);}
            } else {
                while(!isPlayer1Turn && checkWinConditions(jr1, jr2)){
                    board.ShowBoardShot(jr2, jr1);
                    System.out.println(jr2.getName() + "Presse entrer pour lancer les roues");
                    scanner.nextLine();
                    playTurnArtillerie(jr2,jr1);
                    System.out.println("est ce au joueur 2:" + !isPlayer1Turn);
                }
            }
        }

        System.out.println("Le jeu est terminé.");
        System.out.println(player1.isAlive() ? jr1.getName() + " gagne!" : jr2.getName() + " gagne!");
    }

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
}