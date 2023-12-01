package com.example.motivationaltasktracker;

import javafx.scene.control.Label;

public class Tasks {
    private String name;
    private int month;
    private int date;
    private boolean isDone;
    private int difficulty;

    public Tasks(String name, int date, boolean isDone, int difficulty){
        this.date = date;
        this.name = name;
        this.isDone = isDone;
        this.difficulty = difficulty;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void showTask(){
        // Create a Label for task information
        Label taskLabel = new Label("Task: Complete JavaFX Tutorial");

        // Create a CheckBox for task completion status
        CheckBox doneCheckBox = new CheckBox("Done");

        // Create an HBox to hold the Label and CheckBox
        HBox hbox = new HBox(10); // 10 is the spacing between elements
        hbox.setAlignment(Pos.CENTER); // Center the elements horizontally
        hbox.getChildren().addAll(taskLabel, doneCheckBox);
    }
}
