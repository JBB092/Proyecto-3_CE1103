package com.example.clientmobileapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;




public class MapaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtén el grafo (puedes pasar el grafo como parámetro o utilizar alguna clase singleton)
        Grafo grafo = obtenerGrafo();

        // Crea un objeto GraphView
        GraphView graphView = findViewById(R.id.graph);
        graphView.removeAllSeries();

        // Crea una lista de series para almacenar los puntos del grafo
        List<LineGraphSeries<DataPoint>> seriesList = new ArrayList<>();

// Itera sobre los nodos del grafo y agrega series para cada nodo
        for (Nodo nodo : grafo.getNodos()) {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
            for (Enlace enlace : nodo.getEnlaces()) {
                Nodo nodoDestino = enlace.getDestino();
                series.appendData(new DataPoint(nodoDestino.getX(), nodoDestino.getY()), true, 10);
            }
            seriesList.add(series);
        }

// Agrega las series al GraphView
        for (LineGraphSeries<DataPoint> series : seriesList) {
            graphView.addSeries(series);
        }
    }

    // Método ficticio para obtener un grafo de ejemplo
    private Grafo obtenerGrafo() {
        return GrafoDeEjemplo.getGrafo();
    }
}