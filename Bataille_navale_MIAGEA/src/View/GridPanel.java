package View;

import java.awt.*;
import javax.swing.*;

public class GridPanel extends JPanel {
    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int CELL_SIZE = 30;
    private ImageIcon backgroundImage;

    public GridPanel() {
        backgroundImage = new ImageIcon(getClass().getResource("/Images/GrilleForplayers.png"));
        setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS));
        setOpaque(false);
        setLayout(new GridLayout(ROWS, COLS));
        initGrid();
    }

    private void initGrid() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JLabel cell = new JLabel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setOpaque(false);
                this.add(cell);
            }
        }
    }
}
