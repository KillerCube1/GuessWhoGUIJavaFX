package main.scenes;

import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import main.LoadResources;
import main.MainApp;
import main.elements.Fonts;
import main.elements.ScrollingBackground;

public class Loading extends Screen{

    public Loading() {
        super(new StackPane());
    }

    @Override
    public Scene start() {
        StackPane root = new StackPane();
        setRoot(root);

        // Set up elements
        Image logoImage = new Image(Loading.class.getResourceAsStream("/images/Logo.png"));
        ImageView logo = new ImageView(logoImage);
        logo.setPreserveRatio(true);
        logo.fitWidthProperty().bind(MainApp.getStage().widthProperty().multiply(0.5));
        logo.fitHeightProperty().bind(MainApp.getStage().heightProperty().multiply(0.5));

        Text loadingText = new Text("Loading Game...");
        loadingText.setFill(Color.WHITE);
        loadingText.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double size = root.getWidth() / 40; // Adjust the divisor to control the shrinking rate
            return Fonts.loadFont("/fonts/obelixprob-cyr.ttf", size);
        }, root.widthProperty()));

        Text resourceText = new Text("");
        resourceText.setFill(Color.WHITE);
        resourceText.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double size = root.getWidth() / 100; // Adjust the divisor to control the shrinking rate
            return Fonts.loadFont("/fonts/obelixpro-cyr.ttf", size);
        }, root.widthProperty()));

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.WHITE);
        dropShadow.setRadius(3);
        loadingText.setEffect(dropShadow);
        resourceText.setEffect(dropShadow);

        VBox loadingLayout = new VBox(logo, loadingText, resourceText);
        loadingLayout.setAlignment(Pos.CENTER);
        loadingLayout.setOpacity(0);

        Text bottomRightText = new Text("Version 2.0");
        bottomRightText.setFill(Color.WHITE);
        bottomRightText.setFont(Fonts.loadFont("/fonts/obelixprob-cyr.ttf", 10));

        StackPane.setAlignment(bottomRightText, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(bottomRightText, new Insets(0, 10, 10, 0));

        Stop[] stops = new Stop[]{
                new Stop(0, Color.rgb(0, 224, 245)),
                new Stop(1, Color.rgb(0, 106, 220))
        };

        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, null, stops);

        root.setBackground(Background.fill(gradient));

        Rectangle blackScreen = new Rectangle(10, 10, Color.BLACK);
        blackScreen.widthProperty().bind(MainApp.getStage().widthProperty());
        blackScreen.heightProperty().bind(MainApp.getStage().heightProperty());
        blackScreen.setOpacity(1);

        root.getChildren().add(new ScrollingBackground(MainApp.getStage(), "/images/Background.png", 1.5));
        root.getChildren().add(loadingLayout);
        root.getChildren().add(bottomRightText);

        root.getChildren().add(blackScreen); // Fade in effect

        FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(1), blackScreen);
        fadeInTransition.setToValue(0);
        FadeTransition showLogo = new FadeTransition(Duration.seconds(0.5), loadingLayout);
        showLogo.setToValue(1);

        fadeInTransition.play();
        fadeInTransition.setOnFinished(_ -> {
            showLogo.play();
            showLogo.setOnFinished(_ -> {
                LoadResources.beginLoading(resourceText); // Begin loading resources for game
            });
        });

        setSceneControls(this);
        return this;
    }

}
