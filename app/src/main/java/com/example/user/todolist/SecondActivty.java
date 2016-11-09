package com.example.user.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by user on 06-Nov-16.
 */

public class SecondActivty extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textView=(TextView)findViewById(R.id.text);
        textView.setText(getIntent().getStringExtra("textpassed").toString());
    }
}
