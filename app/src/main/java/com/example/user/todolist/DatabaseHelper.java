package com.example.user.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by user on 09-Nov-16.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private String dbName;
    private Context context;
    public DatabaseHelper(Context context,String dbName){
        super(context, dbName, null, 1);
        this.dbName=dbName;
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists tasks (title varchar,description varchar,timeCreated varchar);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public ArrayList<Task> getTasksList(SQLiteDatabase db){
        ArrayList<Task> tasks=new ArrayList<Task>();
        Cursor c= db.rawQuery("select * from tasks",null);
        c.moveToFirst();
        Task task;
        String title,description;
        String date;
        while (c.moveToNext()){
            title=c.getString(c.getColumnIndex("title"));
            description=c.getString(c.getColumnIndex("description"));
            date=c.getString(c.getColumnIndex("timeCreated"));
            task=new Task(title,description,date);
            tasks.add(task);
        }
        return tasks;
    }
    public void add(SQLiteDatabase db,Task task){
        db.execSQL("insert into tasks values('" + task.getTitle() + "','"+task.getDescription()+"','"+task.getDate()+"');");
    }
    public void delete(SQLiteDatabase db,String date){
        db.delete("tasks","timeCreated='"+date+"'",null);
    }
}
