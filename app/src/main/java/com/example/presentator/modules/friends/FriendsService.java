package com.example.presentator.modules.friends;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.presentator.model.entities.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class FriendsService {

    private FriendsAdapter friendsAdapter;
    private DatabaseReference databaseReference;

    public FriendsService(FriendsAdapter friendsAdapter) {
        this.friendsAdapter = friendsAdapter;
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void getFriendsByUser(FirebaseUser user) {
        databaseReference.child("friends").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    String friendUid = d.getValue(String.class);
                    getFriendFromUIDAndPutItInUserList(friendUid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FriendsActivity",
                        "startObserveFriendsEvents: database error occurred",
                        databaseError.toException());
            }
        });
    }

    private void getFriendFromUIDAndPutItInUserList(String uid){
        databaseReference.child("users_new").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                friendsAdapter.addUserUid(user, uid);
                friendsAdapter.addItem(user, true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FriendsActivity",
                        "startObserveFriendsEvents: database error occurred",
                        databaseError.toException());
            }
        });
    }

    public void getUsersByNickname(String nickname) {
        databaseReference.child("users_new").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    User user = d.getValue(User.class);
                    if(user.getNick().contains(nickname)){
                        friendsAdapter.addItem(Collections.singleton(user));
                        friendsAdapter.addUserUid(user, d.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FriendsActivity",
                        "startObserveFriendsEvents: database error occurred",
                        databaseError.toException());
            }
        });
    }
}
