package spaceinvaders.menus;

import java.awt.*;

public class ShooterSelection extends ImageSelection {
    public void setShooterImage() {
        image = loadImage("shooter", "./resources/ShooterImage.png");
    }

    public Image getShooterImage() {
        return image;
    }
}
