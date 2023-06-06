module com.example.calculator_laba4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calculator_laba4 to javafx.fxml;
    exports com.example.calculator_laba4;
}