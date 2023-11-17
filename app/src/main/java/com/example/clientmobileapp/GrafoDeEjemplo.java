package com.example.clientmobileapp;

import java.util.ArrayList;
import java.util.List;

public class GrafoDeEjemplo {

    public static Grafo getGrafo() {
        Grafo grafo = new Grafo();

        Nodo nodo1 = new Nodo(1, 100, 100);
        Nodo nodo2 = new Nodo(2, 200, 200);
        Nodo nodo3 = new Nodo(3, 300, 300);

        // Agrega nodos al grafo
        grafo.agregarNodo(nodo1);
        grafo.agregarNodo(nodo2);
        grafo.agregarNodo(nodo3);

        // Agrega enlaces entre nodos
        grafo.agregarEnlace(nodo1, nodo2);
        grafo.agregarEnlace(nodo2, nodo3);

        return grafo;
    }
}
