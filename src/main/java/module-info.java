module com.example.proyectoiii {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectoiii to javafx.fxml;
    exports com.example.proyectoiii;
}