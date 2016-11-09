package com.example.user.todolist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static String[] countries={"India","USA"};
    public static String[] capitals={"Delhi","Washington"};
    //public static ArrayList<Task> tasks=new ArrayList<Task>();



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add_task){
            Intent intent = new Intent(this,AddTaskActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper dbHelper=new DatabaseHelper(getApplicationContext(),"taskDB");
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);

            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment=fm.findFragmentById(R.id.fragment_container);
            if(fragment==null){
                fragment = createFragment();
                fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
            }

            /*if(tasks.size()==0){
            tasks.add(new Task("India","New Delhi",(new Date()).toString()));
        }*/



        /*recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerAdapter=new RecyclerAdapter(countries,capitals);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);*/
    }
    private Fragment createFragment()
    {
        return new TaskListActivity();
    }
}
