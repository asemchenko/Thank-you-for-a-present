package com.example.presentator.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.presentator.R;
import com.example.presentator.view.NewsFeed;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailEt;
    private EditText passwordEt;
    private Button signInBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Login Form");
        auth = FirebaseAuth.getInstance();
        initWidgetFields();
        bindButtons();
    }

    private void initWidgetFields() {
        emailEt = findViewById(R.id.et_email);
        passwordEt = findViewById(R.id.et_password);
        signInBtn = findViewById(R.id.btn_sign_in);
        signUpBtn = findViewById(R.id.btn_sign_up);
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
        Intent intent = new Intent(getApplicationContext(), NewsFeed.class);
        startActivity(intent);
        finish();
    }

    private void goToSignUp() {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    private void signIn() {
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                goToFeed();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
