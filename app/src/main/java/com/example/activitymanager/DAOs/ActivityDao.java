package com.example.activitymanager.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.activitymanager.Entities.Activity;
import com.example.activitymanager.Entities.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

@Dao
public interface ActivityDao {
    @Insert
    void insert(Activity activity);
    @Query("Select * from Activity where userCreatorId=:userId")
    List<Activity> getActivities(int userId);
    @Query("SELECT * FROM Activity WHERE userCreatorId=:userId ORDER BY id DESC LIMIT 1;")
    Activity getLastActivity(int userId);
}
