package map;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

public class EscMenuLaunchPage {
    private static JFrame Escframe = null;
    static int startY = 100;
    static int gap = 60;
    boolean exit = false;

    public static void showPanel() {
    if ((Escframe == null || !Escframe.isDisplayable())) { // check if the frame is noll or displayble or in cooldown
        // If the frame is null or not displayable, and cooldown is false, create a new frame

        Escframe = new JFrame();
        Escframe.setUndecorated(true); // removes title bar
        Escframe.setSize(400, 400);// frame size
        Escframe.setLayout(null);// frame layour wich is null wich means we can set bounds for each component
        Escframe.setLocationRelativeTo(null);
        Escframe.getContentPane().setBackground(new Color(30, 30, 30)); // Set a background color for better visibility
        Escframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// close operation to dispose the frame when closed

        Escframe.addWindowListener(new WindowAdapter() { // Add a window listener to handle the close event
            @Override
            public void windowClosed(WindowEvent e) {
                Escframe = null;
            }
        });

        JButton EscMenuButton1 = new JButton("resume");
        JButton EscMenuButton2 = new JButton("settings");
        JButton EscMenuButton3 = new JButton("exit");
        EscMenuButton1.setBounds(100, startY, 200, 50); // set bounds for the button (x, y, width, height)
        EscMenuButton2.setBounds(100, startY + gap, 200, 50); // set bounds for the button (x, y, width, height)
        EscMenuButton3.setBounds(100, startY + (gap * 2), 200, 50); // set bounds for the button (x, y, width, height)
        EscMenuButton1.setFocusable(false);// set focusable to false to prevent focus issues wich is where the button is not clickable
        EscMenuButton2.setFocusable(false);// set focusable to false to prevent focus issues wich is where the button is not clickable
        EscMenuButton3.setFocusable(false);// set focusable to false to prevent focus issues wich is where the button is not clickable

        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        EscMenuButton1.setBackground(new Color(70, 130, 180)); // steel blue
        EscMenuButton1.setForeground(Color.WHITE);
        EscMenuButton1.setFont(buttonFont);

        EscMenuButton2.setBackground(new Color(46, 139, 87)); // sea green
        EscMenuButton2.setForeground(Color.WHITE);
        EscMenuButton2.setFont(buttonFont);

        EscMenuButton3.setBackground(new Color(138, 43, 226)); // purple
        EscMenuButton3.setForeground(Color.WHITE);
        EscMenuButton3.setFont(buttonFont);

        EscMenuButton1.addActionListener(e -> {
            System.out.println("resumed!");
            Escframe.dispose(); // close the frame manually
        });
        EscMenuButton2.addActionListener(e -> {
            System.out.println("settings opened!");
        });
        EscMenuButton3.addActionListener(e -> {
            System.out.println("exited!");
            System.exit(0); // Exits the application
        });

        Escframe.add(EscMenuButton1);
        Escframe.add(EscMenuButton2);
        Escframe.add(EscMenuButton3);
        Escframe.setVisible(true);
        }
    }
    
}
