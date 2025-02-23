package spaceinvaders.menus;

import java.awt.*;

public class ShooterSelection extends ImageSelection {
    public ShooterSelection() {
        super();
        createMenu("Shooter");
    }

    public void setCustomShooterImage() {
        shooterImage = loadCustomImage("shooter", "/resources/ShooterImage.png");
    }

    public void setPresetShooterImage(String resourcePath) {
        shooterImage = loadPresetImage("shooter", resourcePath);
    }

    public Image getShooterImage() {
        return shooterImage;
    }
}
