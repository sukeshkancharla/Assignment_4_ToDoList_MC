package com.example.user.todolist;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.zip.Inflater;

/**
 * Created by user on 06-Nov-16.
 */

public class TaskListActivity extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view= inflater.inflate(R.layout.recycler_fragment,container,false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DatabaseHelper dbHelper=new DatabaseHelper(getActivity(),"taskDB");
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
        if(dbHelper.getTasksList(db).size()==0){
            TextView noTask=(TextView)view.findViewById(R.id.no_tasks);
            noTask.setText("No Tasks are there.");
        }
        else {
            mAdapter = new RecyclerAdapter(dbHelper.getTasksList(db));
            mRecyclerView.setAdapter(mAdapter);
            TextView noTask=(TextView)view.findViewById(R.id.no_tasks);
            noTask.setText("");
        }
        return view;
    }
    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder>{
        ArrayList<Task> tasks;
        private String date;

        public RecyclerAdapter(ArrayList<Task> tasks){
            this.tasks=tasks;
        }
        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
            RecyclerHolder recyclerHolder=new RecyclerHolder(view);
            return recyclerHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, int position) {
            holder.txt_title.setText(tasks.get(position).getTitle());
            date = tasks.get(position).getDate();
            holder.txt_date.setText(date.toString());
        }

        @Override
        public int getItemCount()
        {
            return tasks.size();
        }


    }
    private class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt_title,txt_date;
        public RecyclerHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txt_title=(TextView)itemView.findViewById(R.id.text_title);
            txt_date=(TextView)itemView.findViewById(R.id.text_date);
        }

        @Override
        public void onClick(View view) {
            TextView text=(TextView) view.findViewById(R.id.text_title);
            Intent intent=new Intent(getActivity(),TaskPagerActivity.class);
            int position=getAdapterPosition();
            intent.putExtra("position",position);
            Toast.makeText(view.getContext(),text.getText().toString() + " clicked!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseHelper dbHelper=new DatabaseHelper(getActivity(),"taskDB");
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
        if(dbHelper.getTasksList(db).size()==0){
            TextView noTask=(TextView)view.findViewById(R.id.no_tasks);
            noTask.setText("No Tasks are there.");
        }
        else {
            mAdapter=new RecyclerAdapter(dbHelper.getTasksList(db));
            mRecyclerView.setAdapter(mAdapter);
            TextView noTask=(TextView)view.findViewById(R.id.no_tasks);
            noTask.setText("");
        }
    }
}
