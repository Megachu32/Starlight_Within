    package application;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
import map.PlayerStatsAndInventoryLaunchPage;

    public class KeyHandler implements KeyListener {

        public static boolean up, down, left, right, space, block, hitbox, EscButton, stats;

        // for when a key is typed
        @Override
        public void keyTyped(KeyEvent e) {
            // You can leave this method empty if you don't need to handle keyTyped events
        }

        // implemet = its like include in c++ baicly adding a new feature

        // for when the key is pressed
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();

            if (code == KeyEvent.VK_W) {
                up = true;
            } else if (code == KeyEvent.VK_A) {
                left = true;
            } else if (code == KeyEvent.VK_S) {
                down = true;
            } else if (code == KeyEvent.VK_D) {
                right = true;
            }
            else if(code == KeyEvent.VK_SPACE){
                space = true;
            }
            else if(code == KeyEvent.VK_E){
                hitbox = true;
            }
            else if(code == KeyEvent.VK_Q){
                block = true;
            }
            else if(code == KeyEvent.VK_ESCAPE){
                EscButton = true;
            }
            else if(code == KeyEvent.VK_M){
                // This is where you would call the method to show the player stats and inventory
                PlayerStatsAndInventoryLaunchPage.ShowStatsAndInventory(); // Show the player stats and inventory
            }
        }

        // for when the key is released
        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();

            if (code == KeyEvent.VK_W) {
                up = false;
            } else if (code == KeyEvent.VK_A) {
                left = false;
            } else if (code == KeyEvent.VK_S) {
                down = false;
            } else if (code == KeyEvent.VK_D) {
                right = false;
            }
            else if(code == KeyEvent.VK_E){
                hitbox = false;
            }
            else if(code == KeyEvent.VK_Q){
                block = false;
            }
            else if(code == KeyEvent.VK_ESCAPE){
                EscButton = false;
            }
        
        }

        
        
    }
