
package com.example.motivationaltasktracker;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.Collections;

import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

//create a task class with all the parameters that each task has like the difficulty
public class Tasks {
    //Properties of a task below
    private String name;
    private long month;
    private long date;
    private boolean isDone;
    private long difficulty;

    private long taskID;

    private CheckBox doneCheckBox;

    private Label display;
    private HBox hbox;

    //create new pane for when people complete their tasks, static because it is the same across all objects
    private static VBox motivatePane = new VBox();
    private static Scene motivateScene = new Scene(motivatePane);

    //create an arraylist of the tasks static because it applies to the entire class
    public static ArrayList<Tasks> taskList = new ArrayList<Tasks>();

    //create an array list with all possible quotes so that it can randomized for each task
    public static ArrayList<String> quotes = new ArrayList<String>();{
        quotes.add("I don't stop when I'm tired, I stop when I'm done.");
        quotes.add("Everyone fails sometimes and life isn’t supposed to be fair, much less bend to your every whim");
        quotes.add("Nobody cares what you did yesterday. What have you done today");
        quotes.add("You gotta start your journey. It may suck, but eventually you will come out the other side on top.");
        quotes.add("We don’t rise to the level of our expectations, we fall to the level of our training.");
        quotes.add("No one is going to come help you. No one’s coming to save you.");
        quotes.add("There is no better way to grow as a person than to do something everyday that you hate.");
        quotes.add("You can’t use up creativity. The more you use, the more you have.");
    }

    /**
     * constructor to initalize all values and properties, including the checkbox, label, and hbox which are consisted of the other properties
     * the onchecked function is also in the constructor
     * @param name
     * @param date
     * @param month
     * @param isDone
     * @param difficulty
     * @param taskID
     * @param pane
     */
    public Tasks(String name, long date, long month, boolean isDone, long difficulty, long taskID, Pane pane){
        this.date = date;
        this.name = name;
        this.month = month;
        this.isDone = isDone;
        this.difficulty = difficulty;
        this.taskID = taskID;

         doneCheckBox = new CheckBox();
         doneCheckBox.setSelected(isDone);
         display = new Label(this.name + " | Date: " + this.date + "/" + this.month + " | Difficulty: " + this.difficulty);
         display.setStyle(
         "-fx-font-size: 15px;" + // Change font size
         "-fx-font-family: 'Roboto';"  // Change font family
         );
         hbox = new HBox(10); // 10 is the spacing between elements
         hbox.setAlignment(Pos.CENTER); // Center the elements horizontally
         hbox.getChildren().addAll(display, doneCheckBox);

        // Set the action for the checkbox
        doneCheckBox.setOnAction(event -> {
            if (doneCheckBox.isSelected()) {
                taskList.remove(this); // Remove the task from the taskList
                pane.getChildren().remove(this.getHbox());
                try {
                    updateTaskList();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                motivated(pane);
            }
        });
    }
    //getters and setters
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

    public HBox getHbox() {
        return hbox;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }

    /**
     * create a method to compare the dates of all the classes so that they can be arranged on when they must be completed
     */
    public static void sortTasks(){

        //compare the months first

        for(int i = 0; i < taskList.size()-1; i++)
        {
            int min = i;

            for(int j = i+1; j < taskList.size(); j++)
            {
                if(taskList.get(j).getMonth() < taskList.get(min).getMonth())
                    min = j;
            }

            if(min != i)
            {
                // Swap
                Tasks temp = taskList.get(i);
                taskList.set(i,taskList.get(min));
                taskList.set(min,temp);
            }
        }

        //compare the dates
        for(int i = 0; i < taskList.size()-1; i++)
        {
            int min = i;

            for(int j = i+1; j < taskList.size(); j++)
            {
                if(taskList.get(j).getDate() < taskList.get(min).getDate()&& taskList.get(j).getMonth() == taskList.get(min).getMonth()){
                    min = j;
                }

            }

            if(min != i)
            {
                // Swap
                Tasks temp = taskList.get(i);
                taskList.set(i,taskList.get(min));
                taskList.set(min,temp);
            }
        }
    }

    /**
     * Display all of the tasks in the task list
     * the sortTask function also included to reduce redundancy
     */
    public static void displayAllTask(Pane pane) {

        pane.getChildren().removeIf(node -> node instanceof HBox); //remove all hbox before hand

        sortTasks(); // Sort the tasks based on due dates

        //display the hboxes
        for (int i = 0; i<taskList.size(); i++) {
            pane.getChildren().add(taskList.get(i).getHbox());
        }
    }

    /**
     * method for opening images and returning images based in image URL
     * @param imageUrl
     * @return
     */
    private Image loadImageFromUrl(String imageUrl) {
        try {
            // Open a stream from the URL
            InputStream inputStream = new URL(imageUrl).openStream();

            // Load the image from the stream
            return new Image(inputStream);
        } catch (IOException e) {
            // Handle exceptions, e.g., if the URL is invalid
            e.printStackTrace();
            return null;
        }
    }

    /**
     * method for once a task is done
     * @param pane
     */
    public void motivated(Pane pane){

        //create a button to used by the user to go back to the appliction
        Button motivated = new Button();
        motivated.setMinHeight(25);
        motivated.setMinWidth(100);
        motivated.setText("Motivated");
        motivated.setStyle("-fx-content-display: center;");
        motivated.setStyle(
                "-fx-border-color: black;" + // Border color
                        "-fx-border-width: 2px;" + // Border width
                        "-fx-border-radius: 5px;" // Border radius (rounded corners)
        );

        //create text to hold the quote so it can be displayed
        Text displayQuotes = new Text();
        displayQuotes.setStyle(
                "-fx-font-size: 20px;" + // Change font size
                        "-fx-font-family: 'Impact';"  // Change font family
        );
        //use mosue position to change the colour of the button based on if the mouse is hovering over it
        //when the mouse is in the button make the button black and the text white
        motivated.setOnMouseEntered((MouseEvent event) -> {
            motivated.setTextFill(Color.WHITE);
            motivated.setStyle("-fx-background-color: #000000; ");
        });
        //when the mouse leaves the button return it back to its normal properties
        motivated.setOnMouseExited((MouseEvent event) -> {
            motivated.setTextFill(Color.BLACK);
            motivated.setStyle("-fx-background-color: #FFFFFF; "+"-fx-border-color: black;"
                    +"-fx-border-width: 2px;"+"-fx-border-radius: 5px;");
        });
        //get a random number betweeen 0-6 to represent the array list of quotes
        int max = 7;
        int min = 0;
        int range = max - min + 1;
        //use math class to randomly pick a number to represent a quote
        int random = (int) (Math.random()*range) + min;
        //update the text of displayQuotes to have the random quote so that it can be displayed
        displayQuotes.setText(quotes.get(random));
        //add both the quote and the motivated button to the motviated pane for when they complete a task
        motivatePane.setAlignment(Pos.CENTER);
        motivatePane.getChildren().addAll(displayQuotes, motivated);

        //create a string to the redirect of the image we want
        String imageURL = "https://www.dmarge.com/wp-content/uploads/2021/08/goggins2-1.jpg";
        //load the image using a image view and set it on the screen
        Image backgroundImage = loadImageFromUrl(imageURL);
        ImageView imageView = new ImageView(backgroundImage);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //use primaryScreenBounds to make sure that the image fills any sized window
        imageView.setFitWidth(500); // Set your preferred width
        imageView.setFitHeight(600); // Set your preferred height
        motivatePane.getChildren().add(imageView);
        //switch the the movtated scene with the image and randomized quote at the top
        HelloApplication.primaryStage.setScene(motivateScene);

        //change scenes back to the creation and main scene after the user has been motvated
        motivated.setOnAction(event -> {
            HelloApplication.primaryStage.setScene(HelloApplication.originalScene);
            HelloApplication.primaryStage.setMaximized(true);
            //displayAllTask(pane);
            motivatePane.getChildren().clear();
        });
    }

    /**
     * updates the task list and writes all of the information into the info.json file
     * @throws IOException
     */
     public static void updateTaskList() throws IOException{

         //get the file based on local path and create the required variables for writing into json
         File info = new File("./src/main/java/com/example/motivationaltasktracker/info.json");

         FileWriter file = new FileWriter(info);

         JSONArray taskListJson = new JSONArray();

         //loops through all tasks and stores their key info like name, date, and difficulty
         for(int i = 0; i< taskList.size(); i++){

             JSONObject tempInfo = new JSONObject();
             tempInfo.put("Name", taskList.get(i).getName());
             tempInfo.put("Month", taskList.get(i).getMonth());
             tempInfo.put("Date", taskList.get(i).getDate());
             tempInfo.put("IsDone", taskList.get(i).isDone());
             tempInfo.put("Difficulty", taskList.get(i).getDifficulty());
             tempInfo.put("taskID", taskList.get(i).getTaskID());

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

    /**
     * method used to read the information from the json file, used in the very start of code
     * @param pane
     */
     public static void readTasks(Pane pane){
         //JSON parser object to parse read file
         JSONParser jsonParser = new JSONParser();

         File info = new File("./src/main/java/com/example/motivationaltasktracker/info.json");

         try (FileReader reader = new FileReader(info))
         {

             //Read JSON file
             Object obj = jsonParser.parse(reader);

             JSONArray taskList = (JSONArray) obj;

             if (taskList instanceof JSONArray) {
                 //loops through the json file to get all of the jsonObjects and uses the lamda expression to call on the parseTaskObject method which will add the tasks into the actual task List
                 taskList.forEach( emp -> parseTaskObject( (JSONObject) emp, pane ) );
             } else {
                 System.out.println("The file does not contain a JSON object.");
             }

         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException | ParseException e) {
             e.printStackTrace();
         }
     }

    /**
     * records down the information of the task passed in as parameters and put it into arrayList
     * @param thisTask
     * @param pane
     */
     private static void parseTaskObject(JSONObject thisTask, Pane pane)
     {
         //JSONObject thisTask = (JSONObject) task.get("Task");

         long tdate =  (long) thisTask.get("Date");

         boolean tisDone = (boolean) thisTask.get("IsDone");

         String tname = (String) thisTask.get("Name");

         long tmonth = (long) thisTask.get("Month");

         long tdifficulty = (long) thisTask.get("Difficulty");

         long ttaskID = (long) thisTask.get("taskID");

         Tasks tempTask = new Tasks( tname, tdate, tmonth, tisDone, tdifficulty, ttaskID, pane);

         taskList.add(tempTask);
     }

}