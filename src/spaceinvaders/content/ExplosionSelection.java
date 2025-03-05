package spaceinvaders.content;

import java.awt.*;

public class ExplosionSelection extends ImageSelection{
    public static void setPresetExplosionImage(String resourcePath) {
        explosionImage = loadPresetImage("explosion", resourcePath);
    }

    public static Image getExplosionImage() {
        return explosionImage;
    }
}