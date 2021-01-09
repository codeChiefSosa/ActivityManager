package com.example.activitymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitymanager.Entities.Activity;
import com.example.activitymanager.Entities.User;
import com.example.activitymanager.RoomDatabase.SampleRoomDatabase;

public class AddActivity extends AppCompatActivity {

    private EditText describeActivityEditText;
    private TextView timerTextView;

    private boolean isTimerRunning;
    long init, time, now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        describeActivityEditText = findViewById(R.id.desribeActivity_EditText);
        timerTextView = findViewById(R.id.timer_TextView);
        //init = System.currentTimeMillis();
        //new StartCountingTask().execute();
    }

    public void onTimerButtonClicked(View view) {
        if (isTimerRunning == false){
            isTimerRunning = true;
            init = System.currentTimeMillis();
            //new StartCountingTask().execute();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isTimerRunning) {
                        try {
                            now = System.currentTimeMillis();
                            time = (now - init) / 1000;
                            Thread.sleep(30);
                            new Handler(getMainLooper()).post(() -> timerTextView.setText(DateUtils.formatElapsedTime(time)));
                        } catch (Exception ex) {
                            Log.i("Thread error", ex.getLocalizedMessage());
                        }
                    }
                }
            });
            thread.start();
        }
        else {
            isTimerRunning = false;
        }
    }
    public void onAddActivityButtonClicked(View view){
        String activityText = describeActivityEditText.getText().toString();
        String activityTime = timerTextView.getText().toString();
        if (!activityText.isEmpty()){
            Activity activity = new Activity();
            activity.activityText = activityText;
            activity.activityTime = activityTime;
            activity.userCreatorId = ExampleApplication.LoggedUser.id;
            new AddActivityTask(activity).execute();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else{
            new Handler(getMainLooper()).post(() ->
                    Toast.makeText(this,"Description cant be empty!",Toast.LENGTH_LONG).show());
        }
    }

    private class AddActivityTask extends  AsyncTask<Void,Void,Void>{

        private Activity activity;
        AddActivityTask(Activity activity){
            this.activity = activity;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            SampleRoomDatabase.getDatabase(ExampleApplication.getAppContext()).activityDao().insert(activity);
            return null;
        }
    }

    private class StartCountingTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while (isTimerRunning) {
                try {
                    now = System.currentTimeMillis();
                    time = (now - init) / 1000;
                    Thread.sleep(30);
                    new Handler(getMainLooper()).post(() -> timerTextView.setText(DateUtils.formatElapsedTime(time)));
                } catch (Exception ex) {
                    Log.i("Thread error", ex.getLocalizedMessage());
                }
            }
            return null;
        }
    }
}