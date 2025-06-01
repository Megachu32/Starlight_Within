package application;

import java.awt.Rectangle;

public class Hitbox {
    public Rectangle rect;
    public String id;
    public boolean triggered = false;
    public int x, y, width, height;

    public Hitbox(int x, int y, int width, int height, String id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
    }
}
