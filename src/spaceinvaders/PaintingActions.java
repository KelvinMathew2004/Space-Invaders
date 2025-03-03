package spaceinvaders;

import java.awt.*;

import spaceinvaders.menus.BulletSelection;
import spaceinvaders.menus.MusicSelection;

public class PaintingActions {
    MusicSelection musicSelection = new MusicSelection();

    public PaintingActions() {

    }

    public void drawShooter(Graphics g, SpaceInvadersUI game) {
        Image shooter_image = game.shooterSelection.getShooterImage();
        int shooter_height = game.getShooterHeight();
        int shooter_width = game.getShooterWidth();
        int shooter_X_Coordinate = game.getShooter_X_Coordinate();
        int shooter_Y_Coordinate = game.getHeight() - shooter_height - 25;

        g.drawImage(shooter_image, shooter_X_Coordinate, shooter_Y_Coordinate, shooter_width, shooter_height, game);

    }

    public void drawInvaders(Graphics g, java.util.List<SpaceInvadersUI.InvaderBox> invaderboxes, Image invaderboxImage,
            SpaceInvadersUI game) {
        for (SpaceInvadersUI.InvaderBox invaderbox : invaderboxes) {
            g.drawImage(invaderboxImage, invaderbox.x, invaderbox.y, invaderbox.size, invaderbox.size, game);
        }
    }

    public void drawExplosions(Graphics g, java.util.List<SpaceInvadersUI.InvaderBox> invaderboxes, Image explosionImage) {
        musicSelection.playSoundEffect("./resources/Explosion.wav");
        for (SpaceInvadersUI.InvaderBox invaderbox : invaderboxes) {
            if (invaderbox.exploding && explosionImage != null) {
                g.drawImage(explosionImage, invaderbox.x - 5, invaderbox.y - 5, invaderbox.size + 10, invaderbox.size + 10, null);
            }
        }
    }

    public void drawBullets(Graphics g, java.util.List<SpaceInvadersUI.Bullet> bullets) {
        musicSelection.playSoundEffect("./resources/" + BulletSelection.getBulletType() + ".wav");
        BulletSelection.drawBullets(g, bullets);
    }
}
