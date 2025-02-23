package spaceinvaders;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import spaceinvaders.menus.ShooterSelection;
import spaceinvaders.menus.InvaderSelection;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders with Images");

        SpaceInvadersUI game = new SpaceInvadersUI();

        ShooterSelection shooterMenu = new ShooterSelection();
        InvaderSelection invaderMenu = new InvaderSelection();

        JMenuBar menuBar = new JMenuBar();

        shooterMenu.addButton("Custom", e -> shooterMenu.setCustomShooterImage());
        shooterMenu.addButton("Green", e -> shooterMenu.setPresetShooterImage("./resources/ShooterImage2.png"));
        shooterMenu.addButton("Yellow", e -> shooterMenu.setPresetShooterImage("./resources/ShooterImage3.png"));
        shooterMenu.addButton("Magenta", e -> shooterMenu.setPresetShooterImage("./resources/ShooterImage4.png"));
        shooterMenu.addButton("Purple", e -> shooterMenu.setPresetShooterImage("./resources/ShooterImage5.png"));
        shooterMenu.addButton("Pink", e -> shooterMenu.setPresetShooterImage("./resources/ShooterImage6.png"));

        invaderMenu.addButton("Custom", e -> invaderMenu.setCustomInvaderImage());
        invaderMenu.addButton("UFO", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage2.png"));
        invaderMenu.addButton("Black", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage3.png"));
        invaderMenu.addButton("Purple", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage4.png"));
        invaderMenu.addButton("Blue", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage5.png"));
        invaderMenu.addButton("Green", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage6.png"));
        invaderMenu.addButton("Final Boss", e -> invaderMenu.setPresetInvaderImage("./resources/InvaderImage7.png"));
        
        menuBar.add(shooterMenu.menu);
        menuBar.add(invaderMenu.menu);

        frame.setJMenuBar(menuBar);
        frame.add(game);
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
