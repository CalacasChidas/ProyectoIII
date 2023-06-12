package com.example.proyectoiii;

import java.util.*;

public class Dijkstra {
    private int V; // Número de vértices en el grafo
    private List<List<Edge>> adjList; // Lista de adyacencia del grafo

    private static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public Dijkstra(int V) {
        this.V = V;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        adjList.get(src).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(src, weight)); // Si el grafo es no dirigido
    }

    public List<Integer> shortestPath(int src, int dest) {
        // Creamos un arreglo para almacenar las distancias mínimas desde el origen a cada vértice
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Creamos un arreglo para almacenar el camino más corto desde el origen a cada vértice
        int[] prev = new int[V];
        Arrays.fill(prev, -1);

        // Creamos un conjunto para almacenar los vértices visitados
        Set<Integer> visited = new HashSet<>();

        // Inicializamos la distancia del origen a sí mismo como 0
        dist[src] = 0;

        // Calculamos las distancias mínimas
        for (int i = 0; i < V - 1; i++) {
            int u = minDistance(dist, visited);
            visited.add(u);

            for (Edge edge : adjList.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                if (!visited.contains(v) && dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    prev[v] = u;
                }
            }
        }

        // Construimos el camino más corto desde el origen hasta el destino
        List<Integer> path = new ArrayList<>();
        int current = dest;
        while (current != -1) {
            path.add(current);
            current = prev[current];
        }
        Collections.reverse(path);

        return path;
    }

    private int minDistance(int[] dist, Set<Integer> visited) {
        int minDist = Integer.MAX_VALUE;
        int minVertex = -1;

        for (int v = 0; v < V; v++) {
            if (!visited.contains(v) && dist[v] < minDist) {
                minDist = dist[v];
                minVertex = v;
            }
        }

        return minVertex;
    }
}
