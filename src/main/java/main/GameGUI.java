package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import main.elements.ScrollingBackground;
import java.util.Objects;

public class GameGUI extends Application {

    private Scene gameScene;
    private static double spacePercentage = 0.85;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Guess Who Game");

        StackPane root = new StackPane();
        root.getChildren().add(new ScrollingBackground(primaryStage, "/images/Background.png", 1.5));
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
                spacePercentage = 0.8;
            }
            else{
                spacePercentage = 0.85;
            }
        });
    }

    private Parent createContent() {
        StackPane root = new StackPane();

        HBox hboxTop = addHBoxWithLogo();

        HBox hboxBottomText = addHBoxWithText();
        Pane paneBottomImage = addPaneWithImage();

        hboxBottomText.getChildren().add(paneBottomImage);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hboxTop);
        borderPane.setBottom(hboxBottomText);
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
        hbox.setAlignment(Pos.CENTER_RIGHT);

        Label character = new Label("Your Card: ");
        character.setTextFill(Color.WHITE);

        Font font = Font.font("Times New Roman Bold", FontWeight.BOLD, 26);
        character.setFont(font);

        character.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(character, Priority.ALWAYS);

        hbox.getChildren().add(character);
        hbox.widthProperty().addListener((_, _, newVal) -> {
            double padding = newVal.doubleValue() * spacePercentage;
            hbox.setPadding(new Insets(0, 0, 0, padding));
        });
        return hbox;
    }

    private Pane addPaneWithImage() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: transparent;");

        Image image = new Image(Objects.requireNonNull(GameGUI.class.getResourceAsStream("/images/cards/Card_Back.png")));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(60);
        imageView.setPreserveRatio(true);
        imageView.setRotate(-5);

        imageView.setLayoutX(-15);
        imageView.setLayoutY(-5);

        pane.setPrefSize(imageView.getFitWidth() + 60, imageView.getFitHeight() + 100);

        pane.getChildren().addAll(imageView);

        return pane;
    }
}
