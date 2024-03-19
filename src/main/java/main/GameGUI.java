package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.scene.paint.Color.WHITE;

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

        HBox hboxTop = addHBoxWithLogo();
        borderPane.setTop(hboxTop);

        HBox hboxBottom = addHBoxWithText();
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

    public static HBox addHBoxWithLogo() {
        HBox hbox = addHBox();

        Image image = new Image(Objects.requireNonNull(GameGUI.class.getResourceAsStream("/images/Logo.png")));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(90);
        imageView.setPreserveRatio(true);


        HBox.setHgrow(imageView, javafx.scene.layout.Priority.NEVER);
        imageView.setManaged(false);
        hbox.getChildren().add(imageView);

        return hbox;
    }

    public static HBox addHBoxWithText() {
        HBox hbox = addHBox();
        Label character = new Label("Your Card: ");
        character.setAlignment(Pos.CENTER_RIGHT);
        character.setTextFill(Color.WHITE);
        character.setPadding(new Insets(0, 40, 0, 0));

        Font font = Font.font("Arial", FontWeight.BOLD, 26);
        character.setFont(font);

        HBox.setHgrow(character, javafx.scene.layout.Priority.NEVER);
        character.setManaged(false);
        hbox.getChildren().add(character);
        return hbox;
    }

}
