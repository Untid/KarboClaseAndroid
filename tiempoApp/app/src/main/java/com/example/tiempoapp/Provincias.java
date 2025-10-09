package com.example.tiempoapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class Provincias extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincias);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Ubicacion> provincias = Arrays.asList(
                new Ubicacion("Madrid", 40.4168, -3.7038),
                new Ubicacion("Barcelona", 41.3851, 2.1734),
                new Ubicacion("Valencia", 39.4699, -0.3763),
                new Ubicacion("Sevilla", 37.3891, -5.9845),
                new Ubicacion("Zaragoza", 41.6488, -0.8891)
        );

        CiudadAdapter adapter = new CiudadAdapter(provincias, ubicacion -> {
            Intent intent = new Intent(Provincias.this, Localidades.class);
            intent.putExtra("provincia", ubicacion.getNombre());
            intent.putExtra("lat", ubicacion.getLatitud());
            intent.putExtra("lon", ubicacion.getLongitud());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}