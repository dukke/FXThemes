package com.pixelduke.samples.window;

import com.pixelduke.transit.Style;
import com.pixelduke.transit.TransitStyleClass;
import com.pixelduke.transit.TransitTheme;
import com.pixelduke.window.ThemeWindowManager;
import com.pixelduke.window.ThemeWindowManagerFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class DarkThemeSampleMac extends Application {

    private final String darkModeOnLabel = "Switch to Dark mode";
    private final String lightModeOnLabel = "Switch to Light mode";

    public static void main(String[] args) {
        launch(args);
    }

    private static ThemeWindowManager themeWindowManager;

    @Override
    public void start(Stage stage) {
        themeWindowManager = ThemeWindowManagerFactory.create();
        stage.setOnShown((windowEvent -> {
            System.out.println("ThemeWindowManager: " + themeWindowManager.getClass().getName());
            themeWindowManager.setDarkModeForWindowFrame(stage, false);
        }));

        // Note 1: must use UNIFIED style for effect to work.
        stage.initStyle(StageStyle.UNIFIED);

        Button button = new Button(darkModeOnLabel);
        button.setOnAction((actionEvent -> {
            boolean isDark = darkModeOnLabel.equals(button.getText());
            button.setText(isDark ? lightModeOnLabel : darkModeOnLabel);
            themeWindowManager.setDarkModeForWindowFrame(stage, isDark);
        }));
        stage.setTitle("DarkThemeSampleMac");
        var root = new StackPane(button);
        var scene = new Scene(root, 640, 480);

        // Note 2: Must use a transparent fill of the scene & root pane's background's alpha channel to work.
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        scene.getRoot().setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }
}
