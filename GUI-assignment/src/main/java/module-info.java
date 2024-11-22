module com.example.guiassignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.guiassignment to javafx.fxml;
    exports com.example.guiassignment;
}