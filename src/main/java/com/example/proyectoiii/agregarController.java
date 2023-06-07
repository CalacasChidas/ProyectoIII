package com.example.proyectoiii;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class agregarController {

    @FXML
    private ChoiceBox<String> tipos;
    @FXML
    private ChoiceBox <String> ordenarPor;
    @FXML
    private Label velocidadLabel;
    @FXML
    private Label eficienciaLabel;
    @FXML
    private Label fortalezaLabel;

    Avion[] avionesList = new Avion[3];
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
        if (ordenarPor.getValue() != null){
            if (ordenarPor.getValue().equals("Velocidad")) {
                System.out.println("Ordenado por velocidad");
                //InsertionSort
            }else {
                System.out.println("Ordenado por eficiencia");
                //ShellSort
            }
        }else {
            System.out.println("No se ha seleccionado como se desea ordenar");
        }
    }

    public void showInfo(){
        if (tipos.getValue() != null) {
            for (int i = 0; i < avionesList.length; i++) {
                if (tipos.getValue().equals(avionesList[i].getNombre())) {
                    velocidadLabel.setText(String.valueOf(avionesList[i].getVelocidad()));
                    eficienciaLabel.setText(String.valueOf(avionesList[i].getEficiencia()));
                    fortalezaLabel.setText(String.valueOf(avionesList[i].getFortaleza()));
                    break;
                }
            }
        }
    }
    public void agregarAvion(){

    }

}
