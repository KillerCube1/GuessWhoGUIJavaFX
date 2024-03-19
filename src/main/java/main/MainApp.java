package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.scenes.*;

import java.util.Objects;

public class MainApp extends Application {

    private static Stage mainStage;
    private static Scene mainScene;

    @Override
    public void start(Stage stage) {
        // Set up main variables
        mainStage = stage;
        mainScene = new Scene(new StackPane());

        // Set up scene
        stage.setScene(mainScene);
        setSceneControls();

        // Set up stage
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Small_Icon.png"))));
        stage.setTitle("Guess Who");
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();

        // Start Loading scene
        setScreen("Loading");
    }

    public static void main(String[] args) {
        launch();
    }

    void setSceneControls() {
        // Full screen when F11 is pressed
        mainScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                MainApp.getStage().setFullScreen(!MainApp.getStage().isFullScreen());
            }
        });

    }

    public static Stage getStage() {
        return mainStage;
    }

    public static void setScreen(String scene) {
        Screen screen = switch (scene) {
            case "Loading" -> new Loading();
            case "Testing" -> new Testing();
            default -> new Default();
        };
        mainStage.getScene().setRoot(screen);
        screen.start();
    }

}
