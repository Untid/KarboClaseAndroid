package com.example.agenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AmigosHelper extends SQLiteOpenHelper {
    public AmigosHelper(@Nullable Context context){
        super(context,"amigos.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creaBBDD = "CREATE TABLE amigos (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT,telefono TEXT)";
        db.execSQL(creaBBDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Sentencia de actualización

    }
}
