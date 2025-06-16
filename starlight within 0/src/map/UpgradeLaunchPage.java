package map;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

import entity.Player;

public class UpgradeLaunchPage {

    private static JFrame frame = null;
    private static boolean cooldown = false;
    static int startY = 100;
    static int gap = 60;

    static int speedPrice = 100; // Price for speed upgrade
    static int hpPrice = 100; // Price for hp upgrade
    static int manaPrice = 100; // Price for mana upgrade

    public static void showPanel(Player player) {
        // Check if the frame is null or not displayable, and if cooldown is false
        // If the frame is null or not displayable, and cooldown is false, create a new frame
    if ((frame == null || !frame.isDisplayable()) && !cooldown) { // check if the frame is noll or displayble or in cooldown
        // If the frame is null or not displayable, and cooldown is false, create a new frame
        cooldown = true; // Set cooldown IMMEDIATELY to block re-entries

        frame = new JFrame("Upgrade Window");// frame title
        frame.setSize(400, 400);// frame size
        frame.setLayout(null);// frame layour wich is null wich means we can set bounds for each component
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(30, 30, 30)); // Set a background color for better visibility
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// close operation to dispose the frame when closed

        frame.addWindowListener(new WindowAdapter() { // Add a window listener to handle the close event
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
                startCooldown();  // Start delay after closing
            }
        });

        JLabel titleUpgrade = new JLabel ("Upgrade Options"); // label for the panel
        titleUpgrade.setBounds(120,50,300,30); // set bound for the label (x, y, width, height)
        titleUpgrade.setForeground(Color.WHITE); // set the text color to white
        titleUpgrade.setFont(new Font("Arial", Font.BOLD, 18)); // set the font for the label
        frame.add(titleUpgrade); // add the label to the frame

        JLabel infoUpgrade = new JLabel("", SwingConstants.CENTER); // label for the info
        infoUpgrade.setBounds(50,startY + (gap * 2) + 50, 300, 30); // set bounds for the label (x, y, width, height)
        infoUpgrade.setForeground(Color.WHITE); // set the text color to white
        infoUpgrade.setFont(new Font("Arial", Font.PLAIN, 14)); // set the font for the label
        frame.add(infoUpgrade); // add the label to the frame
        
        JButton upgradeButtonspeed = new JButton("Speed");
        JButton upgradeButtonHp = new JButton("hp");
        JButton upgradeButtonMana = new JButton("mana");
        upgradeButtonspeed.setBounds(100, startY, 200, 50); // set bounds for the button (x, y, width, height)
        upgradeButtonHp.setBounds(100, startY + gap, 200, 50); // set bounds for the button (x, y, width, height)
        upgradeButtonMana.setBounds(100, startY + (gap * 2), 200, 50); // set bounds for the button (x, y, width, height)
        upgradeButtonspeed.setFocusable(false);// set focusable to false to prevent focus issues wich is where the button is not clickable
        upgradeButtonHp.setFocusable(false);// set focusable to false to prevent focus issues wich is where the button is not clickable
        upgradeButtonMana.setFocusable(false);// set focusable to false to prevent focus issues wich is where the button is not clickable
        
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        upgradeButtonspeed.setBackground(new Color(70, 130, 180)); // steel blue
        upgradeButtonspeed.setForeground(Color.WHITE);
        upgradeButtonspeed.setFont(buttonFont);

        upgradeButtonHp.setBackground(new Color(46, 139, 87)); // sea green
        upgradeButtonHp.setForeground(Color.WHITE);
        upgradeButtonHp.setFont(buttonFont);

        upgradeButtonMana.setBackground(new Color(138, 43, 226)); // purple
        upgradeButtonMana.setForeground(Color.WHITE);
        upgradeButtonMana.setFont(buttonFont);

        upgradeButtonspeed.addActionListener(e -> {
            if( player.getGold() >= speedPrice) { // Check if player has enough coins
                player.setSpeed(player.getSpeed() + 15); // Upgrade speed
                player.setGold(player.getGold() - speedPrice); // Deduct coins
                speedPrice += 50; // Increase the price for the next speed upgrade
                System.out.println("speed upgraded!");
                infoUpgrade.setText("speed upgraded!"); // Update the info label with the upgrade message
            } else {
                infoUpgrade.setText("Not enough coins for speed upgrade!"); // Update the info label with the error message
            }
        });
        upgradeButtonHp.addActionListener(e -> {
            if( player.getGold() >= hpPrice) { // Check if player has enough coins
                player.setMaxHp(player.getMaxHp() + 10); // Upgrade hp
                player.setGold(player.getGold() - hpPrice); // Deduct coins
                hpPrice += 50; // Increase the price for the next hp upgrade
                System.out.println("hp upgraded!");
                infoUpgrade.setText("hp upgraded!"); // Update the info label with the upgrade message
            } else {
                infoUpgrade.setText("Not enough coins for hp upgrade!"); // Update the info label with the error message
            }
        });
        upgradeButtonMana.addActionListener(e -> {
            if( player.getGold() >= manaPrice) { // Check if player has enough coins
                player.setManaMax(player.getManaMax() + 10); // Upgrade mana
                player.setGold(player.getGold() - manaPrice); // Deduct coins
                manaPrice += 50; // Increase the price for the next mana upgrade
                System.out.println("mana upgraded!");
                infoUpgrade.setText("mana upgraded!"); // Update the info label with the upgrade message
            } else {
                infoUpgrade.setText("Not enough coins for mana upgrade!"); // Update the info label with the error message
            }
        });

        frame.add(upgradeButtonspeed);
        frame.add(upgradeButtonHp);
        frame.add(upgradeButtonMana);
        frame.setVisible(true);
    }
}


    private static void startCooldown() {
        cooldown = true;
        // Use a Swing Timer to reset cooldown after 1 second (1000 ms)
        new Timer(10000, e -> cooldown = false).start();
    }
}
