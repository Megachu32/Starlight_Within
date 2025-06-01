package application;

import entity.Monster;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MonsterSpawn {
    ArrayList<Monster> monsterList;
    int monsterMax = 1;

    public MonsterSpawn() {
        monsterList = new ArrayList<>();
        Random ran = new Random();
        int random = ran.nextInt(monsterMax) + 1;
        for (int i = 0; i < 3; i++) {
            int x = 10;
            int y = 10;
            float speed = 1;
            String namaMoster = "Monster" + i;
            int jenisMosnter = (int) (Math.random() * 5);
            int physicalMosterDamgae = (int) (Math.random() * 20 + 1);
            int physicalMonsterArmor = (int) (Math.random() * 10 + 1);
            int magicalMonsterDamage = (int) (Math.random() * 15 + 1);
            int magicalMonsterArmor = (int) (Math.random() * 5 + 1);
            int monsterHp = (int) (Math.random() * 100 + 50);
            int monsterDrop = (int) (Math.random() * 50 + 10);

            BufferedImage image = null;
                    try {
                        image = ImageIO.read(getClass().getResource("/monsters/Basic Monster Animations/Blinded Grimlock/BlindedGrimlock.png"));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } // Placeholder for image, replace with actual image loading logic

            // switch (ran.nextInt(15) + 1) {
            //     case 1:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Blinded Grimlock/BlindedGrimlock.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            
            //     case 2:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Bloodshot Eye/BloodshotEye.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 3:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Brawny Ogre/BrawnyOgre.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 4:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Crimson Slaad/CrimsonSlaad.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 5:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Crushing Cyclops/CrushingCyclops.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 6:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Death Slime/DeathSlime.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 7:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Fungal Myconid/FungalMyconid.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 8:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Humongous Ettin/HumongousEttin.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 9:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Murky Slaad/MurkySlaad.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 10:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Ochre Jelly/OchreJelly.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 11:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Ocular Watcher/OcularWatcher.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 12:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Red Cap/RedCap.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 13:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Shrieker Mushroom/ShriekerMushroom.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 14:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Stone Troll/StoneTroll.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            //     case 15:
            //         try {
            //             image = ImageIO.read(getClass().getResourceAsStream("/monsters/Basic Monsters Animation/Swamp Troll/SwampTroll.png"));
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         } // Placeholder for image, replace with actual image loading logic
            //         break;
            // }

            monsterList.add(new Monster(x, y, speed, namaMoster, jenisMosnter,
                    physicalMosterDamgae, physicalMonsterArmor,
                    magicalMonsterDamage, magicalMonsterArmor,
                    monsterHp, monsterDrop, image));
        }
    }
}
