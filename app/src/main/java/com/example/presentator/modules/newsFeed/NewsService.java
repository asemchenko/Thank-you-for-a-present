package com.example.presentator.modules.newsFeed;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.presentator.model.entities.Gift;
import com.example.presentator.model.entities.News;
import com.example.presentator.model.entities.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewsService {
    private DatabaseReference db;
    private NewsFeedView newsFeedView;

    public NewsService(NewsFeedView newsFeedView) {
        db = FirebaseDatabase.getInstance().getReference();
        this.newsFeedView = newsFeedView;
    }

    public void startObserveFriendEvents(String curUserUid) {
        db.child("friends").child(curUserUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    String friendUid = d.getValue(String.class);
                    subscribeFriend(friendUid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("NewsFeedActivity", "startObserveFriendsEvents: database error occurred", databaseError.toException());
            }
        });
    }

    private void subscribeFriend(final String friendUid) {
        db.child("users_new").child(friendUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User friendInfo = dataSnapshot.getValue(User.class);
                subscribeForFriend(friendInfo, friendUid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("NewsFeedActivity", "subscribeFriend: database error occurred", databaseError.toException());
            }
        });

    }

    private void subscribeForFriend(final User friend, final String friendUid) {
        db.child("gifts").child(friendUid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Gift gift = dataSnapshot.getValue(Gift.class);
                String giftId = dataSnapshot.getKey();
                newsFeedView.addItem(new News(gift, friend, giftId, friendUid));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Gift gift = dataSnapshot.getValue(Gift.class);
                String giftId = dataSnapshot.getKey();
                News newNews = new News(gift, friend, giftId, friendUid);
                newsFeedView.updateItem(newNews);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
