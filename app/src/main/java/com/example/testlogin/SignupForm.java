package com.example.testlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SignupForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle("Signup Form");
    }
}
