package application;

import javax.swing.*;

import map.MainMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {

    private JFrame frame;
    GamePanel gp;

    // interface menu sebelum masuk
    public UserInterface() {
        MainMenu menu = new MainMenu();
    }

    //ui for player stats
    public UserInterface(GamePanel gp) {
        this.gp = gp;
    }
    public void draw(Graphics2D g2) {
        drawHealthBar(g2);
        drawManaBar(g2);
        // You can add more draw methods like drawScore(g2);
    }

    private void drawHealthBar(Graphics2D g2) {
        int maxHealth = 100;
        int currentHealth = 75; // Normally you'd pull this from your Player class

        int barWidth = 200;
        int barHeight = 20;
        int x = 20;
        int y = 20;

        float healthPercent = (float) currentHealth / maxHealth;
        int healthBarWidth = (int) (barWidth * healthPercent);

        // Draw background
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, barWidth, barHeight);

        // Draw current health
        g2.setColor(Color.RED);
        g2.fillRect(x, y, healthBarWidth, barHeight);

        // Draw border
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, barWidth, barHeight);
    }

    private void drawManaBar(Graphics2D g2) {
        int maxMana = 50;
        int currentMana = 25;

        int barWidth = 200;
        int barHeight = 20;
        int x = 20;
        int y = 50;

        float manaPercent = (float) currentMana / maxMana;
        int manaBarWidth = (int) (barWidth * manaPercent);

        // Draw background
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, barWidth, barHeight);

        // Draw current mana
        g2.setColor(Color.BLUE);
        g2.fillRect(x, y, manaBarWidth, barHeight);

        // Draw border
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, barWidth, barHeight);
    }
}
