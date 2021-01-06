package com.example.activitymanager.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table", indices = {@Index(value = {"UserName"},
        unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String Password;
    @NonNull
    public String UserName;

}
