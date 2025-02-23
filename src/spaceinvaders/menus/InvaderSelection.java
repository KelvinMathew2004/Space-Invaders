package spaceinvaders.menus;

import java.awt.*;

public class InvaderSelection extends ImageSelection {
    public void setInvaderImage() {
        image = loadImage("invader", "./resources/InvaderImage.png");
    }

    public Image getInvaderImage() {
        return image;
    }
}