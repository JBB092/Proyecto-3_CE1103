package com.example.clientmobileapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextCarnet;
    private EditText editTextResidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        // Inicializar vistas
        editTextCarnet = findViewById(R.id.editTextCarnet);
        editTextResidencia = findViewById(R.id.editTextResidencia);
        Button buttonCrearCuenta = findViewById(R.id.buttonCrearCuenta);

        // Configurar clic en el botón "Crear Cuenta"
        buttonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Obtener el texto de los campos de texto
                    String carnet = editTextCarnet.getText().toString();
                    String residencia = editTextResidencia.getText().toString();

                    // Lógica necesaria para crear la cuenta
                    // Mensaje con la información ingresada
                    String mensaje = "Registrando cuenta\nCarnet: " + carnet + "\nResidencia: " + residencia;
                    Toast.makeText(RegisterActivity.this, mensaje, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
