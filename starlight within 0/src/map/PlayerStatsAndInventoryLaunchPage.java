package map;

import entity.Player;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PlayerStatsAndInventoryLaunchPage {

    static Player player = new Player(null, null, null);

    public static void ShowStatsAndInventory() {
        // Load image inside the method to avoid static block issues
        BufferedImage fullImage = null;
        try {
            fullImage = ImageIO.read(PlayerStatsAndInventoryLaunchPage.class.getResourceAsStream("/resource/_Idle.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedImage croppedImage = null;
        if (fullImage != null) {
            croppedImage = fullImage.getSubimage(0, 0, 120, 80);
        }

        Image scalledImage = croppedImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH);

        JFrame StatsFrame = new JFrame("Player Stats and Inventory");
        StatsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        StatsFrame.setSize(500, 400);
        StatsFrame.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Character Stats");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        StatsFrame.add(title, BorderLayout.NORTH);

        // Player sprite panel
        JPanel spritePanel = new JPanel();
        spritePanel.setBackground(new Color(30, 30, 30));
        if (croppedImage != null) {
            JLabel spriteLabel = new JLabel(new ImageIcon(scalledImage));
            spritePanel.add(spriteLabel);
        } else {
            spritePanel.add(new JLabel("Image not found"));
        }
        StatsFrame.add(spritePanel, BorderLayout.CENTER);

        // Inventory grid
        JPanel inventoryPanel = new JPanel(new GridLayout(3, 3));
        inventoryPanel.setBackground(new Color(30, 30, 30));
        for (int i = 0; i < 9; i++) {
            JLabel itemSlot = new JLabel();
            itemSlot.setPreferredSize(new Dimension(50, 50));
            itemSlot.setOpaque(true);
            itemSlot.setBackground(new Color(60, 60, 60));
            itemSlot.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            inventoryPanel.add(itemSlot);
        }
        StatsFrame.add(inventoryPanel, BorderLayout.EAST);

        // Stats panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(new Color(30, 30, 30));

        JLabel hpLabel = new JLabel("HP: " + player.getHp() + " / " + player.getMaxHp());
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statsPanel.add(hpLabel);

        JLabel LevelLabel = new JLabel("lvl: " + player.getLevel());
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statsPanel.add(LevelLabel);

        JLabel goldLabel = new JLabel("Gold: " + player.getGold());
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statsPanel.add(goldLabel);

        JLabel ManaLabel = new JLabel("Mana: " + player.getMana() + " / " + player.getManaMax());
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statsPanel.add(ManaLabel);

        JLabel DefenseLabel = new JLabel("Defense: " + player.getPhysicalArmor());
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statsPanel.add(DefenseLabel);

        StatsFrame.add(statsPanel, BorderLayout.SOUTH);

        StatsFrame.pack(); // Optional: fits components
        StatsFrame.setLocationRelativeTo(null); // Centers the frame
        StatsFrame.setVisible(true);
    }

    private static JLabel createStatLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }
}
