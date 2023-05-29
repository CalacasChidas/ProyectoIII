package com.example.proyectoiii;

public class Avion {
    private String nombre;
    private double velocidad;
    private double eficiencia;
    private int fortaleza;

    public Avion(String nombre, double velocidad, double eficiencia, int fortaleza) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.eficiencia = eficiencia;
        this.fortaleza = fortaleza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(double eficiencia) {
        this.eficiencia = eficiencia;
    }

    public int getFortaleza() {
        return fortaleza;
    }

    public void setFortaleza(int fortaleza) {
        this.fortaleza = fortaleza;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Velocidad: " + velocidad + " km/h");
        System.out.println("Eficiencia: " + eficiencia);
        System.out.println("Fortaleza: " + fortaleza);
    }
}
