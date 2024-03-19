package main.scenes;

import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.MainApp;
import main.elements.Fonts;
import main.elements.ScrollingBackground;

public class Default extends Screen {

    public Default() {
        super(new StackPane());
    }

    @Override
    public Parent start() {
        Text loadingText = new Text("Null window");
        loadingText.setFill(Color.WHITE);
        loadingText.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double size = getWidth() / 40; // Adjust the divisor to control the shrinking rate
            return Fonts.loadFont("/fonts/obelixprob-cyr.ttf", size);
        }, widthProperty()));

        getChildren().add(new ScrollingBackground(MainApp.getStage(), "/main/Background.png", 1.5));
        getChildren().add(loadingText);

        return this;
    }

}
