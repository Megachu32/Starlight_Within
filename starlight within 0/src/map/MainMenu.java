package map;

import application.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainMenu {
    
    private JFrame frame;
    GamePanel gp;

    // interface menu sebelum masuk
    public MainMenu() {
        // Create the main frame (JFrame)
        frame = new JFrame("My Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        setupMenu();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    private void setupMenu() {
        // Create title label
        JLabel titleLabel = new JLabel("My Awesome Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Create a panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        // Create Start button
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
        buttonPanel.add(startButton);

        // Create Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Button Actions
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    startGame();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitGame();
            }
        });
    }

    private void startGame() throws Exception {
        frame.getContentPane().removeAll();
        frame.repaint();
    
        GamePanel gamePanel = new GamePanel(frame);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    
        gamePanel.requestFocusInWindow(); // <<--- ADD THIS
        gamePanel.startGameThread();
    }
    

    private void exitGame() {
        System.out.println("Exiting Game...");
        System.exit(0);
    }
}
