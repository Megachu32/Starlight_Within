package entity;

import application.GamePanel;
import application.KeyHandler;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    int hp;
    int maxHp;
    int xp;
    int level;
    int pyhsicalDamage;
    int magicalDamage;
    int physicalArmor;
    int magicalArmor;
    int mana;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
    }

    public void statusPlayer(int hp, int maxHp, int xp, int level, int damage,int magicalDamage, int physicalArmor, int magicalArmor, int mana) {
        this.hp = hp;
        this.maxHp = maxHp;  
        this.xp = xp;
        this.level = level;
        this.pyhsicalDamage = damage;
        this.magicalDamage = magicalDamage;
        this.physicalArmor = physicalArmor;
        this.magicalArmor = magicalArmor;
        this.mana = mana;
    }
       



    
}
