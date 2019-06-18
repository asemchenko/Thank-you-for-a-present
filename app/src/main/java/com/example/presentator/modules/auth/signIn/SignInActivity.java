package com.example.presentator.modules.auth.signIn;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.presentator.R;
import com.example.presentator.modules.auth.signUp.SignUpActivity;
import com.example.presentator.modules.newsFeed.NewsFeedActivity;

public class SignInActivity extends AppCompatActivity implements SignInView {
    private SignInController controller = new SignInController(this);
    private Button signInBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_form);
        getSupportActionBar().setTitle("Join Presentator");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5F8109")));
        initWidgetFields();
        bindButtons();
    }

    private void initWidgetFields() {
        signInBtn = findViewById(R.id.btn_sign_in);
        signUpBtn = findViewById(R.id.addPresentButton);
    }

    private void bindButtons() {
        signInBtn.setOnClickListener(a -> signIn());
        signUpBtn.setOnClickListener(a -> goToSignUp());
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller.checkOnStart();
    }


    private void goToFeed() {
        Intent intent = new Intent(getApplicationContext(), NewsFeedActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToSignUp() {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    private void signIn() {
        String email = ((EditText) findViewById(R.id.presentName)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.description)).getText().toString().trim();
        controller.startLogin(email, password);
    }

    @Override
    public void endLogin() {
        goToFeed();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }
}
