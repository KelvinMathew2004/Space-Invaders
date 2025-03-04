package spaceinvaders;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import spaceinvaders.menus.BulletSelection;
import spaceinvaders.menus.MusicSelection;

public class ListenerActions {
    MusicSelection musicSelection = new MusicSelection();

    private int fireCooldown = 0;
    private boolean clicked = false;
    public final ScoreCard scoreCount = new ScoreCard();
    public void updatePositions(SpaceInvadersUI game) {

        int shooter_X_Coordinate = game.getShooter_X_Coordinate();
        int shooter_Width = game.getShooterWidth();
        // Move shooter left or right
        if (game.moveLeft && shooter_X_Coordinate > 0) {
            game.setShooter_X_Coordinate(shooter_X_Coordinate - 5);
        }
        if (game.moveRight && shooter_X_Coordinate < game.getWidth() - shooter_Width) {
            game.setShooter_X_Coordinate(shooter_X_Coordinate + 5);
        }

        if (fireCooldown > 0) {
            fireCooldown--;
        }

        if (game.shooting) {
            if (fireCooldown <= 0) {
                int shooter_width = game.getShooterWidth();
                int shooter_height = game.getShooterHeight();
                // if (BulletSelection.getBulletType() == "Bullet")
                //     musicSelection.playSoundEffect("./resources/Bullet.wav", 0.7f);
                // else if (BulletSelection.getBulletType() == "Bullet2") 
                //     musicSelection.playSoundEffect("./resources/Bullet2.wav", 0.7f);
                // else if (BulletSelection.getBulletType() == "Bullet3")
                //     musicSelection.playSoundEffect("./resources/Bullet3.wav", 0.6f);
                musicSelection.playSoundEffect("./resources/" + BulletSelection.getBulletType() + ".wav", 0.7f);
                game.bullets.add(
                        game.new Bullet(shooter_X_Coordinate + shooter_width / 2, game.getHeight() - shooter_height));
                fireCooldown = 20;
                fireCooldown--;
            }
        }

        // Add new falling invaderboxs randomly
        if (game.random.nextInt(100) < 2) {
            if (game.getWidth() > 0) {
                int x = game.random.nextInt(game.getWidth()); 
                game.invaderboxes.add(game.new InvaderBox(x, -40, 40));
            }
        }

        // Move invaderboxes down
        Iterator<SpaceInvadersUI.InvaderBox> invaderboxIterator = game.invaderboxes.iterator();
        while (invaderboxIterator.hasNext()) {
            SpaceInvadersUI.InvaderBox invaderbox = invaderboxIterator.next();
            if (invaderbox.exploding) {
                invaderbox.explosionCounter--;
                if (invaderbox.explosionCounter <= 0) {
                    invaderboxIterator.remove(); // Remove after explosion animation
                }
            } else {
                invaderbox.y += 2;
                if (invaderbox.y > game.getHeight()) {
                    invaderboxIterator.remove(); 
                    scoreCount.decreaseScore(10); 
                }
                if(invaderbox.x < 10 || invaderbox.x > game.getWidth() - (invaderbox.size + 10)) {
                    invaderboxIterator.remove(); // Remove if out of screen
                }
            }
        }

        // Move bullets up
        Iterator<SpaceInvadersUI.Bullet> bulletIterator = game.bullets.iterator();
        while (bulletIterator.hasNext()) {
            SpaceInvadersUI.Bullet bullet = bulletIterator.next();
            bullet.y -= 5;
            if (bullet.y < 0) {
                bulletIterator.remove(); // Remove bullets that go off the screen
            }
        }

        // Check for bullet-invaderbox collisions
        bulletIterator = game.bullets.iterator();
        while (bulletIterator.hasNext()) {
            SpaceInvadersUI.Bullet bullet = bulletIterator.next();
            invaderboxIterator = game.invaderboxes.iterator();
            while (invaderboxIterator.hasNext()) {
                SpaceInvadersUI.InvaderBox invaderbox = invaderboxIterator.next();
                
                if (invaderbox.exploding) {
                    continue;
                }

                if (new Rectangle(bullet.x - 5, bullet.y, 10, 10).intersects(
                        new Rectangle(invaderbox.x, invaderbox.y, invaderbox.size, invaderbox.size))) {
                
                    bulletIterator.remove();
                    invaderbox.exploding = true;
                    invaderbox.explosionCounter = 20;

                    if(invaderbox.explosionCounter == 20) {
                        musicSelection.playSoundEffect("./resources/Explosion2.wav", 0.7f);
                    }

                    scoreCount.increaseScore(1); 
                }
            }
        }

        invaderboxIterator = game.invaderboxes.iterator();
        while (invaderboxIterator.hasNext()) {
            SpaceInvadersUI.InvaderBox invaderbox = invaderboxIterator.next();
            if (invaderbox.exploding) {
                invaderbox.explosionCounter--;
                if (invaderbox.explosionCounter <= 0) {
                    invaderboxIterator.remove();
                }
            }
        }
    }

    public void keyPressed(KeyEvent e, SpaceInvadersUI game) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            game.moveLeft = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            game.moveRight = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            game.shooting = true;
        }
        if (key == KeyEvent.VK_P) {
            if (!clicked) {
                game.pauseGame();
                clicked = true;
            } else {
                game.resumeGame();
                clicked = false;
            }
        }
    }

    public void keyReleased(KeyEvent e, SpaceInvadersUI game) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            game.moveLeft = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            game.moveRight = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            game.shooting = false;
        }
    }
}
