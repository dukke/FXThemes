package com.pixelduke.samples.window;

import com.pixelduke.transit.Style;
import com.pixelduke.transit.TransitStyleClass;
import com.pixelduke.transit.TransitTheme;
import com.pixelduke.window.ThemeWindowManager;
import com.pixelduke.window.ThemeWindowManagerFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DarkThemeSample extends Application {

    private final String darkModeOnLabel = "Switch to Dark mode";
    private final String lightModeOnLabel = "Switch to Light mode";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ThemeWindowManager themeWindowManager = ThemeWindowManagerFactory.create();

        // Create a simple JavaFX window
        StackPane root = new StackPane();
        root.getStyleClass().add(TransitStyleClass.BACKGROUND);
        Button button = new Button(darkModeOnLabel);
        root.getChildren().add(button);

        Scene scene = new Scene(root, 400, 200);

        // TransitTheme is just used here for aesthetic purposes. Not necessary for this sample
        TransitTheme transitTheme = new TransitTheme(Style.LIGHT);
        transitTheme.setScene(scene);

        button.setOnAction(event -> {
            if (button.getText().equals(darkModeOnLabel)) {
                transitTheme.setStyle(Style.DARK);
                themeWindowManager.setDarkModeForWindowFrame(primaryStage, true);
                button.setText(lightModeOnLabel);
            } else {
                transitTheme.setStyle(Style.LIGHT);
                themeWindowManager.setDarkModeForWindowFrame(primaryStage, false);
                button.setText(darkModeOnLabel);
            }
        });

        primaryStage.setTitle("True Dark Mode");
        primaryStage.setScene(scene);

        primaryStage.show();

    }
}

