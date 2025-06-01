package entity;

public abstract class Entity {
    protected int x, y; // Entity Cordinates 
    protected float speed;// Entity's speed
    protected String namaMoster; 
    protected int jenisMosnter;
    protected int physicalMosterDamgae;
    protected int physicalMonsterArmor;
    protected int magicalMonsterDamage;
    protected int magicalMonsterArmor;
    protected int monsterHp;
    protected int monsterDrop;

    public Entity() {
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
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getNamaMoster() {
        return namaMoster;
    }

    public void setNamaMoster(String namaMoster) {
        this.namaMoster = namaMoster;
    }

    public int getJenisMosnter() {
        return jenisMosnter;
    }

    public void setJenisMosnter(int jenisMosnter) {
        this.jenisMosnter = jenisMosnter;
    }

    public int getPhysicalMosterDamgae() {
        return physicalMosterDamgae;
    }

    public void setPhysicalMosterDamgae(int physicalMosterDamgae) {
        this.physicalMosterDamgae = physicalMosterDamgae;
    }

    public int getPhysicalMonsterArmor() {
        return physicalMonsterArmor;
    }

    public void setPhysicalMonsterArmor(int physicalMonsterArmor) {
        this.physicalMonsterArmor = physicalMonsterArmor;
    }

    public int getMagicalMonsterDamage() {
        return magicalMonsterDamage;
    }

    public void setMagicalMonsterDamage(int magicalMonsterDamage) {
        this.magicalMonsterDamage = magicalMonsterDamage;
    }

    public int getMagicalMonsterArmor() {
        return magicalMonsterArmor;
    }

    public void setMagicalMonsterArmor(int magicalMonsterArmor) {
        this.magicalMonsterArmor = magicalMonsterArmor;
    }

    public int getMonsterHp() {
        return monsterHp;
    }

    public void setMonsterHp(int monsterHp) {
        this.monsterHp = monsterHp;
    }

    public int getMonsterDrop() {
        return monsterDrop;
    }

    public void setMonsterDrop(int monsterDrop) {
        this.monsterDrop = monsterDrop;
    }

    

    


}
