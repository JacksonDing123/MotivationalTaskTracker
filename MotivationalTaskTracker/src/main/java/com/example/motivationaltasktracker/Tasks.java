package com.example.motvationaltasktracker;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    
    public static ArrayList<String> Quotes = new ArrayList<String>();{
        Quotes.add("I don't stop when I'm tired, I stop when I'm done.");
        Quotes.add("Everyone fails sometimes and life isn’t supposed to be fair, much less bend to your every whim");
        Quotes.add("Nobody cares what you did yesterday. What have you done today");
        Quotes.add("You gotta start your journey. It may suck, but eventually you will come out the other side on top.");
        Quotes.add("We don’t rise to the level of our expectations, we fall to the level of our training.");
        Quotes.add("No one is going to come help you. No one’s coming to save you.");
        Quotes.add("There is no better way to grow as a person than to do something everyday that you hate.");
        Quotes.add("You can’t use up creativity. The more you use, the more you have.");
    }

    public void showTask(Pane pane){
       // ImageView gog = new ImageView("file.GogginsIMG.jpg");

        Button motivated = new Button();
        motivated.setMinHeight(25);
        motivated.setMinWidth(100);
        motivated.setText("Motivated");
        motivated.setStyle("-fx-content-display: center;");
        motivated.setAlignment(Pos.BOTTOM_CENTER);
        motivated.setStyle(
        "-fx-border-color: black;" + // Border color
        "-fx-border-width: 2px;" + // Border width
        "-fx-border-radius: 5px;" // Border radius (rounded corners)
        );

        Text displayQuotes = new Text();
        displayQuotes.setStyle(
        "-fx-font-size: 20px;" + // Change font size
        "-fx-font-family: 'Impact';"  // Change font family
        );

        HBox motivation = new HBox();
        motivation.getChildren().addAll(motivated, displayQuotes);
        pane.getChildren().addAll(hbox, motivation);
        motivation.setAlignment(Pos.BOTTOM_CENTER);
        motivated.setVisible(false);
        displayQuotes.setVisible(false);

        int max = 7;
        int min = 0;
        int range = max - min + 1;
                
        motivated.setOnMouseEntered((MouseEvent event) -> {
            motivated.setTextFill(Color.WHITE); 
            motivated.setStyle("-fx-background-color: #000000; ");
        });
        
        motivated.setOnMouseExited((MouseEvent event) -> {
            motivated.setTextFill(Color.BLACK); 
            motivated.setStyle("-fx-background-color: #FFFFFF; "+"-fx-border-color: black;" 
            +"-fx-border-width: 2px;"+"-fx-border-radius: 5px;");
        });
        doneCheckBox.setOnAction(event -> {
            //motivated.setVisible(true);
            if (doneCheckBox.isSelected()) {
                pane.getChildren().remove(hbox);
                taskList.remove(Tasks.getTask(this.taskID));
                motivated.setVisible(true);
                int random = (int) (Math.random()*range) + min;
                displayQuotes.setText(Quotes.get(random));
                motivated.setVisible(true);
                displayQuotes.setVisible(true);
                // try {
                //     //Tasks.updateTaskList();
                // } catch (IOException e) {
                //     throw new RuntimeException(e);
                // }
            } else {
                //moticational Quotes
            }
        });
        motivated.setOnAction(event -> {
            motivated.setVisible(false);
            displayQuotes.setVisible(false);
        });
    }

    public static Tasks getTask(int taskID){
        for(int i = 0; i<taskList.size(); i++){
            if(taskList.get(i).getTaskID()==taskID){
                return taskList.get(i);
            }
        }
        return null;
    }}


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

            JSONObject tempTask = new JSONObject();
            tempTask.put("Task", tempInfo);

            taskListJson.add(tempTask);
        }

        try{
            //We can write any JSONArray or JSONObject instance to the file
            file.write(taskListJson.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readTasks(){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("info.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray taskList = (JSONArray) obj;
            //System.out.println(employeeList);

            //Iterate over employee array
            taskList.forEach( emp -> parseTaskObject( (JSONObject) emp ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseTaskObject(JSONObject task)
    {
        //Get employee object within list
        JSONObject thisTask = (JSONObject) task.get("task");

        //Get employee first name
        String firstName = (String) thisTask.get("firstName");
        //System.out.println(firstName);

        //Get employee last name
        String lastName = (String) thisTask.get("lastName");
        //System.out.println(lastName);

        //Get employee website name
        String website = (String) thisTask.get("website");
        //System.out.println(website);
    }
}