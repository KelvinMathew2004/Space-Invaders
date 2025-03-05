package spaceinvaders.content;

import java.awt.*;

import spaceinvaders.SpaceInvadersUI;

public class InvaderSelection extends ImageSelection {
    public static void setCustomInvaderImage(SpaceInvadersUI game) {
        invaderImage = loadCustomImage("invader", "./resources/InvaderImage.png");
        game.repaint();
    }

    public static void setPresetInvaderImage(SpaceInvadersUI game, String resourcePath) {
        invaderImage = loadPresetImage("invader", resourcePath);
        game.repaint();
    }

    public static Image getInvaderImage() {
        return invaderImage;
    }
}