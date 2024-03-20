package main.elements;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class ImageCrop {
    public static WritableImage cropImage(Image image, int x, int y, int w, int h) {
        PixelReader reader = image.getPixelReader();
        return new WritableImage(reader, x, y, w, h);
    }
}
