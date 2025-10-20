package map;

import application.GamePanel;
import application.Music;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainMenu {

    Music musik;

    private JFrame frame;
    private BufferedImage mainMenuImage;

    int x, y;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    {
        x = screenWidth / 2;
        y = screenHeight / 2;
    }
    

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

        musik = new Music();
        musik.playMusic("/musik/menuMusic.wav");



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

    // Button panel with FlowLayout to center buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 20));
    buttonPanel.setOpaque(false);

    JButton startButton = new JButton("Start");
    startButton.setFont(new Font("Arial", Font.PLAIN, 24));
    if (buttonImage != null && buttonImagePress != null) {
        startButton.setPressedIcon(new ImageIcon(buttonImagePress.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
        startButton.setIcon(new ImageIcon(buttonImage.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
        startButton.setHorizontalTextPosition(JButton.CENTER);
        startButton.setVerticalTextPosition(JButton.CENTER);
    }
    startButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    startButton.setBorderPainted(false);
    startButton.setContentAreaFilled(false);
    startButton.setFocusPainted(false);
    startButton.setOpaque(false);

    JButton exitButton = new JButton("Exit");
    exitButton.setFont(new Font("Arial", Font.PLAIN, 24));
    if (buttonImage != null && buttonImagePress != null) {
        exitButton.setPressedIcon(new ImageIcon(buttonImagePress.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
        exitButton.setIcon(new ImageIcon(buttonImage.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
        exitButton.setHorizontalTextPosition(JButton.CENTER);
        exitButton.setVerticalTextPosition(JButton.CENTER);
    }
    exitButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    exitButton.setBorderPainted(false);
    exitButton.setContentAreaFilled(false);
    exitButton.setFocusPainted(false);
    exitButton.setOpaque(false);

    // Add buttons
    buttonPanel.add(startButton);
    buttonPanel.add(exitButton);

    // Add button panel to the bottom (SOUTH) of the background panel
    backgroundPanel.setLayout(new BorderLayout());
    backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

    // Button actions
    startButton.addActionListener(e -> {
        try {
            musik.stopMusic();
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
