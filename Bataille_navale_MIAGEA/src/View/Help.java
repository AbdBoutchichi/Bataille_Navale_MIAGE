package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Help extends JDialog {
    private static final String HELPIMAGE_PATH = "helpImage.png"; // Assurez-vous que le chemin est correct
    private static final Color THEME_COLOR = new Color(249, 246, 233); // Couleur correspondant au thème beige/doré
    private static final Color TITLE_COLOR = new Color(123, 85, 74); // Couleur marron pour le titre
    private static final Color BROWN_COLOR = new Color(123, 85, 74); // Couleur marron pour le texte et les bordures
    private JTextArea textArea; // Rendre le JTextArea accessible à la classe entière
    private JScrollPane textScrollPane; // Pour permettre l'ajout du titre
    private int ruleCount = 0; // Compteur de règles pour la numérisation

    private static final String RULES_FILE_PATH = "rules.txt"; // Chemin vers le fichier de règles


    public Help(Frame owner) {
        super(owner, "Help", true);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(owner);
        
        // SplitPane pour séparer l'image du texte
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(266); // Un tiers de l'écran pour une fenêtre de 800px de large
        splitPane.setDividerSize(0); // Masquer le séparateur
        splitPane.setEnabled(false); // Empêcher le redimensionnement par l'utilisateur

        
        
        
        // Panneau gauche avec l'image
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(HELPIMAGE_PATH));
        JLabel imageLabel = new JLabel(imageIcon);
        splitPane.setLeftComponent(imageLabel);
        
        // Panneau droit pour le texte avec un titre stylisé
        textArea = new JTextArea();
        textArea.setFont(new Font("Stencil", Font.PLAIN, 18));
        textArea.setEditable(false);
        textArea.setBackground(THEME_COLOR);
        textScrollPane = new JScrollPane(textArea);
        textScrollPane.setBorder(null);
        splitPane.setRightComponent(textScrollPane);

        // Ajout d'un titre "RULES"
        JLabel titleLabel = new JLabel("<html><body><h1 style='color: " + getHtmlColor(TITLE_COLOR) + ";'><u>RULES</u></h1></body></html>");
        titleLabel.setBackground(THEME_COLOR);
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Stencil", Font.BOLD, 20));
        textScrollPane.setColumnHeaderView(titleLabel);
        
        // Modification de la couleur de fond de splitPane
        splitPane.setBackground(THEME_COLOR);
        splitPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Ajout d'un footer pour les boutons avec la couleur du thème
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(THEME_COLOR); // Définir la couleur de fond du panneau
        JButton addButton = new JButton("Ajouter Règle");
        stylizeButton(addButton);
        addButton.addActionListener(this::addNewRule);
        JButton closeButton = new JButton("Fermer");
        stylizeButton(closeButton);
        closeButton.addActionListener(e -> dispose());
        footerPanel.add(addButton);
        footerPanel.add(closeButton);

        getContentPane().add(splitPane, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
        getContentPane().setBackground(THEME_COLOR); // Appliquer la couleur du thème au contenu de la fenêtre
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        textScrollPane.setBorder(BorderFactory.createLineBorder(BROWN_COLOR, 2)); // Bordure marron pour les règles

        customizeScrollBar(textScrollPane.getVerticalScrollBar()); // Personnaliser le scrollbar vertical
        customizeScrollBar(textScrollPane.getHorizontalScrollBar()); // Personnaliser le scrollbar horizontal
    }

    private void saveRulesToFile() {
        try (Writer writer = new BufferedWriter(new FileWriter(RULES_FILE_PATH))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erreur lors de la sauvegarde des règles.",
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Méthode pour charger les règles d'un fichier
    private void loadRulesFromFile() {
        File rulesFile = new File(RULES_FILE_PATH);
        if (rulesFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(rulesFile))) {
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                textArea.setText(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                    "Erreur lors du chargement des règles.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private String getHtmlColor(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
    
    private void stylizeButton(JButton button) {
        button.setFont(new Font("Stencil", Font.BOLD, 16));
        button.setBackground(new Color(199, 153, 119)); // Une couleur qui s'harmonise avec le thème
        button.setForeground(Color.WHITE); // Texte en blanc
        button.setFocusPainted(false);
        button.setBorderPainted(false); // Pour un bouton plus stylisé
        button.setOpaque(true);
    }

    private void addNewRule(ActionEvent e) {
        final JDialog dialog = new JDialog();
        dialog.setTitle("Nouvelle règle");
        dialog.setModal(true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
    
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(THEME_COLOR);
    
        JTextArea ruleTextArea = new JTextArea();
        ruleTextArea.setLineWrap(true);
        ruleTextArea.setWrapStyleWord(true);
        ruleTextArea.setBackground(THEME_COLOR);
        JScrollPane scrollPane = new JScrollPane(ruleTextArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(BROWN_COLOR, 2));
        contentPanel.add(scrollPane, BorderLayout.CENTER);
    
        // Panneau pour les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(THEME_COLOR);
        
        JButton okButton = new JButton("OK");
        stylizeButton(okButton);
        okButton.addActionListener(actionEvent -> {
            textArea.append("Règle " + (++ruleCount) + ": " + ruleTextArea.getText() + "\n\n");
            saveRulesToFile(); // Sauvegarder les règles après l'ajout
            dialog.dispose();
        });
    
        JButton cancelButton = new JButton("Annuler");
        stylizeButton(cancelButton);
        cancelButton.addActionListener(actionEvent -> dialog.dispose());
    
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
    
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
    
        dialog.setContentPane(contentPanel);
        dialog.setVisible(true);
    }

     private void customizeScrollBar(JScrollBar scrollBar) {
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = THEME_COLOR.darker(); // Couleur du thumb (la partie mobile du scrollbar)
                this.trackColor = THEME_COLOR; // Couleur du track (l'arrière-plan du scrollbar)
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
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Help helpDialog = new Help(null);
            helpDialog.setVisible(true);
        });
    }
}
