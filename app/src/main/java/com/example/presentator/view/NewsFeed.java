package com.example.presentator.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.presentator.R;
import com.example.presentator.adapter.NewsAdapter;
import com.example.presentator.model.entities.News;
import com.example.presentator.model.entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class NewsFeed extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;

    private ArrayList allNewsByUser = new ArrayList<News>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        DatabaseReference newsRef = FirebaseDatabase.getInstance().getReference().child("news");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getValue(News.class).getUser().getMail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
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
}
