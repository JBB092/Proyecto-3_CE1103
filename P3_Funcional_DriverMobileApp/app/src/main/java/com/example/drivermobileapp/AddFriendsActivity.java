package com.example.drivermobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * AddFriendsActivity represents the screen to send friend requests.
 *
 * <p>This activity receives data (nombre and carnet) from PrincipalScreen and displays the add friend information.</p>
 *
 * <p>Author: Jose Barquero</p>
 */
public class AddFriendsActivity extends AppCompatActivity {

    // Views
    private TextView textViewAddFriends;
    private EditText editTextMensaje;

    // User data
    private String nombre;
    private String carnet;

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
        setContentView(R.layout.addfriends_screen);

        // Configure the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarAddFriends);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        //textViewAddFriends = findViewById(R.id.textViewAddFriends);
        editTextMensaje = findViewById(R.id.editTextMensaje);

        // Get data from the intent
        Intent intent = getIntent();
        if (intent.hasExtra("nombre") && intent.hasExtra("carnet")) {
            nombre = intent.getStringExtra("nombre");
            carnet = intent.getStringExtra("carnet");

            // Update the text view with add friend information
            /*String addFriendsText = "Enviar solicitud de amistad con el nombre de " + nombre + " (" + carnet + "):";
            textViewAddFriends.setText(addFriendsText);*/
        }

        // Set up click listener for "Enviar Solicitud" button
        findViewById(R.id.buttonEnviarSolicitud).setOnClickListener(view -> enviarSolicitudAmistad());
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
     * Method to handle the click on "Enviar Solicitud" button.
     */
    private void enviarSolicitudAmistad() {
        // Get the message from the input field
        String mensaje = editTextMensaje.getText().toString();

        // Perform the action of sending friend request with the provided message
        // Add your logic here

        // Display a toast indicating that the request has been sent
        Toast.makeText(this, "Solicitud de amistad enviada", Toast.LENGTH_SHORT).show();

        // Optionally, navigate to another screen or finish this activity
        // Add your navigation logic here
    }
}