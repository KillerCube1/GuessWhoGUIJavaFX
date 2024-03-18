package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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


        MenuItem menuItem1 = new MenuItem("Menu Item 1");
        MenuItem menuItem2 = new MenuItem("Menu Item 2");
        MenuItem menuItem3 = new MenuItem("Pause Game");
        MenuItem menuItem4 = new MenuItem("Settings");
        MenuItem menuItem5 = new MenuItem("Exit Game");

        menuItem1.setOnAction(_ -> {
            System.out.println("Menu Item 1 clicked");
        });

        menuItem2.setOnAction(_ -> {
            System.out.println("Menu Item 2 clicked");
        });

        menuItem3.setOnAction(_ -> {
            System.out.println("Pause Game clicked");
            //TODO: Display a pause screen title mainly for single player
        });

        menuItem4.setOnAction(_ -> {
            System.out.println("Settings!");
            //TODO: Open new stage to allow for different settings during game possibly change background color and stuff idk
        });

        menuItem5.setOnAction(_ -> {
            System.out.println("Goodbye!");
            primaryStage.close();
            //TODO: Show a simple stage of exiting the game!
        });

        Menu menu = new Menu("Options");
        menu.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4, menuItem5);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);

        VBox menuBox = new VBox(menuBar);

        borderPane.setTop(menuBox);


        Scene scene = new Scene(borderPane, 1600, 900);
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
