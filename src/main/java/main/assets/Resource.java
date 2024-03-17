package main.assets;

import main.scenes.Screen;
import java.util.HashMap;

import main.scenes.Loading;

public class Resource {

    private static HashMap<String, Screen> scenes;

    public static void loadScenes() {

        scenes = new HashMap<>();

        scenes.put("Loading", new Loading());

    }

    public static Screen getScene(String key) {
        return scenes.get(key);
    }

}
