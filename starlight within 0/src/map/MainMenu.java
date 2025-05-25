package map;

import application.GamePanel;
<<<<<<< Updated upstream

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
=======
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

>>>>>>> Stashed changes
public class MainMenu {
    
    private JFrame frame;
    GamePanel gp;

<<<<<<< Updated upstream
    // interface menu sebelum masuk
=======
    int x, y;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();

    {
        x = screenWidth;
        y = screenHeight;
    }
    

    // Custom JPanel that draws the background image
    class MainMenuPanel extends JPanel {
        private BufferedImage backgroundImage;

        public MainMenuPanel(BufferedImage image) {
            this.backgroundImage = image;
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // Constructor
>>>>>>> Stashed changes
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
    // what you mean by setup menu?
    private void setupMenu() {
        // Create title label
        JLabel titleLabel = new JLabel("star light within", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

<<<<<<< Updated upstream
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
=======
    private void setupMenu(JPanel backgroundPanel) {
    BufferedImage buttonImage = null;
    BufferedImage buttonImagePress = null;
    try {
        buttonImage = ImageIO.read(getClass().getResourceAsStream("/resource/[1] Normal.png"));
        buttonImagePress = ImageIO.read(getClass().getResourceAsStream("/resource/[2] Clicked.png"));
    } catch (IOException e) {
        e.printStackTrace();
>>>>>>> Stashed changes
    }

    // Panel to hold both buttons centered horizontally at bottom
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20)); // gap=50px, margin=20px
    bottomPanel.setOpaque(false);

    JButton startButton = new JButton("Start");
    JButton exitButton = new JButton("Exit");

    Font buttonFont = new Font("Arial", Font.PLAIN, 24);
    startButton.setFont(buttonFont);
    exitButton.setFont(buttonFont);

    if (buttonImage != null && buttonImagePress != null) {
        ImageIcon icon = new ImageIcon(buttonImage.getScaledInstance(200, 50, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon = new ImageIcon(buttonImagePress.getScaledInstance(200, 50, Image.SCALE_SMOOTH));

        startButton.setIcon(icon);
        startButton.setPressedIcon(pressedIcon);
        exitButton.setIcon(icon);
        exitButton.setPressedIcon(pressedIcon);
    }

    for (JButton button : new JButton[]{startButton, exitButton}) {
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    bottomPanel.add(startButton);
    bottomPanel.add(exitButton);

    backgroundPanel.add(bottomPanel, BorderLayout.SOUTH); // Always bottom and centered

    // Button Actions
    startButton.addActionListener(e -> {
        try {
            startGame();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    });

    exitButton.addActionListener(e -> exitGame());
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
