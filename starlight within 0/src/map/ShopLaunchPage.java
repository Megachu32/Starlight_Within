package map;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class ShopLaunchPage {
    private static JFrame ShopFrame = null;
    private static boolean cooldown = false;
    static int startY = 100;
    static int gap = 60;

    public static void showPanel() {
        if ((ShopFrame == null || !ShopFrame.isDisplayable()) && !cooldown) {
            cooldown = true;

            ShopFrame = new JFrame("Shop");
            ShopFrame.setSize(400, 400);
            ShopFrame.setLayout(null);
            ShopFrame.setLocationRelativeTo(null);
            ShopFrame.getContentPane().setBackground(new Color(30, 30, 30)); // Set a background color for better visibility
            ShopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            ShopFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    ShopFrame = null;
                    startCooldown(); // Delay before next open
                }
            });

            JButton shopButton1 = new JButton("Upgrade Sword");
            JButton shopButton2 = new JButton("Upgrade Shield");
            JButton shopButton3 = new JButton("Upgrade Armor");
            shopButton1.setBounds(100, startY, 200, 50);
            shopButton2.setBounds(100, startY + gap, 200, 50);
            shopButton3.setBounds(100, startY + (gap * 2), 200, 50);
            shopButton1.setFocusable(false);
            shopButton2.setFocusable(false);
            shopButton3.setFocusable(false);

            Font buttonFont = new Font("Arial", Font.BOLD, 16);

            shopButton1.setBackground(new Color(70, 130, 180)); // steel blue
            shopButton1.setForeground(Color.WHITE);
            shopButton1.setFont(buttonFont);

            shopButton2.setBackground(new Color(46, 139, 87)); // sea green
            shopButton2.setForeground(Color.WHITE);
            shopButton2.setFont(buttonFont);

            shopButton3.setBackground(new Color(138, 43, 226)); // purple
            shopButton3.setForeground(Color.WHITE);
            shopButton3.setFont(buttonFont);

            shopButton1.addActionListener(e -> {
                System.out.println("Sword upgraded!");
            });
            shopButton2.addActionListener(e -> {
                System.out.println("shiled upgraded!");
            });
            shopButton3.addActionListener(e -> {
                System.out.println("armor upgraded!");
            });

            ShopFrame.add(shopButton1);
            ShopFrame.add(shopButton2);
            ShopFrame.add(shopButton3); // Fix here
            ShopFrame.setVisible(true);
        }
    }

    private static void startCooldown() {
        new Timer(1000, e -> cooldown = false).start(); // 1 second cooldown
    }
}
