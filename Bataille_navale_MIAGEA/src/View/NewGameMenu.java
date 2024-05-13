package View;

import java.awt.CardLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;



public class NewGameMenu extends JFrame implements MenuCallback{

    public JPanel cardPanel;
    private JTextField firstNameField;
    private CardLayout cardLayout;
    private ImageIcon playerAvatar; // Stocke l'avatar du joueur

    public final static String Acceuil_View = "Acceuil_View";
    public final static String MAIN_MENU = "Main Menu";
    private final static String DIFFICULTY_MENU = "Difficulty Menu";
    private final static String PROFILE_MENU = "Profile Menu";
    private final static String PLACEMENT_PANEL = "Placement Panel";
    private final static String RADAR_PLACEMENT_PANEL = "Radar Placement Panel";
    private final static String HELP_MENU = "Help Panel";

    private String playerName;


    public NewGameMenu() {
        initializeWindow();
        initializeCardPanel();
        setupUI();
    }

    private void setupUI() {
        AcceuilView acceuilView = new AcceuilView(this);
        HelpPanel helpPanel = new HelpPanel(this);

        cardPanel.add(acceuilView, "AcceuilView");
        cardPanel.add(helpPanel, "HelpPanel");

        add(cardPanel);
        cardLayout.show(cardPanel, "AcceuilView");
    }

    @Override
    public void navigate(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

    private void initializeWindow() {
        setTitle("New Game Options");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(10, 25, 48));
    }

    private void initializeCardPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(new Color(10, 25, 48));
        cardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        cardPanel.add(new AcceuilView(this),Acceuil_View);
        cardPanel.add(createMainMenu(), MAIN_MENU);
        cardPanel.add(createDifficultyMenu(), DIFFICULTY_MENU);
        cardPanel.add(createPlayerProfile(), PROFILE_MENU);
        cardPanel.add(createPlacementPanel(), PLACEMENT_PANEL);
        cardPanel.add(createRadarPlacementPanel(), RADAR_PLACEMENT_PANEL);
        cardPanel.add(new HelpPanel(this), HELP_MENU);
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
        playerName = firstNameField.getText();
        cardLayout.show(cardPanel, RADAR_PLACEMENT_PANEL);
    });
    mainMenu.add(btnRadar);

        JButton btnReturn = new JButton("Retour au menu principal");
        styleButton(btnReturn, 250, 340, 300, 50);
        btnReturn.addActionListener(e -> cardLayout.show(cardPanel, Acceuil_View));
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
    
    
    private JPanel createPlayerProfile() {
        JPanel profilePanel = new JPanel(null);
        profilePanel.setBackground(new Color(10, 25, 48));
    
        // Label pour afficher l'avatar
        JLabel avatarLabel = new JLabel();
        avatarLabel.setBounds(400, 50, 100, 100);  // Position ajustée pour être au-dessus des champs de texte
        avatarLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        profilePanel.add(avatarLabel);
    
        // Bouton pour choisir l'avatar
        JButton avatarButton = new JButton("Choisir Avatar");
        styleButton(avatarButton, 400, 160, 200, 30);  // Position ajustée sous l'avatar
        avatarButton.addActionListener(e -> chooseAvatar(avatarLabel));
        profilePanel.add(avatarButton);
    
        // Label pour le nom
        JLabel nameLabel = new JLabel("Nom du joueur:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setBounds(250, 200, 200, 30);  // Position ajustée en dessous de l'avatar
        profilePanel.add(nameLabel);
    
        // Champ de texte pour le nom
        firstNameField = new JTextField(10);
        firstNameField.setBounds(450, 200, 300, 30);
        profilePanel.add(firstNameField);
    
        // Bouton de soumission
        JButton submitButton = new JButton("Valider");
        styleButton(submitButton, 350, 240, 300, 50);
        submitButton.addActionListener(e -> {
            playerName = firstNameField.getText();
            updatePlacementPanel(playerName, avatarLabel.getIcon());
            cardLayout.show(cardPanel, PLACEMENT_PANEL);
        });        
        profilePanel.add(submitButton);
    
        // Bouton de retour
        JButton backButton = new JButton("Retour");
        styleButton(backButton, 350, 300, 300, 50);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, DIFFICULTY_MENU));
        profilePanel.add(backButton);
    
        return profilePanel;
    }
    

private void chooseAvatar(JLabel avatarLabel) {
    JFileChooser fileChooser = new JFileChooser("./Avatars/");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "png", "jpg", "jpeg", "gif");
    fileChooser.setFileFilter(filter);
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        ImageIcon icon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        avatarLabel.setIcon(icon);
    }
}


    
    private JPanel createPlacementPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        ImageIcon playerAvatar = new ImageIcon("path/to/avatar.png");
        PlacementPanel placementPanel = new PlacementPanel("PlayerName", playerAvatar);
        panel.add(placementPanel);
        return panel;
    } 
    
    private void updatePlacementPanel(String playerName, Icon avatarIcon) {
        ImageIcon playerAvatar = (ImageIcon) avatarIcon;
        PlacementPanel newPlacementPanel = new PlacementPanel(playerName, playerAvatar);
        cardPanel.add(newPlacementPanel, PLACEMENT_PANEL);  
        cardLayout.show(cardPanel, PLACEMENT_PANEL);
        cardPanel.revalidate();
        cardPanel.repaint();
    }
    
    


public void showDifficultyMenu(ActionEvent e) {
    cardLayout.show(cardPanel, DIFFICULTY_MENU);
}

public void showAcceuilMenu() {
    cardLayout.show(cardPanel, Acceuil_View);
}

@Override
    public void showMainMenu() {
        cardLayout.show(cardPanel, MAIN_MENU);
    }
@Override
    public void ShowHelpMenu() {
        cardLayout.show(cardPanel, HELP_MENU);
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







