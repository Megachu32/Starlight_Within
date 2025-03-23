package application;
import entity.Player;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    final int OriginalTaleSize = 32; // tile size
    final int scale = 10; // scale
    final int tileSize = OriginalTaleSize * scale;
    int fps = 60; 

    public BufferedImage playerImage;
    public BufferedImage playerImageMove;
    public BufferedImage playerJump;
    BufferedImage[] frames;
    BufferedImage[] framesRun;
    BufferedImage[] framesJump;
    int currentFrame = 0;
    int frameWidth;
    int frameHeight;
    int animationCounter = 0;
    int animationSpeed = 100;
    int spriteTotalFrame = 10; 
    String direction = "right";
    static boolean isMoving;
    static boolean isJump;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 7; 

    public GamePanel(JFrame window) throws IOException {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final int screenWidth = gd.getDisplayMode().getWidth();
        final int screenHeight = gd.getDisplayMode().getHeight();

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // ✅ Load player image first before calculating frame size
        playerImage = ImageIO.read(getClass().getResourceAsStream("/resource/_Idle.png"));
        playerImageMove = ImageIO.read(getClass().getResource("/resource/Colour1/NoOutline/120x80_PNGSheets/_Run.png"));
        playerJump = ImageIO.read(getClass().getResource("/resource/Colour1/NoOutline/120x80_PNGSheets/_Roll.png"));

        //Check if the image is loaded
        if (playerImage == null) {
            System.out.println("Error _Idle.png not found");
            return;
        }
        if(playerImageMove == null){
            System.out.println("Error _Run.png not found");
        }
        if(playerJump == null){
            System.out.println("Error _Roll.png not found");
        }

        // ✅ Calculate frame dimensions AFTER loading the image
        frameWidth = playerImage.getWidth() / spriteTotalFrame;
        frameHeight = playerImage.getHeight();

        // ✅ Load sprite frames
        frames = new BufferedImage[spriteTotalFrame];
        for (int i = 0; i < spriteTotalFrame; i++) {
            frames[i] = playerImage.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }

        framesRun = new BufferedImage[spriteTotalFrame];
        for(int i = 0; i < spriteTotalFrame; i++){
            framesRun[i] = playerImageMove.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }

        framesJump = new BufferedImage[12];
        for(int i = 0; i < 12; i++){
            framesJump[i] = playerJump.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }
        

        System.out.println("Loaded " + spriteTotalFrame + " frames. Frame width: " + frameWidth);
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
        isMoving = false; // Track if the player is moving
        isJump = false;

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
            if(direction.equals("right")){
                playerX += playerSpeed ;
            }
            if(direction.equals("left")){
                playerX -= playerSpeed ;
            }
            direction = "space";
            isMoving = true;
            isJump = true;
        }
    }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            
            // Ensure Jump animation has priority
if (framesJump != null && isJump) {
    BufferedImage spriteJump = framesJump[currentFrame];

    if (direction.equals("left")) {
        spriteJump = flipImageHorizontally(spriteJump);
    }

    g2.drawImage(spriteJump, playerX, playerY, tileSize, tileSize, this);
}
// Only draw run if not jumping
else if (framesRun != null && isMoving) {
    BufferedImage spriteRun = framesRun[currentFrame];

    if (direction.equals("left")) {
        spriteRun = flipImageHorizontally(spriteRun);
    }

    g2.drawImage(spriteRun, playerX, playerY, tileSize, tileSize, this);
}
// Draw idle last
else if (frames != null) {
    BufferedImage spriteIdle = frames[currentFrame];

    if (direction.equals("left")) {
        spriteIdle = flipImageHorizontally(spriteIdle);
    }

    g2.drawImage(spriteIdle, playerX, playerY, tileSize, tileSize, this);
}

            
            
    
            g2.dispose();

        
        // ✅ Keep animating even when idle
        if (++animationCounter >= animationSpeed) {
            animationCounter = 0;
            if (isJump) {
                currentFrame = (currentFrame + 1) % framesJump.length;
            } else if (isMoving) {
                currentFrame = (currentFrame + 1) % framesRun.length;
            } else {
                currentFrame = (currentFrame + 1) % frames.length;
            }
        }
    }
    private BufferedImage flipImageHorizontally(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();
        
        // Corrected transformation
        g.drawImage(image, width, 0, -width, height, null);  
        g.dispose();
        
        return flipped;
    }
}
