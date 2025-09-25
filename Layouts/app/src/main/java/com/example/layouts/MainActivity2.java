
package com.example.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    Button btnVolver;
    TextView txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main2);

        txtNombre = findViewById(R.id.miTxt);
        Bundle miBundle = getIntent().getExtras();
        txtNombre.setText(miBundle.getString("nombre"));
        btnVolver = findViewById(R.id.btnVolver);


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver();
            }
        });

    }

    protected void volver() {
        Intent miIntento = new Intent(getBaseContext(), MainActivity.class);

        startActivity(miIntento);


    }


}