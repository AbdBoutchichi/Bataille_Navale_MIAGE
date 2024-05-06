package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayerProfilePanel extends JPanel {
    protected JTextField firstNameField, lastNameField, birthDateField, nicknameField;
    private JButton submitButton, backButton;
    private Runnable onProfileSubmitted;

    public PlayerProfilePanel(Runnable onProfileSubmitted) {
        this.onProfileSubmitted = onProfileSubmitted;
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridLayout(6, 2, 10, 10));
        setBackground(new Color(120, 90, 40));

        add(new JLabel("Prénom:"));
        firstNameField = new JTextField(10);
        add(firstNameField);

        add(new JLabel("Nom:"));
        lastNameField = new JTextField(10);
        add(lastNameField);

        add(new JLabel("Date de Naissance:"));
        birthDateField = new JTextField(10);
        add(birthDateField);

        add(new JLabel("Pseudo:"));
        nicknameField = new JTextField(10);
        add(nicknameField);

        submitButton = new JButton("Valider");
        submitButton.addActionListener(this::handleProfileSubmission);
        add(submitButton);

        backButton = new JButton("Retour");
        backButton.addActionListener(e -> setVisible(false)); // Assume it hides the panel
        add(backButton);
    }

    private void handleProfileSubmission(ActionEvent e) {
        if (onProfileSubmitted != null) {
            onProfileSubmitted.run();
        }
        // Ceci lance une nouvelle JFrame, s'assurer qu'elle n'est pas ajoutée ou gérée par CardLayout
        new PlacementPage("Player Name").setVisible(true);
        JOptionPane.showMessageDialog(this, "Profil enregistré pour " + nicknameField.getText(), "Profil Complet", JOptionPane.INFORMATION_MESSAGE);
    }
}
