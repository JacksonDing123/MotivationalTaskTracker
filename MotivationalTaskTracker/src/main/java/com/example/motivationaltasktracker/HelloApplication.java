package com.example.motivationaltasktracker;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Jackson Ding, Nick Mavs, Ben Sheng, Crescent School, ICS4U
 * Version 1.19 - ALL PROBLEMS FIXED (Hopefully)
 */
public class HelloApplication extends Application {
    //declare the stage and scene that can be accessed by other classes
    public static Stage primaryStage;
    public static Scene originalScene;
    private int taskIDSet = 0;//tempoary variable to set the IDs for the tasks

    @Override
    public void start(Stage primaryStage) throws Exception {
        //initializes the primary stage and make sure its maximized to the window
        this.primaryStage = primaryStage;
        primaryStage.setMaximized(true);


        // Create a StackPane (a simple layout pane)
        VBox root = new VBox();

        // Create a Scene and set the root node and initialize for the gloabl variable
        Scene scene = new Scene(root);

        this.originalScene = scene;

        // Set the Scene to the Stage
        primaryStage.setScene(scene);

        // Set the title of the Stage
        primaryStage.setTitle("Motivational Task Tracker");

        root.setAlignment(Pos.TOP_CENTER);

        // Create a Label for task information
        Label title = new Label("Motivational Task Tracker");
        root.getChildren().add(title);
        title.setStyle(
                "-fx-font-size: 40px;" + // Change font size
                        "-fx-font-family: 'Roboto';"  // Change font family
        );

        // Create a TextField for the name of the users task
        TextField name = new TextField();
        name.setAlignment(Pos.CENTER);
        name.setPromptText("Name");
        name.setStyle(
                "-fx-font-size: 20px;" + // Change font size
                        "-fx-font-family: 'Roboto';"  // Change font family
        );

        // Create a TextField for the date a of a user's task
        TextField date = new TextField();
        date.setAlignment(Pos.CENTER);
        date.setPromptText("Date (Number Only)");
        date.setStyle(
                "-fx-font-size: 20px;" + // Change font size
                        "-fx-font-family: 'Roboto';"  // Change font family
        );

        // Create a TextField for the month of the user's task
        TextField month = new TextField();
        month.setAlignment(Pos.CENTER);
        month.setPromptText("Month (Number Only)");
        month.setStyle(
                "-fx-font-size: 20px;" + // Change font size
                        "-fx-font-family: 'Roboto';"  // Change font family
        );

        // Create a TextField for the difficulty of the users task
        TextField difficulty = new TextField();
        difficulty.setAlignment(Pos.CENTER);
        difficulty.setPromptText("Difficulty (Scale of 1 to 10)");
        difficulty.setStyle(
                "-fx-font-size: 20px;" + // Change font size
                        "-fx-font-family: 'Roboto';"  // Change font family
        );

        //create a button which makes a task using all the information entered in the text fields
        Button createTask = new Button("Create Task");
        createTask.setStyle(
                "-fx-font-size: 20px;" + // Change font size
                        "-fx-font-family: 'Roboto';"  // Change font family
        );

        //add the button and text fields to the pane
        root.getChildren().addAll(name, date, month, difficulty, createTask);
        //reads the tasks and upadtes the tasks for the JSON file
         Tasks.readTasks(root);
         Tasks.displayAllTask(root);

        //when the button to create a task is pressed create a new task and add all of the information entered
        createTask.setOnAction(event -> {
            Tasks.taskList.add(new Tasks(name.getText(), Integer.parseInt(date.getText()), Integer.parseInt(month.getText()), false, Integer.parseInt(difficulty.getText()), taskIDSet++, root));
            name.clear();
            date.clear();
            month.clear();
            difficulty.clear();
            Tasks.displayAllTask(root);
            try {
                Tasks.updateTaskList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Show the Stage
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}