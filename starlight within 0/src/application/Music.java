package application;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class Music {
    Clip clip;

    public void playMusic(String musicFilePath){
        try {
            URL pathmusik = getClass().getResource(musicFilePath);
            if(pathmusik == null) {
                System.out.println("Music file path is null");
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(pathmusik);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // loops forever
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic(){ 
        if (clip != null && clip.isRunning()){
            clip.stop();
        }
    }
}
