package map;

import application.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainMenu {

    private JFrame frame;
    private BufferedImage mainMenuImage;

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

        if(mainMenuImage == null) {
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

        // Buttons panel
        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setOpaque(false); // Transparent panel

        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        if(buttonImage != null && buttonImagePress != null) {
            startButton.setPressedIcon(new ImageIcon(buttonImagePress.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
            startButton.setIcon(new ImageIcon(buttonImage.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
            startButton.setHorizontalTextPosition(JButton.CENTER);
            startButton.setVerticalTextPosition(JButton.CENTER);
        }
        startButton.setBounds(700,820, 200, 50);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);
        buttonPanel.add(startButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        if(buttonImage != null && buttonImagePress != null) {
            exitButton.setPressedIcon(new ImageIcon(buttonImagePress.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
            exitButton.setIcon(new ImageIcon(buttonImage.getScaledInstance(200, 50, Image.SCALE_SMOOTH)));
            exitButton.setHorizontalTextPosition(JButton.CENTER);
            exitButton.setVerticalTextPosition(JButton.CENTER);
        }
        exitButton.setBounds(1000,820, 200, 50);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);
        buttonPanel.add(exitButton);

        // Add button panel to center
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Button actions
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

        gamePanel.requestFocusInWindow();
        gamePanel.startGameThread();
    }

    private void exitGame() {
        System.out.println("Exiting Game...");
        System.exit(0);
    }
}
