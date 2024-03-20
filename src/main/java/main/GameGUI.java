package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Objects;

public class GameGUI extends Application {

    private Scene gameScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Guess Who Game");

        StackPane root = new StackPane();
        gameScene = new Scene(root);
        primaryStage.setScene(gameScene);
        setSceneControls(primaryStage);

        primaryStage.setFullScreen(true);
        primaryStage.show();

        root.getChildren().add(createContent());
        gameScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    }

    private void setSceneControls(Stage stage) {
        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });
    }


    private Parent createContent() {
        StackPane root = new StackPane();

        HBox hboxTop = addHBoxWithLogo();

        HBox hboxBottom = addHBoxWithText();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hboxTop);
        borderPane.setBottom(hboxBottom);

        root.getChildren().add(borderPane);
        return root;
    }

    private HBox addHBox() {
        Color color1 = Color.rgb(150, 4, 19); // #bc0413
        Color color2 = Color.rgb(239, 24, 41);  // #ef1829

        Stop[] stops = {
                new Stop(0, color1),
                new Stop(1, color2)
        };

        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 60, 12));
        hbox.setSpacing(10);
        hbox.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        return hbox;
    }

    private HBox addHBoxWithLogo() {
        HBox hbox = addHBox();

        Image image = new Image(Objects.requireNonNull(GameGUI.class.getResourceAsStream("/images/Logo.png")));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(90);
        imageView.setPreserveRatio(true);

        HBox.setHgrow(imageView, Priority.NEVER);
        imageView.setManaged(false);
        hbox.getChildren().add(imageView);

        return hbox;
    }

    private HBox addHBoxWithText() {
        HBox hbox = addHBox();

        Label character = new Label("Your Card: ");
        character.setTextFill(Color.WHITE);

        Font font = Font.font("Arial", FontWeight.BOLD, 26);
        character.setFont(font);

        character.setMaxWidth(Region.USE_PREF_SIZE);

        hbox.getChildren().add(character);
        return hbox;
    }
}
