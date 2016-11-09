package com.example.user.todolist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user on 06-Nov-16.
 */

public class TaskFragment extends Fragment{
    private static final String ARG_TASK_ID = "task_id";
    private Button mDelete;
    private Task mTask;
    private TextView mTitleField;
    private TextView mDescriptionField;
    private TextView mDateCreated;
    private int taskId;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    public static TaskFragment newInstance(int taskId) {
        Bundle args = new Bundle();
        args.putInt(ARG_TASK_ID, taskId);
        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskId = (int) getArguments().getInt(ARG_TASK_ID);
        dbHelper=new DatabaseHelper(getActivity(),"taskDB");
        db=dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
        mTask = dbHelper.getTasksList(db).get(taskId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_task_details, container, false);

        mTitleField = (TextView) v.findViewById(R.id.title_text);
        mTitleField.setText(mTask.getTitle());
        mDateCreated = (TextView) v.findViewById(R.id.description_text);
        mDateCreated.setText(mTask.getDescription().toString());
        mDescriptionField = (TextView) v.findViewById(R.id.date);
        mDescriptionField.setText(mTask.getDate().toString());
        mDelete=(Button)v.findViewById(R.id.delete_task);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper=new DatabaseHelper(getActivity(),"taskDB");
                db=dbHelper.getWritableDatabase();
                dbHelper.onCreate(db);
                dbHelper.delete(db,mTask.getDate());
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}


