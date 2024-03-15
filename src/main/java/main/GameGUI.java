package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class GameGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button[] buttonList = {
                createButton("Male?", "gendmale", Color.web("#FF4343")),
                createButton("Female?", "gendfemale", Color.web("#FF8100")),
                createButton("Hat?", "hat", Color.web("#FFD300")),
                createButton("Bald?", "bald", Color.web("#61FF13")),
                createButton("Beard?", "beard", Color.web("#0FFF93")),
                createButton("Moustache?", "moustache", Color.web("#00D8FF")),
                createButton("Rosy Cheeks?", "rosyCheeks", Color.web("#0095FF")),
                createButton("Glasses?", "glasses", Color.web("#0066FF")),
                createButton("Black Hair?", "hairblack", Color.web("#3000FF")),
                createButton("Red Hair?", "hairred", Color.web("#7C58FF")),
                createButton("Brown Hair?", "hairbrown", Color.web("#D41CFF")),
                createButton("Blonde Hair?", "hairblonde", Color.web("#FF049B")),
                createButton("White Hair?", "hairwhite", Color.web("#FF65B2")),
                createButton("Blue Eyes?", "eyesblue", Color.web("#FF9393")),
                createButton("Brown Eyes?", "eyesbrown", Color.web("#FFB7B7"))
        };
        VBox buttonContainer = new VBox(10);
        buttonContainer.setPadding(new Insets(50));

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(buttonContainer);

        for (Button button : buttonList) {
            button.getStyleClass().add("custom-button");
            buttonContainer.getChildren().add(button);
        }



        Scene scene = new Scene(borderPane, 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String label, String id, Color color) {
        Button button = new Button(label);
        button.setId(id);
        button.setTextFill(color);
        button.setPrefWidth(150);
        button.setPrefHeight(150);
        return button;
    }
}
