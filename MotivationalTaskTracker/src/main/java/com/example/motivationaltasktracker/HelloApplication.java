package com.example.motivationaltasktracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Create a Button
        Button button = new Button("Click me!");

        // Create a StackPane (a simple layout pane)
        StackPane root = new StackPane();
        root.getChildren().add(button);

        // Create a Scene and set the root node
        Scene scene = new Scene(root, 300, 200);

        // Set the Scene to the Stage
        primaryStage.setScene(scene);

        // Set the title of the Stage
        primaryStage.setTitle("Simple JavaFX App");

        // Show the Stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}