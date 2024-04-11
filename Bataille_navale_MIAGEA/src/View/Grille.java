package View;

import Modele.*;
import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Scanner;



public class Grille extends JPanel{

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int CELL_SIZE = 30;
    public Scanner scan;
    public String img = "/Images/Mer.gif";

    public Grille(int dim, Player jr, Player adv){
        scan = new Scanner(System.in);
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(false);
        setLayout(new GridLayout(ROWS, COLS));
        initGrille(jr, adv);
    }

    public void initGrille(Player jr, Player adv){
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Carreaux cell = new Carreaux(row, col);
                cell.setOpaque(true);
                ImageIcon imageI = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
                Image pict;
                
                for (Boat b : jr.getCellsBoats()) {
                    if (b.isPosition(col, row)) {
                        System.out.println(b.getName() + " en " + b.getOrientation() + " à " + col + " ; " + row);
                        imageI = new ImageIcon(getClass().getResource(b.part(col, row)));
                        
                        for (Cell c : adv.cellsShot) {

                                if(c.position(row, col)){
                                    cell.setBackground(Color.red);
                                } else { cell.setBackground(Color.blue); }
                        }
                        pict = imageI.getImage();
                        pict = pict.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
                        imageI = new ImageIcon(pict);
                        cell = new Carreaux(row, col, imageI);
                       
                    } else {  }
                }
                
                

                
            
                this.add(cell);
            }
        }

        
       
        
    }

    public static void main(String[] arg){
        Scanner scan = new Scanner(System.in);
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);

        Player jr = new Player("Bob");
        Player adv = new Player("Bill");

        //jr.boats.add(new PorteAvion(0, 0, 'H'));

        jr.placeBoatsRand(new Board(10, jr, adv));
        //jr.boats.remove(1);
        
        

        frame.add(new Grille(10, jr, adv));

        frame.pack();

        frame.setVisible(true);
        scan.nextLine();

        


    } 
    
}


