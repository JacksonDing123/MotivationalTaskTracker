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
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    public static Stage stage;
    public static Button createButton = new Button();
    public static FXMLLoader fxmlLoaderMain = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    public static FXMLLoader fxmlLoaderCreate = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    //create panes
    public static Pane mainPane = new Pane();
    public static Pane createPane = new Pane();
    //create 2 scenes 
    public static Scene mainScene = new Scene(mainPane,1000,800);
    public static Scene createScene = new Scene (createPane,600,400); 
    
    @Override
<<<<<<< HEAD
    public void start(Stage stage) throws IOException {
        stage.setTitle("Motivational Tracker");
        stage.setScene(mainScene);
        mainScene = new Scene(fxmlLoaderMain.load(), 1000, 800);
        createScene = new Scene(fxmlLoaderCreate.load(), 600, 400);
        stage.show();
    
}
public void interactiveButtons() {
    //use mouse entered and exited to change between 2 colours to show behind the transparent icon images
    //rulesCircle.setOnMouseEntered((MouseEvent event) -> {
    //     rulesCircle.setFill(Color.web("#d9b3ff"));
    // });
=======
    public void start(Stage primaryStage) throws IOException {

        ArrayList<Tasks> taskList = new ArrayList<Tasks>();

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
        TextField difficulty = new TextField();
        difficulty.setPromptText("Difficulty");

        root.getChildren().addAll(createTask, name, date, difficulty);

        createTask.setOnAction(event -> {
            taskList.add(new Tasks(name.getText(), Integer.parseInt(date.getText()), false, Integer.parseInt(difficulty.getText())));
            taskList.get(taskList.size()-1).showTask(root);
            name.clear();
            date.clear();
            difficulty.clear();
        });

        // Show the Stage
        primaryStage.show();
    }
>>>>>>> Jackson

    //rulesCircle.setOnMouseExited((MouseEvent event) -> {
    //     rulesCircle.setFill(Color.web("#ffffff"));
    // });
    }
public static void actions() {

    createButton.setOnMouseClicked(event -> {

    stage.setScene(createScene);

    stage.centerOnScreen();

    });
}
public static void createIcons() {

    createButton.setTranslateX(200);
    createButton.setTranslateY(100);
    createButton.setMinWidth(100);
    createButton.setMinHeight(50);
    mainPane.getChildren().add(createButton);
}
    public static void main(String[] args) {
        launch();
        createIcons();
    }
}