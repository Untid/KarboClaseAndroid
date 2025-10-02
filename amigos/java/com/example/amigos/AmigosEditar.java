package com.example.amigos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AmigosEditar extends AppCompatActivity {

    EditText etEditaNombre;
    EditText etEditaTelefono;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos_editar);

        Intent intent = getIntent();
        String itemID = intent.getStringExtra("itemID");

        etEditaNombre=findViewById(R.id.etEditaNombre);
        etEditaTelefono=findViewById(R.id.etEditaTelefono);
        btnGuardar=findViewById(R.id.btnGuardar);

        SQLiteDatabase db = new AmigosHelper(this).getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT nombre,telefono FROM amigos WHERE id="+itemID,null);
        cursor.moveToNext();
        etEditaNombre.setText(cursor.getString(0));
        etEditaTelefono.setText(cursor.getString(1));
        cursor.close();
        db.close();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = new AmigosHelper(getBaseContext()).getWritableDatabase();
                ContentValues valores = new ContentValues();
                String[] filtro={itemID};
                valores.put("nombre",etEditaNombre.getText().toString());
                valores.put("telefono",etEditaTelefono.getText().toString());

                db.update("amigos",valores,"id=?",filtro);
                db.close();

                Intent intent=new Intent(getBaseContext(),Amigos.class);
                startActivity(intent);
            }
        });


    }
}