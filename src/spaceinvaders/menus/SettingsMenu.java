package spaceinvaders.menus;

import spaceinvaders.SpaceInvadersUI;

public class SettingsMenu extends MenuBar {

    public SettingsMenu(SpaceInvadersUI game) {
        createMenu("Settings");

        addButton("Pause", e -> game.pauseGame());
        addButton("Resume", e -> game.resumeGame());
        addButton("Restart", e -> game.startNewGame());
    }

}
