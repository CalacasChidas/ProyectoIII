package com.example.proyectoiii;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;
import java.util.List;

public class agregarController {

    @FXML
    private ChoiceBox<String> tipos;
    @FXML
    private ChoiceBox <String> ordenarPor;

    Avion[] avionesList = new Avion[2];
    List<String> tiposAviones = new ArrayList<>();

    public void loadItems(){
        tiposAviones.add("F18 SuperHornett");
        tiposAviones.add("F22 Raptor");
        tiposAviones.add("C130H Hercules");
        tipos.getItems().addAll(tiposAviones);
        ordenarPor.getItems().addAll("Velocidad", "Eficiencia");
        avionesList[0] = new Avion("F18 SuperHornett", 9,7,6);
        avionesList[1] = new Avion("F22 Raptor", 10, 8,10);
        avionesList[2] = new Avion("C130H Hercules", 5,10,10);
    }

    public void ordenar(){
        if (ordenarPor.getValue().equals("Velocidad")) {
            //InsertionSort

        }if (ordenarPor.getValue().equals("Eficiencia")){
            //ShellSort
        }else {
            System.out.println("No se ha seleccionado como se desea ordenar");
        }
    }

}
