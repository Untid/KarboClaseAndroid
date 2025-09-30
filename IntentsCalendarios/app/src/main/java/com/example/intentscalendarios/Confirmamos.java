package com.example.intentscalendarios;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class Confirmamos extends AppCompatActivity {

    private TextView txtTitulo2;
    private TextView txtUbicacion2;
    private TextView txtDescripcion2;
    private TextView txtFechaInicio2;
    private TextView txtFechaFin2;
    // -------------------------------Respuestas-------------------------------------
    private TextView txtTituloRes;
    private TextView txtUbicacionRes;
    private TextView txtDescripcionRes;
    private TextView txtFechaInicioRes;
    private TextView txtFechaFinRes;
    //------------------------------Botones---------------------------------
    private Button btnConfirmar;
    private Button btnVolver;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmamos);

        // -----------------------------------CONEXIONES DE VARIABLES-------------------------------------------------------------------

        txtTitulo2 = findViewById(R.id.txtTitulo2);
        txtUbicacion2 = findViewById(R.id.txtUbicacion2);
        txtDescripcion2 = findViewById(R.id.txtDescripcion2);
        txtFechaInicio2 = findViewById(R.id.txtFechaInicio2);
        txtFechaFin2 = findViewById(R.id.txtFechaFin2);

        txtTituloRes = findViewById(R.id.txtTituloRes);
        txtUbicacionRes = findViewById(R.id.txtUbicacionRes);
        txtDescripcionRes= findViewById(R.id.txtDescripcionRes);
        txtFechaInicioRes = findViewById(R.id.txtFechaInicioRes);
        txtFechaFinRes = findViewById(R.id.txtFechaFinRes);

        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnVolver = findViewById(R.id.btnVolver);



//-------------------------------------------AGRICULTOR-----------------------------------------------------------------------------

        Bundle miBundle = getIntent().getExtras();

        txtTituloRes.setText(miBundle.getString("Titulo"));
        txtUbicacionRes.setText(miBundle.getString("Ubicacion"));
        txtDescripcionRes.setText(miBundle.getString("Descripcion"));
        txtFechaInicioRes.setText(miBundle.getString("FechaInicio"));
        txtFechaFinRes.setText(miBundle.getString("FechaFinal"));

// -------------------------------------------BOTONESS--------------------------------------------------------------------------------

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calendarIntent = new Intent(Intent.ACTION_INSERT);
                calendarIntent.setType("vnd.android.cursor.item/event");

                calendarIntent.putExtra(CalendarContract.Events.TITLE, txtTituloRes.getText());
                calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, txtUbicacionRes.getText());
                calendarIntent.putExtra(CalendarContract.Events.DESCRIPTION, txtDescripcionRes.getText());

                // Ejemplo de fecha y hora de inicio (23 de enero de 2021 a las 7:30 AM)
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2026, 10, 27, 7, 30); // Meses son base 0, por eso es 0 para enero
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());

                // Ejemplo de fecha y hora de fin (23 de enero de 2021 a las 10:30 AM)
                Calendar endTime = Calendar.getInstance();
                endTime.set(2026, 10, 27, 10, 30);
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());

                startActivity(calendarIntent);
            }
        });


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntento = new Intent(getBaseContext(), MainActivity.class);

                startActivity(miIntento);
            }
        });
    }
}