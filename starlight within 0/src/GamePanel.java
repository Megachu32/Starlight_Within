import java.awt.Color;// for color
import java.awt.Dimension;// for dimension
import java.awt.Graphics;// for graphics
import java.awt.Graphics2D;// for graphics 2d
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;// import Jpanel
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{
    //for screen settings
    // the runnable is for thread
    // final = cant be changed anymore

    final int OriginalTaleSize = 32; // for the tile of the windows not used for now
    final int scale = 3;// for scaling the ugly ahh character


    final int tileSize = OriginalTaleSize * scale; // for the size of the tile not used for now

    final int maxScreenCol = 16; // the max column of the screen not used for now beacuse we are using the screen size
    final int maxScreenRow = 12; //same as above

    // setting the fps 

    int fps = 60; // frame per second

    KeyHandler keyH =  new KeyHandler(); // initializing the keyhandler

    Thread gameThread; // initailizing the thread important to import java.lang.Thread

    // player default position
    int playerX = 100; // the x position of the player
    int playerY = 100;  //the y position of the player
    int playerSpeed = 4; // the speed of the player

    private Image playerImage; // for player image initialization impottant to import java.awt.Image


    public GamePanel (JFrame window){

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); //code for getting the screen size
        final int screenWidth = gd.getDisplayMode().getWidth(); // getting the screen width
        final int screenHeight = gd.getDisplayMode().getHeight(); // getting the screen height

        this.setPreferredSize(new Dimension (screenWidth, screenHeight)); // setting the gamepanel size
        this.setBackground(Color.BLACK); // setting the background color
        this.setDoubleBuffered(true); // for smooth rendering
        this.addKeyListener(keyH); // adding the keyhandler witch is the keylistener
        this.setFocusable(true); // for the keyholder to work and focus on the gamepanel

        // for loanding the player image
        try {
            playerImage = ImageIO.read(getClass().getResource("/resource/spade_monster.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    // thread code that allow the game to run with loop and clock

    public void startGameThread(){
        gameThread = new Thread(this); // initializing the thread
        gameThread.start();// starting the tread

    }



    // the core thread program
    @Override
    public void run() {

        double renderInteval  = 1000000000.0/fps; // nano second defided by 60 so it get the intercval of 1 frame
        double delta = 0; // delta is the time berween frame 
        long lastRenderTIme = System.nanoTime(); // get the current time in nano second
        long currentTime; // curent time in nano second

        // thread game loop 
        while (gameThread != null){

            currentTime = System.nanoTime();// get the curent time in nano second again

            delta += (currentTime - lastRenderTIme) / renderInteval; // calculate the delta time

            lastRenderTIme = currentTime; // set the last render time to the current time

            if (delta >= 1){
                // 1. UPADATE THE CHARACTER POSITION
                update();
                //2. RENDER THE CHANGE
                repaint();
                delta --;
            }
            
        }
    }
    // method for upating the position of player character
    public void update(){
        if (keyH.up){
            playerY -= playerSpeed;
        }
        if (keyH.down){
            playerY += playerSpeed;
        }
        if (keyH.left){
            playerX -= playerSpeed;
        }
        if (keyH.right){
            playerX += playerSpeed;
        }

    }

    // method for rendering the player character
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);// calling Jpanel fucntion

        Graphics2D g2 = (Graphics2D)g;// converting graphic to 2d so you can draw on thw jdk

        if (playerImage != null) {
            g2.drawImage(playerImage, playerX, playerY, tileSize, tileSize, null); // draw player character image
        } else {
            g2.setColor(Color.WHITE);
            g2.fillRect(playerX, playerY, tileSize, tileSize); // Draw square if image not found
        }
    

        g2.dispose();// erasing it again for 1 frame


    }





}
