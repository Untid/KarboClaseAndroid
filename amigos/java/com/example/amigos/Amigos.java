package com.example.amigos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Amigos extends AppCompatActivity {

    FloatingActionButton fbAnadir;
    RecyclerView rvAmigos;
    List<Amigo> itemList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos);


        fbAnadir=findViewById(R.id.fbAnadir);

        SQLiteDatabase db = new AmigosHelper(this).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM amigos ORDER BY nombre",null);
        while(cursor.moveToNext()){
            String id=cursor.getString(0);
            String nombre=cursor.getString(1);
            String telefono=cursor.getString(2);
            itemList.add(new Amigo(id,nombre,telefono));
        }
        cursor.close();
        db.close();

        rvAmigos= findViewById(R.id.rvAmigos);
        rvAmigos.setLayoutManager(new LinearLayoutManager(this));
        AdaptadorAmigos adaptadorAmigos=new AdaptadorAmigos(itemList,rvAmigos);
        rvAmigos.setAdapter(adaptadorAmigos);


        fbAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getBaseContext(),AmigosNuevo.class);
                startActivity(intent);
            }
        });
    }
}