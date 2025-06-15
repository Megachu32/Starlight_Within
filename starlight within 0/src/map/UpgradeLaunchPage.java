package map;

import javax.swing.*;
import java.awt.event.*;

public class UpgradeLaunchPage {

    private static JFrame frame = null;
    private static boolean cooldown = false;

    public static void showPanel() {
    if ((frame == null || !frame.isDisplayable()) && !cooldown) { // check if the frame is noll or displayble or in cooldown
        // If the frame is null or not displayable, and cooldown is false, create a new frame
        cooldown = true; // Set cooldown IMMEDIATELY to block re-entries

        frame = new JFrame("Upgrade Window");// frame title
        frame.setSize(400, 400);// frame size
        frame.setLayout(null);// frame layour wich is null wich means we can set bounds for each component
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// close operation to dispose the frame when closed

        frame.addWindowListener(new WindowAdapter() { // Add a window listener to handle the close event
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
                startCooldown();  // Start delay after closing
            }
        });

        JButton upgradeButtonspeed = new JButton("Speed");
        upgradeButtonspeed.setBounds(100, 100, 200, 50); // set bounds for the button (x, y, width, height)
        upgradeButtonspeed.setFocusable(false);// set focusable to false to prevent focus issues wich is where the button is not clickable
        upgradeButtonspeed.addActionListener(e -> {
            System.out.println("Speed upgraded!");
        });

        frame.add(upgradeButtonspeed);
        frame.setVisible(true);
    }
}


    private static void startCooldown() {
        cooldown = true;
        // Use a Swing Timer to reset cooldown after 1 second (1000 ms)
        new Timer(10000, e -> cooldown = false).start();
    }
}
