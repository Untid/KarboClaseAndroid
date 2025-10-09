package com.example.eltiempo.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eltiempo.model.CiudadModel;

import java.util.ArrayList;
import java.util.List;

public class CiudadDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "weather.db";
    private static final int DB_VERSION = 1;

    public CiudadDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ciudades (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "latitud REAL, " +
                "longitud REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ciudades");
        onCreate(db);
    }

    public void insertarCiudad(CiudadModel ciudadModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", ciudadModel.getNombre());
        values.put("latitud", ciudadModel.getLatitud());
        values.put("longitud", ciudadModel.getLongitud());
        db.insert("ciudades", null, values);
        db.close();
    }

    public List<CiudadModel> obtenerTodasLasCiudades() {
        List<CiudadModel> ciudades = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ciudades", null);
        if (cursor.moveToFirst()) {
            do {
                ciudades.add(new CiudadModel(
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ciudades;
    }
}
