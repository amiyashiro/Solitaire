
package solitaire.images;

import javafx.scene.image.Image;

/**
 *
 * @author Adam
 */
public class ImageManager {
    public static String getResource(String resource) {
        return ImageManager.class.getResource(resource).toExternalForm();
    }
    
    public static Image getImage(String image) {
        return new Image(getResource(image));
    }
}
