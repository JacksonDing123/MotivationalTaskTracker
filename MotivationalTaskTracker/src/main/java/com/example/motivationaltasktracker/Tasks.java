package com.example.motivationaltasktracker;

public class Tasks {
    private String name;
    private String date;
    private boolean isDone;
    private int difficulty;

    public Tasks(String name, String date, boolean isDone, int difficulty){
        this.date = date;
        this.name = name;
        this.isDone = isDone;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
