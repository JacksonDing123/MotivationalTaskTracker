module com.example.motivationaltasktracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.example.motivationaltasktracker to javafx.fxml;
    exports com.example.motivationaltasktracker;
}