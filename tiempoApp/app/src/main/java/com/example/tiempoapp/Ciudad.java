package com.example.tiempoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Ciudad extends AppCompatActivity {
    private TextView tvNombre, tvTemperatura;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudad);

        tvNombre = findViewById(R.id.tvNombre);
        tvTemperatura = findViewById(R.id.tvTemperatura);
        progressBar = findViewById(R.id.progressBar);

        String nombre = getIntent().getStringExtra("nombre");
        double lat = getIntent().getDoubleExtra("lat", 0);
        double lon = getIntent().getDoubleExtra("lon", 0);

        tvNombre.setText(nombre);
        progressBar.setVisibility(View.VISIBLE);

        // En onCreate(), después de obtener lat/lon:
        WeatherService.obtenerClima(this, lat, lon, new WeatherService.WeatherCallback() {
            @Override
            public void onSuccess(double temperatura) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    tvTemperatura.setText("Temperatura: " + String.format("%.1f", temperatura) + "°C");
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    tvTemperatura.setText("Error: " + error);
                });
            }
        });

        // Guardar en SQLite
        CiudadDBHelper dbHelper = new CiudadDBHelper(this);
        dbHelper.insertarCiudad(new Ubicacion(nombre, lat, lon));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CicloVida", "Ciudad.onResume()");
    }
}