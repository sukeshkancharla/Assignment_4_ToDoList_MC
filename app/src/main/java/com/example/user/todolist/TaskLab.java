package com.example.user.todolist;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskLab {
    private static TaskLab sTaskLab;

    private ArrayList<Task> mTasks;

    public static TaskLab get(Context context) {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab(context);
        }
        return sTaskLab;
    }

    private TaskLab(Context context) {
        /*mTasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Task crime = new Task();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mTasks.add(crime);
        }*/
    }

    public List<Task> getCrimes() {
        return mTasks;
    }

    public Task getCrime(String title) {
        for (Task task : mTasks) {
            if (task.getTitle().equals(title)) {
                return task;
            }
        }
        return null;
    }
}
