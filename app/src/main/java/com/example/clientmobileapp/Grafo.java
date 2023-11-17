package com.example.clientmobileapp;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

    private List<Nodo> nodos;

    public Grafo() {
        this.nodos = new ArrayList<>();
    }

    public void agregarNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    public void agregarEnlace(Nodo origen, Nodo destino) {
        Enlace enlace = new Enlace(origen, destino);
        origen.agregarEnlace(enlace);
    }

    public List<Nodo> getNodos() {
        return nodos;
    }
}

