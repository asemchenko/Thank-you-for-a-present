package com.example.presentator.modules.profile;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.presentator.model.entities.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileService {
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private IProfileController controller;

    public ProfileService(IProfileController controller) {
        this.controller = controller;
    }

    public void loadUserInfo(String uid) {
        db.child("users_new").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User friendInfo = dataSnapshot.getValue(User.class);
                controller.setUserInfo(friendInfo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("NewsFeedActivity", "subscribeFriend: database error occurred", databaseError.toException());
            }
        });
    }
}