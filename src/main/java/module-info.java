module com.example.poesudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.poesudoku to javafx.fxml;
    opens com.example.poesudoku.controller to javafx.fxml;
    exports com.example.poesudoku;
}