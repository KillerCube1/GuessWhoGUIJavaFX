package main.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import main.MainApp;

public class Screen extends Scene {
    public Screen(Parent parent) {
        super(parent);
    }

    public Scene start() {
        return null;
    }

    void setSceneControls(Scene scene) {
        // Full screen when F11 is pressed
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                MainApp.getStage().setFullScreen(!MainApp.getStage().isFullScreen());
            }
        });

    }
}
