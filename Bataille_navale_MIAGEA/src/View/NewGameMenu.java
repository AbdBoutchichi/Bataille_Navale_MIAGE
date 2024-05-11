package View;

import java.awt.CardLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;



public class NewGameMenu extends JFrame {

    private JPanel cardPanel;
    private JTextField firstNameField, pseudoField;
    private CardLayout cardLayout;

    private final static String MAIN_MENU = "Main Menu";
    private final static String DIFFICULTY_MENU = "Difficulty Menu";
    private final static String PROFILE_MENU = "Profile Menu";
    private final static String PLACEMENT_PANEL = "Placement Panel";
    private final static String RADAR_PLACEMENT_PANEL = "Radar Placement Panel";

    private String playerName;


    public NewGameMenu() {
        initializeWindow();
        initializeCardPanel();
    }

    private void initializeWindow() {
        setTitle("New Game Options");
        setSize(800, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(10, 25, 48));
    }

    private void initializeCardPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(new Color(10, 25, 48));
        cardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        cardPanel.add(createMainMenu(), MAIN_MENU);
        cardPanel.add(createDifficultyMenu(), DIFFICULTY_MENU);
        cardPanel.add(createPlayerProfile(), PROFILE_MENU);
        cardPanel.add(createPlacementPanel(), PLACEMENT_PANEL);
        cardPanel.add(createRadarPlacementPanel(), RADAR_PLACEMENT_PANEL);  // Add this line
        add(cardPanel);
    }
    
    
    private JPanel createRadarPlacementPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        RadarPlacementPanel radarPlacementPanel = new RadarPlacementPanel(playerName);
        panel.add(radarPlacementPanel);
        return panel;
    }
    
    private JPanel createMainMenu() {
        JPanel mainMenu = new JPanel();
        mainMenu.setBackground(new Color(120, 90, 40));
        mainMenu.setLayout(null);

        JButton btnVsComputer = new JButton("Jouer contre l'ordinateur");
        styleButton(btnVsComputer, 250, 100, 300, 50);
        btnVsComputer.addActionListener(e -> {
            cardLayout.show(cardPanel, PROFILE_MENU);
        });
        mainMenu.add(btnVsComputer);

        JButton btnVsPlayer = new JButton("Jouer contre un autre joueur");
        styleButton(btnVsPlayer, 250, 160, 300, 50);
        btnVsPlayer.addActionListener(e -> {
            cardLayout.show(cardPanel, PROFILE_MENU);
        });
        mainMenu.add(btnVsPlayer);

        JButton btnArtillery = new JButton("Jouer en artillerie");
        styleButton(btnArtillery, 250, 220, 300, 50);
        mainMenu.add(btnArtillery);

        JButton btnRadar = new JButton("Jouer en RadarMode");
    styleButton(btnRadar, 250, 280, 300, 50);
    btnRadar.addActionListener(e -> {
        playerName = firstNameField.getText(); // Assurez-vous que firstNameField est accessible et contient le nom du joueur.
        updateRadarPlacementPanel(playerName);
        cardLayout.show(cardPanel, RADAR_PLACEMENT_PANEL);
    });
    mainMenu.add(btnRadar);

        JButton btnReturn = new JButton("Retour au menu principal");
        styleButton(btnReturn, 250, 340, 300, 50);
        btnReturn.addActionListener(e -> cardLayout.show(cardPanel, MAIN_MENU));
        mainMenu.add(btnReturn);

        return mainMenu;
    }

   
    private JPanel createDifficultyMenu() {
        JPanel difficultyMenu = new JPanel();
        difficultyMenu.setOpaque(false);
        difficultyMenu.setLayout(null);
        difficultyMenu.setBackground(new Color(10, 25, 48));

        String[] difficulties = {"Facile", "Moyen", "Difficile"};
        int yPos = 100;
        for (String diff : difficulties) {
            JButton btn = new JButton(diff);
            styleButton(btn, 250, yPos, 300, 50);
            btn.addActionListener(e -> {
                showProfileMenu(PROFILE_MENU);
            });
            yPos += 70;
            difficultyMenu.add(btn);
        }

        return difficultyMenu;
    }
    
    private void updateRadarPlacementPanel(String playerName) {
        RadarPlacementPanel newRadarPlacementPanel = new RadarPlacementPanel(playerName);
        cardPanel.remove(cardPanel.getComponent(4)); // Assurez-vous que c'est l'index correct après tous les ajouts.
        cardPanel.add(newRadarPlacementPanel, RADAR_PLACEMENT_PANEL);
        cardPanel.revalidate();
        cardPanel.repaint();
    }
    
    private JPanel createPlayerProfile() {
        JPanel profilePanel = new JPanel(null);
        profilePanel.setBackground(new Color(10, 25, 48));

        JLabel nameLabel = new JLabel("Nom du joueur:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setBounds(250, 100, 200, 30);
        profilePanel.add(nameLabel);

        firstNameField = new JTextField(10);
        firstNameField.setBounds(450, 100, 300, 30);
        profilePanel.add(firstNameField);

        JButton submitButton = new JButton("Valider");
        styleButton(submitButton, 250, 140, 300, 50);
        submitButton.addActionListener(e -> {
            playerName = firstNameField.getText();
            updatePlacementPanel(playerName);
            cardLayout.show(cardPanel, PLACEMENT_PANEL);
        });
        profilePanel.add(submitButton);

        JButton backButton = new JButton("Retour");
        styleButton(backButton, 250, 240, 300, 50);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, DIFFICULTY_MENU));
        profilePanel.add(backButton);
    
        return profilePanel;
    }
    

    
    private JPanel createPlacementPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        PlacementPanel placementPanel = new PlacementPanel("");
        panel.add(placementPanel);
        return panel;
    } 
    
    private void updatePlacementPanel(String playerName) {
        PlacementPanel newPlacementPanel = new PlacementPanel(playerName);
        cardPanel.remove(cardPanel.getComponent(3)); 
        cardPanel.add(newPlacementPanel, PLACEMENT_PANEL); 
        cardPanel.revalidate();
        cardPanel.repaint();
    }

private void showDifficultyMenu(ActionEvent e) {
    cardLayout.show(cardPanel, DIFFICULTY_MENU);
}

private void showProfileMenu(String nextPanel) {
    cardLayout.show(cardPanel, PROFILE_MENU);
}

    


private void openPlacementPage() {
    cardLayout.show(cardPanel, PLACEMENT_PANEL);
}


    private void styleButton(JButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(38, 50, 56)); 
        button.setFocusPainted(false);
        button.setBorderPainted(false); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NewGameMenu gameMenu = new NewGameMenu();
            gameMenu.setVisible(true);
        });
    }
}







