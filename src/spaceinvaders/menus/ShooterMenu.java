package spaceinvaders.menus;

import spaceinvaders.SpaceInvadersUI;
import spaceinvaders.content.ShooterSelection;

public class ShooterMenu extends MenuBar {
    public ShooterMenu(SpaceInvadersUI game) {
        createMenu("Shooter");

        addButton("Custom", e -> ShooterSelection.setCustomShooterImage(game));
        addButton("Nebula Striker", e -> ShooterSelection.setPresetShooterImage(game, "./resources/ShooterImage2.png"));
        addButton("Void Phoenix", e -> ShooterSelection.setPresetShooterImage(game, "./resources/ShooterImage3.png"));
        addButton("Galactic Reaper", e -> ShooterSelection.setPresetShooterImage(game, "./resources/ShooterImage4.png"));
        addButton("Solar Viper", e -> ShooterSelection.setPresetShooterImage(game, "./resources/ShooterImage5.png"));
        addButton("Cosmo Sentinel", e -> ShooterSelection.setPresetShooterImage(game, "./resources/ShooterImage6.png"));
    }

}
