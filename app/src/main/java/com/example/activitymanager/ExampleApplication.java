package com.example.activitymanager;

import android.app.Application;
import android.content.Context;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class ExampleApplication extends Application {

    private static Application mApplication;
    public static Context getAppContext(){
        return mApplication.getApplicationContext();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        mApplication = this;
    }
}
