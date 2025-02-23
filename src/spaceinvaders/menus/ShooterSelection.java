package spaceinvaders.menus;

import java.awt.*;

public class ShooterSelection extends ImageSelection {
    public ShooterSelection() {
        super();
        createMenu("Shooter");
    }

    public void setShooterImage() {
        shooterImage = loadImage("shooter", "./resources/ShooterImage.png");
    }

    public Image getShooterImage() {
        return shooterImage;
    }
}
