package com.example.todo;

// TaskDao.java
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    private TaskDbHelper dbHelper;

    public TaskDao(Context context) {
        dbHelper = new TaskDbHelper(context);
    }

    public long insertTask(Task task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_ID, task.getId());
        values.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, task.getDescription());
        long newRowId = db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                TaskContract.TaskEntry.COLUMN_ID + " DESC" // Últimas primero
        );

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_ID));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DESCRIPTION));
            Task task = new Task(description);
            task.setId(id);
            tasks.add(task);
        }
        cursor.close();
        db.close();
        return tasks;
    }

    public int updateTask(Task task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, task.getDescription());
        int rows = db.update(
                TaskContract.TaskEntry.TABLE_NAME,
                values,
                TaskContract.TaskEntry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(task.getId())}
        );
        db.close();
        return rows;
    }

    public int deleteTask(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete(
                TaskContract.TaskEntry.TABLE_NAME,
                TaskContract.TaskEntry.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
        db.close();
        return rows;
    }
}
