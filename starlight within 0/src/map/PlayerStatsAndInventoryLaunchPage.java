package map;

import entity.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;// Assuming you have a Player class that holds player stats

public class PlayerStatsAndInventoryLaunchPage {
    // Remove direct instantiation; pass Player as a parameter instead

    static Player player = new Player(null, null, null); // Assuming you have a Player class that holds player stats
    public static void ShowStatsAndInventory() {
        JFrame StatsFrame = new JFrame("Player Stats and Inventory");
        StatsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        StatsFrame.setSize(500,400);
        StatsFrame.setLayout(new BorderLayout()); // what is border layout? bodrer layout is a layout manager that arranges components in five regions: north, south, east, west, and center.


        // player sprite
        JLabel spritelabel = new JLabel(new ImageIcon(""));
        JPanel spritePanel = new JPanel();
        spritePanel.add(spritelabel);

        StatsFrame.add(spritePanel, BorderLayout.CENTER);

        // inventory panel
        JPanel inventoryPanel = new JPanel(new GridLayout(3,3));
        for (int i = 0 ; i < 9 ; i++){
            JLabel itemSlot = new JLabel();
            itemSlot.setPreferredSize(new java.awt.Dimension(50, 50));
            itemSlot.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
            itemSlot.setOpaque(true);
            itemSlot.setBackground(new Color(60, 60, 60));
            itemSlot.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            inventoryPanel.add(itemSlot);
            
        }
        StatsFrame.add(inventoryPanel, BorderLayout.EAST);

         JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.add(new JLabel("HP: 100 / 100"));
        statsPanel.add(new JLabel("Mana: 50 / 50"));
        statsPanel.add(new JLabel("Strength: 20"));
        statsPanel.add(new JLabel("Defense: 15"));
        StatsFrame.add(statsPanel, BorderLayout.SOUTH);

        StatsFrame.getContentPane().setBackground(new Color(30, 30, 30));
        spritePanel.setBackground(new Color(30, 30, 30));
        inventoryPanel.setBackground(new Color(30, 30, 30));
        statsPanel.setBackground(new Color(30, 30, 30));
        
        JLabel stat = new JLabel("HP: " + player.getHp() + " / " + player.getMaxHp());
        stat.setForeground(Color.WHITE);
        stat.setFont(new Font("Arial", Font.BOLD, 16));
        statsPanel.add(stat);

        JLabel title = new JLabel("Character Stats");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        StatsFrame.add(title, BorderLayout.NORTH);



        


        StatsFrame.setVisible(true);




    }
}
