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
import javafx.stage.Stage;

public class Windows11CornerSample  extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Win11ThemeWindowManager win11ThemeWindowManager = (Win11ThemeWindowManager) ThemeWindowManagerFactory.create();

        System.out.println("OS : " + System.getProperty("os.name"));

        // Create radio buttons
        RadioButton rectangularRadioButton = new RadioButton("Rectangular");
        RadioButton roundRadioButton = new RadioButton("Round");
        RadioButton roundSmallRadioButton = new RadioButton("Round Small");

        rectangularRadioButton.setOnAction(event -> win11ThemeWindowManager.setWindowCornerPreference(primaryStage, Win11ThemeWindowManager.CornerPreference.RECTANGULAR));

        roundRadioButton.setOnAction(event -> win11ThemeWindowManager.setWindowCornerPreference(primaryStage, Win11ThemeWindowManager.CornerPreference.ROUND));

        roundSmallRadioButton.setOnAction(event -> win11ThemeWindowManager.setWindowCornerPreference(primaryStage, Win11ThemeWindowManager.CornerPreference.ROUND_SMALL));

        // Create a toggle group to manage radio button selection
        ToggleGroup toggleGroup = new ToggleGroup();
        rectangularRadioButton.setToggleGroup(toggleGroup);
        roundRadioButton.setToggleGroup(toggleGroup);
        roundSmallRadioButton.setToggleGroup(toggleGroup);

        // Create an HBox to display radio buttons horizontally
        HBox hbox = new HBox(10); // 10 is the spacing between radio buttons
        hbox.getChildren().addAll(rectangularRadioButton, roundRadioButton, roundSmallRadioButton);

        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hbox, 600, 400);

        TransitTheme transitTheme = new TransitTheme(Style.LIGHT);
        transitTheme.setScene(scene);


        primaryStage.setTitle("Window Corner Sample");
        primaryStage.setScene(scene);


        primaryStage.show();
    }
}