package View;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class AcceuilView extends JPanel {
    private MenuCallback callback;
    private static final String BACKGROUND_IMAGE_PATH = "/Images/background.png";
    public static final String NEW_GAME_BUTTON_IMAGE_PATH = "/Images/NewGame.png";
    private static final String CONTINUE_BUTTON_IMAGE_PATH = "/Images/Continue.png";
    private static final String OPTIONS_BUTTON_IMAGE_PATH = "/Images/Options.png";
    private static final String QUIT_BUTTON_IMAGE_PATH = "/Images/Quit.png";
    private static final String HELP_IMAGE_PATH = "/Images/HELP.png"; 
    private static final String WELCOMEICONE = "/Images/GameMenu.png"; 
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 140;

    public AcceuilView(MenuCallback callback) {
        this.callback = callback;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JLabel backgroundLabel = new JLabel(new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)));
        add(backgroundLabel);
        backgroundLabel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 20, 20, 20);

        backgroundLabel.add(createImageButton(WELCOMEICONE, 500, 150), gbc);

        gbc.gridwidth = 1; 
        gbc.gridy++;
        gbc.weighty = 0.2; 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        backgroundLabel.add(createImageButton(NEW_GAME_BUTTON_IMAGE_PATH, 180, 120), gbc);
        
       

        gbc.gridx = 1;
        backgroundLabel.add(createImageButton(CONTINUE_BUTTON_IMAGE_PATH, 180, 120), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        backgroundLabel.add(createImageButton(OPTIONS_BUTTON_IMAGE_PATH, 180, 120), gbc);
        gbc.gridx = 1;
        backgroundLabel.add(createImageButton(QUIT_BUTTON_IMAGE_PATH, 180, 120), gbc);
        

        gbc.gridwidth = 2; 
        gbc.gridx = 0;
        gbc.gridy++;
        backgroundLabel.add(createImageButton(HELP_IMAGE_PATH, 180, 120), gbc);
    }

    private JButton createImageButton(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
    
        JButton button = new JButton(scaledIcon);
        button.setName(imagePath);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(width, height));
    
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                animateButton(button, width, height, (int)(width * 1.1), (int)(height * 1.1), 500);
            }
    
            public void mouseExited(java.awt.event.MouseEvent evt) {
                animateButton(button, (int)(width * 1.1), (int)(height * 1.1), width, height, 500);
            }
        });

        if (imagePath.equals(HELP_IMAGE_PATH)) {
            button.addActionListener(e -> callback.ShowHelpMenu());
        } else if (imagePath.equals(NEW_GAME_BUTTON_IMAGE_PATH)) {
            button.addActionListener(e -> callback.showMainMenu());
        } else if (imagePath.equals(OPTIONS_BUTTON_IMAGE_PATH)) {
            button.addActionListener(e -> callback.navigate("OptionsPanel"));
        } else if (imagePath.equals(QUIT_BUTTON_IMAGE_PATH)) {
            button.addActionListener(e -> System.exit(0)); // Quit the application
        } else {
            button.addActionListener(this::buttonAction);
        }
        return button;


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
        });
        timer.start();
    }
    
    /*private void showHelpView() {
        HelpPanel helpView = new HelpPanel();
        helpView.setVisible(true);
    }*/

    /*private void showMainMenu() {
        NewGameMenu MENU = new NewGameMenu();
        MENU.setVisible(true);
    }*/
   

    private void buttonAction(ActionEvent e) {
      
    }

}
