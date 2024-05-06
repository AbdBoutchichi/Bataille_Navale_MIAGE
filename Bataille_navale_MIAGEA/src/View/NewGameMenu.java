package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class NewGameMenu extends JFrame {

    private JPanel cardPanel;
    private final static String MAIN_MENU = "Main Menu";
    private final static String DIFFICULTY_MENU = "Difficulty Menu";
    private final static String PROFILE_MENU = "Profile Menu";
    private CardLayout cardLayout;


    public NewGameMenu() {
        initializeWindow();
        initializeCardPanel();
    }

    private void initializeWindow() {
        setTitle("New Game Options");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(10, 25, 48)); // Dark navy background
    }

    private void initializeCardPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(new Color(10, 25, 48));
        cardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        cardPanel.add(createMainMenu(), MAIN_MENU);
        cardPanel.add(createDifficultyMenu(), DIFFICULTY_MENU);
        add(cardPanel);
    }
    
    private JPanel createMainMenu() {
        JPanel mainMenu = new JPanel();
        mainMenu.setBackground(new Color(120, 90, 40)); // Adapt color to theme
        mainMenu.setLayout(null);

        JButton btnVsComputer = new JButton("Jouer contre l'ordinateur");
        styleButton(btnVsComputer, 250, 100, 300, 50);
        btnVsComputer.addActionListener(this::showDifficultyMenu);
        mainMenu.add(btnVsComputer);

        JButton btnVsPlayer = new JButton("Jouer contre un autre joueur");
        styleButton(btnVsPlayer, 250, 160, 300, 50);
        btnVsPlayer.addActionListener(e -> openPlacementPage());
        mainMenu.add(btnVsPlayer);

        JButton btnArtillery = new JButton("Jouer en artillerie");
        styleButton(btnArtillery, 250, 220, 300, 50);
        mainMenu.add(btnArtillery);

        JButton btnRadar = new JButton("Jouer en RadarMode");
        styleButton(btnRadar, 250, 220, 300, 50);
        mainMenu.add(btnRadar);

        JButton btnReturn = new JButton("Retour au menu principal");
        styleButton(btnReturn, 250, 280, 300, 50);
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

   

    private void showDifficultyMenu(ActionEvent e) {
        cardLayout.show(cardPanel, DIFFICULTY_MENU);
    }

    private void showProfileMenu(String nextPanel) {
        PlayerProfilePanel profilePanel = new PlayerProfilePanel(() -> {
            new PlacementPage("Player Name").setVisible(true); // Assurez-vous que ceci est correctement géré
        });
        cardPanel.add(profilePanel, PROFILE_MENU);
        cardLayout.show(cardPanel, PROFILE_MENU);
    }
    


    private void openPlacementPage() {
        new PlacementPage("Modele").setVisible(true);
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







