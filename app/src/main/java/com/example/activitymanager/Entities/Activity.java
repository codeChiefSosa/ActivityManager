package com.example.activitymanager.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "activity_table")
public class Activity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int userCreatorId;
    public String activityText;
    public String activityTime;
}
