package com.example.activitymanager.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.activitymanager.DAOs.ActivityDao;
import com.example.activitymanager.DAOs.UserDao;
import com.example.activitymanager.Entities.Activity;
import com.example.activitymanager.Entities.User;

import javax.inject.Inject;


@Database(entities = {User.class, Activity.class}, version = 2, exportSchema = false)

public abstract class SampleRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ActivityDao activityDao();

    public static volatile SampleRoomDatabase INSTANCE;

    public static SampleRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (SampleRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SampleRoomDatabase.class, "sample_database")
                            //.allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
