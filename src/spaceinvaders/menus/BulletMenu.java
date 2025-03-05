package spaceinvaders.menus;

import javax.swing.JMenu;

import spaceinvaders.SpaceInvadersUI;
import spaceinvaders.content.BulletSelection;

public class BulletMenu extends MenuBar {

    public BulletMenu(SpaceInvadersUI game) {
        createMenu("Bullet");

        addButton("Custom", e -> BulletSelection.loadCustomBulletImage(game));
        addButton("Fireball", e -> BulletSelection.setPresetBulletImage(game, "Bullet"));
        addButton("Nuke", e -> BulletSelection.setPresetBulletImage(game, "Bullet2"));
        addButton("Laser", e -> BulletSelection.setPresetBulletImage(game, "Bullet3"));
    }

}
