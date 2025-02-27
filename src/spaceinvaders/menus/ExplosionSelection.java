package spaceinvaders.menus;

import java.awt.*;

public class ExplosionSelection extends ImageSelection{
    public ExplosionSelection(){
        super();
    }

    public void setPresetExplosionImage(String resourcePath) {
        explosionImage = loadPresetImage("explosion", resourcePath);
    }

    public Image getExplosionImage() {
        return explosionImage;
    }
}