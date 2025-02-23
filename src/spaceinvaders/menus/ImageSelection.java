package spaceinvaders.menus;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

import spaceinvaders.GameExceptions;
import spaceinvaders.menus.Exceptions.InvalidImage;

public class ImageSelection extends MenuBar{
    protected static Image shooterImage;
    protected static Image invaderImage;

    protected static Image loadCustomImage(String imageType, String defaultResourcePath) {
        String imageUrl = JOptionPane.showInputDialog(null, "Enter URL for " + imageType + " image (or leave blank for default):");
        if(defaultResourcePath == "./resources/Bullet.png"){
            return null;
        }
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Image img = InvalidImage.checkUrlForImage(imageUrl, imageType);
            if (img != null) {
                return img;
            }
        }
        return InvalidImage.checkResourceForImage(defaultResourcePath, imageType);
    }

    protected static Image loadPresetImage(String imageType, String resourcePath) {
        return InvalidImage.checkResourceForImage(resourcePath, imageType);
    }
}
