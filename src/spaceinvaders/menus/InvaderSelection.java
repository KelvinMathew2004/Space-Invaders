package spaceinvaders.menus;

import java.awt.*;

public class InvaderSelection extends ImageSelection {
    public InvaderSelection() {
        super();
        createMenu("Invader");
    }

    public void setInvaderImage() {
        invaderImage = loadImage("invader", "./resources/InvaderImage.png");
    }

    public Image getInvaderImage() {
        return invaderImage;
    }
}