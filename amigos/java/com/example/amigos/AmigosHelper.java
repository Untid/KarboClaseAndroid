package com.example.amigos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AmigosHelper extends SQLiteOpenHelper {

    public AmigosHelper(@Nullable Context context) {
        super(context, "amjigos.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creaBBDD = "CREATE TABLE amigos (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT,telefono TEXT)";
        sqLiteDatabase.execSQL(creaBBDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Sentencia de actualización
    }
}
