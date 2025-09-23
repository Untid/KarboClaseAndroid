package com.example.imc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    //EditText -------------------------------
    private EditText editTextNombre;
    // TextViews -------------------------------
    private TextView txtSaludo;
    private TextView txtPeso;
    private TextView txtAltura;
    // Buttons -------------------------------
    private Button btnCalcular;
    // RadioButtons -------------------------------
    private RadioButton rbVaron;
    private RadioButton rbHembra;
    // SeekBars -------------------------------
    private SeekBar sbPeso;
    private SeekBar sbAltura;

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

        //EditText -------------------------------
        editTextNombre = findViewById(R.id.editTextNombre);

        // TextViews -------------------------------
        txtSaludo = findViewById(R.id.txtSaludo);
        txtPeso = findViewById(R.id.txtPeso);
        txtAltura = findViewById(R.id.txtAltura);

        // Buttons -------------------------------
        btnCalcular = findViewById(R.id.btnCalcular);

        // RadioButtons -------------------------------
        rbVaron = findViewById(R.id.rbVaron);
        rbHembra = findViewById(R.id.rbHembra);

        // SeekBars -------------------------------
        sbPeso = findViewById(R.id.sbPeso);
        sbAltura = findViewById(R.id.sbAltura);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tratamiento = "";
                boolean error = false;
                if (!(editTextNombre.getText().length()==0)){
                    if (rbVaron.isChecked()){
                        tratamiento="D. ";

                    } else if (rbHembra.isChecked()) {
                        tratamiento="Dña. ";

                    }
                    else{
                        mensajeError("No has puesto el sexo", v);
                        error = true;
                    }
                    Double imc = 1.0*sbPeso.getProgress() / (sbAltura.getProgress()/100.0) * (sbAltura.getProgress()/100.0);
                    if(!error){
                        txtSaludo.setText("Hola " + tratamiento + editTextNombre.getText()+ " tu IMC es: "+imc);
                    }
                }else {
                    mensajeError("No has escrito el nombre", v);
                }
            }});
        sbPeso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("Javier", ""+progress);
                txtPeso.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbAltura.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtAltura.setText(""+progress/100.0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }
    private void mensajeError(String mensaje, View v){
        Snackbar.make(v,mensaje,Snackbar.LENGTH_LONG).show();
    }
}