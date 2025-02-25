package spaceinvaders;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import spaceinvaders.menus.ShooterSelection;
import spaceinvaders.menus.BulletSelection;
import spaceinvaders.menus.InvaderSelection;
import spaceinvaders.menus.MusicSelection;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders with Images");

        SpaceInvadersUI game = new SpaceInvadersUI();

        ShooterSelection shooterMenu = new ShooterSelection();
        InvaderSelection invaderMenu = new InvaderSelection();
        MusicSelection musicMenu = new MusicSelection();
        BulletSelection bulletMenu = new BulletSelection();

        JMenuBar menuBar = new JMenuBar();

        shooterMenu.addButton("Custom", e -> shooterMenu.setCustomShooterImage());
        shooterMenu.addButton("Nebula Striker", e -> shooterMenu.setPresetShooterImage("./resources/ShooterImage2.png"));
        shooterMenu.addButton("Void Phoenix", e -> shooterMenu.setPresetShooterImage("./resources/ShooterImage3.png"));
        shooterMenu.addButton("Galactic Reaper", e -> shooterMenu.setPresetShooterImage("./resources/ShooterImage4.png"));
        shooterMenu.addButton("Solar Viper", e -> shooterMenu.setPresetShooterImage("./resources/ShooterImage5.png"));
        shooterMenu.addButton("Cosmo Sentinel", e -> shooterMenu.setPresetShooterImage("./resources/ShooterImage6.png"));

        invaderMenu.addButton("Custom", e -> invaderMenu.setCustomInvaderImage());
        invaderMenu.addButton("UFO", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage2.png"));
        invaderMenu.addButton("Xenovores", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage3.png"));
        invaderMenu.addButton("Nebulons", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage4.png"));
        invaderMenu.addButton("Mechavores", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage5.png"));
        invaderMenu.addButton("Voidspawn", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage6.png"));
        invaderMenu.addButton("Zygar Swarm", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage7.png"));

        bulletMenu.addButton("Custom", e -> bulletMenu.loadCustomBulletImage());
        bulletMenu.addButton("Fireball", e -> bulletMenu.setPresetBulletImage("./resources/Bullet.png"));
        bulletMenu.addButton("Nuke", e -> bulletMenu.setPresetBulletImage("./resources/Bullet2.png"));
        bulletMenu.addButton("Laser", e -> bulletMenu.setPresetBulletImage("./resources/Bullet3.png"));

        musicMenu.addButton("Custom", e -> musicMenu.loadCustomMusic("./resources/Music3.wav"));
        musicMenu.addButton("Space", e -> musicMenu.loadPresetMusic("./resources/Music.wav"));
        musicMenu.addButton("Fun", e -> musicMenu.loadPresetMusic("./resources/Music2.wav"));
        musicMenu.addButton("Retro Arcade", e -> musicMenu.loadPresetMusic("./resources/Music3.wav"));
        musicMenu.addButton("Electronic", e -> musicMenu.loadPresetMusic("./resources/Music4.wav"));
        musicMenu.addButton("Stop Music", e -> musicMenu.stopMusic());
        
        menuBar.add(shooterMenu.menu);
        menuBar.add(invaderMenu.menu);
        menuBar.add(bulletMenu.menu);
        menuBar.add(musicMenu.menu);

        frame.setJMenuBar(menuBar);
        frame.add(game);
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
