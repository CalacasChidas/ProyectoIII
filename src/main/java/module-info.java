module com.example.proyectoiii {
    requires javafx.controls;
    requires javafx.fxml;
    requires jssc;


    opens com.example.proyectoiii to javafx.fxml;
    exports com.example.proyectoiii;
}