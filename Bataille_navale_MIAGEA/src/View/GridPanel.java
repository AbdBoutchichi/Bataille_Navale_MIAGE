package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GridPanel extends JPanel {
    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int CELL_SIZE = 30;
    private ImageIcon backgroundImage;

    public GridPanel() {
        backgroundImage = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(false);
        setLayout(new GridLayout(ROWS, COLS));
        initGrid();
    }

    private void initGrid() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JLabel cell = new JLabel();
                cell.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 50)));
                cell.setOpaque(false);
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
