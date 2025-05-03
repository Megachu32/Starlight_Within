package application;

import entity.Player;
import map.Loby;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javax.imageio.ImageIO;
import javax.swing.*;
import entity.*;

public class GamePanel extends JPanel implements Runnable {

    final int OriginalTileSize = 32;
    final int scale = 10;
    final int tileSize = OriginalTileSize * scale;

    final int fps = 60;

    BufferedImage[] framesIdle;
    BufferedImage[] framesRun;
    BufferedImage[] framesRoll;
    int currentFrame = 0;
    int animationCounter = 0;
    final int animationSpeed = 100; // Lower number = faster animation (smaller is faster)

    int frameWidth;
    int frameHeight;

    String direction = "right";
    boolean isMoving = false;
    boolean isRolling = false;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    int playerX = 100;
    int playerY = 100;
    final int playerSpeed = 7;

    int screenWidthTemp;
    int screenHeightTemp;

    Instant timeNow = Instant.now();
    Instant lastRollTime = Instant.now(); // when you last rolled

    Loby loby;

    public GamePanel(JFrame window) throws IOException {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final int screenWidth = gd.getDisplayMode().getWidth();
        final int screenHeight = gd.getDisplayMode().getHeight();
        screenWidthTemp = screenWidth;
        screenHeightTemp = screenHeight;

        loby = new Loby();

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        loadSprites();
    }

    private void loadSprites() throws IOException {
        BufferedImage idleSheet = ImageIO.read(getClass().getResourceAsStream("/resource/_Idle.png"));
        BufferedImage runSheet = ImageIO.read(getClass().getResource("/resource/Colour1/NoOutline/120x80_PNGSheets/_Run.png"));
        BufferedImage rollSheet = ImageIO.read(getClass().getResource("/resource/Colour1/NoOutline/120x80_PNGSheets/_Roll.png"));

        if (idleSheet == null || runSheet == null || rollSheet == null) {
            System.out.println("Error loading images");
            return;
        }

        frameWidth = idleSheet.getWidth() / 10;
        frameHeight = idleSheet.getHeight();

        framesIdle = new BufferedImage[10];
        for (int i = 0; i < 10; i++) {
            framesIdle[i] = idleSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }

        framesRun = new BufferedImage[10];
        for (int i = 0; i < 10; i++) {
            framesRun[i] = runSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }

        framesRoll = new BufferedImage[12];
        for (int i = 0; i < 12; i++) {
            framesRoll[i] = rollSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }

        System.out.println("Loaded sprites successfully. Frame size: " + frameWidth + "x" + frameHeight);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double renderInterval = 1000000000.0 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / renderInterval;
            lastTime = currentTime;

            while (delta >= 1) {
                update();
                delta--;
            }

            repaint();
        }
    }

    public void update() {
        isMoving = false;
        isRolling = false;
    
        Duration timeSinceLastRoll = Duration.between(lastRollTime, Instant.now());
    
        if (keyH.left) {
            playerX -= playerSpeed;
            direction = "left";
            isMoving = true;
        }
        if (keyH.right) {
            playerX += playerSpeed;
            direction = "right";
            isMoving = true;
        }
        if (keyH.up) {
            playerY -= playerSpeed;
            isMoving = true;
        }
        if (keyH.down) {
            playerY += playerSpeed;
            isMoving = true;
        }
    
        if (keyH.space) {
            lastRollTime = Instant.now(); // reset timer
            isRolling = true;

            if (direction.equals("right")) {
                playerX += playerSpeed;
            } else if (direction.equals("left")) {
                playerX -= playerSpeed;
            }
        }
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        loby.draw(g2, -playerX, -playerY, screenWidthTemp, screenHeightTemp);

        BufferedImage spriteToDraw = null;

        if (isRolling) {
            spriteToDraw = framesRoll[currentFrame % framesRoll.length];
        } else if (isMoving) {
            spriteToDraw = framesRun[currentFrame % framesRun.length];
        } else {
            spriteToDraw = framesIdle[currentFrame % framesIdle.length];
        }

        if (direction.equals("left")) {
            spriteToDraw = flipImageHorizontally(spriteToDraw);
        }

        g2.drawImage(spriteToDraw, (screenWidthTemp - tileSize) / 2, (screenHeightTemp - (tileSize * 2)) / 2, tileSize, tileSize, this);
        
        drawHealthAndManaBars(g2);

        g2.dispose();

        updateAnimation();
    }

    private void updateAnimation() {
        animationCounter++;
        if (animationCounter >= animationSpeed) {
            animationCounter = 0;
            currentFrame++;
        }
    }

    private BufferedImage flipImageHorizontally(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();
        g.drawImage(image, width, 0, -width, height, null);
        g.dispose();
        return flipped;
    }

    private void drawHealthAndManaBars(Graphics2D g2) {
        int healthMax = player.getMaxHp();
        int healthCurrent = player.getHp();
    
        int manaMax = player.getManaMax();
        int manaCurrent = player.getMana();
    
        // Bar sizes
        int barWidth = 250;
        int barHeight = 30;
        int x = 20;
        int y = 30;
    
        // Health Bar
        g2.setColor(Color.DARK_GRAY); // Background
        g2.fillRect(x, y, barWidth, barHeight);
        g2.setColor(Color.RED); // Health lost
        g2.fillRect(x, y, barWidth, barHeight);
        g2.setColor(Color.GREEN); // Current health
        int healthWidth = (int)((double)healthCurrent / healthMax * barWidth);
        g2.fillRect(x, y, healthWidth, barHeight);
    
        // Mana Bar (below health bar)
        int manaY = y + barHeight + 10;
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(x, manaY, barWidth, barHeight);
        g2.setColor(Color.BLUE);
        int manaWidth = (int)((double)manaCurrent / manaMax * barWidth);
        g2.fillRect(x, manaY, manaWidth, barHeight);
    
        // Optional: Draw borders
        g2.setColor(Color.WHITE);
        g2.drawRect(x, y, barWidth, barHeight);
        g2.drawRect(x, manaY, barWidth, barHeight);
    }
    
}
