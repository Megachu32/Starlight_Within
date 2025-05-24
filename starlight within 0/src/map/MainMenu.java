package map;

import application.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu {

    private JFrame frame;
    private BufferedImage mainMenuImage;

    int x, y;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    int buttonWidth = 200;
    int buttonHeight = 60;
    int spacing = 60; // space between buttons

    int totalWidth = (buttonWidth * 2) + spacing;
    int xStart = (screenWidth - totalWidth) / 2 + 260; // 20px from left edge
    int yPos = screenHeight - buttonHeight - 20; // 80px from bottom

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
    public MainMenu() {
        frame = new JFrame("My Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null); // Center the frame

        try {
            mainMenuImage = ImageIO.read(getClass().getResourceAsStream("/resource/WhatsApp Image 2025-05-23 at 10.16.25_64f92536.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mainMenuImage == null) {
            System.err.println("Error: Main menu image not found.");
            return;
        }

        MainMenuPanel backgroundPanel = new MainMenuPanel(mainMenuImage);
        setupMenu(backgroundPanel);
        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    private void setupMenu(JPanel backgroundPanel) {
        BufferedImage buttonImage = null;
        BufferedImage buttonImagePress = null;
        try {
            buttonImage = ImageIO.read(getClass().getResourceAsStream("/resource/[1] Normal.png"));
            buttonImagePress = ImageIO.read(getClass().getResourceAsStream("/resource/[2] Clicked.png"));
            System.out.println("Button images loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading button images: " + e.getMessage());
            e.printStackTrace();
        }


        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setOpaque(false);

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        if (buttonImage != null && buttonImagePress != null) {
            startButton.setPressedIcon(new ImageIcon(buttonImagePress.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
            startButton.setIcon(new ImageIcon(buttonImage.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
            startButton.setHorizontalTextPosition(JButton.CENTER);
            startButton.setVerticalTextPosition(JButton.CENTER);
        }
        startButton.setBounds(xStart, yPos, buttonWidth, buttonHeight);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);
        buttonPanel.add(startButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        if (buttonImage != null && buttonImagePress != null) {
            exitButton.setPressedIcon(new ImageIcon(buttonImagePress.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
            exitButton.setIcon(new ImageIcon(buttonImage.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
            exitButton.setHorizontalTextPosition(JButton.CENTER);
            exitButton.setVerticalTextPosition(JButton.CENTER);
        }
        exitButton.setBounds(xStart + buttonWidth + spacing, yPos, buttonWidth, buttonHeight);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);
        buttonPanel.add(exitButton);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20)); // Centered with 20 px top margin
        wrapper.add(buttonPanel);

        // Add to bottom
        frame.add(wrapper, BorderLayout.SOUTH);

        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

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

        gamePanel.requestFocusInWindow();
        gamePanel.startGameThread();
    }

    private void exitGame() {
        System.out.println("Exiting Game...");
        System.exit(0);
    }
}
