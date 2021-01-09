package com.example.activitymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitymanager.AsyncResponses.AsyncResponse;
import com.example.activitymanager.Entities.User;
import com.example.activitymanager.Services.LoginService;

import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginService loginService;
    EditText usernameEditText;
    EditText passwordEditText;
    TextView createdUserTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.username_EditText);
        passwordEditText = findViewById(R.id.password_EditText);
        createdUserTextView = findViewById(R.id.accountCreated_TextView);
    }

    public void onRegisterButtonClicked(View view) {

        EditText nameText = findViewById(R.id.username_EditText);
        EditText passwordText = findViewById(R.id.password_EditText);
        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();
        boolean registerSuccessful = false;

        try{
             registerSuccessful = new RegisterUserTask(name, password)
                     .execute()
                     .get();
        }
        catch (Exception ex) {
        }
        if (registerSuccessful){
            createdUserTextView.setAlpha(1f);
            createdUserTextView.setVisibility(View.VISIBLE);
            createdUserTextView.animate()
                    .alpha(0f)
                    .setDuration(5000);
        }
    }

    public void onLoginButtonClicked(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean loginSuccessful = false;
        try {
            loginSuccessful = new LoginUserTask(username,password)
                    .execute()
                    .get();
        }
        catch (Exception ex){
        }
        if(loginSuccessful){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        }

    private class RegisterUserTask extends AsyncTask<Void,Void,Boolean>
    {
        private String username;
        private String password;
        RegisterUserTask(String username, String password){
            this.username = username;
            this.password = password;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return loginService.registerUser(username, password);
        }
    }
    private class LoginUserTask extends AsyncTask<Void,Void,Boolean>{

        private String username;
        private String password;
        LoginUserTask(String username, String password){
            this.username = username;
            this.password = password;
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            return loginService.loginUser(username,password);
        }
    }
}