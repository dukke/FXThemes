package com.pixelduke.samples.window;

import com.pixelduke.transit.Style;
import com.pixelduke.transit.TransitTheme;
import com.pixelduke.window.ThemeWindowManagerFactory;
import com.pixelduke.window.Win11ThemeWindowManager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowBackdropSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Win11ThemeWindowManager win11ThemeWindowManager = (Win11ThemeWindowManager) ThemeWindowManagerFactory.create();
//        System.setProperty("prism.forceUploadingPainter", "true");    DOESNT SEEM TO WORK

        System.out.println("OS : " + System.getProperty("os.name"));

        // Create radio buttons
        RadioButton noneRadioButton = new RadioButton("None");
        RadioButton micaRadioButton = new RadioButton("Mica");
        RadioButton micaAltRadioButton = new RadioButton("Mica Alt");
        RadioButton acrylicRadioButton = new RadioButton("Acrylic");

//        micaAltRadioButton.setSelected(true);

        noneRadioButton.setOnAction(event -> win11ThemeWindowManager.setWindowBackdrop(primaryStage, Win11ThemeWindowManager.Backdrop.NONE));

        micaRadioButton.setOnAction(event -> win11ThemeWindowManager.setWindowBackdrop(primaryStage, Win11ThemeWindowManager.Backdrop.MICA));

        micaAltRadioButton.setOnAction(event -> win11ThemeWindowManager.setWindowBackdrop(primaryStage, Win11ThemeWindowManager.Backdrop.MICA_ALT));

        acrylicRadioButton.setOnAction(event -> win11ThemeWindowManager.setWindowBackdrop(primaryStage, Win11ThemeWindowManager.Backdrop.ACRYLIC));

        // Create a toggle group to manage radio button selection
        ToggleGroup toggleGroup = new ToggleGroup();
        noneRadioButton.setToggleGroup(toggleGroup);
        micaRadioButton.setToggleGroup(toggleGroup);
        micaAltRadioButton.setToggleGroup(toggleGroup);
        acrylicRadioButton.setToggleGroup(toggleGroup);

        // Create an HBox to display radio buttons horizontally
        HBox hbox = new HBox(10); // 10 is the spacing between radio buttons
        hbox.getChildren().addAll(noneRadioButton, micaRadioButton, micaAltRadioButton, acrylicRadioButton);

        hbox.setAlignment(Pos.CENTER);
//        hbox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        hbox.setStyle("-fx-background-color: transparent; -fx-font-size: 1.5em;");

        Scene scene = new Scene(hbox, 600, 400);
        scene.setFill(Color.TRANSPARENT);

        TransitTheme transitTheme = new TransitTheme(Style.LIGHT);
        transitTheme.setScene(scene);


        primaryStage.setTitle("Window Frame Materials");
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setScene(scene);


        primaryStage.show();

//        enableDarkModeForWindowFrame(primaryStage, true);

    }
}