package com.example.activitymanager.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.activitymanager.Entities.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(User user);
    @Query("Select * from user_table where UserName=:userName and Password=:password")
    User getUser(String userName, String password);
}
