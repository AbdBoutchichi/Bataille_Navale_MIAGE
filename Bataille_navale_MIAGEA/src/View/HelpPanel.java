package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class HelpPanel extends JPanel {
    private static final Color THEME_COLOR = new Color(249, 246, 233);
    private static final Color TITLE_COLOR = new Color(123, 85, 74);
    private static final Color BROWN_COLOR = new Color(123, 85, 74);
    private JTextArea textArea;
    private JScrollPane textScrollPane;
    private int ruleCount = 0;
    private MenuCallback callback;

    public HelpPanel(MenuCallback callback) {
        this.callback = callback;
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(266);
        splitPane.setDividerSize(0);
        splitPane.setEnabled(false);
    
        URL imageUrl = getClass().getResource("/Images/helpImage.png");
        if (imageUrl == null) {
            throw new RuntimeException("Resource not found: /Images/helpImage.png");
        }
        ImageIcon imageIcon = new ImageIcon(imageUrl);
        
        JLabel imageLabel = new JLabel(imageIcon);
        splitPane.setLeftComponent(imageLabel);
    
        textArea = new JTextArea();
        textArea.setFont(new Font("Stencil", Font.PLAIN, 18));
        textArea.setEditable(false);
        textArea.setBackground(THEME_COLOR);
        textScrollPane = new JScrollPane(textArea);
        textScrollPane.setBorder(null);
        splitPane.setRightComponent(textScrollPane);
    
        JLabel titleLabel = new JLabel("<html><body><h1 style='color: " + getHtmlColor(TITLE_COLOR) + ";'><u>RULES</u></h1></body></html>");
        titleLabel.setBackground(THEME_COLOR);
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Stencil", Font.BOLD, 20));
        textScrollPane.setColumnHeaderView(titleLabel);
    
        splitPane.setBackground(THEME_COLOR);
        splitPane.setBorder(BorderFactory.createEmptyBorder());
    
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(THEME_COLOR);
        JButton addButton = new JButton("Ajouter Règle");
        stylizeButton(addButton);
        addButton.addActionListener(this::addNewRule);
        JButton deleteRuleButton = new JButton("Supprimer Règle");
        stylizeButton(deleteRuleButton);
        deleteRuleButton.addActionListener(this::deleteRule);
        JButton returnButton = new JButton("<- Back");
        stylizeButton(returnButton);
        returnButton.addActionListener(e ->callback.navigate("AcceuilView"));
        footerPanel.add(addButton);
        footerPanel.add(deleteRuleButton);
        footerPanel.add(returnButton);
    
        add(splitPane, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
        setBackground(THEME_COLOR);
        textScrollPane.setBorder(BorderFactory.createLineBorder(BROWN_COLOR, 2));
    
        customizeScrollBar(textScrollPane.getVerticalScrollBar());
        customizeScrollBar(textScrollPane.getHorizontalScrollBar());
    }
    
    private String getHtmlColor(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    private void deleteRule(ActionEvent e) {

        UIManager.put("OptionPane.background", THEME_COLOR);
        UIManager.put("Panel.background", THEME_COLOR);
        UIManager.put("Button.background", new Color(199, 153, 119));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Stencil", Font.BOLD, 12));
    
        String ruleNumberStr = JOptionPane.showInputDialog(this, "Enter the rule number to delete:", "Delete Rule", JOptionPane.PLAIN_MESSAGE);
    
        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("Button.background", null);
        UIManager.put("Button.foreground", null);
        UIManager.put("Button.font", new Font("Stencil", Font.BOLD, 12));
    
        processRuleDeletion(ruleNumberStr);
    }

    private void processRuleDeletion(String ruleNumberStr) {
        if (ruleNumberStr != null && !ruleNumberStr.trim().isEmpty()) {
            try {
                int ruleNumber = Integer.parseInt(ruleNumberStr.trim()) - 1;
                String[] lines = textArea.getText().split("\n");
                StringBuilder newText = new StringBuilder();
                int newRuleCount = 0;
                
                for (int i = 0; i < lines.length; i++) {
                    if (i != ruleNumber) {
                        newText.append("Rule ").append(++newRuleCount).append(": ").append(lines[i].substring(lines[i].indexOf(':') + 1).trim()).append("\n");
                    }
                }
                textArea.setText(newText.toString());
                ruleCount = newRuleCount;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid rule number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    
    

    private void stylizeButton(JButton button) {
        button.setFont(new Font("Stencil", Font.BOLD, 16));
        button.setBackground(new Color(199, 153, 119));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }

    private void addNewRule(ActionEvent e) {
    UIManager.put("Panel.background", THEME_COLOR); 
    UIManager.put("OptionPane.background", THEME_COLOR); 
    UIManager.put("Button.background", new Color(199, 153, 119));  
    UIManager.put("Button.foreground", Color.WHITE);  
    UIManager.put("Button.font", new Font("Stencil", Font.BOLD, 12));  
    UIManager.put("Label.font", new Font("Stencil", Font.PLAIN, 16));  
    UIManager.put("TextField.font", new Font("Stencil", Font.PLAIN, 16)); 

    String newRule = JOptionPane.showInputDialog(this, "Enter the new rule:", "New Rule", JOptionPane.PLAIN_MESSAGE);
    if (newRule != null && !newRule.isEmpty()) {
        textArea.append("Rule " + (++ruleCount) + ": " + newRule + "\n");
    }

    UIManager.put("Panel.background", null);
    UIManager.put("OptionPane.background", null);
    UIManager.put("Button.background", null);
    UIManager.put("Button.foreground", null);
    UIManager.put("Button.font", null);
    UIManager.put("Label.font", null);
    UIManager.put("TextField.font", null);
}


    private void customizeScrollBar(JScrollBar scrollBar) {
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = THEME_COLOR.darker();
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
    }
}
