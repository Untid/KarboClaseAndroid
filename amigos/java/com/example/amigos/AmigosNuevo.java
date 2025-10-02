package com.example.amigos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AmigosNuevo extends AppCompatActivity {

    EditText etNombre;
    EditText etTelefono;
    Button btnAnadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos_nuevo);

        etNombre=findViewById(R.id.etEditaNombre);
        etTelefono=findViewById(R.id.etEditaTelefono);
        btnAnadir=findViewById(R.id.btnGuardar);

        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=new AmigosHelper(getBaseContext()).getWritableDatabase();
                ContentValues valores = new ContentValues();

                valores.put("nombre",etNombre.getText().toString());
                valores.put("telefono",etTelefono.getText().toString());

                db.insert("amigos",null,valores);
                db.close();

                Intent intent = new Intent(getBaseContext(),Amigos.class);
                startActivity(intent);
            }
        });
    }
}