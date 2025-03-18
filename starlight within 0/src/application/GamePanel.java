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
    BufferedImage[] frames;
    BufferedImage[] framesRun;
    int currentFrame = 0;
    int frameWidth;
    int frameHeight;
    int animationCounter = 0;
    int animationSpeed = 100;
    int spriteTotalFrame = 10; 
    String direction = "right";
    static boolean isMoving;

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

        //Check if the image is loaded
        if (playerImage == null) {
            System.out.println("Error _Idle.png not found");
            return;
        }
        if(playerImageMove == null){
            System.out.println("Error _Run.png not found");
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
    }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
    
            //for triger moving run 
            if(framesRun != null && framesRun.length > 0 && framesRun[currentFrame] != null && isMoving == true){
                
                BufferedImage spriteRuns = framesRun[currentFrame];

                if (direction.equals("left")) {
                    // Flip sprite for left-facing idle animation
                    spriteRuns = flipImageHorizontally(spriteRuns);
                }

                g2.drawImage(spriteRuns, playerX, playerY, tileSize, tileSize, this);
            }
            // else{
            //     g2.setColor(Color.WHITE);
            //     g2.fillRect(playerX, playerY, tileSize, tileSize);
            // }
            
            //for triger staying
            if (frames != null && frames.length > 0 && frames[currentFrame] != null && isMoving == false) {
                BufferedImage sprite = frames[currentFrame];
    
                if (direction.equals("left")) {
                    // Flip sprite for left-facing idle animation
                    sprite = flipImageHorizontally(sprite);
                }
    
                g2.drawImage(sprite, playerX, playerY, tileSize, tileSize, this);
            } 
            // else {
            //     g2.setColor(Color.WHITE);
            //     g2.fillRect(playerX, playerY, tileSize, tileSize);
            // }
    
            g2.dispose();

        
        // ✅ Keep animating even when idle
        if (++animationCounter >= animationSpeed) {
            animationCounter = 0;
            if (spriteTotalFrame > 0) {
                currentFrame = (currentFrame + 1) % spriteTotalFrame;
            }
        }
    }
    private BufferedImage flipImageHorizontally(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();
        g.drawImage(image, 0, 0, width, height, width, 0, 0, height, null);
        g.dispose();
        return flipped;
    }
}
