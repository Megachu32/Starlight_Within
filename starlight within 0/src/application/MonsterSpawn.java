package application;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Monster;

public class MonsterSpawn {
    ArrayList<Monster> monsterList;
    int monsterCount;

    public MonsterSpawn() throws IOException {
        monsterList = new ArrayList<>();
        monsterCount = 0;

        Random ran = new Random();
        int random = ran.nextInt(10) + 1; // Random number of monsters between 1 and 5
        for (int i = 0; i < random; i++) {
            BufferedImage monsterImage = null; // Placeholder for monster image, replace with actual image loading logic
            switch (ran.nextInt(15) + 1) {
                case 1:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Blinded Grimlock/BlindedGrimlock.png"));
                    break;
                case 2:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Bloodshot Eye/BloodshotEye.png"));
                    break;
                case 3:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Brawny Ogre/BrawnyOgre.png"));      
                    break;
                case 4:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Crimson Slaad/CrimsonSlaad.png"));
                    break;
                case 5:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Crushing Cyclops/CrushingCyclops.png"));
                    break;
                case 6:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Death Slime/DeathSlime.png"));
                    break;
                case 7:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Fungal Myconid/FungalMyconid.png"));
                    break;
                case 8:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Humongous Ettin/HumongousEttin.png"));
                    break;
                case 9:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Murky Slaad/MurkySlaad.png"));
                    break;
                case 10:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Ochre Jelly/OchreJelly.png"));
                    break;
                case 11:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Ocular Watcher/OcularWatcher.png"));
                    break;
                case 12:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Red Cap/RedCap.png"));
                    break;
                case 13:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Shrieker Mushroom/ShriekerMushroom.png"));
                    break;
                case 14:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Stone Troll/StoneTroll.png"));
                    break;
                case 15:
                    monsterImage = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monster Animations/Swamp Troll/SwampTroll.png"));
                    break;
            }

            monsterList.add(new Monster(ran.nextInt(1000), ran.nextInt(1000), ran.nextInt(5) + 1, 
                                      "Monster " + (i + 1), ran.nextInt(3), 
                                      ran.nextInt(10) + 1, ran.nextInt(5), 
                                      ran.nextInt(10) + 1, ran.nextInt(5), 
                                      ran.nextInt(50) + 50, ran.nextInt(100), monsterImage));
        }
    }

    public void moveMonsters(int n) {
        Random ran = new Random();
        int random = ran.nextInt(100) + 1;

        if(random == 1){
            switch(ran.nextInt(4) + 1){
                case 1:
                    monsterList.get(n).setX(monsterList.get(n).getX() + monsterList.get(n).getSpeed() * 2);
                    break;
                case 2:
                    monsterList.get(n).setX(monsterList.get(n).getX() - monsterList.get(n).getSpeed() * 2);
                    break;
                case 3:
                    monsterList.get(n).setY(monsterList.get(n).getY() + monsterList.get(n).getSpeed() * 2);
                    break;
                case 4:
                    monsterList.get(n).setY(monsterList.get(n).getY() - monsterList.get(n).getSpeed() * 2);
                    break;
            }
        }
    }

    
}
