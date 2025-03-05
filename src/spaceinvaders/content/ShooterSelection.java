package spaceinvaders.content;

import java.awt.*;

import spaceinvaders.SpaceInvadersUI;

public class ShooterSelection extends ImageSelection {

    public static void setCustomShooterImage(SpaceInvadersUI game) {
        shooterImage = loadCustomImage("shooter", "./resources/ShooterImage.png");
        game.repaint();
    }

    public static void setPresetShooterImage(SpaceInvadersUI game, String resourcePath) {
        shooterImage = loadPresetImage("shooter", resourcePath);
        game.repaint();
    }

    public static Image getShooterImage() {
        return shooterImage;
    }
}
