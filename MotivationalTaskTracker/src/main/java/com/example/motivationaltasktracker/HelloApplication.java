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
import java.util.ArrayList;

public class HelloApplication extends Application {

    private int taskIDSet = 0;
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

        Button createTask = new Button("Create Task");

        // Create a TextField for user input
        TextField name = new TextField();
        name.setPromptText("Name");

        // Create a TextField for user input
        TextField date = new TextField();
        date.setPromptText("Date");

        // Create a TextField for user input
        TextField month = new TextField();
        month.setPromptText("month");

        // Create a TextField for user input
        TextField difficulty = new TextField();
        difficulty.setPromptText("Difficulty");

        root.getChildren().addAll(createTask, name, date, month, difficulty);

        createTask.setOnAction(event -> {
            Tasks.taskList.add(new Tasks(name.getText(), Integer.parseInt(date.getText()), Integer.parseInt(month.getText()), false, Integer.parseInt(difficulty.getText()), taskIDSet++));
            Tasks.taskList.get(Tasks.taskList.size()-1).showTask(root);
            name.clear();
            date.clear();
            month.clear();
            difficulty.clear();
        });



        // Show the Stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}