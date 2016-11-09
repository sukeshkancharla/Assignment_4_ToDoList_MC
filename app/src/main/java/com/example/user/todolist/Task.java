package com.example.user.todolist;

import java.util.Date;

/**
 * Created by user on 06-Nov-16.
 */

public class Task {
    private String title;
    private String description;
    private String date;
    public Task(String title,String description){
        this.title=title;
        this.description=description;
        this.date=(new Date()).toString();
    }
    public Task(String title,String description,String date){
        this.title=title;
        this.description=description;
        this.date=date;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
