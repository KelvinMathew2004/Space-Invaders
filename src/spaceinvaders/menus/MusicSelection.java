package spaceinvaders.menus;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.net.URL;

import spaceinvaders.GameExceptions;

public class MusicSelection extends MenuBar {
    private static Clip clip;

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

    public void loadPresetMusic(String musicPath) {
        stopMusic();
        try {
            File file = new File(musicPath);
            if (!file.exists()) {
                GameExceptions.showErrorDialog("Music file not found: " + musicPath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
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

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
