package com.example.activitymanager;

import android.app.Application;
import android.content.Context;

import com.example.activitymanager.Entities.User;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class ExampleApplication extends Application {

    private static Application mApplication;
    public static Context getAppContext(){
        return mApplication.getApplicationContext();
    }

    public static User LoggedUser;

    @Override
    public void onCreate(){
        super.onCreate();
        mApplication = this;
    }
}
