package com.example.proyectoiii;

import javafx.scene.shape.Shape;

/**
 * Clase edge
 */
public class Edge {
    private Node startNode;
    private Node endNode;
    private int weight;

    public Edge(Node startNode, Node endNode, int weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    /**
     * Setters y Getters necesarios
     * @return
     */
    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public int getWeight() {
        return weight;
    }
}