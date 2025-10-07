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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private TaskDao taskDao;
    private EditText editTextTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        Button btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerViewTasks);
        taskDao = new TaskDao(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 1. Primero crea el adaptador (aunque la lista esté vacía)
        List<Task> emptyList = new ArrayList<>();
        adapter = new TaskAdapter(emptyList, new TaskAdapter.OnTaskActionListener() {
            @Override
            public void onEditClick(int position) {
                editTask(position);
            }

            @Override
            public void onDeleteClick(int position) {
                deleteTask(position);
            }
        });

        recyclerView.setAdapter(adapter);

        // 2. AHORA sí carga las tareas y actualiza el adaptador
        loadTasks();

        // 3. Configura el botón
        btnAdd.setOnClickListener(v -> {
            String taskText = editTextTask.getText().toString().trim();
            if (!taskText.isEmpty()) {
                Task newTask = new Task(taskText);
                taskDao.insertTask(newTask);
                loadTasks(); // Recargar y notificar
                editTextTask.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Escribe una tarea", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTasks() {
        List<Task> tasks = taskDao.getAllTasks();
        adapter.updateTasks(tasks);
    }

    private List<Task> getTasksFromAdapter() {
        // Hack temporal: como no exponemos la lista, la volvemos a cargar
        // Mejor práctica: mantener una lista local o usar un ViewModel (pero en Java simple)
        return taskDao.getAllTasks();
    }

    private void editTask(int position) {
        List<Task> currentTasks = taskDao.getAllTasks();
        if (position < 0 || position >= currentTasks.size()) return;

        Task currentTask = currentTasks.get(position);
        EditText editText = new EditText(this);
        editText.setText(currentTask.getDescription());
        editText.setSingleLine(true);

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

    private void deleteTask(int position) {
        List<Task> currentTasks = taskDao.getAllTasks();
        if (position < 0 || position >= currentTasks.size()) return;

        long taskId = currentTasks.get(position).getId();
        taskDao.deleteTask(taskId);
        loadTasks(); // Refrescar
    }
}