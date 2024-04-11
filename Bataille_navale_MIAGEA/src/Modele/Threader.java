package Modele;

import java.util.Scanner;

public class Threader {

    int x;
    Scanner scanner;

    boolean lifeOfThread;
    //fait avancer la valeur de x
    Thread counterX = new Thread(() -> {
        try {
            System.out.println("Le compteur se lance");
            while (true) {
             // Défilement automatique jusqu'à ce que le thread soit interrompu
                System.out.println("" + x);
                Thread.sleep(100); // Défilement toutes les 1 seconde
                x++; // Incrémentation de la valeur de la coordonnée
                if(x==10) x=0;
            }
            
        } catch (InterruptedException e) {
            System.err.println("X vaut :" + x);
            
        }
        System.out.println ("Fin du décompte X");
    });


    public Threader(){
        x=0;
        scanner = new Scanner(System.in);
        
    }

    //lance une roue en arriere plan et attend que le joueur l'arrete pour rendre une valeur
    public int roue(){
        counterX.start();
        scanner.nextLine();
        counterX.interrupt();
        return x;
    }


}
