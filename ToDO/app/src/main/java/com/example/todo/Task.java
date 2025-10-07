package com.example.todo;
    public class Task {
        private long id;
        private String description;
        public Task(String description) {
            this.id = System.currentTimeMillis();
            this.description = description;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }