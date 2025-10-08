package com.example.todo;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase auxiliar para la gestión de la base de datos SQLite.
 *
 * <p>Esta clase extiende {@link SQLiteOpenHelper}, lo que permite</p>
 * <ul>
 *     <li>Crear la base de datos cuando aún no existe.</li>
 *     <li>Actualizarla cuando cambia la versión del esquema.</li>
 *     <li>Ejecutar sentencias SQL para crear o eliminar tablas</li>
 * </ul>
 *
 * <p>Su principal propósito es definir la estructura de la tabla y manejar su ciclo de vida.</p>
 */
public class TaskDbHelper extends SQLiteOpenHelper {

    // --- Constantes de configuración de la base de datos ---
    private static final String DATABASE_NAME = "Tasks.db"; // Nombre del archivo de la base de datos
    private static final int DATABASE_VERSION = 1;

    // --- Sentencia SQL para crear la tabla de tareas ---
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TaskContract.TaskEntry.TABLE_NAME + " (" +
                    TaskContract.TaskEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    TaskContract.TaskEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL)";

    // --- Sentencia SQL para eliminar la tabla ---
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE_NAME;

    /**
     * Constructor del helper de base de datos
     *
     * @param context
     */
    public TaskDbHelper(Context context) {
        // Llama al constructor de SQLiteOpenHelper con el nombre y versión de la base de datos
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Método llamado automáticamente cuando la base de datos se crea por primera vez.
     * Aquí se ejecuta la sentencia SQL para crear la tabla principal.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * Método llamado cuando se actualiza la versión de la base de datos.
     * Este proceso borra las tablas existentes y las vuelve a crear desde cero.
     * <p>Esto no es práctico realmente</p>
     *
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}