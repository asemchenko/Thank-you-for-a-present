package com.example.presentator.modules.newsFeed;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.example.presentator.R;
import com.example.presentator.common.Menu;
import com.example.presentator.model.entities.News;

public class NewsFeedActivity extends AppCompatActivity implements NewsFeedView {
    private NewsController newsController = new NewsController(this);
    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;
    private ImageButton addPresentBtn;
    private ImageButton newsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        initFields();
        bindButtons();
        initRecyclerView();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(newsRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        newsRecyclerView.addItemDecoration(dividerItemDecoration);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5F8109")));
        newsController.startObserveFriends();
    }

    private void bindButtons() {
        addPresentBtn.setOnClickListener(view -> Menu.goToGiftAdding(this));
    }

    private void initFields() {
        newsRecyclerView = findViewById(R.id.news_recycler_view);
        addPresentBtn = findViewById(R.id.addPresentButton);
        newsButton = findViewById(R.id.present_add_btn_feed);
    }

    private void initRecyclerView() {
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter();
        newsRecyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void addItem(News news) {
        newsAdapter.addItem(news);
    }

    @Override
    public void updateItem(News news) {
        newsAdapter.updateItem(news);
    }
}