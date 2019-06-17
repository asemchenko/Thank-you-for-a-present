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
import com.example.presentator.modules.newsFeed.NewsFeedActivity;
import com.example.presentator.modules.auth.signUp.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements SignInView {

    private FirebaseAuth auth;
    private EditText emailEt;
    private EditText passwordEt;
    private Button signInBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_form);
        getSupportActionBar().setTitle("Join Presentator");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5F8109")));
        auth = FirebaseAuth.getInstance();
        initWidgetFields();
        bindButtons();
    }

    private void initWidgetFields() {
        emailEt = findViewById(R.id.presentName);
        passwordEt = findViewById(R.id.description);
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
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            goToFeed();
        }
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
        String email = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                goToFeed();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void endLogin() {
        //todo
    }

    @Override
    public void showError(String error) {
        //todo
    }
}
