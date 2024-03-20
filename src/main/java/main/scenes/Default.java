package main.scenes;

import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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

        getChildren().add(new ScrollingBackground(MainApp.getStage(), "/images/Background.png", 1.5));
        getChildren().add(loadingText);

        return this;
    }

}
