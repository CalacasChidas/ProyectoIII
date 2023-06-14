package com.example.proyectoiii;

/**
 * Clase node
 */
public class Node {
    private int id;
    private int x;
    private int y;
    private NodeType type;

    public Node(int id, int x, int y, NodeType type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Getters y Setters
     * @return
     */
    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public NodeType getType() {
        return type;
    }

    static enum NodeType {
        Aeropuerto,
        Portaaviones
    }
}
