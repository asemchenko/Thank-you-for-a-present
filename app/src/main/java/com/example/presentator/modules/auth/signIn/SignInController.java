package com.example.presentator.modules.auth.signIn;

import com.google.firebase.auth.FirebaseAuth;

public class SignInController {
    private SignInView view;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public SignInController(SignInView view) {
        this.view = view;
    }

    public void startLogin(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                view.endLogin();
            } else {
                view.showError("Invalid credentials");
            }
        });
    }

    public void checkOnStart() {
        if (auth.getCurrentUser() != null) {
            view.endLogin();
        }
    }
}
