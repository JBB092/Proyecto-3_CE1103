package com.example.clientmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_screen);

        // Initialize buttons
        Button buttonAmigos = findViewById(R.id.buttonAmigos);
        Button buttonSolicitarServicio = findViewById(R.id.buttonSolicitarServicio);
        Button buttonCalificacion = findViewById(R.id.buttonCalificacion);
        Button buttonCerrarSesion = findViewById(R.id.buttonCerrarSesion);

        // Set click listeners for buttons
        buttonAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Amigos" button click
                // You can add your logic here
            }
        });

        buttonSolicitarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Solicitar Servicio" button click
                // You can add your logic here
            }
        });

        buttonCalificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Calificación" button click
                // You can add your logic here
            }
        });

        buttonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cerrar sesión y regresar a la pantalla inicial
                Intent intent = new Intent(PrincipalScreen.this, MainActivity.class);
                startActivity(intent);
                finish(); // Opcional: Cerrar la actividad actual
            }
        });
    }
}
