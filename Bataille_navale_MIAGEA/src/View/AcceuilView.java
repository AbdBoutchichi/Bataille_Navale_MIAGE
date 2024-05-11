package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class AcceuilView extends JFrame {

    private static final String BACKGROUND_IMAGE_PATH = "/Images/background.png";
    private static final String NEW_GAME_BUTTON_IMAGE_PATH = "/Images/NewGame.png";
    private static final String CONTINUE_BUTTON_IMAGE_PATH = "/Images/Continue.png";
    private static final String OPTIONS_BUTTON_IMAGE_PATH = "/Images/Options.png";
    private static final String QUIT_BUTTON_IMAGE_PATH = "/Images/Quit.png";
    private static final String ICON_1_IMAGE_PATH = "/Images/Icone1.png"; // Your first decorative icon path
    private static final String ICON_2_IMAGE_PATH = "/Images/Icone2.png"; // Your second decorative icon path
    private static final String HELP_IMAGE_PATH = "/Images/HELP.png"; // Your third decorative icon path
    private static final String WELCOMEICONE = "/Images/GameMenu.png"; // Your third decorative icon path
    //private static final String WELCOME_TEXT = "Welcome to Battleship";
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 140;

    public AcceuilView() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Naval Battle Game Menu");
        setPreferredSize(new Dimension(800, 700));

        

        

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        setContentPane(backgroundLabel);
        backgroundLabel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel welcomeIconLabel = new JLabel(new ImageIcon(getClass().getResource(WELCOMEICONE)));
        gbc.gridx = 0; // Align to the first column
        gbc.gridy = 0; // Align to the first row
        gbc.gridwidth = GridBagConstraints.REMAINDER; // The component will be the only one in its row
        gbc.anchor = GridBagConstraints.PAGE_START; // Anchor to the start of the page
        gbc.insets = new Insets(0, 0, 0, 0); // Top padding of 10, bottom padding of 20, no left and right padding

        // Add the welcome icon to the layout
        backgroundLabel.add(createImageButton(WELCOMEICONE, 500, 100), gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Add the "Game Menu" banner label
        //JLabel bannerLabel = new JLabel(WELCOMEICONE, SwingConstants.CENTER);
        //bannerLabel.setFont(new Font("Serif", Font.BOLD, 36)); // Use an appropriate font
        //bannerLabel.setForeground(Color.BLUE); // Choose color that matches your theme
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.PAGE_START;
       // backgroundLabel.add(bannerLabel, gbc);

        // Reset grid width
        gbc.gridwidth = 1;

        // First row of buttons (New Game and Continue)
        gbc.gridy = 1;
        gbc.gridx = 0;
        backgroundLabel.add(createImageButton(NEW_GAME_BUTTON_IMAGE_PATH, 200, BUTTON_HEIGHT), gbc);

        gbc.gridx = 1; // Middle cell for the icon
        JLabel icon1Label = new JLabel(new ImageIcon(getClass().getResource(ICON_1_IMAGE_PATH)));
        backgroundLabel.add(createImageButton(ICON_1_IMAGE_PATH, 100, 100), gbc);

        gbc.gridx = 2;
        backgroundLabel.add(createImageButton(CONTINUE_BUTTON_IMAGE_PATH, BUTTON_WIDTH, BUTTON_HEIGHT), gbc);

        // Second row of buttons (Options and Quit)
        gbc.gridy = 2;
        gbc.gridx = 0;
        backgroundLabel.add(createImageButton(OPTIONS_BUTTON_IMAGE_PATH, BUTTON_WIDTH, BUTTON_HEIGHT), gbc);

        gbc.gridx = 1; // Middle cell for the icon
        JLabel icon2Label = new JLabel(new ImageIcon(getClass().getResource(ICON_2_IMAGE_PATH)));
        backgroundLabel.add(createImageButton(ICON_2_IMAGE_PATH, 100, 100), gbc);

        gbc.gridx = 2;
        backgroundLabel.add(createImageButton(QUIT_BUTTON_IMAGE_PATH, BUTTON_WIDTH, BUTTON_HEIGHT), gbc);

        // Add the third icon at the bottom
        gbc.gridy = 3; // Increment Y for the next row
        gbc.gridx = 1; // Middle cell for the icon
        JLabel HELPLabel = new JLabel(new ImageIcon(getClass().getResource(HELP_IMAGE_PATH)));
        backgroundLabel.add(createImageButton(HELP_IMAGE_PATH, 150, 100), gbc);
        

        pack();
        setLocationRelativeTo(null);
    }

    private void animateButton(JButton button, int startWidth, int startHeight, int endWidth, int endHeight, long duration) {
        final int delay = 10;
        final long startTime = System.currentTimeMillis();
    
        Timer timer = new Timer(delay, e -> {
            long currentTime = System.currentTimeMillis();
            float progress = Math.min(1.0f, (float)(currentTime - startTime) / duration);
    
            int currentWidth = startWidth + (int) ((endWidth - startWidth) * progress);
            int currentHeight = startHeight + (int) ((endHeight - startHeight) * progress);
    
            button.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(button.getName())).getImage().getScaledInstance(currentWidth, currentHeight, Image.SCALE_SMOOTH)));
    
            if (progress >= 1.0f) {
                ((Timer)e.getSource()).stop();
            }
    
            AcceuilView.this.pack();
        });
        timer.start();
    }
    

    private JButton createImageButton(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
    
        JButton button = new JButton(scaledIcon);
        button.setName(imagePath); // Utilisez le chemin de l'image comme nom pour retrouver l'icône originale lors de l'animation
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(width, height));
    
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                animateButton(button, width, height, (int)(width * 1.1), (int)(height * 1.1), 500); // Agrandit le bouton pendant 1 seconde
            }
    
            public void mouseExited(java.awt.event.MouseEvent evt) {
                animateButton(button, (int)(width * 1.1), (int)(height * 1.1), width, height, 500); // Réduit le bouton pendant 1 seconde
            }
        });

        if (imagePath.equals(HELP_IMAGE_PATH)) {
            button.addActionListener(e -> showHelpView());
        }else  if(imagePath.equals(NEW_GAME_BUTTON_IMAGE_PATH)){
            button.addActionListener(e->showNewGameMenuView());
        } else {
            button.addActionListener(this::buttonAction);
        }
        return button;
    
       
      
    }

    private void showHelpView() {
        Help helpView = new Help(this); 
        helpView.setVisible(true);
    }

    private void showNewGameMenuView(){
        NewGameMenu newGameMenuView = new NewGameMenu();
        newGameMenuView.setVisible(true);
    }
    
    
    

    private void buttonAction(ActionEvent e) {
      
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AcceuilView().setVisible(true));
    }
}
