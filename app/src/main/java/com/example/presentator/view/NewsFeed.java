package com.example.presentator.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.presentator.R;
import com.example.presentator.adapter.NewsAdapter;
import com.example.presentator.model.entities.News;
import com.example.presentator.model.entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;

public class NewsFeed extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;
    private DatabaseReference db;
    private ArrayList<News> allNewsByUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        DatabaseReference newsRef = FirebaseDatabase.getInstance().getReference().child("news");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    // FIXME почисти этот треш
                    if (ds.getValue(News.class).getUser().getMail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                        allNewsByUser.add(ds.getValue(News.class));
                    }
                }
                loadNews();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        newsRef.addListenerForSingleValueEvent(valueEventListener);

        initRecyclerView();
    }


    private void initFields() {
        // TODO вынеси все поля сюда
        db = FirebaseDatabase.getInstance().getReference();
    }

    private Collection<News> getNews() {
        return allNewsByUser;
    }

    private void loadNews() {
        Collection<News> news = getNews();
        newsAdapter.setItems(news);
    }

    private void initRecyclerView() {
        newsRecyclerView = findViewById(R.id.news_recycler_view);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsAdapter = new NewsAdapter();
        newsRecyclerView.setAdapter(newsAdapter);
    }

    private void startObserveFriendEvents() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        db.child("users").child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void subscribeForFriend(String friendUid) {
        db.child("gifts").child(friendUid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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

    private void addNews()
}
