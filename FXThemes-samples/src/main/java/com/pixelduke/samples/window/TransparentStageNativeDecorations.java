package com.pixelduke.samples.window;

import com.pixelduke.transit.Style;
import com.pixelduke.transit.TransitStyleClass;
import com.pixelduke.transit.TransitTheme;
import com.pixelduke.window.ThemeWindowManagerFactory;
import com.pixelduke.window.Win11ThemeWindowManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TransparentStageNativeDecorations extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Win11ThemeWindowManager themeWindowManager = (Win11ThemeWindowManager) ThemeWindowManagerFactory.create();

        // Create a simple JavaFX window
        StackPane root = new StackPane();
        root.getStyleClass().add(TransitStyleClass.BACKGROUND);
        Button button = new Button("Add native Window decorations");
        root.getChildren().add(button);

        Scene scene = new Scene(root, 400, 200);

        // TransitTheme is just used here for aesthetic purposes. Not necessary for this sample
        TransitTheme transitTheme = new TransitTheme(Style.LIGHT);
        transitTheme.setScene(scene);

        button.setOnAction(event -> {
            themeWindowManager.setWindowCornerPreference(primaryStage, Win11ThemeWindowManager.CornerPreference.ROUND);
        });

        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setTitle("Transparent Stage With Native Decorations");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}