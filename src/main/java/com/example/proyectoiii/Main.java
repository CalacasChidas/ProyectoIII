package com.example.proyectoiii;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.proyectoiii.Controller.drawGraph;
import static com.example.proyectoiii.Controller.generateGraph;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setTitle("Random Graph");
        primaryStage.show();
        generateGraph();
        drawGraph(root);
    }

}