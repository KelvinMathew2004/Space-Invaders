package spaceinvaders.menus;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

import spaceinvaders.GameExceptions;

public class ImageSelection extends MenuBar{
    protected static Image shooterImage;
    protected static Image invaderImage;
    protected static Image explosionImage;

    protected static Image loadCustomImage(String imageType, String defaultResourcePath) {
        String imageUrl = JOptionPane.showInputDialog(null,
                "Enter URL for " + imageType + " image (or leave blank for default):");

        if(defaultResourcePath == "./resources/Bullet.png"){
            return null;
        }

        // Need to handle case where url is not an image, ie a png or jpeg.
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                return ImageIO.read(new URL(imageUrl));
            } catch (MalformedURLException e) {
                GameExceptions.showErrorDialog(
                        "Invalid URL for " + imageType + " image: " + "\nLoading default image");
            } catch (IOException e) {
                GameExceptions.showErrorDialog(
                        "Failed to load " + imageType + " image: " + "\nLoading default image");
            }
        }

        // If no URL is provided or URL fails, load the default resource
        try {
            return ImageIO.read(ImageSelection.class.getResource(defaultResourcePath));
        } catch (IOException e) {
            GameExceptions.showErrorDialog("Failed to load default " + imageType + " image: ");
        }

        return null;
    }

    protected static Image loadPresetImage(String imageType, String resourcePath) {
        try {
            return ImageIO.read(ImageSelection.class.getResource(resourcePath));
        } catch (IOException e) {
            GameExceptions.showErrorDialog("Failed to load default " + imageType + " image: ");
        }

        return null;
    }
}
