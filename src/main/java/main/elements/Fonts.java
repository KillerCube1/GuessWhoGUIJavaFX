package main.elements;

import javafx.scene.text.Font;

public class Fonts {

    public static Font loadFont(String font, double fontSize) {
        return Font.loadFont(Fonts.class.getResourceAsStream("/fonts/obelixprob-cyr.ttf"), fontSize);
    }

}
