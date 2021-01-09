package com.example.activitymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.activitymanager.Entities.Activity;
import com.example.activitymanager.RecyclerViewAdapters.ActivityRecyclerViewAdapter;
import com.example.activitymanager.RoomDatabase.SampleRoomDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ViewTrainingsActivity extends AppCompatActivity {

    private ArrayList<Activity> mActivitiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trainings);
        innitList();
    }

    private void innitList(){
        try {
            new GetActivitiesTask(ExampleApplication.LoggedUser.id).execute().get();
        }
        catch (Exception ex){
        }
        innitRecyclerView();
    }

    private void innitRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.activities_RecyclerView);
        recyclerView.setAdapter(new ActivityRecyclerViewAdapter(mActivitiesList,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private class GetActivitiesTask extends AsyncTask<Void,Void,Void>{
        private int mUserId;
        GetActivitiesTask(int userId){
            mUserId = userId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
           mActivitiesList = (ArrayList<Activity>)SampleRoomDatabase.getDatabase(ExampleApplication.getAppContext()).activityDao().getActivities(mUserId);
           Collections.reverse(mActivitiesList);
           return null;
        }
    }
}