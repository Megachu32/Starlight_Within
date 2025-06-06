package application;

import entity.Monster;
import entity.Player;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import map.Loby;
import map.Maps;
import map.Traning;
import map.UpgradePanel;

public class GamePanel extends JPanel implements Runnable{


    // bounde for the game
    ArrayList<Rectangle> bounds = new ArrayList<>(); // List to hold boundaries
    ArrayList<Hitbox> hitboxes = new ArrayList<>(); // List to hold hitboxes

    UpgradePanel upgradePanel = new UpgradePanel(null);
    

    Player player; // calling the player

    Music musik;

    /*map variuables*/
    // calling mapps
    Maps currentMap;

    //map size
    int mapHeight;
    int mapWidth;

    String mapName = "loby"; // current map name

    final int OriginalTileSize = 32; // size of spirite
    final int scale = 10; // upscaling size of spirite
    final int tileSize = OriginalTileSize * scale; // the size of spirite in the game
    {
    bounds.add(new Rectangle(0, 500, 10000, 5));
    bounds.add(new Rectangle(0, 1850, 10000, 5));
    }
    {
    hitboxes.add(new Hitbox(500, 900, 100, 100, "shop"));
    hitboxes.add(new Hitbox(500, 1700, 100, 100, "upgrade"));
    hitboxes.add(new Hitbox(3000, 900, 100, 100, "idk1"));
    hitboxes.add(new Hitbox(3000, 1700, 100, 100, "idk2")); // Example hitbox, adjust as needed
    }

    public void setBoundsForMap(String mapName) {
    bounds.clear(); // Remove old bounds

        if (mapName.equals("loby")) {
            bounds.add(new Rectangle(0, 500, 10000, 5));
            bounds.add(new Rectangle(0, 1850, 10000, 5));
            // add other Loby bounds here
        } else if (mapName.equals("traning")) {
            bounds.add(new Rectangle(0, 600, 10000, 5));
            bounds.add(new Rectangle(0, 1850, 10000, 5));
            // add your specific Training bounds here
        }
    }
    public void setHitboxForMap(String maName){
        hitboxes.clear();
        if (maName.equals("loby")) {
        } else if (maName.equals("traning")) {
        }
    }


    final int fps = 60;// fps for the game

    // initralize the variable for the image
    BufferedImage[] framesIdle;
    BufferedImage[] framesRun;
    BufferedImage[] framesRoll;
    BufferedImage[] framesAttack;

    int currentFrame = 0;

    // sprite size
    int frameWidth;
    int frameHeight;

    //character direction and movement
    String direction = "right";
    boolean isMoving = false;
    boolean isRolling = false;
    boolean rolling = false;
    boolean isAttacking = false; // for the attack animation
    int rollingCounter = 0;
    int attackCounter = 0;
    final int rollDuration = 36; // How many frames the roll lasts (same as roll animation frames)
    final int attackDuration = 12; // How many frames the attack lasts (same as attack animation frames)
    int rollDelay = 0; // How long to wait before rolling again
    Instant timeNow = Instant.now();// current time for calculating rolling delay
    Instant lastRollTime = Instant  .now(); // when you last rolled

    KeyHandler keyH = new KeyHandler(); // calling keybaord
    MouseHandler mouseH = new MouseHandler(); // calling mouse
    Thread gameThread;

    // player speed and 
    final int playerSpeed = 7;
    int playerX = 1800;
    int playerY = 700;

    // screen size
    int screenWidthTemp;
    int screenHeightTemp;

    BufferedImage currentImage;

    public boolean toggleCursor = false;

    long lastAnimationTime = System.nanoTime();
    double animationDelay = 0.05; // seconds between frames, so ~6.6 FPS for animations

    //other variables
    BufferedImage punchingBag;
    int bagX, bagY; // position of punching bag

    //monsters variable
    MonsterSpawn monsterSpawn; // calling the monster spawn
    int monsterMoveDelay = 0; // delay for monster movement
    final int monsterMoveTrashold = 10; // counter for monster movement

    Boolean toggleHitbox = false;

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
        hideCursor(); // to hide the cursor
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final int screenWidth = gd.getDisplayMode().getWidth();
        final int screenHeight = gd.getDisplayMode().getHeight();
        screenWidthTemp = screenWidth;
        screenHeightTemp = screenHeight;
        // calling currentMap
        currentMap = new Loby();

        upgradePanel.setVisible(false); // initially hidden
        this.setLayout(null); // disable layout manager
        upgradePanel.setBounds(100, 100, 400, 300); // adjust position on screen
        this.add(upgradePanel);

        // calling music player
        musik = new Music();
        // load music
        musik.playMusic("/musik/lobyMusic.wav");
        // getingy the map size
        mapHeight = currentMap.getImage().getHeight();
        mapWidth = currentMap.getImage().getWidth();

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);

        player = new Player(this, keyH, mouseH);

        loadSprites();
    }

    private void loadSprites() throws IOException {
        BufferedImage idleSheet = ImageIO.read(getClass().getResourceAsStream("/resource/_Idle.png"));
        BufferedImage runSheet = ImageIO.read(getClass().getResource("/resource/Colour1/NoOutline/120x80_PNGSheets/_Run.png"));
        BufferedImage rollSheet = ImageIO.read(getClass().getResource("/resource/Colour1/NoOutline/120x80_PNGSheets/_Roll.png"));
        BufferedImage attackSheet = ImageIO.read(getClass().getResource("/resource/Colour1/NoOutline/120x80_PNGSheets/_Attack.png"));
        punchingBag = ImageIO.read(getClass().getResource("/Samll things/Untitled24_20250525142430.png"));
        monsterSpawn = new MonsterSpawn(); // initialize monster spawn

        if (idleSheet == null || runSheet == null || rollSheet == null || attackSheet == null || punchingBag == null) {
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
    public boolean collidesWithBounds(Rectangle Rect) {
    for (Rectangle bound : bounds) {
        if (Rect.intersects(bound)) {
            return true;
        }
    }
    return false;
    }

    private void onHitboxTrigger(Rectangle hitbox) {
    // Identify which hitbox and do something
    System.out.println("Player triggered hitbox at: " + hitbox);

    // Example: show message, spawn monster, change map, etc.
    }
    private void onHitboxTrigger(Hitbox hb) {
    switch (hb.id) {
        case "shop":
            System.out.println("Player entered shop");
            break;
        case "upgrade":
            break;
    }
}


    //used for the player to move
    public void update() {
        isMoving = false;
        isRolling = false;
        isAttacking = mouseH.attack;

        playerX = Math.max(0, Math.min(playerX, mapWidth - tileSize)); // Clamp playerX to map bounds so they can't go out of the map
        playerY = Math.max(0, Math.min(playerY, mapHeight - tileSize)); // Clamp playerY to map bounds so they can't go out of the map

        // Clamp player positions first
        playerX = Math.max(0, Math.min(playerX, mapWidth - tileSize));
        playerY = Math.max(0, Math.min(playerY, mapHeight - tileSize));

        // Check if player is at left or right edge for teleport
        if (mapName.equals("loby") && playerX == 0) {
            mapName = "traning";
            mapTeleport(mapName);
        } else if (mapName.equals("traning") && playerX == mapWidth - tileSize) {
            mapName = "loby";
            mapTeleport(mapName);
        }
    
        Duration timeSinceLastRoll = Duration.between(lastRollTime, Instant.now());
    
        Rectangle Rect = new Rectangle(playerX, playerY, tileSize, tileSize);

        Rectangle playerRect = new Rectangle(playerX, playerY, tileSize, tileSize);

        // Defensive version of hitbox collision check
        for (Hitbox hb : hitboxes) {
            if (hb == null || hb.rect == null) {
                // Skip if hitbox or its rectangle is null (avoid crashes)
                continue;
            }
            
            // Check collision with player rectangle
            if (playerRect.intersects(hb.rect)) {
                try {
                    onHitboxTrigger(hb);  // Call your trigger safely
                } catch (Exception e) {
                    // Catch any unexpected error inside trigger so it doesn't freeze the game
                    System.err.println("Error in onHitboxTrigger: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }


        if (keyH.up) {
            Rect.y -= playerSpeed;
            if (!collidesWithBounds(Rect)) {
                playerY -= playerSpeed;
                direction = "up";
                isMoving = true;
            }
        }
        if (keyH.down) {
            Rect.y += playerSpeed;
            if (!collidesWithBounds(Rect)) {
                playerY += playerSpeed;
                direction = "down";
                isMoving = true;
            }
        }
        if (keyH.left) {
            Rect.x -= playerSpeed;
            if (!collidesWithBounds(Rect)) {
                playerX -= playerSpeed;
                direction = "left";
                isMoving = true;
            }
        }
        if (keyH.right) {
            Rect.x += playerSpeed;
            if (!collidesWithBounds(Rect)) {
                playerX += playerSpeed;
                direction = "right";
                isMoving = true;
            }
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
        if(keyH.hitbox) {
            toggleHitbox = !toggleHitbox; // toggle hitbox visibility
        }

        if (mouseH.attack) { // left click mouse button
            isMoving = false;
            isRolling = false;
            attackCounter++;

            if (attackCounter >= attackDuration) {
                mouseH.attack = false;
                attackCounter = 0;
            }
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

        // coordinates for the punching bag
        bagX = mapWidth / 2 - punchingBag.getWidth() / 2;
        bagY = mapHeight / 2 - punchingBag.getHeight() / 2;

        // Center the punching bag on the screen
        int centerX = screenWidthTemp / 2 - punchingBag.getWidth() / 2;
        int centerY = screenHeightTemp / 2 - punchingBag.getHeight() / 2;

        // Adjust the punching bag position based on camera so it stays in the center
        int drawBagX = bagX - cameraX;
        int drawBagY = bagY - cameraY;

        // Clamp camera to map bounds
        cameraX = Math.max(0, Math.min(cameraX, mapWidth - screenWidthTemp));
        cameraY = Math.max(0, Math.min(cameraY, mapHeight - screenHeightTemp));
        // drawing the map
        g2.drawImage(currentMap.getImage(), 0, 0, screenWidthTemp, screenHeightTemp, cameraX, cameraY, cameraX + screenWidthTemp, cameraY + screenHeightTemp, this);
        // drwing the actual player
        BufferedImage spriteToDraw = null;

        // draws the punching bag if the current map is Traning
        if(currentMap instanceof Traning) {
            if (punchingBag != null) {
                g2.drawImage(punchingBag, drawBagX, drawBagY, 250, 250, this);
            }
        }

        if (isRolling) {
            spriteToDraw = framesRoll[currentFrame % framesRoll.length];
        } else if (isMoving) {
            spriteToDraw = framesRun[currentFrame % framesRun.length];
        } 
        else if(mouseH.attack){
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
        if(toggleHitbox){
            g2.drawRect(playerDrawX,playerDrawY, tileSize, tileSize);
        }

        if(currentMap instanceof Loby) {
            // System.out.println("runing monster spawn");a
            for (int m = 0; m < monsterSpawn.monsterList.size(); m++) {
                Monster monster = monsterSpawn.monsterList.get(m);
                BufferedImage[] framesMonsters = monster.getFrame();

                if (monster.getImage() != null) {
                    monsterSpawn.moveMonsters(m);
                    g2.drawImage(
                        framesMonsters[currentFrame % framesMonsters.length],
                        monster.getX() - cameraX,
                        monster.getY() - cameraY,
                        tileSize / 3,
                        tileSize / 3,
                        null
                    );

                    // --- Calculate distance ---
                    int dx = playerX - monster.getX();
                    int dy = playerY - monster.getY();
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    // --- Start chasing if close enough ---
                    if (distance < 200) {

                        monsterMoveDelay++;
                        if (monsterMoveDelay >= monsterMoveTrashold) {
                            monsterMoveDelay = 0; // reset the delay counter
                                
                            // Update monster position towards player
                            if (monster.getX() < playerX) {
                                monster.setX((int) (monster.getX() + monster.getSpeed()));
                            } else if (monster.getX() > playerX) {
                                monster.setX((int) (monster.getX() - monster.getSpeed()));
                            }

                            if (monster.getY() < playerY) {
                                monster.setY((int) (monster.getY() + monster.getSpeed()));
                            } else if (monster.getY() > playerY) {
                                monster.setY((int) (monster.getY() - monster.getSpeed()));
                            }

                            System.out.println("Monster " + monster.getNamaMoster() + " is chasing the player.");
                            System.out.println("Monster position: (" + monster.getX() + ", " + monster.getY() + ")");
                        } else {
                            continue; // skip this iteration if not time to move
                        }
                    }
                }
                if(toggleHitbox){ // toggle visible hitbox
                    g2.setColor(Color.GREEN);
                    g2.drawRect(monster.getX() - cameraX, monster.getY() - cameraY, tileSize / 3, tileSize / 3);
                }
            }
        }
        
        drawHealthAndManaBars(g2);// drawing the health and mana bars

        g2.setColor(Color.RED);
        for (Rectangle rect : bounds) {
            g2.drawRect(rect.x - cameraX, rect.y - cameraY, rect.width, rect.height);
        }
        g2.setColor(new Color(0, 255, 0, 100)); // semi-transparent green for hitboxes

        for (Hitbox hitbox : hitboxes) {
            g2.fillRect(hitbox.x - cameraX, hitbox.y - cameraY, hitbox.width, hitbox.height);
        }

        g2.dispose();

        updateAnimation();

    }

    private void updateAnimation() {
        long now = System.nanoTime(); // get current time
        double elapsedTime = (now - lastAnimationTime) / 1_000_000_000.0; // convert to seconds

        // Optional: change speed based on direction because why not
        double delay = animationDelay;

        if (elapsedTime >= delay) { //for the animation speed
            currentFrame++;
            lastAnimationTime = now;
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
    // to get the player health and mana then transfer it to the interface to draw as a layer
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

    public void mapTeleport(String newMapName) {
    System.out.println("Player is at the edge of the map, teleporting to the next map.");

    mapName = newMapName; // update current mapName

    if (mapName.equals("loby")) {
        currentMap = new Loby();
        mapHeight = currentMap.getImage().getHeight();
        mapWidth = currentMap.getImage().getWidth();
        playerX = 1800;
        playerY = 700;
        setBoundsForMap("loby");
    } else if (mapName.equals("traning")) {
        currentMap = new Traning();
        mapHeight = currentMap.getImage().getHeight();
        mapWidth = currentMap.getImage().getWidth();
        playerX = 1800;
        playerY = 700;
        setBoundsForMap("traning");
    }
}

public void CheckCollision() {
}
    
}
