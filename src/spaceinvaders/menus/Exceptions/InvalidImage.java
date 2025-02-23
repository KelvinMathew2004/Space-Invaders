package spaceinvaders.menus.Exceptions;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import spaceinvaders.GameExceptions;

public class InvalidImage {
    public static Image checkUrlForImage(String imageUrl, String imageType) {
        try {
            return ImageIO.read(new URL(imageUrl));
        } catch (MalformedURLException e) {
            GameExceptions.showErrorDialog("Invalid URL for " + imageType + " image: " + e.getMessage() + "\nLoading default image");
        } catch (IOException e) {
            GameExceptions.showErrorDialog("Failed to load " + imageType + " image: " + e.getMessage() + "\nLoading default image");
        }
        return null;
    }

    public static Image checkResourceForImage(String resourcePath, String imageType) {
        try {
            return ImageIO.read(InvalidImage.class.getResource(resourcePath));
        } catch (IOException e) {
            GameExceptions.showErrorDialog("Failed to load default " + imageType + " image: " + e.getMessage());
        }
        return null;
    }
}