package com.example.user.todolist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by user on 07-Nov-16.
 */

public class AddTaskActivity extends AppCompatActivity{
    private Button mAdd,mView;
    private EditText mTitle;
    private EditText mDescription;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mAdd=(Button)findViewById(R.id.add_button);
        mTitle=(EditText)findViewById(R.id.add_title);
        mDescription=(EditText)findViewById(R.id.add_description);
        mView=(Button)findViewById(R.id.view);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper=new DatabaseHelper(getApplicationContext(),"taskDB");
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                dbHelper.onCreate(db);
                dbHelper.add(db,new Task(mTitle.getText().toString(),mDescription.getText().toString()));
                Toast.makeText(getApplicationContext(),"Task Added",Toast.LENGTH_SHORT).show();
                mTitle.setText("");
                mDescription.setText("");
            }
        });
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
