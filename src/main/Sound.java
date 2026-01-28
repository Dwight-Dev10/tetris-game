package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    Clip musicClip;
    URL[] url = new URL[10];

    public Sound(){
        url[0] = getClass().getResource("/white-labyrinth-active.wav");
        url[1] = getClass().getResource("/delete line.wav");
        url[2] = getClass().getResource("/gameover.wav");
        url[3] = getClass().getResource("/rotation.wav");
        url[5] = getClass().getResource("/touch floor.wav");

    }
    public void play(int i, boolean music){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url[i]);
            Clip clip = AudioSystem.getClip();

            if(music) {
                musicClip = clip;
            }
            clip.open(ais);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent lineEvent) {
                    if(lineEvent.getType() == LineEvent.Type.STOP){
                        clip.close(); // memory usage
                    }
                }
            });
            ais.close();
            clip.start();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
