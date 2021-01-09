package com.example.activitymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private TextView helloTextView;
    private TextView noTrainingTextView;
    private ConstraintLayout lastTrainingLayout;
    private TextView lastTrainingTimeTextView;
    private TextView lastTrainingDescriptionTextView;
    private Button viewAllTrainingsButton;
    private Activity lastTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adjustLayoutDependingOnLastTraining();
    }

    public void assignViews(){
        helloTextView = findViewById(R.id.hello_EditText);
        noTrainingTextView = findViewById(R.id.noTraining_TextView);
        lastTrainingLayout = findViewById(R.id.lastTraining_ConstraintLayout);
        lastTrainingTimeTextView = findViewById(R.id.lastTrainingTime_TextView);
        lastTrainingDescriptionTextView = findViewById(R.id.lastTrainingDescription_TextView);
        viewAllTrainingsButton = findViewById(R.id.viewAllTrainings_Button);
        helloTextView.setText("Hello " + ExampleApplication.LoggedUser.UserName + "!");
    }
    public void adjustLayoutDependingOnLastTraining(){
        assignViews();
        try {
            new GetLastActivityTask()
                    .execute()
                    .get();
        }
        catch (Exception ex){
        }
        if (lastTraining != null){
            lastTrainingTimeTextView.setText(lastTraining.activityTime);
            lastTrainingDescriptionTextView.setText(lastTraining.activityText);
            noTrainingTextView.setVisibility(View.GONE);
        }
        else{
            lastTrainingLayout.setVisibility(View.GONE);
            viewAllTrainingsButton.setVisibility(View.GONE);
            noTrainingTextView.setVisibility(View.VISIBLE);
        }
    }


    public void onAddActivityButtonClicked(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }

    public void OnViewAllTrainingsButtonClicked(View view) {
        Intent intent = new Intent(this, ViewTrainingsActivity.class);
        startActivity(intent);
    }

    private class GetLastActivityTask extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... voids) {
            lastTraining = SampleRoomDatabase.getDatabase(ExampleApplication.getAppContext())
                    .activityDao()
                    .getLastActivity(ExampleApplication.LoggedUser.id);
            return null;
        }
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
