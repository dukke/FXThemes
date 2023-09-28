package com.pixelduke.samples.window;

import com.pixelduke.transit.Style;
import com.pixelduke.transit.TransitStyleClass;
import com.pixelduke.transit.TransitTheme;
import com.pixelduke.window.ThemeWindowManagerFactory;
import com.pixelduke.window.Win11ThemeWindowManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChangeWindowColorsSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Win11ThemeWindowManager themeWindowManager = (Win11ThemeWindowManager) ThemeWindowManagerFactory.create();

        StackPane root = new StackPane();

        // Create a GridPane to arrange the ColorPickers and labels.
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        // Create a label for "Text" and a corresponding ColorPicker.
        Label textLabel = new Label("Text:");
        ColorPicker textColorPicker = new ColorPicker();
        textColorPicker.setOnAction(event -> {
            Color color = textColorPicker.getValue();
            themeWindowManager.setWindowTextColor(primaryStage, color);
        });

        // Create a label for "Framer" and a corresponding ColorPicker.
        Label framerLabel = new Label("Frame:");
        ColorPicker framerColorPicker = new ColorPicker();
        framerColorPicker.setOnAction(event -> {
            Color color = framerColorPicker.getValue();
            themeWindowManager.setWindowFrameColor(primaryStage, color);
        });

        // Create a label for "Border" and a corresponding ColorPicker.
        Label borderLabel = new Label("Border:");
        ColorPicker borderColorPicker = new ColorPicker();
        borderColorPicker.setOnAction(event -> {
            Color color = borderColorPicker.getValue();
            themeWindowManager.setWindowBorderColor(primaryStage, color);
        });

        // Add labels and ColorPickers to the GridPane.
        gridPane.add(textLabel, 0, 0);
        gridPane.add(textColorPicker, 0, 1);
        gridPane.add(framerLabel, 1, 0);
        gridPane.add(framerColorPicker, 1, 1);
        gridPane.add(borderLabel, 2, 0);
        gridPane.add(borderColorPicker, 2, 1);


        root.getChildren().add(gridPane);
        StackPane.setAlignment(gridPane, Pos.CENTER);

        // Create a scene with the GridPane.
        Scene scene = new Scene(root, 500, 250);

        root.getStyleClass().add(TransitStyleClass.BACKGROUND);

        TransitTheme transitTheme = new TransitTheme(Style.DARK);
        transitTheme.setScene(scene);

        primaryStage.setTitle("Window Frame Colors");
        primaryStage.setScene(scene);


        primaryStage.show();

        themeWindowManager.setDarkModeForWindowFrame(primaryStage, true);
    }
}
