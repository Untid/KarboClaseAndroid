package com.example.aplicacion1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView txtTitulo;
        EditText editTextNombre;
        Button btnSaluda;


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        txtTitulo = findViewById(R.id.txtTitulo); //Con esto relaciones el XML con el programa en sí a través del id
        editTextNombre = findViewById(R.id.editTextNombre);
        btnSaluda = findViewById(R.id.btnSaluda);


        txtTitulo.setText("Buenos días");

        btnSaluda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTitulo.setText("Saludos nuevo gorrino " + editTextNombre.getText());
                editTextNombre.setText("");
            }
        });







    }
}