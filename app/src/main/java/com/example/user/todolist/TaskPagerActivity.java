package com.example.user.todolist;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by user on 06-Nov-16.
 */

public class TaskPagerActivity extends AppCompatActivity{
    private TaskAdapter mTaskAdapter;
    private ViewPager mViewPager;
    private int position;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_pager);
        position=(int)getIntent().getIntExtra("position",0);
        FragmentManager fragmentManager=getSupportFragmentManager();
        mTaskAdapter=new TaskAdapter(fragmentManager);
        mViewPager=(ViewPager)findViewById(R.id.view_pager);
        mViewPager.setAdapter(mTaskAdapter);
        mViewPager.setCurrentItem(position);
    }

    private class TaskAdapter extends FragmentPagerAdapter{
        public TaskAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }
        @Override
        public Fragment getItem(int position) {
            return TaskFragment.newInstance(position);
        }
        @Override
        public int getCount() {
            dbHelper=new DatabaseHelper(getApplicationContext(),"taskDB");
            db=dbHelper.getWritableDatabase();
            dbHelper.onCreate(db);
            return dbHelper.getTasksList(db).size();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fragmentManager=getSupportFragmentManager();
        mTaskAdapter=new TaskAdapter(fragmentManager);
        mViewPager.setAdapter(mTaskAdapter);
        mViewPager.setCurrentItem(position);
    }
}
