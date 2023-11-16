package com.example.clientmobileapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LogginActivity extends AppCompatActivity {

    private EditText editTextCarnet;
    private EditText editTextNombre;
    private Button buttonIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loggin_screen);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarLoggin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializar vistas
        editTextCarnet = findViewById(R.id.editTextCarnet);
        editTextNombre = findViewById(R.id.editTextNombre);
        buttonIniciarSesion = findViewById(R.id.buttonIniciarSesion);

        // Configurar clic en el botón "Iniciar Sesión"
        buttonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el número de carnet ingresado
                String carnet = editTextCarnet.getText().toString();

                // Validar que el carnet solo contiene números
                if (!isValidCarnet(carnet)) {
                    Toast.makeText(LogginActivity.this, "El carnet solo debe contener números", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Obtener el nombre ingresado
                String nombre = editTextNombre.getText().toString();

                // Validar que el nombre solo contiene letras
                if (!isValidName(nombre)) {
                    Toast.makeText(LogginActivity.this, "El nombre solo debe contener letras", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Realizar la lógica necesaria para iniciar sesión
                // En este ejemplo, simplemente mostramos un mensaje con el número de carnet
                String mensaje = "Iniciando sesión con Carnet: " + carnet + "\nNombre: " + nombre;
                Toast.makeText(LogginActivity.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Manejar la acción de la flecha de retorno
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Método para validar que el carnet solo contiene números
    private boolean isValidCarnet(String carnet) {
        return carnet.matches("[0-9]+");
    }

    // Método para validar que el nombre solo contiene letras
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
