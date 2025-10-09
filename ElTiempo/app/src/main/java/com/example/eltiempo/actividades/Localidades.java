package com.example.eltiempo.actividades;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eltiempo.R;
import com.example.eltiempo.adapRecycle.CiudadAdapter;
import com.example.eltiempo.model.CiudadModel;

import java.util.Arrays;
import java.util.List;

public class Localidades extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localidades);

        String provincia = getIntent().getStringExtra("provincia");
        double lat = getIntent().getDoubleExtra("lat", 0);
        double lon = getIntent().getDoubleExtra("lon", 0);

        // Simulamos localidades dentro de la provincia
        List<CiudadModel> localidades = Arrays.asList(
                new CiudadModel("Centro de " + provincia, lat, lon),
                new CiudadModel("Norte de " + provincia, lat + 0.1, lon),
                new CiudadModel("Sur de " + provincia, lat - 0.1, lon)
        );

        RecyclerView rv = findViewById(R.id.recyclerViewLocalidades);
        rv.setLayoutManager(new LinearLayoutManager(this));
        CiudadAdapter adapter = new CiudadAdapter(localidades, ciudadModel -> {
            Intent intent = new Intent(Localidades.this, CiudadModel.class);
            intent.putExtra("nombre", ciudadModel.getNombre());
            intent.putExtra("lat", ciudadModel.getLatitud());
            intent.putExtra("lon", ciudadModel.getLongitud());
            startActivity(intent);
        });
        rv.setAdapter(adapter);
    }
}
