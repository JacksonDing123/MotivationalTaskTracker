package com.example.motivationaltasktracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        // Create a StackPane (a simple layout pane)
        VBox root = new VBox();

        // Create a Scene and set the root node
        Scene scene = new Scene(root, 300, 200);

        // Set the Scene to the Stage
        primaryStage.setScene(scene);

        // Set the title of the Stage
        primaryStage.setTitle("Simple JavaFX App");

        root.setAlignment(Pos.TOP_CENTER);

        // Create a Label for task information
        Label title = new Label("Motivational Task Tracker");
        root.getChildren().add(title);

        Tasks task1 = new Tasks("task 1", 13, true, 3);
        task1.showTask(root);

        Button createTask = new Button("Create Task");

        // Create a TextField for user input
        TextField name = new TextField();
        name.setPromptText("Name");

        // Create a TextField for user input
        TextField date = new TextField();
        date.setPromptText("Date");

        // Create a TextField for user input
        TextField month = new TextField();
        month.setPromptText("Month");

        root.getChildren().addAll(createTask, name, date, month);

        // Show the Stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}