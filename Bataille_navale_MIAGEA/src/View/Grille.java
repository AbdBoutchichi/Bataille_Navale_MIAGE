package View;

import Modele.*;
import java.awt.*;

import javax.swing.*;

import Controler.CarreauxInteract;




public class Grille extends JPanel{

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int CELL_SIZE = 30;
    public JFrame window;
    private ImageIcon backgroundImage;

    public Grille(int dim, Player jr, Player adv, JFrame frame){
        this.setSize(CELL_SIZE * COLS, CELL_SIZE * ROWS);
        this.setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        this.setMaximumSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        
        this.setOpaque(false);
        this.setLayout(new GridLayout(ROWS, COLS));
        window = frame;
        
        initGrilleBoat(jr, adv);
    }

    public Grille(int dim, Player jr, Player adv, Grille grilleBoat, JFrame frame){
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(true);
        setLayout(new GridLayout(ROWS, COLS));
        window = frame;
        initGrilleShot(jr, adv, grilleBoat);
    }


    

    public void initGrilleBoat(Player jr, Player adv){
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Carreaux cell = new Carreaux(row, col);
                ImageIcon imageI;
                Image pict;
                
                for (Boat b : jr.getCellsBoats()) {
                    if (b.isPosition(col, row)) {
                        imageI = new ImageIcon(getClass().getResource(b.part(col, row)));
                        
                        for (Cell s : adv.cellsShot) {

                                if(s.position(row, col)){
                                    cell.setBackground(Color.red);
                                } else { cell.setBackground(Color.blue); }
                        }
                        pict = imageI.getImage();
                        pict = pict.getScaledInstance(CELL_SIZE, CELL_SIZE,  java.awt.Image.SCALE_SMOOTH);
                        imageI = new ImageIcon(pict);
                        cell = new Carreaux(row, col, imageI);
                       
                    } else { 
                        imageI = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
                    }
                    
                }
                
                

                
            
                this.add(cell);
            }
        }

        
       
        
    }

    public void initGrilleShot(Player jr, Player adv, Grille boat){
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                Carreaux cell = new Carreaux(row, col);
                cell.setOpaque(true);
                cell.addActionListener(new CarreauxInteract(col, row, jr, adv, this, boat));
                for (Cell c : jr.cellsShot) {
                    if (c.position(col, row)) {
                        cell.setIcon(null);
                        cell.setBackground(Color.red);
                        
                        for (Boat b : adv.getCellsBoats()) {

                            if(b.isPosition(row, col)){
                                cell.setBackground(Color.green);
                            }
                                
                            

                        }
                        
                    }
                }
                this.add(cell);
            }
        }

        
       
        
    }

    public static void main(String[] arg){
        JFrame frame = new JFrame();
        
        frame.setSize(2000, 1000);

        Player jr = new Player("Bob");
        Player adv = new Player("Bill");

        jr.placeBoatsRand();
        adv.placeBoatsRand();
        

        Grille grille1 = new Grille(10, jr, adv, frame);
        Grille grille2 = new Grille(10, jr, adv, grille1, frame);
        
        frame.add(grille1, BorderLayout.WEST);
        frame.add(grille2, BorderLayout.EAST);

        frame.pack();

        frame.setVisible(true);

        


    } 

    
    
}


