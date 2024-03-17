package main;

import javafx.animation.*;
import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.elements.Fonts;
import main.elements.ScrollingBackground;

import java.io.IOException;
import java.io.InputStream;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Set up Root panel and Scene
        StackPane root = new StackPane();
        Scene scene = new Scene(root);

        // Set up stage
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Small_Icon.png")));
        stage.setTitle("Guess Who");
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);

        // Set up stage controls
        scene.setOnKeyPressed(event -> { // Full screen when F11 is pressed
            if (event.getCode() == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });

        Image logoImage = new Image(getClass().getResourceAsStream("/images/Logo.png"));
        ImageView logo = new ImageView(logoImage);
        logo.setPreserveRatio(true);
        logo.fitWidthProperty().bind(stage.widthProperty().multiply(0.5));
        logo.fitHeightProperty().bind(stage.heightProperty().multiply(0.5));

        Text loadingText = new Text("Loading Game...");
        loadingText.setFill(Color.WHITE);
        loadingText.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double size = root.getWidth() / 40; // Adjust the divisor to control the shrinking rate
            return Fonts.loadFont("/fonts/obelixprob-cyr.ttf", size);
        }, root.widthProperty()));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.WHITE);
        dropShadow.setRadius(4);
        loadingText.setEffect(dropShadow);

        VBox loadingLayout = new VBox(logo, loadingText);
        loadingLayout.setAlignment(Pos.CENTER);

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

        root.getChildren().add(new ScrollingBackground(stage, "/images/Background.png", 1.5));
        root.getChildren().add(loadingLayout);
        root.getChildren().add(bottomRightText);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
