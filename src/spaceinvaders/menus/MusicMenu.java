package spaceinvaders.menus;

import spaceinvaders.content.MusicSelection;

public class MusicMenu extends MenuBar {
    MusicSelection musicSelection = new MusicSelection();

    public MusicMenu() {
        createMenu("Music");

        addButton("Custom", e -> musicSelection.loadCustomMusic("./resources/Music3.wav"));
        addButton("Space", e -> musicSelection.loadPresetMusic("./resources/Music.wav"));
        addButton("Fun", e -> musicSelection.loadPresetMusic("./resources/Music2.wav"));
        addButton("Retro Arcade", e -> musicSelection.loadPresetMusic("./resources/Music3.wav"));
        addButton("Electronic", e -> musicSelection.loadPresetMusic("./resources/Music4.wav"));
        addButton("Stop Music", e -> musicSelection.stopMusic());
    }

}
