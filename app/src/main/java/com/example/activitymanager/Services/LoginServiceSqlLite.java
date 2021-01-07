package com.example.activitymanager.Services;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
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
import io.reactivex.Single;


public class LoginServiceSqlLite implements LoginService{


    @Inject
    LoginServiceSqlLite(){}




    @Override
    public boolean loginUser(String username, String password) {
        SampleRoomDatabase db = SampleRoomDatabase.getDatabase(ExampleApplication.getAppContext());
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            password = new BigInteger(1,hashedPassword).toString(16);
        }
        catch (Exception e )
        {
            return false;
        }
        try {
            Single<User> user = db.userDao().getUser(username, password);
            ExampleApplication.LoggedUser = user.blockingGet();
            return true;
        }
        catch (Exception ex){
            new Handler(Looper.getMainLooper()).post(()->
                    Toast.makeText(ExampleApplication.getAppContext(),"Wrong username or password!", Toast.LENGTH_LONG).show());
            return  false;
            }
        }


    @Override
    public boolean registerUser(String username, String password) {
        if (username.isEmpty()) {
            new Handler(Looper.getMainLooper()).post(() ->
                    Toast.makeText(ExampleApplication.getAppContext(), "Username cant be empty!", Toast.LENGTH_LONG).show());
            return false;
        }
        if (password.length() < 5){
            new Handler(Looper.getMainLooper()).post(() ->
                    Toast.makeText(ExampleApplication.getAppContext(), "Password too short! Minimum 5 characters!", Toast.LENGTH_LONG).show());
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
            new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(ExampleApplication.getAppContext(), "User already exists!",Toast.LENGTH_LONG).show());
            return false;
        }
        return true;
    }
}
