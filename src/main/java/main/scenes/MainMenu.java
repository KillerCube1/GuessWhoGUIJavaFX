package main.scenes;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import main.MainApp;
import main.elements.Fonts;
import main.elements.ScrollingBackground;

import java.util.Objects;

public class MainMenu extends Screen {

    public MainMenu() {
        super(new StackPane());
    }

    @Override
    public Parent start() {
        Stop[] stops = new Stop[]{
                new Stop(0, Color.rgb(17, 85, 204)),
                new Stop(1, Color.rgb(0, 164, 255))
        };

        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, null, stops);

        // Set up the Top and Bottom bars
        Rectangle TopBar = new Rectangle();
        Rectangle BottomBar = new Rectangle();
        TopBar.setFill(gradient);
        BottomBar.setFill(gradient);

        Rectangle BackTopBar = new Rectangle();
        Rectangle BackBottomBar = new Rectangle();
        BackTopBar.setFill(Color.WHITE);
        BackBottomBar.setFill(Color.WHITE);

        TopBar.heightProperty().bind(MainApp.getStage().heightProperty().multiply(0.12));
        TopBar.widthProperty().bind(MainApp.getStage().widthProperty());
        StackPane.setAlignment(TopBar, Pos.TOP_CENTER);

        BackTopBar.heightProperty().bind(MainApp.getStage().heightProperty().multiply(0.13));
        BackTopBar.widthProperty().bind(MainApp.getStage().widthProperty());
        StackPane.setAlignment(BackTopBar, Pos.TOP_CENTER);

        BottomBar.heightProperty().bind(MainApp.getStage().heightProperty().multiply(0.12));
        BottomBar.widthProperty().bind(MainApp.getStage().widthProperty());
        StackPane.setAlignment(BottomBar, Pos.BOTTOM_CENTER);

        BackBottomBar.heightProperty().bind(MainApp.getStage().heightProperty().multiply(0.13));
        BackBottomBar.widthProperty().bind(MainApp.getStage().widthProperty());
        StackPane.setAlignment(BackBottomBar, Pos.BOTTOM_CENTER);

        // Set up logo at the Top
        Image logoImage = new Image(Objects.requireNonNull(Loading.class.getResourceAsStream("/images/Logo.png")));
        ImageView logo = new ImageView(logoImage);
        logo.setPreserveRatio(true);
        logo.fitWidthProperty().bind(MainApp.getStage().widthProperty().multiply(0.35));
        logo.fitHeightProperty().bind(MainApp.getStage().heightProperty().multiply(0.25));
        StackPane.setAlignment(logo, Pos.TOP_CENTER);

        // Set up menu name
        Pane menuTextPane = new Pane();

        Text menuText = new Text("Main Menu");

        menuText.setFill(Color.WHITE);
        menuText.fontProperty().bind(Bindings.createObjectBinding(() -> {
            double size = getHeight() / 20; // Adjust the divisor to control the shrinking rate
            return Fonts.loadFont("/fonts/obelixprob-cyr.ttf", size);
        }, heightProperty()));

        menuText.xProperty().bind(menuTextPane.widthProperty().multiply(0.01));
        menuText.yProperty().bind(menuTextPane.heightProperty().multiply(0.089));

        menuTextPane.getChildren().add(menuText);

        // Set up play button
        Image playImage = new Image(Objects.requireNonNull(Loading.class.getResourceAsStream("/images/Play_Image.jpg")));
        ImageView playButtonImage = new ImageView(playImage);
        playButtonImage.setPreserveRatio(true);
        playButtonImage.fitWidthProperty().bind(MainApp.getStage().widthProperty().multiply(0.5));
        playButtonImage.fitHeightProperty().bind(MainApp.getStage().heightProperty().multiply(0.5));
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.3);
        playButtonImage.setEffect(colorAdjust);

        Rectangle playButtonStroke = new Rectangle();
        playButtonStroke.setFill(null);
        playButtonStroke.setStroke(Color.WHITE);
        playButtonStroke.setStrokeWidth(5);
        playButtonStroke.widthProperty().bind(playButtonImage.widthProperty());
        playButtonStroke.heightProperty().bind(playButtonImage.heightProperty());

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.WHITE);
        dropShadow.setRadius(3);
        playButtonStroke.setEffect(dropShadow);

        getChildren().add(new ScrollingBackground(MainApp.getStage(), "/images/Background.png", 1.5));
        getChildren().addAll(BackTopBar, BackBottomBar);
        getChildren().addAll(TopBar, BottomBar);
        getChildren().addAll(logo, menuTextPane);
        getChildren().add(playButtonStroke);
        getChildren().add(playButtonImage);

        return this;
    }

}
