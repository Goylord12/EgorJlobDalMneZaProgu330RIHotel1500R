module com.example.generator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.generator to javafx.fxml;
    exports com.example.generator;
}