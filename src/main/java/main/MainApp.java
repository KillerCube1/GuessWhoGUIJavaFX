package main;

import javafx.animation.*;
import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the image
        Image backgroundImage = new Image(getClass().getResourceAsStream("/images/Background.jpg"));

        // Create ImageView and set the image
        Rectangle background = new Rectangle();
        background.widthProperty().bind(stage.widthProperty());
        background.heightProperty().bind(stage.heightProperty());
        ImagePattern imagePattern = new ImagePattern(backgroundImage, 0, 0, 0.25, 0.25, true);
        background.setFill(imagePattern);

        // Create a button
        Button button = new Button("Click Me");

        // Create a scale transition for button growth
        ScaleTransition scaleTransitionGrow = new ScaleTransition(Duration.millis(200), button);
        scaleTransitionGrow.setToX(1.1);
        scaleTransitionGrow.setToY(1.1);

        // Create a scale transition for button shrink
        ScaleTransition scaleTransitionShrink = new ScaleTransition(Duration.millis(200), button);
        scaleTransitionShrink.setToX(1);
        scaleTransitionShrink.setToY(1);

        ScaleTransition scaleTransitionClick = new ScaleTransition(Duration.millis(50), button);
        scaleTransitionClick.setToX(1);
        scaleTransitionClick.setToY(1);

        // Registering mouse events to trigger the scale transitions
        button.setOnMouseEntered(event -> scaleTransitionGrow.play());
        button.setOnMouseExited(event -> scaleTransitionShrink.play());
        button.setOnMousePressed(event -> scaleTransitionClick.play());
        button.setOnMouseReleased(event -> scaleTransitionGrow.play());
        button.setOnMouseClicked(event -> StyledButtonController.handleButtonClick() );

        // Apply CSS styling to the button
        button.getStyleClass().add("styled-button");

        // Create a layout pane and add the button to it
        StackPane root = new StackPane();
        root.getChildren().add(background);
        root.getChildren().add(button);

        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Styled Button");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
