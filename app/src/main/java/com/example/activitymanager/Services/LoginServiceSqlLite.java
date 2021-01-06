package com.example.activitymanager.Services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.activitymanager.Entities.User;
import com.example.activitymanager.ExampleApplication;
import com.example.activitymanager.RoomDatabase.SampleRoomDatabase;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;


public class LoginServiceSqlLite implements LoginService{


    @Inject
    LoginServiceSqlLite(){}




    @Override
    public boolean loginUser(String username, String password) {
        SampleRoomDatabase db = SampleRoomDatabase.getDatabase(ExampleApplication.getAppContext());
        User user = db.userDao().getUser(username);
        return true;
    }

    @Override
    public boolean registerUser(String username, String password) {
        if (username.isEmpty()) {
            Toast.makeText(ExampleApplication.getAppContext(), "Username cant be empty!", Toast.LENGTH_LONG).show();
            return false;
        }
        if (password.length() < 5){
            Toast.makeText(ExampleApplication.getAppContext(), "Password too short! Minimum 5 characters!", Toast.LENGTH_LONG).show();
            return false;
        }
        User user = new User();
        user.UserName = username;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            user.Password = new BigInteger(1,hashedPassword).toString(16);
        }
        catch (Exception e )
        {
        }
        SampleRoomDatabase db = SampleRoomDatabase.getDatabase(ExampleApplication.getAppContext());
        try {
            db.userDao().insert(user);
        }
        catch (Exception e){
            Toast.makeText(ExampleApplication.getAppContext(), "User already exists!",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
