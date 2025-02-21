import javax.swing.JFrame; //this is for Jframe

public class App {
    public static void main(String[] args) throws Exception {
        
        JFrame window = new JFrame();// new Jframe named windows

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//for the x button on the jdk windows
        window.setResizable(false);// for so that the windows cant be resized
        window.setTitle("starlight within 0");// this is for the windows / the game name



        // game panel a Jframe sub to make a windows jdk pannel
        GamePanel gamePanel = new GamePanel(window); // this is basicly calling the gamepanel class
        window.add(gamePanel); // adding the gamepanel code to display in the jdk windows
        window.pack(); // making so that the windows is the same size as the gamepanel

        window.setLocationRelativeTo(null);// for so the jdk windows appear in the middle
        window.setVisible(true);// to actualy display the windows

        gamePanel.startGameThread(); // calling thr gamepannel thread to start the game



    }
}
