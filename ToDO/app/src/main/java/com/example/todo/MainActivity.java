package com.example.todo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase principal de la lista de tareas.
 * La interfaz AppCompatActivity permite manejar la interfaz de usuario principal.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private TaskDao taskDao;
    private EditText editTextTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ------------ Inicialización de vistas y componentes-------------------------------
        editTextTask = findViewById(R.id.editTextTask);
        Button btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerViewTasks);
        taskDao = new TaskDao(this); // Inicializao el DAO con el contexto actual


        // Configura el RecyclerView con un layout vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Crea el adaptador (inicialmente con una lista vacía)
        List<Task> emptyList = new ArrayList<>();
        adapter = new TaskAdapter(emptyList, new TaskAdapter.OnTaskActionListener() {
            @Override
            public void onEditClick(int position) {
                editTask(position); // Acción cuando se presiona "editar"
            }
            @Override
            public void onDeleteClick(int position) {
                deleteTask(position); // Acción cuando se presiona "eliminar"
            }
        });

        // Asocia el adaptador al RecyclerView
        recyclerView.setAdapter(adapter);

        // Carga las tareas a la base de datos (SQLite)
        loadTasks();

        // Configura el botón "Agregar tarea"
        btnAdd.setOnClickListener(v -> {
            String taskText = editTextTask.getText().toString().trim();

            if (!taskText.isEmpty()) {
                // Crea una nueva tarea y la guarda en base de datos
                Task newTask = new Task(taskText);
                taskDao.insertTask(newTask);

                // Recarga las tareas para reflejar los cambios
                loadTasks();
                editTextTask.setText("");
            } else {
                // Si el campo está vacío, muestra un aviso al usuario
                Toast.makeText(MainActivity.this, "Escribe una tarea", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Carga todas las tareas desde la base de datos y actualiza el RecycleView
     */
    private void loadTasks() {
        List<Task> tasks = taskDao.getAllTasks(); // Obtiene las tareas de la base de datos
        adapter.updateTasks(tasks);               // Notifica al adaptador para refrescar la vista
    }

    /**
     * Permite editar una tarea existente.
     * Abre un cuadro de diálogo donde el usuario puede modificar el texto.
     * @param position
     */
    private void editTask(int position) {
        List<Task> currentTasks = taskDao.getAllTasks();

        // Verifica que la posición sea válida
        if (position < 0 || position >= currentTasks.size()) return;

        Task currentTask = currentTasks.get(position);

        // Crea un campo de texto para editar
        EditText editText = new EditText(this);
        editText.setText(currentTask.getDescription());
        editText.setSingleLine(true);

        // Crea un diálogo de edición
        new AlertDialog.Builder(this)
                .setTitle("Editar tarea")
                .setView(editText)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    String newText = editText.getText().toString().trim();
                    if (!newText.isEmpty()) {
                        currentTask.setDescription(newText);
                        taskDao.updateTask(currentTask);
                        loadTasks(); // Refrescar lista
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    /**
     * Elimina una tarea de la base de datos y actualiza la lista.
     * @param position
     */
    private void deleteTask(int position) {
        List<Task> currentTasks = taskDao.getAllTasks();

        // Verifica que la posición sea válida
        if (position < 0 || position >= currentTasks.size()) return;

        // Obtiene el ID de la tarea a eliminar
        long taskId = currentTasks.get(position).getId();

        // Borra la tarea del almacenamiento
        taskDao.deleteTask(taskId);

        // Refresca la lista del RecyclerView
        loadTasks();
    }
}