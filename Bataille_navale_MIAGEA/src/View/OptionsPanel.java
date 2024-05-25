package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class OptionsPanel extends JPanel {
    private MenuCallback callback;
    private static final Color THEME_COLOR = new Color(249, 246, 233);
    private static final Color TITLE_COLOR = new Color(123, 85, 74);
    private static final Color BROWN_COLOR = new Color(123, 85, 74);

    public OptionsPanel(MenuCallback callback) {
        this.callback = callback;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(THEME_COLOR);

        JTextPane aboutText = new JTextPane();
        aboutText.setContentType("text/html");
        aboutText.setText(
            "<html>" +
            "<body style='text-align:justify; font-family:Stencil; font-size:16px; color:rgb(123, 85, 74); background-color:rgb(249, 246, 233);'>" +
            "<h1 style='text-align:center; font-size:24px; color:rgb(123, 85, 74);'>About Us</h1>" +
            "<p>Ce projet s'intitule Bataille Navale réalisé par:</p>" +
            "<table style='width:100%; border-collapse:collapse;'>" +
            "<tr>" +
            "<th style='padding:8px; border:1px solid rgb(123, 85, 74);'>Nom</th>" +
            "<th style='padding:8px; border:1px solid rgb(123, 85, 74);'>Photo</th>" +
            "</tr>" +
            "<tr>" +
            "<td style='padding:8px; border:1px solid rgb(123, 85, 74); text-align:center;'>Abdelmoughit BOUTCHICHI</td>" +
            "<td style='padding:8px; border:1px solid rgb(123, 85, 74); text-align:center;'>" +
            "<img src='" + getClass().getResource("/Images/Abdel.png") + "' alt='Person 1' style='width:60px; height:60px; display:block; margin:auto;'/>" +
            "</td>" +
            "</tr>" +
            "<tr>" +
            "<td style='padding:8px; border:1px solid rgb(123, 85, 74); text-align:center;'>LETICIA KHAROUNI</td>" +
            "<td style='padding:8px; border:1px solid rgb(123, 85, 74); text-align:center;'>" +
            "<img src='" + getClass().getResource("/Images/Leticia.png") + "' alt='Person 2' style='width:60px; height:60px; display:block; margin:auto;'/>" +
            "</td>" +
            "</tr>" +
            "<tr>" +
            "<td style='padding:8px; border:1px solid rgb(123, 85, 74); text-align:center;'>LILIAN MARIE-JOSEPH</td>" +
            "<td style='padding:8px; border:1px solid rgb(123, 85, 74); text-align:center;'>" +
            "<img src='" + getClass().getResource("/Images/Lilian.png") + "' alt='Person 3' style='width:60px; height:60px; display:block; margin:auto;'/>" +
            "</td>" +
            "</tr>" +
            "</table>" +
            "<p>Le jeu de bataille navale est un jeu de stratégie où chaque joueur dispose d'une flotte de navires qu'il doit positionner secrètement sur une grille. " +
            "Les joueurs se relaient ensuite pour tenter de couler les navires adverses en devinant leurs positions sur la grille. " +
            "Chaque joueur dispose d'une grille de tir où il marque ses tentatives et une grille de positionnement où il place ses navires. " +
            "Le but du jeu est de couler tous les navires de l'adversaire.</p>" +
            "</body>" +
            "</html>"
        );
        aboutText.setEditable(false);
        aboutText.setBackground(THEME_COLOR);
        aboutText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(aboutText);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = BROWN_COLOR;
                this.trackColor = THEME_COLOR;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(THEME_COLOR);

        JButton backButton = new JButton("Retour à l'Accueil");
        styleButton(backButton);
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.addActionListener(e -> callback.showAcceuilMenu());
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Stencil", Font.BOLD, 16));
        button.setForeground(THEME_COLOR);
        button.setBackground(BROWN_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }
}
