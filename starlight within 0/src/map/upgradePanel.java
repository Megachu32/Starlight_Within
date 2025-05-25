package map;

import java.awt.Component;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class upgradePanel extends JPanel{
    public upgradePanel(JFrame frame){
        // setting the layout of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // making the title fo the panel
        JLabel panelName = new JLabel("upgrade panel");
        panelName.setFont(new Font("Arial", Font.BOLD, 24));
        panelName.setAlignmentX(Component.CENTER_ALIGNMENT);
        // making buttons fot the panel
        JButton upgradeButtonSpeed = new JButton("upgrade speed");
        JButton upgradeButtonAttack = new JButton("upgrade attack");
        JButton upgradeButtonDefense = new JButton("upgrade defense");
        JButton upgradeButtonHealth = new JButton("upgrade health");
        JButton BackButton = new JButton("back");
        // the buttons layout
        upgradeButtonSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);
        upgradeButtonAttack.setAlignmentX(Component.CENTER_ALIGNMENT);
        upgradeButtonDefense.setAlignmentX(Component.CENTER_ALIGNMENT);
        upgradeButtonHealth.setAlignmentX(Component.CENTER_ALIGNMENT);
        BackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // adding the buttons and the title to the panel
        add(Box.createVerticalStrut(40));
        add(panelName);
        add(Box.createVerticalStrut(20));
        add(upgradeButtonSpeed);
        add(Box.createVerticalStrut(20));
        add(upgradeButtonAttack);
        add(Box.createVerticalStrut(20));
        add(upgradeButtonDefense);
        add(Box.createVerticalStrut(20));
        add(upgradeButtonHealth);
        add(Box.createVerticalStrut(20));
        add(BackButton);
        // action listeners for the buttons
        upgradeButtonSpeed.addActionListener(e -> {
            // Logic for upgrading speed
            System.out.println("Speed upgraded!");
        });
        upgradeButtonAttack.addActionListener(e -> {
            // Logic for upgrading attack
            System.out.println("Attack upgraded!");
        });
        upgradeButtonDefense.addActionListener(e -> {
            // Logic for upgrading defense
            System.out.println("Defense upgraded!");
        });
        upgradeButtonHealth.addActionListener(e -> {
            // Logic for upgrading health
            System.out.println("Health upgraded!");
        });
        BackButton.addActionListener(e -> {
            // Logic for going back to the previous panel
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new upgradePanel(frame));
            frame.revalidate();
            frame.repaint();
        });
        

        

        

        
    }
}
