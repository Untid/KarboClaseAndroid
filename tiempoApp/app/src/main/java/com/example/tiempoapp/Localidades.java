package com.example.tiempoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

        TextView tvTitulo = findViewById(R.id.tvTitulo);
        tvTitulo.setText("Localidades en " + provincia);

        List<Ubicacion> localidades = Arrays.asList(
                new Ubicacion("Centro de " + provincia, lat, lon),
                new Ubicacion("Norte de " + provincia, lat + 0.05, lon),
                new Ubicacion("Sur de " + provincia, lat - 0.05, lon)
        );

        RecyclerView rv = findViewById(R.id.recyclerViewLocalidades);
        rv.setLayoutManager(new LinearLayoutManager(this));
        CiudadAdapter adapter = new CiudadAdapter(localidades, ubicacion -> {
            Intent intent = new Intent(Localidades.this, Ciudad.class);
            intent.putExtra("nombre", ubicacion.getNombre());
            intent.putExtra("lat", ubicacion.getLatitud());
            intent.putExtra("lon", ubicacion.getLongitud());
            startActivity(intent);
        });
        rv.setAdapter(adapter);
    }
}