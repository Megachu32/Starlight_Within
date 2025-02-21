import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;// import Jpanel

public class GamePanel extends JPanel implements Runnable{
    //for screen settings

    // final = cant be changed anymore

    final int OriginalTaleSize = 32; // for the tile of the windows 16 times 16
    final int scale = 3;// for scaling the ugly ahh character


    final int tileSize = OriginalTaleSize * scale; // 32 * 3 

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;

    public GamePanel (){
        this.setPreferredSize(new Dimension (screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        while (gameThread != null){

            //TODO
            // 1. UPADATE THE CHARACTER POSITION
            update();
            //2. RENDER THE CHANGE
            repaint();
        }
    }

    public void update(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.WHITE);

        g2.fillRect(100, 100, 48, 48);

        g2.dispose();


    }





}
