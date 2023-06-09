package com.example.proyectoiii;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jssc.*;

public class Controller {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NODE_RADIUS = 15;
    private static final int WEIGHT_LOW = 1;
    private static final int WEIGHT_HIGH = 10;
    public static List<Node> nodes;
    public static List<Edge> edges;
    private static List<Node> aeropuertoNodes;
    private static List<Node> portaavionesNodes;


    @FXML
    public void initialize() {
        //comm();
    }

    /**
     * Genera la cantidad de nodos aleatorios entres 5 y 10, y en una posicion aleatoria de la ventana.
     * Tambien genera las rutas aleatorias y el diferente peso de acuerdo al tipo de nodo.
     */
    static void generateGraph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        aeropuertoNodes = new ArrayList<>();
        portaavionesNodes = new ArrayList<>();

        Random random = new Random();


        int numNodes = random.nextInt(5) + 5;
        for (int i = 0; i < numNodes; i++) {
            int x = random.nextInt(WIDTH - NODE_RADIUS * 2) + NODE_RADIUS;
            int y = random.nextInt(HEIGHT - NODE_RADIUS * 2) + NODE_RADIUS;
            Node.NodeType type = random.nextBoolean() ? Node.NodeType.Aeropuerto : Node.NodeType.Portaaviones;
            Node node = new Node(i, x, y, type);
            nodes.add(node);
        }

        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                Node.NodeType startNodeType = nodes.get(i).getType();
                Node.NodeType endNodeType = nodes.get(j).getType();
                int weight;
                if (startNodeType == Node.NodeType.Aeropuerto && endNodeType == Node.NodeType.Portaaviones) {
                    weight = random.nextInt(10) + 11;  // Peso mayor de A a B
                } else if (startNodeType == Node.NodeType.Portaaviones && endNodeType == Node.NodeType.Aeropuerto) {
                    weight = random.nextInt(10) + 1;   // Peso menor de B a A
                } else {
                    weight = random.nextInt(10) + 1;   // Peso normal para otras combinaciones
                }
                edges.add(new Edge(nodes.get(i), nodes.get(j), weight));
            }
        }
    }


    static void drawGraph(Pane pane) {
        pane.getChildren().clear();

        for (Edge edge : edges) {
            Line line = new Line(edge.getStartNode().getX(), edge.getStartNode().getY(),
                    edge.getEndNode().getX(), edge.getEndNode().getY());

            Text weightText = new Text((edge.getStartNode().getX() + edge.getEndNode().getX()) / 2,
                    (edge.getStartNode().getY() + edge.getEndNode().getY()) / 2,
                    Integer.toString(edge.getWeight()));

            pane.getChildren().addAll(line, weightText);
        }

        for (Node node : nodes) {
            javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(node.getX(), node.getY(), NODE_RADIUS);
            if (node.getType() == Node.NodeType.Aeropuerto) {
                circle.setStyle("-fx-fill: orange;");
            } else {
                circle.setStyle("-fx-fill: purple;");
            }
            pane.getChildren().add(circle);
        }
    }

    public void comm() {
        SerialPort puerto = new SerialPort("COM3");
        try {
            puerto.openPort();
            puerto.setParams(SerialPort.BAUDRATE_19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            puerto.addEventListener((SerialPortEvent event) -> {
                if (event.isRXCHAR()) {
                    try {
                        String x = puerto.readString();
                        if (x.equals("U")) {
                            System.out.println("UP");
                        }
                        if (x.equals("D")) {
                            System.out.println("DOWN");
                        }
                        if (x.equals("L")) {
                            System.out.println("LEFT");
                        }
                        if (x.equals("R")) {
                            System.out.println("RIGHT");
                        }
                    } catch (SerialPortException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (SerialPortException e) {
            throw new RuntimeException(e);
        }
    }
}