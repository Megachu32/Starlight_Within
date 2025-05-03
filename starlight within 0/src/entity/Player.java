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
    int manaMax;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
    }

    public void statusPlayer(int hp, int maxHp, int xp, int level, int damage,int magicalDamage, int physicalArmor, int magicalArmor, int mana, int manaMax) {
        this.hp = hp;
        this.maxHp = maxHp;  
        this.xp = xp;
        this.level = level;
        this.pyhsicalDamage = damage;
        this.magicalDamage = magicalDamage;
        this.physicalArmor = physicalArmor;
        this.magicalArmor = magicalArmor;
        this.mana = mana;
        this.manaMax = manaMax;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPyhsicalDamage() {
        return pyhsicalDamage;
    }

    public void setPyhsicalDamage(int pyhsicalDamage) {
        this.pyhsicalDamage = pyhsicalDamage;
    }

    public int getMagicalDamage() {
        return magicalDamage;
    }

    public void setMagicalDamage(int magicalDamage) {
        this.magicalDamage = magicalDamage;
    }

    public int getPhysicalArmor() {
        return physicalArmor;
    }

    public void setPhysicalArmor(int physicalArmor) {
        this.physicalArmor = physicalArmor;
    }

    public int getMagicalArmor() {
        return magicalArmor;
    }

    public void setMagicalArmor(int magicalArmor) {
        this.magicalArmor = magicalArmor;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getManaMax() {
        return manaMax;
    }

    public void setManaMax(int manaMax) {
        this.manaMax = manaMax;
    }
       
    


    
}
