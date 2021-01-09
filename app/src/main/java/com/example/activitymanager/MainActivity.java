package com.example.activitymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.activitymanager.Entities.Activity;
import com.example.activitymanager.RecyclerViewAdapters.ActivityRecyclerViewAdapter;
import com.example.activitymanager.RoomDatabase.SampleRoomDatabase;
import com.example.activitymanager.Services.LoginService;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.hello_EditText);
        textView.setText("Hello " + ExampleApplication.LoggedUser.UserName + "!");
    }

    public void onAddActivityButtonClicked(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }

    public void OnViewAllTrainingsButtonClicked(View view) {
        Intent intent = new Intent(this, ViewTrainingsActivity.class);
        startActivity(intent);
    }

    private class AddActivityAsyncTask extends  AsyncTask<Void,Void,Void>{
        private Activity activity;
        AddActivityAsyncTask(Activity activity){
            this.activity = activity;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SampleRoomDatabase.getDatabase(ExampleApplication.getAppContext())
                    .activityDao()
                    .insert(activity);
            return null;
        }
    }
}
