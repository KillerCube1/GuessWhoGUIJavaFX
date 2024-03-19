package main;

import javafx.application.Platform;
import javafx.scene.text.Text;

public class LoadResources {

    // This method is called when the loading screen begins
    // (Resources are loaded from inside here)
    public static void beginLoading(Text loadingText) {
        new Thread(() -> {
            // Started Loading
            System.out.println("started loading..");
            try {
                Thread.sleep(1000);
                updateLoadingText(loadingText, "BOOTKAS TAKING ALL RESOURCES");
                Thread.sleep(1000);

                Platform.runLater(() -> MainApp.setScreen("MainMenu"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private static void updateLoadingText(Text loadingText, String text) {
        Platform.runLater(() -> loadingText.setText(text));
    }

}
