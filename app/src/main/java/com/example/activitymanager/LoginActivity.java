package com.example.activitymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.activitymanager.Entities.User;
import com.example.activitymanager.Services.LoginService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginService loginService;
    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.username_EditText);
        passwordEditText = findViewById(R.id.password_EditText);
    }

    public void onRegisterButtonClicked(View view) {

        EditText nameText = findViewById(R.id.username_EditText);
        EditText passwordText = findViewById(R.id.password_EditText);
        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();
        try {
            loginService.registerUser(name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLoginButtonClicked(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        loginService.loginUser(username,password);
    }
}