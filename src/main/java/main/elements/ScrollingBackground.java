package main.elements;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Objects;

public class ScrollingBackground extends Rectangle {
    private final Image backgroundImage;
    private double imageSize;

    public ScrollingBackground(Stage stage, String filePath) {
        // Load the image
        backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
        imageSize = 1;

        // Create rectangle bound to stage proportions
        widthProperty().bind(stage.widthProperty());
        heightProperty().bind(stage.heightProperty());

        // Begin infinite scrolling animation
        final double[] x = {0};
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Create image pattern
                ImagePattern imagePattern = new ImagePattern(
                        backgroundImage, // Background image
                        -x[0], // X Position
                        x[0],  // Y Position
                        imageSize / (stage.getWidth() / backgroundImage.getWidth()),      // Calculate proper Width
                        imageSize / (stage.getHeight() / backgroundImage.getHeight()),    // Calculate proper Height
                        true
                );

                setFill(imagePattern);

                // Increment
                x[0] += 0.0001;
                if (x[0] >= 100) x[0] = 0.0;
            }
        }.start();
    }

    public ScrollingBackground(Stage stage, String filePath, double size) {
        this(stage, filePath);
        setImageSize(size);
    }

    public void setImageSize(double size) {
        this.imageSize = size;
    }
}
