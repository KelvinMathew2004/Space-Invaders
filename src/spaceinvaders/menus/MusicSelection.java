package spaceinvaders.menus;

import javax.sound.sampled.*;
import java.io.InputStream;
import javax.swing.*;
import java.net.URL;

import spaceinvaders.GameExceptions;

public class MusicSelection extends MenuBar {
    private static Clip clip;
    private static Clip sfx;

    public MusicSelection() {
        super();
        createMenu("Music");
    }

    public void loadCustomMusic(String defaultMusicPath) {
        String musicUrl = JOptionPane.showInputDialog(null, "Enter URL for music file (WAV only, or leave blank for default):");

        if (musicUrl != null && !musicUrl.isEmpty()) {
            try {
                playMusicFromURL(musicUrl);
                return;
            } catch (Exception e) {
                GameExceptions.showErrorDialog("Failed to load music from URL: " + e.getMessage() + "\nPlaying default music.");
            }
        }

        loadPresetMusic(defaultMusicPath);
    }

    public void loadPresetMusic(String resourcePath) {
        stopMusic();
        try {
            InputStream audioSrc = getClass().getResourceAsStream(resourcePath);
            if (audioSrc == null) {
                GameExceptions.showErrorDialog("Music file not found: " + resourcePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            GameExceptions.showErrorDialog("Error playing music: " + e.getMessage());
        }
    }

    public void playMusicFromURL(String musicUrl) throws Exception {
        stopMusic();

        URL url = new URL(musicUrl);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }

    public void playSoundEffect(String resourcePath, float volume) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream(resourcePath);
            if (audioSrc == null) {
                GameExceptions.showErrorDialog("Music file not found: " + resourcePath);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            sfx = AudioSystem.getClip();
            sfx.open(audioStream);
            FloatControl gainControl = (FloatControl) sfx.getControl(FloatControl.Type.MASTER_GAIN);
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();
            float range = max - min;
            float dB = min + (range * volume);
            gainControl.setValue(dB);
            sfx.start();
        } catch (Exception e) {
            GameExceptions.showErrorDialog("Error playing music: " + e.getMessage());
        }
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
