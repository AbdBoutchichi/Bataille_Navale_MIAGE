package View;

import javax.swing.*;
import java.awt.*;

public class EndGamePanel extends JFrame {
    private static final Color THEME_COLOR = new Color(249, 246, 233);
    private static final Color TEXT_COLOR = new Color(123, 85, 74);
    private static final Font BUTTON_FONT = new Font("Stencil", Font.BOLD, 16);
    private static final Font LABEL_FONT = new Font("Serif", Font.BOLD, 18);

    private JButton mainMenuButton;
    private JButton restartButton;
    private JButton modeSelectionButton;
    private JButton quitButton;

    public EndGamePanel(String player1Name, String player2Name, int duration, int[] statsPlayer1, int[] statsPlayer2) {
        setTitle("Game Over");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(THEME_COLOR);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(createStatsPanel(player1Name, duration, statsPlayer1));
        centerPanel.add(createStatsPanel(player2Name, duration, statsPlayer2));

        add(centerPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        initActionListener();
    }

    private JPanel createStatsPanel(String playerName, int duration, int[] stats) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(THEME_COLOR);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(TEXT_COLOR, 2), playerName));

        JLabel durationLabel = new JLabel("Duration: " + duration + "s");
        JLabel hitsLabel = new JLabel("Hits: " + stats[0]);
        JLabel missesLabel = new JLabel("Misses: " + stats[1]);
        JLabel shotsLabel = new JLabel("Total Shots: " + stats[2]);

        durationLabel.setForeground(TEXT_COLOR);
        hitsLabel.setForeground(TEXT_COLOR);
        missesLabel.setForeground(TEXT_COLOR);
        shotsLabel.setForeground(TEXT_COLOR);

        durationLabel.setFont(LABEL_FONT);
        hitsLabel.setFont(LABEL_FONT);
        missesLabel.setFont(LABEL_FONT);
        shotsLabel.setFont(LABEL_FONT);

        panel.add(durationLabel);
        panel.add(shotsLabel);
        panel.add(hitsLabel);
        panel.add(missesLabel);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(THEME_COLOR);

        mainMenuButton = new JButton("Main Menu");
        restartButton = new JButton("Restart");
        modeSelectionButton = new JButton("Change Mode");
        quitButton = new JButton("Quit");

        stylizeButton(mainMenuButton);
        stylizeButton(restartButton);
        stylizeButton(modeSelectionButton);
        stylizeButton(quitButton);

        buttonPanel.add(mainMenuButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(modeSelectionButton);
        buttonPanel.add(quitButton);

        return buttonPanel;
    }

    private void initActionListener(){
        mainMenuButton.addActionListener(e ->{
            NewGameMenu menu = new NewGameMenu();
            this.setVisible(false);
            menu.setVisible(true);
            this.dispose();
        });

        quitButton.addActionListener(e -> System.exit(0));

        modeSelectionButton.addActionListener(e ->{
            System.out.println("Frigo");
            NewGameMenu menu = new NewGameMenu();
            this.setVisible(false);
            this.dispose();
            menu.showMainMenu();

            menu.setVisible(true);
            
        });

    }

    private void stylizeButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(new Color(199, 153, 119));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }
}
