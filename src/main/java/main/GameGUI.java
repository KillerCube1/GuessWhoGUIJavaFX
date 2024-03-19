package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

//TODO: Set background color of screen when not turn to red in css.
//TODO: Add Card images placeholders of some sort.
//TODO: Add layout for menu bar or text chat area of some sort.
//TODO: Change Background.png to original folder wasnt sure how to get it to work.
public class GameGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox buttonContainer = new VBox(10);
        buttonContainer.setPadding(new Insets(50));

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(buttonContainer);


        HBox hboxTop = addHBox();
        borderPane.setTop(hboxTop);

        HBox hboxBottom = addHBox();
        borderPane.setBottom(hboxBottom);


        Scene scene = new Scene(borderPane, 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static HBox addHBox() {
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

}
