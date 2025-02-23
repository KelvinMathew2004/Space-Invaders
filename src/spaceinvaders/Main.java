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

        shooterMenu.addButton("Custom", e -> shooterMenu.setShooterImage());
        invaderMenu.addButton("Custom", e -> invaderMenu.setInvaderImage());
        
        menuBar.add(shooterMenu.menu);
        menuBar.add(invaderMenu.menu);

        frame.setJMenuBar(menuBar);
        frame.add(game);
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
