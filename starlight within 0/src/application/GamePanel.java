package application;

import entity.Player;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import javax.imageio.ImageIO;
import javax.swing.*;
import map.Loby;

public class GamePanel extends JPanel implements Runnable {

    Player player; // calling the player

    Music musik;

    // calling loby
    Loby loby;

    //map size
    int mapHeight;
    int mapWidth;

    final int OriginalTileSize = 32; // whats this
    final int scale = 10; // whats this
    final int tileSize = OriginalTileSize * scale; // whats this

    final int fps = 60;// fps for the game

    // initralize the variable for the image
    BufferedImage[] framesIdle;
    BufferedImage[] framesRun;
    BufferedImage[] framesRoll;
    BufferedImage[] framesAttack;

    int currentFrame = 0;
    int animationCounter = 0;
    final int animationSpeed = 20; // Lower number = faster animation (smaller is faster)

    // sprite size
    int frameWidth;
    int frameHeight;

    String direction = "right";
    boolean isMoving = false;
    boolean isRolling = false;

    KeyHandler keyH = new KeyHandler(); // calling keybaord
    Thread gameThread;

    
    final int playerSpeed = 7;

    int screenWidthTemp;
    int screenHeightTemp;
    int playerX = 1800;
    int playerY = 700;

    boolean rolling = false;
    int rollingCounter = 0;
    final int rollDuration = 36; // How many frames the roll lasts (same as roll animation frames)
    BufferedImage currentImage;

    Instant timeNow = Instant.now();// whats this
    Instant lastRollTime = Instant.now(); // when you last rolled

    public boolean toggleCursor = false;

    public boolean canRoll() {
        //TODO connect to roll method
        Instant now = Instant.now();
        Duration timeElapsed = Duration.between(lastRollTime, now);

        if (timeElapsed.getSeconds() >= 1) {
            lastRollTime = now; // update to the new roll time
            keyH.space = true; // reset the space key
            return true;
        }
        keyH.space = false; // reset the space key
        return false;
    }

    public GamePanel(JFrame window) throws IOException {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final int screenWidth = gd.getDisplayMode().getWidth();
        final int screenHeight = gd.getDisplayMode().getHeight();
        screenWidthTemp = screenWidth;
        screenHeightTemp = screenHeight;
        // calling loby
        loby = new Loby();

        // calling music player
        musik = new Music();
        // load music
        musik.playMusic("/musik/lobyMusic.wav");
        // getingy the map size
        mapHeight = loby.image.getHeight();
        mapWidth = loby.image.getWidth();

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        player = new Player(this, keyH);

        loadSprites();
    }

    private void loadSprites() throws IOException {
        BufferedImage idleSheet = ImageIO.read(getClass().getResourceAsStream("/resource/_Idle.png"));
        BufferedImage runSheet = ImageIO.read(getClass().getResource("/resource/Colour1/NoOutline/120x80_PNGSheets/_Run.png"));
        BufferedImage rollSheet = ImageIO.read(getClass().getResource("/resource/Colour1/NoOutline/120x80_PNGSheets/_Roll.png"));
        BufferedImage attackSheet = ImageIO.read(getClass().getResource("/resource/Colour1/NoOutline/120x80_PNGSheets/_Attack.png"));

        if (idleSheet == null || runSheet == null || rollSheet == null || attackSheet == null) {
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
        framesAttack = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            framesAttack[i] = attackSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }

        System.out.println("Loaded sprites successfully. Frame size: " + frameWidth + "x" + frameHeight);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    // render interval
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

    //used for the player to move
    public void update() {
        isMoving = false;
        isRolling = false;

        playerX = Math.max(0, Math.min(playerX, mapWidth - tileSize));
        playerY = Math.max(0, Math.min(playerY, mapHeight - tileSize));

        
    
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
            boolean canRoll = canRoll();
            if (canRoll) {
                rolling = true;
                isRolling = true;
                keyH.space = false; // Consume the key press
                lastRollTime = Instant.now(); // Start cooldown
                return;
            }
        }
        if (rolling) {
            isRolling = true;
            rollingCounter++;

            if (direction.equals("right")) {
                playerX += playerSpeed;
            } else if (direction.equals("left")) {
                playerX -= playerSpeed;
            }

            if (rollingCounter >= rollDuration) {
                rolling = false;
                rollingCounter = 0;
            }

            return; // Skip rest of update() while rolling
        }

        if (keyH.attack) {
            isMoving = false;
            isRolling = false;
        }
    }

    // for actually outputting the image
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // camrea initialization
        // Center the camera on the player
        int cameraX = playerX - screenWidthTemp / 2 + tileSize / 2;
        int cameraY = playerY - screenHeightTemp / 2 + tileSize / 2;

        // Clamp camera to map bounds
        cameraX = Math.max(0, Math.min(cameraX, mapWidth - screenWidthTemp));
        cameraY = Math.max(0, Math.min(cameraY, mapHeight - screenHeightTemp));
        // drawing the map
        g2.drawImage(loby.image, 0, 0, screenWidthTemp, screenHeightTemp, cameraX, cameraY, cameraX + screenWidthTemp, cameraY + screenHeightTemp, this);
        // drwing the actual player
        BufferedImage spriteToDraw = null;

        if (isRolling) {
            spriteToDraw = framesRoll[currentFrame % framesRoll.length];
        } else if (isMoving) {
            spriteToDraw = framesRun[currentFrame % framesRun.length];
        } 
        else if(keyH.attack){
            spriteToDraw = framesAttack[currentFrame % framesAttack.length];
        }
        else {
            spriteToDraw = framesIdle[currentFrame % framesIdle.length];
        }

        if (direction.equals("left")) {
            spriteToDraw = flipImageHorizontally(spriteToDraw);
        }
        // drawing the player
        int playerDrawX = playerX - cameraX;
        int playerDrawY = playerY - cameraY;

        g2.drawImage(spriteToDraw,playerDrawX,playerDrawY, tileSize, tileSize, null);
        
        drawHealthAndManaBars(g2);// whats this

        g2.dispose();

        updateAnimation();
    }

    private void updateAnimation() {
        animationCounter++;
        //for the left rolling speed
        if (animationCounter >= animationSpeed && direction.equals("left")) {
            animationCounter = 0;
            currentFrame++;
        }
        //for the right rolling speed cuz it's too fast
        else if(animationCounter >= animationSpeed * 2 && direction.equals("right")){
            animationCounter = 0;
            currentFrame++;
        }
    }

    //for the flipping of the image
    private BufferedImage flipImageHorizontally(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();
        g.drawImage(image, width, 0, -width, height, null);
        g.dispose();
        return flipped;
    }
    // why is there two mana and healt bars code in here and the other one in the user interface
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

    public void hideCursor() {
    // Create a new blank cursor image
        BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
        blankCursorImg, new Point(0, 0), "blank cursor");
        setCursor(blankCursor);
    }

    public void showCursor() {
        setCursor(Cursor.getDefaultCursor());
    }

    
}
