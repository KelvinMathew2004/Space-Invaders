package spaceinvaders;

import javax.swing.JOptionPane;

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
}
        