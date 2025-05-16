package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {

    private JFrame frame;
    GamePanel gp;

    // interface menu sebelum masuk
    public UserInterface() {
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

    //ui for player stats
    public UserInterface(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        drawHealthBar(g2);
        drawManaBar(g2);
        // You can add more draw methods like drawScore(g2);
    }

    private void drawHealthBar(Graphics2D g2) {
        int maxHealth = 100;
        int currentHealth = 75; // Normally you'd pull this from your Player class

        int barWidth = 200;
        int barHeight = 20;
        int x = 20;
        int y = 20;

        float healthPercent = (float) currentHealth / maxHealth;
        int healthBarWidth = (int) (barWidth * healthPercent);

        // Draw background
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, barWidth, barHeight);

        // Draw current health
        g2.setColor(Color.RED);
        g2.fillRect(x, y, healthBarWidth, barHeight);

        // Draw border
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, barWidth, barHeight);
    }

    private void drawManaBar(Graphics2D g2) {
        int maxMana = 50;
        int currentMana = 25;

        int barWidth = 200;
        int barHeight = 20;
        int x = 20;
        int y = 50;

        float manaPercent = (float) currentMana / maxMana;
        int manaBarWidth = (int) (barWidth * manaPercent);

        // Draw background
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, barWidth, barHeight);

        // Draw current mana
        g2.setColor(Color.BLUE);
        g2.fillRect(x, y, manaBarWidth, barHeight);

        // Draw border
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, barWidth, barHeight);
    }
}
