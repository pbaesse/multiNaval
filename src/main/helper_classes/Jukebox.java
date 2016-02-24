package main.helper_classes;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import main.Launcher;

/**
 *
 * @author luann
 */
public class Jukebox {

    private Clip clip;

    public void play(/*Class<?> name,*/ String pathname) {
        //String path = name.getResource("resource/" + pathname).getPath();
        
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(pathname));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
        
        clip.start();
    }
}
