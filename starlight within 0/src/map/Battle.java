package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Battle extends Maps{
    // use better naming
    public BufferedImage image;
    // fuck you
    public Battle() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/PNG/3/castle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // to draw the actual image
    public void draw(Graphics2D g2,int x, int y, int screenWidth, int screenHeight) {
        if (image != null) {
            g2.drawImage(image, x, y, screenWidth, screenHeight, null);
        }
    }

    @Override
    public BufferedImage getImage() {
        // TODO Auto-generated method stub
        return image;
    }
}
