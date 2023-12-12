package com.example.motivationaltasktracker;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Tasks {
    private String name;
    private long month;
    private long date;
    private boolean isDone;
    private long difficulty;

    private long taskID;

    private CheckBox doneCheckBox;

    private Label display;

    // Create an HBox to hold the Label and CheckBox
    HBox hbox;

    public static ArrayList<Tasks> taskList = new ArrayList<Tasks>();

    public Tasks(String name, long date, long month, boolean isDone, long difficulty, long taskID){
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

    public long getMonth() {
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

    public long getDate() {
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

    public long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public long getTaskID() {
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

    public static Tasks getTask(long taskID){
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

            JSONObject tempInfo = new JSONObject();
            tempInfo.put("Name", taskList.get(i).getName());
            tempInfo.put("Month", taskList.get(i).getMonth());
            tempInfo.put("Date", taskList.get(i).getDate());
            tempInfo.put("IsDone", taskList.get(i).isDone());
            tempInfo.put("Difficulty", taskList.get(i).getDifficulty());
            tempInfo.put("taskID", taskList.get(i).getTaskID());

            //JSONObject tempTask = new JSONObject();
            //tempTask.put("Task", tempInfo);

            taskListJson.add(tempInfo);
        }

        try{
            //We can write any JSONArray or JSONObject instance to the file
            file.write(taskListJson.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readTasks(Pane pane){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("/Users/jacksonding/Documents/AP CompSCi/MotivationalTaskTracker/MotivationalTaskTracker/src/main/java/com/example/motivationaltasktracker/info.json"))
        {

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray taskList = (JSONArray) obj;

            if (taskList instanceof JSONArray) {
//                JSONObject jsonObject = (JSONObject) obj;
                taskList.forEach( emp -> parseTaskObject( (JSONObject) emp, pane ) );//emp is a array
            } else {
                System.out.println("The file does not contain a JSON object.");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseTaskObject(JSONObject thisTask, Pane pane)
    {
        //JSONObject thisTask = (JSONObject) task.get("Task");

        long tdate = (long) thisTask.get("Date");

        boolean tisDone = (boolean) thisTask.get("IsDone");

        String tname = (String) thisTask.get("Name");

        long tmonth = (long) thisTask.get("Month");

        long tdifficulty = (long) thisTask.get("Difficulty");

        long ttaskID = (long) thisTask.get("taskID");

        Tasks tempTask = new Tasks( tname, tdate, tmonth, tisDone, tdifficulty, ttaskID);

        taskList.add(tempTask);

        tempTask.showTask(pane);
    }
}
