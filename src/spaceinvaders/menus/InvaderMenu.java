package spaceinvaders.menus;

import spaceinvaders.SpaceInvadersUI;
import spaceinvaders.content.InvaderSelection;

public class InvaderMenu extends MenuBar {
    public InvaderMenu(SpaceInvadersUI game) {
        createMenu("Invader");

        addButton("Custom", e -> InvaderSelection.setCustomInvaderImage(game));
        addButton("UFO", e -> InvaderSelection.setPresetInvaderImage(game, "./resources/InvaderImage2.png"));
        addButton("Xenovores", e -> InvaderSelection.setPresetInvaderImage(game, "./resources/InvaderImage3.png"));
        addButton("Nebulons", e -> InvaderSelection.setPresetInvaderImage(game, "./resources/InvaderImage4.png"));
        addButton("Mechavores", e -> InvaderSelection.setPresetInvaderImage(game, "./resources/InvaderImage5.png"));
        addButton("Voidspawn", e -> InvaderSelection.setPresetInvaderImage(game, "./resources/InvaderImage6.png"));
        addButton("Zygar Swarm", e -> InvaderSelection.setPresetInvaderImage(game, "./resources/InvaderImage7.png"));
    }

}
