package application;

import java.awt.*;
import javax.swing.*;
import map.MainMenu;

public class UserInterface {

    GamePanel gp;

    // interface menu sebelum masuk
    public UserInterface() {
        MainMenu menu = new MainMenu();
    }

    //ui for player stats
    public UserInterface(GamePanel gp) {
        this.gp = gp;
    }
}
