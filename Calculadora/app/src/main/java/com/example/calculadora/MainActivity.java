package com.example.calculadora;

import android.content.Intent;
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

    EditText editTextn1;
    EditText editTextn2;
    //-----------------------------
    TextView txtPrueba;
    //-----------------------------
    Button btnSuma;
    Button btnResta;
    Button btnMulti;
    Button btnDivi;
    Button btnCalcular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextn1 = findViewById(R.id.editTextn1);
        editTextn2 = findViewById(R.id.editTextn2);

        txtPrueba = findViewById(R.id.txtPrueba);

        btnSuma = findViewById(R.id.btnSuma);
        btnResta = findViewById(R.id.btnResta);
        btnMulti = findViewById(R.id.btnMulti);
        btnDivi = findViewById(R.id.btnDivi);
        btnCalcular = findViewById(R.id.btnCalcular);


        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultado = suma(editTextn1.getText().toString(), editTextn2.getText().toString());

                Intent miIntentoSuma = new Intent(getBaseContext(),Resultado.class);
                miIntentoSuma.putExtra("resultado",resultado);
                startActivity(miIntentoSuma);

                txtPrueba.setText(resultado);
            }
        });

        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultado = Resta(editTextn1.getText().toString(), editTextn2.getText().toString());

                Intent miIntentoResta = new Intent(getBaseContext(),Resultado.class);
                miIntentoResta.putExtra("resultado",resultado);
                startActivity(miIntentoResta);

                txtPrueba.setText(resultado);

            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultado = Multi(editTextn1.getText().toString(), editTextn2.getText().toString());

                Intent miIntentoMulti = new Intent(getBaseContext(),Resultado.class);
                miIntentoMulti.putExtra("resultado",resultado);
                startActivity(miIntentoMulti);

                txtPrueba.setText(resultado);

            }
        });

        btnDivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultado = Divi(editTextn1.getText().toString(), editTextn2.getText().toString());

                Intent miIntentoDivi = new Intent(getBaseContext(),Resultado.class);
                miIntentoDivi.putExtra("resultado",resultado);
                startActivity(miIntentoDivi);

                txtPrueba.setText(resultado);

            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(getBaseContext(),Resultado.class);
                startActivity(miIntent);
            }
        });

    }

    private String suma(String uno, String dos) {
        double suma = 0;

        suma = Double.parseDouble(uno) + Double.parseDouble(dos);

        String resultado = String.valueOf(suma);
        return resultado;
    }

    private String Resta(String uno, String dos) {
        double resta = 0;

        resta = Double.parseDouble(uno) - Double.parseDouble(dos);

        String resultado = String.valueOf(resta);
        return resultado;
    }

    private String Multi(String uno, String dos) {
        double multi = 0;

        multi = Double.parseDouble(uno) * Double.parseDouble(dos);

        String resultado = String.valueOf(multi);
        return resultado;
    }

    private String Divi(String uno, String dos) {
        double divi = 0;

        divi = Double.parseDouble(uno) / Double.parseDouble(dos);

        String resultado = String.valueOf(divi);
        return resultado;
    }
}