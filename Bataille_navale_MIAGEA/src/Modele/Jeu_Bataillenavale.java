package Modele;

import java.util.*;

/**
 * 
 */
public class Jeu_Bataillenavale {

    /**
     * Default constructor
     */
    public Jeu_Bataillenavale() {
        
        //playerPlay=null;
        winnerPlayer=null;
        //buffer=null;
        scanner = new Scanner(System.in);
        menu = new Menu();

        menu.ShowMenu();
        menu.displayMainMenu();

        switch (menu.getPlayerChoice()) {
            case 1:
                
                new NormalMode(1);

            case 2:
                
                new NormalMode(2);

            case 3:
                
                new ModeArtillerie();

            case 4:
            
                break;

            case 5:
                
                break;

            default:
                break;
        }

        


        
    }

    public Scanner scanner;
    private Menu menu;

    /**
     * 
     */
    //public int playerPlay;

    /**
     * 
     */
    public String winnerPlayer;

    /**
     * 
     */
    //public BufferReader buffer;

    /**
     * @return
     */
    public void start() {
        
    }

    /*public static void main(String[] args){
        new Jeu_Bataillenavale();
    }*/

    ///**
    // * @return
    // */
    //public void Jouer() {
    //    // TODO implement here
    //    return null;
    //}

    ///**
    // * @return
    // */
    //public boolean over() {
    //    // TODO implement here
    //    return false;
    //}

    ///**
    // * @return
    // */
    //public void end() {
    //    // TODO implement here
    //    return null;
    //}

    ///**
    // * @return
    // */
    //public boolean isTurn() {
    //    // TODO implement here
    //    return false;
    //}

    ///**
    // * @return
    // */
    //public void SetFirstPlayer() {
    //    // TODO implement here
    //    return null;
    //}

    ///**
    // * @return
    // */
    //public void AffichageRegleJeu() {
    //    // TODO implement here
    //    return null;
    //}

}