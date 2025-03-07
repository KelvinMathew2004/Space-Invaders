package spaceinvaders.content;

import java.awt.*;
import spaceinvaders.SpaceInvadersUI;

public class BulletSelection extends ImageSelection{
    private static Image bulletImage;
    private static String bulletType;

    public static void loadCustomBulletImage(SpaceInvadersUI game) {
        bulletImage = loadCustomImage("bullet", "./resources/Bullet.png");
        game.repaint();
    }

    public static void setPresetBulletImage(SpaceInvadersUI game, String resourceName) {
        bulletImage = loadPresetImage("bullet", "./resources/" + resourceName + ".png");
        bulletType = resourceName;
        game.repaint();
    }

    public static String getBulletType() {
        return bulletType;
    }

    public static void drawBullets(SpaceInvadersUI game, Graphics g, java.util.List<SpaceInvadersUI.Bullet> bullets) {        
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
        game.repaint();
    }
}
