package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.ImageIcon;

public class Carreaux extends JButton{

    public int posX;
    public int posY;
    private ActionListener e;

    public Carreaux(int x, int y, ImageIcon image){
        super(image);
        posX = x;
        posY = y;
        this.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,50)));
        this.setOpaque(false);
        this.setBackground(new Color(250,250, 250, 0));
        
    }

    public Carreaux(int x, int y){

        
        super();
        posX = x;
        posY = y;
        this.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,50)));
        this.setOpaque(true);
        this.setBackground(new Color(0,0, 0, 0));
        this.setRolloverEnabled(false);
        //ImageIcon background = new ImageIcon(getClass().getResource("/Images/Mer.gif"));
        //this.setIcon(background);
        this.addActionListener(e);
    }
    
}
