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

import Modele.Player;
import Modele.PlayerComputer;

public class NewGameMenu extends JFrame implements MenuCallback {

    public JPanel cardPanel;
    public JTextField firstNameField;
    private CardLayout cardLayout;
    private ImageIcon playerAvatar;

    public final static String Acceuil_View = "Acceuil_View";
    public final static String MAIN_MENU = "Main Menu";
    private final static String DIFFICULTY_MENU = "Difficulty Menu";
    public final static String PROFILE_MENU = "Profile Menu";
    public final static String PLACEMENT_PANEL = "Placement Panel";
    private final static String RADAR_PLACEMENT_PANEL = "Radar Placement Panel";
    public final static String HELP_MENU = "HelpPanel";
    public final static String OPTIONS_PANEL = "OptionsPanel"; // Ensure this is defined

    private String playerName;
    public Player player1;
    public Player player2;
    public int selectedDifficulty;
    public boolean singlePlayerMode = false;
    public boolean firstPlayer;
    public GameMode selectedGameMode;

    public NewGameMenu() {
        initializeWindow();
        initializeCardPanel();
        setupUI();
    }

    private void setupUI() {
        AcceuilView acceuilView = new AcceuilView(this);
        HelpPanel helpPanel = new HelpPanel(this);
        OptionsPanel optionsPanel = new OptionsPanel(this); // Create the options panel

        cardPanel.add(acceuilView, Acceuil_View);
        cardPanel.add(helpPanel, HELP_MENU);
        cardPanel.add(optionsPanel, OPTIONS_PANEL); // Add the options panel

        add(cardPanel);
        cardLayout.show(cardPanel, Acceuil_View);
    }

    @Override
    public void navigate(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

    private void initializeWindow() {
        setTitle("New Game Options");
        setSize(870, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(10, 25, 48));
    }

    private void initializeCardPanel() {
        firstPlayer = true;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(new Color(10, 25, 48));
        cardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        cardPanel.add(new AcceuilView(this), Acceuil_View);
        cardPanel.add(createMainMenu(), MAIN_MENU);
        cardPanel.add(createDifficultyMenu(), DIFFICULTY_MENU);
        cardPanel.add(createPlayerProfile(), PROFILE_MENU);
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
            selectedGameMode = GameMode.NORMAL;
            cardLayout.show(cardPanel, DIFFICULTY_MENU);
        });
        mainMenu.add(btnVsComputer);

        JButton btnVsPlayer = new JButton("Jouer contre un autre joueur");
        styleButton(btnVsPlayer, 250, 160, 300, 50);
        btnVsPlayer.addActionListener(e -> {
            selectedGameMode = GameMode.NORMAL;
            singlePlayerMode = false;
            firstPlayer = true;
            cardLayout.show(cardPanel, PROFILE_MENU);
        });
        mainMenu.add(btnVsPlayer);

        JButton btnArtillery = new JButton("Jouer en artillerie");
        styleButton(btnArtillery, 250, 220, 300, 50);
        btnArtillery.addActionListener(e -> {
            selectedGameMode = GameMode.ARTILLERY;
            singlePlayerMode = false;
            firstPlayer = true;
            cardLayout.show(cardPanel, PROFILE_MENU);
        });
        mainMenu.add(btnArtillery);

        JButton btnRadar = new JButton("Jouer en RadarMode");
        styleButton(btnRadar, 250, 280, 300, 50);
        btnRadar.addActionListener(e -> {
            selectedGameMode = GameMode.RADAR;
            singlePlayerMode = false;
            firstPlayer = true;
            cardLayout.show(cardPanel, PROFILE_MENU);
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
                selectedDifficulty = "Facile".equals(diff) ? PlayerComputer.EASY : "Moyen".equals(diff) ? PlayerComputer.MEDIUM : PlayerComputer.HARD;
                singlePlayerMode = true;
                cardLayout.show(cardPanel, PROFILE_MENU);
            });
            yPos += 70;
            difficultyMenu.add(btn);
        }

        return difficultyMenu;
    }

    private JPanel createPlayerProfile() {
        JPanel profilePanel = new JPanel(null);
        profilePanel.setBackground(new Color(10, 25, 48));

        JLabel avatarLabel = new JLabel();
        avatarLabel.setBounds(400, 50, 100, 100);
        avatarLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        profilePanel.add(avatarLabel);

        JButton avatarButton = new JButton("Choisir Avatar");
        styleButton(avatarButton, 400, 160, 200, 30);
        avatarButton.addActionListener(e -> chooseAvatar(avatarLabel));
        profilePanel.add(avatarButton);

        JLabel nameLabel = new JLabel("Nom du joueur:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setBounds(250, 200, 200, 30);
        profilePanel.add(nameLabel);

        firstNameField = new JTextField(10);
        firstNameField.setBounds(450, 200, 300, 30);
        profilePanel.add(firstNameField);

        JButton submitButton = new JButton("Valider");
        styleButton(submitButton, 350, 240, 300, 50);
        submitButton.addActionListener(e -> {
            playerName = firstNameField.getText();
            updatePlacementPanel(playerName, avatarLabel.getIcon(), selectedGameMode);
        });
        profilePanel.add(submitButton);

        JButton backButton = new JButton("Retour");
        styleButton(backButton, 350, 300, 300, 50);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, DIFFICULTY_MENU));
        profilePanel.add(backButton);

        return profilePanel;
    }

    public void chooseAvatar(JLabel avatarLabel) {
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

    private JPanel createPlacementPanel(GameMode mode) {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        ImageIcon playerAvatar = new ImageIcon("path/to/avatar.png");
        PlacementPanel placementPanel = new PlacementPanel("PlayerName", this, playerAvatar, mode);
        panel.add(placementPanel);
        return panel;
    }

    private void updatePlacementPanel(String playerName, Icon avatarIcon, GameMode mode) {
        ImageIcon playerAvatar = (ImageIcon) avatarIcon;
        PlacementPanel newPlacementPanel = new PlacementPanel(playerName, this, playerAvatar, mode);
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

    public void showProfileMenu(String nextPanel) {
        firstNameField.setText("");
        cardLayout.show(cardPanel, PROFILE_MENU);
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
