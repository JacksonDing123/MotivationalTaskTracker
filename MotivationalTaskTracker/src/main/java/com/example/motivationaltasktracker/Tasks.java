package com.example.motivationaltasktracker;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.util.Timer;
import java.util.ArrayList;

public class Tasks extends HelloApplication{
    private String name;
    private int month;
    private int date;
    private boolean isDone;
    private int difficulty;

    private int taskID;

    private CheckBox doneCheckBox;

    private Label display;
    private Label displayQuotes = new Label();

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
        pane.getChildren().add(hbox);

        pane.getChildren().add(displayQuotes);
        displayQuotes.setVisible(false);



        int max = 7;
        int min = 0;
        int range = max - min + 1;

        doneCheckBox.setOnAction(event -> {
            if (doneCheckBox.isSelected()) {
                pane.getChildren().remove(hbox);
                int random = (int) (Math.random()*range) + min;
                displayQuotes.setText(Quotes.get(random));
                displayQuotes.setVisible(true);
                //motivated.setVisible(true);

                
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
}
