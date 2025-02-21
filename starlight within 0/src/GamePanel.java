import java.awt.Color;// for color
import java.awt.Dimension;// for dimension
import java.awt.Graphics;// for graphics
import java.awt.Graphics2D;// for graphics 2d
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;// import Jpanel
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    //for screen settings
    // the runnable is for thread
    // final = cant be changed anymore

    final int OriginalTaleSize = 32; // for the tile of the windows 16 times 16
    final int scale = 3;// for scaling the ugly ahh character


    final int tileSize = OriginalTaleSize * scale; // 32 * 3 

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    // setting the fps 

    int fps = 60;

    KeyHandler keyH =  new KeyHandler();

    Thread gameThread;

    // player default position
    int playerX = 100;
    int playerY = 100;  
    int playerSpeed = 4;

    public GamePanel (JFrame window){

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        final int screenWidth = gd.getDisplayMode().getWidth();
        final int screenHeight = gd.getDisplayMode().getHeight();



        this.setPreferredSize(new Dimension (screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    // thread code that allow the game to run with loop and clock

    public void startGameThread(){
        gameThread = new Thread(this); // initializing the thread
        gameThread.start();// starting the tread

    }



    // the core thread program
    @Override
    public void run() {

        double renderInteval  = 1000000000.0/fps;
        double delta = 0;
        long lastRenderTIme = System.nanoTime();
        long currentTime;
    
        while (gameThread != null){




            currentTime = System.nanoTime();

            delta += (currentTime - lastRenderTIme) / renderInteval;

            lastRenderTIme = currentTime;

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

        g2.setColor(Color.WHITE);// setting the box color

        g2.fillRect(playerX, playerY, 32, 32);// fillimg the rectangle

        g2.dispose();// erasing it again for 1 frame


    }





}
