package com.example.eltiempo.actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eltiempo.R;
import com.example.eltiempo.helper.CiudadDBHelper;
import com.example.eltiempo.model.CiudadModel;
import com.example.eltiempo.servicio.WeatherService;

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

        WeatherService.obtenerClima(lat, lon, new WeatherService.WeatherCallback() {
            @Override
            public void onSuccess(double temperatura) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    tvTemperatura.setText("Temperatura: " + temperatura + "°C");
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
        dbHelper.insertarCiudad(new CiudadModel(nombre, lat, lon));
    }
}
