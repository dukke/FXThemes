package com.pixelduke.samples.window;

import com.pixelduke.transit.Style;
import com.pixelduke.transit.TransitTheme;
import com.pixelduke.window.ThemeWindowManager;
import com.pixelduke.window.ThemeWindowManagerFactory;
import com.pixelduke.window.Win10ThemeWindowManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Windows10BackdropSample extends Application {

    public static void main(String[] args) {
//        System.setProperty("prism.forceUploadingPainter", "true");
//        System.setProperty("prism.order", "sw");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ThemeWindowManager themeWindowManager = ThemeWindowManagerFactory.create();

        StackPane root = new StackPane();

        root.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(root, 400, 200);

        scene.setFill(Color.TRANSPARENT);

//        TransitTheme transitTheme = new TransitTheme(Style.LIGHT);
//        transitTheme.setScene(scene);

        Button button = new Button("Change");
        root.getChildren().add(button);


        primaryStage.setTitle("Blured Window background");
        primaryStage.initStyle(StageStyle.UNIFIED);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);  // Works
        primaryStage.setScene(scene);

        primaryStage.show();

        Win10ThemeWindowManager win10ThemeWindowManager = (Win10ThemeWindowManager) themeWindowManager;
        win10ThemeWindowManager.enableAcrylic(primaryStage, 100, 0x990500);

        button.setOnAction(event -> win10ThemeWindowManager.enableAcrylic(primaryStage, 100, 0x990500));
    }
}


