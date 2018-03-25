package com.example.android.yos_1202154119_modul5;

/**
 * Created by Yosafat Dhimas on 25/03/2018.
 */

public class Todo {
    String todo;
    String description;
    String priority;

    public Todo(String todo, String desc, String priority){
        this.todo = todo;
        this.description = desc;
        this.priority = priority;
    }

    //method getter dan setter

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getPrior() {
        return priority;
    }

    public void setPrior(String priority) {
        this.priority = priority;
    }
}
