package com.example.proyectoiii;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

import java.util.*;

import static com.example.proyectoiii.Controller.edges;
import static com.example.proyectoiii.Controller.nodes;
import static com.example.proyectoiii.InsertionSort.insertionSort;
import static com.example.proyectoiii.Main.root;
import static com.example.proyectoiii.ShellSort.shellSort;

public class agregarController {

    @FXML
    private ChoiceBox<String> tipos;
    @FXML
    private ChoiceBox<String> ordenarPor;
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
    private ImageView avionImage;

    Avion[] avionesList = new Avion[3];
    List<String> tiposAviones = new ArrayList<>();

    InsertionSort insertionSort = new InsertionSort();
    ShellSort shellSort = new ShellSort();

    public void loadItems() {
        tiposAviones.add("F18 SuperHornett");
        tiposAviones.add("F22 Raptor");
        tiposAviones.add("C130H Hercules");
        tipos.getItems().addAll(tiposAviones);
        ordenarPor.getItems().addAll("Velocidad", "Eficiencia");
        avionesList[0] = new Avion("F18 SuperHornett", 9, 10, 6);
        avionesList[1] = new Avion("F22 Raptor", 10, 2, 10);
        avionesList[2] = new Avion("C130H Hercules", 5, 5, 10);
    }

    public void ordenar() {
        String av1 = "";
        String av2 = "";
        String av3 = "";
        if (ordenarPor.getValue() == null) {
            System.out.println("No se ha seleccionado como se desea ordenar");
        } else {
            if (ordenarPor.getValue().equals("Velocidad")) {
                int[] list = new int[3];
                for (int i = 0; i < avionesList.length; i++) {
                    list[i] = (int) avionesList[i].getVelocidad();
                }
                System.out.println(Arrays.toString(list));
                insertionSort(list);
                System.out.println(Arrays.toString(list));

                labelOrdenar.setText("Más veloces:");

                for (int j = 0; j < avionesList.length; j++) {
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

            }
            if (ordenarPor.getValue().equals("Eficiencia")) {
                int[] list = new int[3];
                for (int i = 0; i < avionesList.length; i++) {
                    list[i] = (int) avionesList[i].getEficiencia();
                }
                System.out.println(Arrays.toString(list));
                shellSort(list);
                System.out.println(Arrays.toString(list));

                labelOrdenar.setText("Más eficientes:");

                for (int j = 0; j < avionesList.length; j++) {
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

            }
        }
    }

    public void showInfo() {
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

    public void cargarAvion() {
        Image img = new Image("/avi.png");
        avionImage = new ImageView(img);
        avionImage.setFitWidth(35);
        avionImage.setFitHeight(35);
        avionImage.setPreserveRatio(true);
        avionImage.setSmooth(true);
        avionImage.setCache(true);

        if (!nodes.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(nodes.size());
            Node selectedNode = nodes.get(randomIndex);

            // Establecer la posición del avión sobre el nodo seleccionado
            avionImage.setLayoutX(selectedNode.getX());
            avionImage.setLayoutY(selectedNode.getY());

            root.getChildren().add(avionImage);
            animarAvion(selectedNode);
        }
    }

    public void animarAvion(Node startNode) {
        if (!nodes.isEmpty()) {
            List<Node> neighbors = getNeighbors(startNode);

            if (!neighbors.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(neighbors.size());
                Node nextNode = neighbors.get(randomIndex);

                // Crear una instancia de PathTransition
                PathTransition pathTransition = new PathTransition();
                pathTransition.setNode(avionImage);
                pathTransition.setDuration(Duration.seconds(5));
                pathTransition.setPath(createLine(startNode.getX(), startNode.getY(), nextNode.getX(), nextNode.getY()));
                pathTransition.setCycleCount(1);
                pathTransition.setAutoReverse(false);

                // Configurar un evento de finalización para animar el siguiente nodo
                pathTransition.setOnFinished(event -> animarAvion(nextNode));

                pathTransition.play();
            }
        }
    }

    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();

        for (Edge edge : edges) {
            if (edge.getStartNode().equals(node)) {
                neighbors.add(edge.getEndNode());
            }
        }

        return neighbors;
    }

    public Line createLine(double startX, double startY, double endX, double endY) {
        Line line = new Line();
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        return line;
    }


}
