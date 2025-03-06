package spaceinvaders;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

import spaceinvaders.menus.BulletMenu;
import spaceinvaders.menus.InvaderMenu;
import spaceinvaders.menus.MusicMenu;
import spaceinvaders.menus.ShooterMenu;
import spaceinvaders.menus.SettingsMenu;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Space Invaders with Images");

            SpaceInvadersUI game = new SpaceInvadersUI();

            ShooterMenu shooter = new ShooterMenu(game);
            InvaderMenu invader = new InvaderMenu(game);
            MusicMenu music = new MusicMenu();
            BulletMenu bullet = new BulletMenu(game);
            SettingsMenu settings = new SettingsMenu(game);

            JMenuBar menuBar = new JMenuBar();
            menuBar.add(shooter.getMenu());
            menuBar.add(invader.getMenu());
            menuBar.add(bullet.getMenu());
            menuBar.add(music.getMenu());
            menuBar.add(settings.getMenu());

            frame.setJMenuBar(menuBar);
            frame.add(game);
            frame.setSize(600, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
