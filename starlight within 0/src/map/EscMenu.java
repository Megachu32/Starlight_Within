package map;

import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EscMenu extends JPanel{
	public EscMenu() {
        // setting the layout of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // making the title for the ponel
        JLabel panelName = new JLabel("Menu");
        panelName.setAlignmentX(CENTER_ALIGNMENT);
        panelName.setFont(new Font("Arial", Font.BOLD, 24));

        // making tht buttons for the panel
        JButton resumeButton = new JButton("Resume");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton settingsButton = new JButton("Settings");
        JButton exitButton = new JButton("Exit");

        // the buttons layout
        resumeButton.setAlignmentX(CENTER_ALIGNMENT);
        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        loadButton.setAlignmentX(CENTER_ALIGNMENT);
        settingsButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);

        // adding the buttons and the title to the panel
        add(Box.createVerticalStrut(40));
        add(panelName);
        add(Box.createVerticalStrut(20));
        add(resumeButton);
        add(Box.createVerticalStrut(20));
        add(saveButton);
        add(Box.createVerticalStrut(20));
        add(loadButton);
        add(Box.createVerticalStrut(20));
        add(settingsButton);
        add(Box.createVerticalStrut(20));
        add(exitButton);

        // action listeners for the buttons
        resumeButton.addActionListener(e -> {
            // Logic to resume the game
            System.out.println("Game resumed!");
        });
        saveButton.addActionListener(e -> {
            // Logic to save the game
            System.out.println("Game saved!");
        });
        loadButton.addActionListener(e -> {
            // Logic to load the game
            System.out.println("Game loaded!");
        });
        settingsButton.addActionListener(e -> {
            // Logic to open settings
            System.out.println("Settings opened!");
        });
        exitButton.addActionListener(e -> {
            // Logic to exit the game
            System.out.println("Game exited!");
        });
        
    }
}
