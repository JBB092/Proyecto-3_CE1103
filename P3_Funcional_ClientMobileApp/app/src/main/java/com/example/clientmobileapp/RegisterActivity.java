package com.example.clientmobileapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * RegisterActivity represents the user registration screen.
 *
 * <p>This activity allows users to enter their name, student ID (carnet), and residence.
 * Upon clicking the "Crear Cuenta" button, it validates the input, creates a JSON object with
 * the provided information, saves the JSON object to a file, and displays a toast message with the
 * registration details.</p>
 *
 * <p>Author: Jose Barquero</p>
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextCarnet;
    private EditText editTextResidencia;

    /**
     * Called when the activity is starting.
     *
     * <p>This is where most initialization should go: calling {@code setContentView(int)}
     * to inflate the activity's UI, using {@code findViewById(int)} to programmatically interact with widgets
     * in the UI, and configuring the activity's toolbar.</p>
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           then this Bundle contains the data it most recently supplied in {@link #onSaveInstanceState(Bundle)}.
     *                           Note: Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        // Configure the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarRegister);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextCarnet = findViewById(R.id.editTextCarnet);
        editTextResidencia = findViewById(R.id.editTextResidencia);
        Button buttonCrearCuenta = findViewById(R.id.buttonCrearCuenta);

        // Set up click listener for "Crear Cuenta" button
        buttonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Get the text from the input fields
                    String nombre = editTextNombre.getText().toString();
                    String carnet = editTextCarnet.getText().toString();
                    String residencia = editTextResidencia.getText().toString();

                    // Validate that the name contains only letters
                    if (!isValidName(nombre)) {
                        Toast.makeText(RegisterActivity.this, "Name should only contain letters", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Validate that the student ID contains only numbers
                    if (!isValidCarnet(carnet)) {
                        Toast.makeText(RegisterActivity.this, "Student ID should only contain numbers", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Validate that the residence contains only letters
                    if (!isValidResidencia(residencia)) {
                        Toast.makeText(RegisterActivity.this, "Residence should only contain letters", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Create a JSON object with the validated data
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("nombre", nombre);
                    jsonObject.put("carnet", carnet);
                    jsonObject.put("residencia", residencia);

                    // Get the internal storage directory for the application
                    File internalStorageDir = getFilesDir();

                    // Create a JSON file in the internal storage directory
                    File jsonFile = new File(internalStorageDir, "registration.json");

                    // Write the JSON object to the file
                    try (FileWriter fileWriter = new FileWriter(jsonFile)) {
                        fileWriter.write(jsonObject.toString());
                        Toast.makeText(RegisterActivity.this, "Data saved to registration.json", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this, "Error saving data", Toast.LENGTH_SHORT).show();
                    }

                    // Perform the necessary logic to create the account
                    // Display a toast message with the entered information
                    //String mensaje = "Creating account\nName: " + nombre + "\nStudent ID: " + carnet + "\nResidence: " + residencia;
                    //Toast.makeText(RegisterActivity.this, mensaje, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Called when the user selects an item from the options menu.
     *
     * <p>Handle the action of the up arrow, usually back to the parent activity.</p>
     *
     * @param item The menu item selected.
     * @return True if the item was handled successfully, false otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the action of the up arrow
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Validate that the name contains only letters.
     *
     * @param name The entered name.
     * @return True if the name is valid, false otherwise.
     */
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    /**
     * Validate that the student ID contains only numbers.
     *
     * @param carnet The entered student ID.
     * @return True if the student ID is valid, false otherwise.
     */
    private boolean isValidCarnet(String carnet) {
        return carnet.matches("[0-9]+");
    }

    /**
     * Validate that the residence contains only letters.
     *
     * @param residencia The entered residence.
     * @return True if the residence is valid, false otherwise.
     */
    private boolean isValidResidencia(String residencia) {
        return residencia.matches("[a-zA-Z]+");
    }
}
