package View;

import Modele.*;
import java.awt.*;

import javax.swing.*;

import Controler.*;




public class GridPanel extends JPanel{

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int CELL_SIZE = 45;
    private ImageIcon backgroundImage;

    private boolean inert;

    public GridPanel(int dim, Player jr, Player adv, JFrame frame){
        backgroundImage = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        

        this.setSize(CELL_SIZE * COLS, CELL_SIZE * ROWS);
        this.setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        this.setMaximumSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        
        this.setOpaque(false);
        this.setLayout(new GridLayout(ROWS, COLS));

        
        
        initGridPanelBoat(jr, adv);
    }

    public GridPanel(Player jr, Player adv){
        backgroundImage = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(true);
        setLayout(new GridLayout(ROWS, COLS));

        
        
        initGridPanelInert(jr, adv);
    }

    public GridPanel(int dim, Player jr, Player adv, GridPanel grilleBoat, NewGame page){
        backgroundImage = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(true);
        setLayout(new GridLayout(ROWS, COLS));


        initGridPanelShot(jr, adv, grilleBoat, page);
    }

    public GridPanel(int dim, Player jr, String orientation, int size, String name){
        backgroundImage = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(true);
        setLayout(new GridLayout(ROWS, COLS));

        initGridPanelPlacement(jr, orientation, size, name);
    }
    
    //Génére une grille de bouton 
    public void initGridPanelBoat(Player jr, Player adv){
        inert = false;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Carreaux cell = new Carreaux(row, col);
                ImageIcon imageI;
                Image pict;
                //Il détermine les position occupé par chaque bateau
                for (Boat b : jr.getCellsBoats()) {
                    if (b.isPosition(col, row)) {
                        //Attribue a chaque cellule la bonne image de bateau
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


    //Génére une grille de bouton 
    public void initGridPanelShot(Player jr, Player adv, GridPanel boat, NewGame page){
        inert = false;
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                Carreaux cell = new Carreaux(row, col);
                

                cell.setOpaque(true);
                //Attribue un lecteur de tire a chaque bouton
                cell.addActionListener(new CarreauxInteract(col, row, jr, adv, this, boat, page));

                //Détermine la position de chaque tire du joueur
                for (Cell c : jr.cellsShot) {
                    if (c.position(col, row)) {
                        cell.setIcon(null);
                        //Donne une nouvelle couleur au bouton si le joueur a tiré a cette position
                        cell.setBackground(Color.red);
                        //récupere les position occupé par chaque bateau
                        for (Boat b : adv.getCellsBoats()) {
                            //Change la couleur du bouton si il y un bateau dessus
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

    public void initGridPanelPlacement(Player jr, String orientation, int size, String name){
        inert = false;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Carreaux cell = new Carreaux(row, col);
                ImageIcon imageI;
                Image pict;
                
                Placement p = new Placement(jr, this, col, row, size, name, orientation, 10);
                //Attribue a chaque bouton un action listener pour pouvoir placer les bateaux
                    cell.addActionListener(p);
                //recupére toutes les cellules sur lesquelles se trouve un bateau
                for (Boat b : jr.getCellsBoats()) {
                    if (b.isPosition(col, row)) {
                        
                        imageI = new ImageIcon(getClass().getResource(b.part(col, row)));
                        
                        pict = imageI.getImage();
                        pict = pict.getScaledInstance(CELL_SIZE, CELL_SIZE,  java.awt.Image.SCALE_SMOOTH);
                        imageI = new ImageIcon(pict);
                        cell = new Carreaux(row, col, imageI);
                        //Retire l'action listener si il y a un bateau sur la case
                        cell.removeActionListener(p);
                  
                        
                    } 
                    
                }
                
                

                
            
                this.add(cell);
            }
        }
    }

    public void initGridPanelInert(Player jr, Player adv){
        inert = true;
        Color touch = new Color(100, 255,100);
        Color miss = new Color(255, 100, 100);
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                Carreaux cell = new Carreaux(row, col);
                
                cell.setBackground(new Color(255, 255, 255, 50));
                
                //Détermine la position de chaque tire du joueur
                for (Cell c : jr.cellsShot) {
                    if (c.position(col, row)) {
                        cell.setIcon(null);
                        //Donne une nouvelle couleur au bouton si le joueur a tiré a cette position
                        cell.setBackground(miss);
                        //récupere les position occupé par chaque bateau
                        for (Boat b : adv.getCellsBoats()) {
                            //Change la couleur du bouton si il y un bateau dessus
                            if(b.isPosition(row, col)){
                                cell.setBackground(touch);
                            }
                                
                            

                        }
                        
                    }
                    
                }
                this.add(cell);
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Cette ligne est nécessaire pour le nettoyage de l'arrière-plan
        // Dessiner l'image GIF en arrière-plan

        if (backgroundImage != null) {
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        //g.setColor(new Color(255, 255, 255, 100));
        // Pas besoin d'appeler initGrid ici, il suffit de dessiner l'image
    }

    

    
    
}


