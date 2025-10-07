package com.example.todo;

public final class TaskContract {
    private TaskContract() {}

    public static class TaskEntry {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DESCRIPTION = "description";
    }
}
