package com.example.motivationaltasktracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

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