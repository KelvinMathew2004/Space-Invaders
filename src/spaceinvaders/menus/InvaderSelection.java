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

    public void setPresetInvaderImage(String defaultResourcePath) {
        invaderImage = loadPresetImage("invader", defaultResourcePath);
    }

    public Image getInvaderImage() {
        return invaderImage;
    }
}