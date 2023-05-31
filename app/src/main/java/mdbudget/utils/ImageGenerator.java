package mdbudget.utils;

import javafx.scene.image.Image;

public class ImageGenerator {

    public static Image generate(String imageName, int setWidth, int setHeight) {
        Image image = new Image(ImageGenerator.class.getResource("/images/" + imageName).toString(), setWidth, setHeight, false, false, false);
        return image;
    }
}
