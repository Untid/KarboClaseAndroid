package com.example.intentscalendarios;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView txtTitulo;
    private TextView txtUbicacion;
    private TextView txtDescripcion;
    private TextView txtFechaInicio;
    private TextView txtFechaFin;

    private EditText editTextTitulo;
    private EditText editTextUbicacion;
    private EditText editTextDescripcion;
    private EditText editTextFechaInicio;
    private EditText editTextFechaFin;

    private Button btnAgregar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // -----------------------------------CONEXIONES DE VARIABLES-------------------------------------------------------------------

        txtTitulo = findViewById(R.id.txtTitulo);
        txtUbicacion = findViewById(R.id.txtUbicacion);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtFechaInicio = findViewById(R.id.txtFechaInicio);
        txtFechaFin = findViewById(R.id.txtFechaFin);

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextUbicacion = findViewById(R.id.editTextUbicacion);
        editTextDescripcion = findViewById(R.id.editTextDescripcion);
        editTextFechaInicio = findViewById(R.id.editTextFechaInicio);
        editTextFechaFin = findViewById(R.id.editTextFechaFin);

        btnAgregar = findViewById(R.id.btnAgregar);


        //--------------------------------------Intent pasando movidas------------------------------------------------------------

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntento = new Intent(getBaseContext(),Confirmamos.class);

                String Titulo = editTextTitulo.getText().toString();

                miIntento.putExtra("Titulo",Titulo);
                miIntento.putExtra("Ubicacion",editTextUbicacion.getText().toString());
                miIntento.putExtra("Descripcion",editTextDescripcion.getText().toString());

                miIntento.putExtra("FechaInicio",editTextFechaInicio.getText().toString());
                miIntento.putExtra("FechaFinal",editTextFechaFin.getText().toString());

                startActivity(miIntento);
            }
        });
    }

    /**
     * Método creado para cambiar la fecha insertada en String a Date
     *
     * @param fechaHora
     */
    private void editFechaHora(String fechaHora){


    }



}