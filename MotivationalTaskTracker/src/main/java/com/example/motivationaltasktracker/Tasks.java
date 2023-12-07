package com.example.motivationaltasktracker;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Tasks {
    private String name;
    private int month;
    private int date;
    private boolean isDone;
    private int difficulty;

    private int taskID;

    private CheckBox doneCheckBox;

    private Label display;

    // Create an HBox to hold the Label and CheckBox
    HBox hbox;

    public static ArrayList<Tasks> taskList = new ArrayList<Tasks>();

    public Tasks(String name, int date, int month, boolean isDone, int difficulty, int taskID){
        this.date = date;
        this.name = name;
        this.month = month;
        this.isDone = isDone;
        this.difficulty = difficulty;
        this.taskID = taskID;
        doneCheckBox = new CheckBox();
        doneCheckBox.setSelected(isDone);
        display = new Label(this.name + " date: " + this.date + "/" + this.month + " difficulty: " + this.difficulty);
        hbox = new HBox(10); // 10 is the spacing between elements
        hbox.setAlignment(Pos.CENTER); // Center the elements horizontally
        hbox.getChildren().addAll(display, doneCheckBox);
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

    public int getTaskID() {
        return taskID;
    }

    public void showTask(Pane pane){
        pane.getChildren().add(hbox);

        doneCheckBox.setOnAction(event -> {
            if (doneCheckBox.isSelected()) {
                pane.getChildren().remove(hbox);
                taskList.remove(Tasks.getTask(this.taskID));
                try {
                    Tasks.updateTaskList();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                //moticational Quotes
            }
        });
    }

    public static Tasks getTask(int taskID){
        for(int i = 0; i<taskList.size(); i++){
            if(taskList.get(i).getTaskID()==taskID){
                return taskList.get(i);
            }
        }
        return null;
    }

    public static void updateTaskList() throws IOException{

        //JSONArray taskListJson = new JSONArray();
        FileWriter file = new FileWriter("/Users/jacksonding/Documents/AP CompSCi/MotivationalTaskTracker/MotivationalTaskTracker/src/main/java/com/example/motivationaltasktracker/info.json");

        JSONArray taskListJson = new JSONArray();

        for(int i = 0; i< taskList.size(); i++){

            JSONObject temp = new JSONObject();
            temp.put("Name", taskList.get(i).getName());
            temp.put("Month", taskList.get(i).getMonth());
            temp.put("Date", taskList.get(i).getDate());
            temp.put("IsDone", taskList.get(i).isDone());
            temp.put("Difficulty", taskList.get(i).getDifficulty());
            temp.put("taskID", taskList.get(i).getTaskID());
            taskListJson.add(temp);
        }

        try{
            //We can write any JSONArray or JSONObject instance to the file
            file.write(taskListJson.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
