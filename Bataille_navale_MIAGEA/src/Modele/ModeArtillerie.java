package Modele;

import java.lang.Thread.*;
import java.util.Scanner;

public class ModeArtillerie extends NormalMode{

    public ModeArtillerie(){}

    

    public int tireArtX(Player plr1, Player plr2)  {
        int x = -1;
        int y = -1;
        Scanner sc = new Scanner(System.in);
        boolean val = true;

        while(val){
            
        }
        
    }

    public int tireArtY(Player plr1, Player plr2)  throws InterruptedException{
        
        Thread counterThread = new Thread(() -> {
            y= -1;
            try {
                while(x<0 && x> 9){
                    if (y== 9 ) x=0;
                    else y++;
                    System.out.println("X vaut " + x);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread keyboardThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            try {
                while (true) {
                    if (scanner.nextLine().isEmpty()) {
                        System.out.println("Y vaut " + y);
                    } 
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                scanner.close();
            }
        });
        counterThread.start();
        keyboardThread.start();

        System.out.println("les threads sont fini et y vaut " + y);

        return x;
    }

    private void playTurn(Player jr1,Player jr2) {
        System.out.println("Au tour de " + jr1.getName() + ".");
        int x = -1;
        int y = -1;
        if (jr1 instanceof PlayerComputer) {
            // Gère un tour automatique pour PlayerComputer
            System.out.println("tour NPC");
            ((PlayerComputer) jr1).chooseNextMove(jr2);
            board.ShowBoardShot(jr1, jr2);
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
        if(!jr2.isTouch(y, x)){
            isPlayer1Turn = !isPlayer1Turn;
        }
        
    }


}
