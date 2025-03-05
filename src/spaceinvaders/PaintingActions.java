package spaceinvaders;

import java.awt.*;

import spaceinvaders.content.BulletSelection;
import spaceinvaders.content.ShooterSelection;

public class PaintingActions {

    public PaintingActions() {

    }

    public void drawShooter(Graphics g, SpaceInvadersUI game) {
        Image shooter_image = ShooterSelection.getShooterImage();
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
        for (SpaceInvadersUI.InvaderBox invaderbox : invaderboxes) {
            if (invaderbox.exploding && explosionImage != null) {
                g.drawImage(explosionImage, invaderbox.x - 5, invaderbox.y - 5, invaderbox.size + 10, invaderbox.size + 10, null);
            }
        }
    }

    public void drawBullets(SpaceInvadersUI game, Graphics g, java.util.List<SpaceInvadersUI.Bullet> bullets) {
        BulletSelection.drawBullets(game, g, bullets);
    }
}
