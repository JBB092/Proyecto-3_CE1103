package com.example.clientmobileapp;

public class Enlace {

    private Nodo origen;
    private Nodo destino;

    public Enlace(Nodo origen, Nodo destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public Nodo getOrigen() {
        return origen;
    }

    public Nodo getDestino() {
        return destino;
    }
}
