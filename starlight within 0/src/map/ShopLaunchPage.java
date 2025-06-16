package map;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

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

            JLabel titleShop = new JLabel ("what do you want to buy"); // label for the panel
            titleShop.setBounds(120,50,300,30); // set bound for the label (x, y, width, height)
            titleShop.setForeground(Color.WHITE); // set the text color to white
            titleShop.setFont(new Font("Arial", Font.BOLD, 18)); // set the font for the label
            ShopFrame.add(titleShop); // add the label to the frame

            JLabel infoShop = new JLabel("", SwingConstants.CENTER); // label for the info
            infoShop.setBounds(50,startY + (gap * 2) + 50, 300, 30); // set bounds for the label (x, y, width, height)
            infoShop.setForeground(Color.WHITE); // set the text color to white
            infoShop.setFont(new Font("Arial", Font.PLAIN, 14)); // set the font for the label
            ShopFrame.add(infoShop); // add the label to the frame

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
                infoShop.setText("Sword upgraded!"); // Update info label
            });
            shopButton2.addActionListener(e -> {
                System.out.println("shiled upgraded!");
                infoShop.setText("Shield upgraded!"); // Update info label
            });
            shopButton3.addActionListener(e -> {
                System.out.println("armor upgraded!");
                infoShop.setText("Armor upgraded!"); // Update info label
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
