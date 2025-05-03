package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Loby {

    BufferedImage mum;

    public Loby() {
        try {
            mum = ImageIO.read(getClass().getResourceAsStream("/resource/lolme.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2,int x, int y, int screenWidth, int screenHeight) {
        if (mum != null) {
            g2.drawImage(mum, x, y, screenWidth, screenHeight, null);
        }
    }
}
