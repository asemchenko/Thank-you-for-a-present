package com.example.testlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Login Form");

    }
}
