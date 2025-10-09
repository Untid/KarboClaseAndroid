package com.example.eltiempo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eltiempo.actividades.Provincias;

public class PrimeraPantalla extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_pantalla);

        Button btnProvincias = findViewById(R.id.btnProvincias);
        btnProvincias.setOnClickListener(v -> {
            Intent intent = new Intent(PrimeraPantalla.this, Provincias.class);
            startActivity(intent);
        });
    }
}
