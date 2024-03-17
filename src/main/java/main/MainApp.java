package main;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.assets.Resource;

import java.util.Objects;

public class MainApp extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage stage) {
        mainStage = stage;

        // Load in scenes
        Resource.loadScenes();

        // Start Loading scene
        setScreen("Loading");

        // Set up stage
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Small_Icon.png"))));
        stage.setTitle("Guess Who");
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return mainStage;
    }

    public static void setScreen(String scene) {
        mainStage.setScene(Resource.getScene(scene).start());
    }

}
