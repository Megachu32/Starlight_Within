package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Maps {
    // random map genration
    public BufferedImage image;
    public int nomorMap;
    public String namaMap;

    public BufferedImage getImage(){
        return image;
    }
}
