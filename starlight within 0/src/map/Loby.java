package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Loby {
    // use better naming
    public BufferedImage mum;
    // fuck you
    public Loby() {
        try {
            mum = ImageIO.read(getClass().getResourceAsStream("/PNG/1/terrace.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // to draw the actual image
    public void draw(Graphics2D g2,int x, int y, int screenWidth, int screenHeight) {
        if (mum != null) {
            g2.drawImage(mum, x, y, screenWidth, screenHeight, null);
        }
    }
}
