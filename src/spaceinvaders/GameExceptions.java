package spaceinvaders;

import javax.swing.JOptionPane;
import spaceinvaders.SpaceInvadersUI;

public class GameExceptions {
    
    // Method to display error dialog
    public static void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
 
    public static void showGameOver(String errorMessage) {
        int option = JOptionPane.showConfirmDialog(
            null, errorMessage + "\nDo you want to restart?", "Game Over",
            JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE
        );
    
        if(option == JOptionPane.YES_OPTION) {
            SpaceInvadersUI.gameInstance.startNewGame();
        }
        else{
            System.exit(0);
        }
    }

    public static int showGamePaused(String infoMessage) {
        int option = JOptionPane.showOptionDialog(
            null, infoMessage + "\nDo you want to restart or resume?", "Game Paused",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
            new String[]{"Restart", "Resume"}, "Resume"
        );
        return option;
    }
}
        