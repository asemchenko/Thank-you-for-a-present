package com.example.presentator.modules.auth.signUp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.presentator.R;
import com.example.presentator.model.entities.User;
import com.example.presentator.modules.newsFeed.NewsFeedActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText fullNameEt;
    private EditText usernameEt;
    private EditText emailEt;
    private EditText passwordEt;
    private EditText passwordConfirmationEt;
    private RadioGroup genderRadioGroup;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);
        getSupportActionBar().setTitle("Sign up");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5F8109")));
        initWidgetFields();
        bindButtons();
    }

    private void initWidgetFields() {
        fullNameEt = findViewById(R.id.sign_up_et_full_name);
        usernameEt = findViewById(R.id.sign_up_et_username);
        emailEt = findViewById(R.id.sign_up_et_email);
        passwordEt = findViewById(R.id.description);
        passwordConfirmationEt = findViewById(R.id.sign_up_et_password_confirmation);
        genderRadioGroup = findViewById(R.id.sign_up_radio_group_gender);
        signUpBtn = findViewById(R.id.sign_up_btn_sign_up);
    }

    private void bindButtons() {
        signUpBtn.setOnClickListener(view -> {
            signUp();
        });
    }

    private void signUp() {
        // FIXME wrap all below to User class
        String password = passwordEt.getText().toString().trim();
        String passwordConfirmation = passwordConfirmationEt.getText().toString().trim();
        if (!password.equals(passwordConfirmation)) {
            showPasswordsNotSameError();
            return;
        }
        User.Gender gender = getSelectedGender();
        if (gender == null) {
            showGenderRequiredError();
            return;
        }
        String fullName = fullNameEt.getText().toString().trim();
        String username = usernameEt.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        User user = new User(fullName, username, gender);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser currentUser = auth.getCurrentUser();
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference users = db.getReference("users_new"); // FIXME tmp rename
                users.child(currentUser.getUid()).setValue(user);
                goToFeed();
            } else {
                String cause = "";
                if (task.getException() != null) {
                    cause = task.getException().getLocalizedMessage();
                }
                showErrMsgWithToast("Sorry, did not work: " + cause);
            }
        });
    }

    private void showGenderRequiredError() {
        showErrMsgWithToast("Gender required");
    }

    private User.Gender getSelectedGender() {
        int selectedRadioBtnId = genderRadioGroup.getCheckedRadioButtonId();
        switch (selectedRadioBtnId) {
            case R.id.sign_up_rb_gender_male:
                return User.Gender.MALE;
            case R.id.sign_up_rb_gender_female:
                return User.Gender.FEMALE;
            default:
                return null;
        }
    }


    private void showPasswordsNotSameError() {
        showErrMsgWithToast("Passwords are not same");
    }

    private void showErrMsgWithToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void goToFeed() {
        Intent intent = new Intent(this, NewsFeedActivity.class);
        startActivity(intent);
        finish();
    }
}
