package com.example.eltiempo.actividades;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eltiempo.R;
import com.example.eltiempo.adapRecycle.CiudadAdapter;
import com.example.eltiempo.helper.CiudadDBHelper;
import com.example.eltiempo.model.CiudadModel;

import java.util.Arrays;
import java.util.List;

public class Provincias extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CiudadAdapter adapter;
    private CiudadDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincias);

        dbHelper = new CiudadDBHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Aquí cargarías las provincias (ej: Madrid, Barcelona...)
        // Por simplicidad, simulamos con datos fijos
        List<CiudadModel> provincias = Arrays.asList(
                new CiudadModel("Madrid", 40.4168, -3.7038),
                new CiudadModel("Barcelona", 41.3851, 2.1734),
                new CiudadModel("Valencia", 39.4699, -0.3763)
        );

        adapter = new CiudadAdapter(provincias, ciudadModel -> {
            Intent intent = new Intent(Provincias.this, Localidades.class);
            intent.putExtra("provincia", ciudadModel.getNombre());
            intent.putExtra("lat", ciudadModel.getLatitud());
            intent.putExtra("lon", ciudadModel.getLongitud());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}
