package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controler.CarreauxInteract;
import Controler.InteractOrdi;
import Controler.InteractRadar;
import Controler.Placement;
import Modele.Board;
import Modele.BoardRadar;
import Modele.Boat;
import Modele.Cell;
import Modele.Player;
import Modele.PlayerComputer;




public class GridPanel extends JPanel{

    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int CELL_SIZE = 45;
    private ImageIcon backgroundImage;

    private boolean inert;

    //Affichage des bateaux sans interraction
    public GridPanel(int dim, Player jr, Player adv, JFrame frame){
        backgroundImage = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        

        this.setSize(CELL_SIZE * COLS, CELL_SIZE * ROWS);
        this.setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        this.setMaximumSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        
        this.setOpaque(false);
        this.setLayout(new GridLayout(ROWS, COLS));

        
        
        initGridPanelBoat(jr, adv);
    }

    //Creation Grille de tire inert 
    public GridPanel(Player jr, Player adv){
        backgroundImage = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(true);
        setLayout(new GridLayout(ROWS, COLS));

        
        
        initGridPanelInert(jr, adv);
    }

    //Creation d'une Grille de tire avec interraction
    public GridPanel(int dim, Player jr, Player adv, GridPanel grilleBoat, NewGame page){
        backgroundImage = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(true);
        setLayout(new GridLayout(ROWS, COLS));


        initGridPanelShot(jr, adv, grilleBoat, page);
    }

    //Creation d'une Grille de jeu contre l'ordi
    public GridPanel(int dim, Player jr, PlayerComputer adv, GridPanel grilleBoat, NewGameOrdi page){
        backgroundImage = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(true);
        setLayout(new GridLayout(ROWS, COLS));


        adv.placeShipsRandomly(new Board(dim, jr, adv));

        initGridPanelShotOrdi(jr, adv, grilleBoat, page);
    }

    //Creation d'une Grille de placement
    public GridPanel(int dim, Player jr, String orientation, int size, String name){
        backgroundImage = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(true);
        setLayout(new GridLayout(ROWS, COLS));

        initGridPanelPlacement(jr, orientation, size, name);
    }

    public GridPanel(Player jr, Player adv, GridPanel grilleBoat, NewGameRadar page){
        backgroundImage = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(true);
        setLayout(new GridLayout(ROWS, COLS));


        initGridPanelRadar(jr, adv, grilleBoat, page);
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
                    if (b.isPosition(row, col)) {
                        //Attribue a chaque cellule la bonne image de bateau
                        imageI = new ImageIcon(getClass().getResource(b.part(row, col)));
                        
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
                cell.addActionListener(new CarreauxInteract(row, col, jr, adv, this, boat, page));

                //Détermine la position de chaque tire du joueur
                for (Cell c : jr.cellsShot) {
                    if (c.position(row, col)) {
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
                    if (c.position(row, col)) {
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

    public void initGridPanelRadar(Player jr, Player adv, GridPanel boat, NewGameRadar page){
        inert = false;
        BoardRadar radar = new BoardRadar(10, jr, adv);
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                Carreaux cell = new Carreaux(row, col);
                

                cell.setOpaque(true);
                //Attribue un lecteur de tire a chaque bouton
                cell.addActionListener(new InteractRadar(row, col, jr, adv, this, boat, page));

                //Détermine la position de chaque tire du joueur
                for (Cell c : jr.cellsShot) {
                    if (c.position(row, col)) {
                        cell.setIcon(null);
                        //Donne une nouvelle couleur au bouton si le joueur a tiré a cette position
                        cell.setBackground(Color.red);
                        cell.setText(""+radar.radar(row, col, jr, adv));
                        //récupere les position occupé par chaque bateau
                        for (Boat b : adv.getCellsBoats()) {
                            //Change la couleur du bouton si il y un bateau dessus
                            if(b.isPosition(row, col)){
                                cell.setBackground(Color.green);
                                cell.setText("");
                            } else {
                                
                                
                            }
                                
                            

                        }
                        
                    }
                    
                }
                this.add(cell);
            }
        }
    }


    public void initGridPanelShotOrdi(Player jr, PlayerComputer adv, GridPanel boat, NewGameOrdi page){
        inert = false;
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                Carreaux cell = new Carreaux(row, col); // Assurez-vous que Carreaux prend col puis row dans cet ordre
                
                cell.setOpaque(true);
                // Attribue un écouteur de clic à chaque bouton
                cell.addActionListener(new InteractOrdi(row, col, jr, adv, this, boat, page));
    
                // Vérifie les tirs du joueur pour déterminer la couleur de la cellule
                boolean hasPlayerShotHere = false;
                for (Cell c : jr.cellsShot) {
                    if (c.position(row, col)) {
                        hasPlayerShotHere = true;
                        cell.setBackground(Color.red); // Couleur pour indiquer que le joueur a tiré ici
                        break;
                    }
                }
    
                // Vérifie la présence des bateaux de l'ordinateur et ajuste la couleur si touché
                boolean isShipHere = false;
                for (Boat b : adv.getCellsBoats()) {
                    if (b.isPosition(row, col)) {
                        isShipHere = true;
                        if (hasPlayerShotHere) {
                            cell.setBackground(Color.green); // Couleur pour indiquer un bateau touché
                        }
                        break;
                    }
                }
    
                // Si le joueur n'a pas tiré ici et il n'y a pas de bateau,
                // on garde la couleur initiale (peut-être transparente ou une couleur de mer)
                if (!hasPlayerShotHere && !isShipHere) {
                    cell.setBackground(new Color(0, 0, 255, 50)); // Couleur par défaut pour les cellules non touchées
                }
    
                this.add(cell); // Ajoute la cellule au panel
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


