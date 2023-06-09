package com.example.proyectoiii;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import static com.example.proyectoiii.InsertionSort.insertionSort;
import static com.example.proyectoiii.ShellSort.shellSort;

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
    @FXML
    private Label labelOrdenar;
    @FXML
    private Label ord1, ord2, ord3;

    Avion[] avionesList = new Avion[3];
    List<String> tiposAviones = new ArrayList<>();

    InsertionSort insertionSort = new InsertionSort();
    ShellSort shellSort = new ShellSort();

    public void loadItems(){
        tiposAviones.add("F18 SuperHornett");
        tiposAviones.add("F22 Raptor");
        tiposAviones.add("C130H Hercules");
        tipos.getItems().addAll(tiposAviones);
        ordenarPor.getItems().addAll("Velocidad", "Eficiencia");
        avionesList[0] = new Avion("F18 SuperHornett", 9,10,6);
        avionesList[1] = new Avion("F22 Raptor", 10, 2,10);
        avionesList[2] = new Avion("C130H Hercules", 5,5,10);
    }

    public void ordenar(){
        String av1 = "";
        String av2 = "";
        String av3 = "";
        if (ordenarPor.getValue().equals("Velocidad")) {
            int[] list = new int[3];
            for (int i = 0; i < avionesList.length; i++) {
                list[i] = (int) avionesList[i].getVelocidad();
            }
            System.out.println(Arrays.toString(list));
            insertionSort(list);
            System.out.println(Arrays.toString(list));

            labelOrdenar.setText("Más veloces:");

            for(int j = 0; j < avionesList.length; j++) {
                if (list[0] == avionesList[j].getVelocidad()) {
                    av1 = avionesList[j].getNombre() + ": " + avionesList[j].getVelocidad();
                }
                if (list[1] == avionesList[j].getVelocidad()) {
                    av2 = avionesList[j].getNombre() + ": " + avionesList[j].getVelocidad();
                }
                if (list[2] == avionesList[j].getVelocidad()) {
                    av3 = avionesList[j].getNombre() + ": " + avionesList[j].getVelocidad();
                }
            }
            ord1.setText(av1);
            ord2.setText(av2);
            ord3.setText(av3);

        }if (ordenarPor.getValue().equals("Eficiencia")){
            int[] list = new int[3];
            for (int i = 0; i < avionesList.length; i++) {
                list[i] = (int) avionesList[i].getEficiencia();
            }
            System.out.println(Arrays.toString(list));
            shellSort(list);
            System.out.println(Arrays.toString(list));

            labelOrdenar.setText("Más eficientes:");

            for(int j = 0; j < avionesList.length; j++) {
                if (list[0] == avionesList[j].getEficiencia()) {
                    av1 = avionesList[j].getNombre() + ": " + avionesList[j].getEficiencia();
                }
                if (list[1] == avionesList[j].getEficiencia()) {
                    av2 = avionesList[j].getNombre() + ": " + avionesList[j].getEficiencia();
                }
                if (list[2] == avionesList[j].getEficiencia()) {
                    av3 = avionesList[j].getNombre() + ": " + avionesList[j].getEficiencia();
                }
            }
            ord1.setText(av1);
            ord2.setText(av2);
            ord3.setText(av3);

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
