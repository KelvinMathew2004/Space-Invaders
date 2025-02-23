package spaceinvaders.menus;

import java.awt.*;

public class InvaderSelection extends ImageSelection {
    public InvaderSelection() {
        super();
        createMenu("Invader");
    }

    public void setCustomInvaderImage() {
        invaderImage = loadCustomImage("invader", "./resources/InvaderImage.png");
    }

    public void setPresetInvaderImage(String resourcePath) {
        invaderImage = loadPresetImage("invader", resourcePath);
    }

    public Image getInvaderImage() {
        return invaderImage;
    }
}