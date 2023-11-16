package com.example.clientmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_first_screen);

        Button buttonRegister = findViewById(R.id.buttonRegister);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la actividad RegisterActivity
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la actividad LogginActivity
                Intent intent = new Intent(MainActivity.this, LogginActivity.class);
                startActivity(intent);
            }
        });
    }
}
