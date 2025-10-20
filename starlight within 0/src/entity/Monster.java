package entity;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Monster extends Entity {

    BufferedImage image;

    public Monster(int x, int y, int speed, String namaMoster, int jenisMosnter, 
                   int physicalMosterDamgae, int physicalMonsterArmor, 
                   int magicalMonsterDamage, int magicalMonsterArmor, 
                   int monsterHp, int monsterDrop, BufferedImage image) {
        super();
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.namaMoster = namaMoster;
        this.jenisMosnter = jenisMosnter;
        this.physicalMosterDamgae = physicalMosterDamgae;
        this.physicalMonsterArmor = physicalMonsterArmor;
        this.magicalMonsterDamage = magicalMonsterDamage;
        this.magicalMonsterArmor = magicalMonsterArmor;
        this.monsterHp = monsterHp;
        this.monsterDrop = monsterDrop;
        this.image = image;
    }

    @Override
    public int getJenisMosnter() {
        // TODO Auto-generated method stub
        return super.getJenisMosnter();
    }

    @Override
    public int getMagicalMonsterArmor() {
        // TODO Auto-generated method stub
        return super.getMagicalMonsterArmor();
    }

    @Override
    public int getMagicalMonsterDamage() {
        // TODO Auto-generated method stub
        return super.getMagicalMonsterDamage();
    }

    @Override
    public int getMonsterDrop() {
        // TODO Auto-generated method stub
        return super.getMonsterDrop();
    }

    @Override
    public int getMonsterHp() {
        // TODO Auto-generated method stub
        return super.getMonsterHp();
    }

    @Override
    public String getNamaMoster() {
        // TODO Auto-generated method stub
        return super.getNamaMoster();
    }

    @Override
    public int getPhysicalMonsterArmor() {
        // TODO Auto-generated method stub
        return super.getPhysicalMonsterArmor();
    }

    @Override
    public int getPhysicalMosterDamgae() {
        // TODO Auto-generated method stub
        return super.getPhysicalMosterDamgae();
    }

    @Override
    public int getSpeed() {
        // TODO Auto-generated method stub
        return super.getSpeed();
    }

    @Override
    public int getX() {
        // TODO Auto-generated method stub
        return super.getX();
    }

    @Override
    public int getY() {
        // TODO Auto-generated method stub
        return super.getY();
    }

    @Override
    public void setJenisMosnter(int jenisMosnter) {
        // TODO Auto-generated method stub
        super.setJenisMosnter(jenisMosnter);
    }

    @Override
    public void setMagicalMonsterArmor(int magicalMonsterArmor) {
        // TODO Auto-generated method stub
        super.setMagicalMonsterArmor(magicalMonsterArmor);
    }

    @Override
    public void setMagicalMonsterDamage(int magicalMonsterDamage) {
        // TODO Auto-generated method stub
        super.setMagicalMonsterDamage(magicalMonsterDamage);
    }

    @Override
    public void setMonsterDrop(int monsterDrop) {
        // TODO Auto-generated method stub
        super.setMonsterDrop(monsterDrop);
    }

    @Override
    public void setMonsterHp(int monsterHp) {
        // TODO Auto-generated method stub
        super.setMonsterHp(monsterHp);
    }

    @Override
    public void setNamaMoster(String namaMoster) {
        // TODO Auto-generated method stub
        super.setNamaMoster(namaMoster);
    }

    @Override
    public void setPhysicalMonsterArmor(int physicalMonsterArmor) {
        // TODO Auto-generated method stub
        super.setPhysicalMonsterArmor(physicalMonsterArmor);
    }

    @Override
    public void setPhysicalMosterDamgae(int physicalMosterDamgae) {
        // TODO Auto-generated method stub
        super.setPhysicalMosterDamgae(physicalMosterDamgae);
    }

    @Override
    public void setSpeed(int speed) {
        // TODO Auto-generated method stub
        super.setSpeed(speed);
    }

    @Override
    public void setX(int x) {
        // TODO Auto-generated method stub
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        // TODO Auto-generated method stub
        super.setY(y);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    public BufferedImage[] getFrame() {
        BufferedImage[] images = new BufferedImage[4];
        int frameWidth = image.getWidth() / 10 + 10;
        int frameHeight = image.getHeight();
        for (int i = 0; i < 4; i++) {
            images[i] = image.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }
        return images;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
}
