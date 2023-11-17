package com.example.clientmobileapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Nodo {
    private int id;
    private float x;
    private float y;
    private List<Enlace> enlaces;

    public Nodo(int id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.enlaces = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public List<Enlace> getEnlaces() {
        return enlaces;
    }

    public void agregarEnlace(Enlace enlace) {
        enlaces.add(enlace);
    }
}

