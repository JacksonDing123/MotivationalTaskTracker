package com.example.motivationaltasktracker;

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
}
