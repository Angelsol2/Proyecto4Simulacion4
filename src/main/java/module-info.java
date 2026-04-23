module org.example.proyecto4simulacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.proyecto4simulacion to javafx.fxml;
    exports org.example.proyecto4simulacion;
}