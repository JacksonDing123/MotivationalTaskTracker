module com.example.motivationaltasktracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.motivationaltasktracker to javafx.fxml;
    exports com.example.motivationaltasktracker;
}