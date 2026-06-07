package com.example.fafa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class TrenApp extends Application {
    public static Stage primaryStage;
    public static Scene exerciseGroups;
    public static Scene users;
    public static Scene records;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        exerciseGroups = createScene("exercise-group-view.fxml");
        users = createScene("user-view.fxml");
        records = createScene("record-view.fxml");

        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(675);
        primaryStage.setTitle("Система учета тренировок");
        primaryStage.getIcons().add(new Image(TrenApp.class.getResourceAsStream("Tren.png")));

        exerciseGroups.getStylesheets().add("base-styles.css");
        users.getStylesheets().add("base-styles.css");
        records.getStylesheets().add("base-styles.css");

        primaryStage.setScene(exerciseGroups);
        primaryStage.show();
    }

    private Scene createScene(String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TrenApp.class.getResource(name));
        return new Scene(fxmlLoader.load());
    }

    public static void main(String[] args) {
        launch();
    }
}