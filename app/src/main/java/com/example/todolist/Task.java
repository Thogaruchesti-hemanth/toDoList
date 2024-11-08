package com.example.todolist;

public class Task {

    private String taskDescription;

    public Task(String taskDescription){
        this.taskDescription=taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
