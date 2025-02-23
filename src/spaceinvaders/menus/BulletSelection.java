package spaceinvaders.menus;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import spaceinvaders.GameExceptions;
import spaceinvaders.SpaceInvadersUI;

public class BulletSelection extends ImageSelection{
    private static Image bulletImage;

    public BulletSelection() {
        super();
        createMenu("Bullets");
    }

    public void loadCustomBulletImage() {
        bulletImage = loadCustomImage("bullet", "./resources/Bullet.png");
    }

    public void setPresetBulletImage(String resourcePath) {
        bulletImage = loadPresetImage("bullet", resourcePath);
    }

    public static void drawBullets(Graphics g, java.util.List<SpaceInvadersUI.Bullet> bullets) {        
        if (bulletImage != null) {
            for (SpaceInvadersUI.Bullet bullet : bullets) {
                g.drawImage(bulletImage, bullet.getX()-5, bullet.getY()-5, 10, 20, null);
            }
        } else {
            g.setColor(Color.YELLOW);
            for (SpaceInvadersUI.Bullet bullet : bullets) {
                int[] xPoints = {bullet.getX(), bullet.getX() - 5, bullet.getX() + 5};
                int[] yPoints = {bullet.getY(), bullet.getY() + 10, bullet.getY() + 10};
                g.fillPolygon(xPoints, yPoints, 3);
            }
        }
    }
}
