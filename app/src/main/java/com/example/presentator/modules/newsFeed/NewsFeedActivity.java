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
    protected NewsController newsController;
    protected NewsAdapter newsAdapter;
    private RecyclerView newsRecyclerView;
    private ImageButton addPresentBtn;
    private ImageButton newsButton;
    private ImageButton accountButton;

    protected void createController() {
        newsController = new NewsController(this);
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        initFields();
        bindButtons();
        createController();
        initRecyclerView();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(newsRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        newsRecyclerView.addItemDecoration(dividerItemDecoration);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5F8109")));
        getSupportActionBar().setTitle("News");
        newsController.start();
    }

    private void bindButtons() {
        addPresentBtn.setOnClickListener(view -> Menu.goToGiftAdding(this));
        accountButton.setOnClickListener(view -> Menu.goToProfile(this));

    }

    private void initFields() {
        newsRecyclerView = findViewById(R.id.news_recycler_view);
        addPresentBtn = findViewById(R.id.addPresentButton);
        accountButton = findViewById(R.id.account_button);
        newsButton = findViewById(R.id.profile_feed_btn);
    }

    private void initRecyclerView() {
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        createAdapter();
        newsRecyclerView.setAdapter(newsAdapter);
    }

    protected void createAdapter() {
        newsAdapter = new NewsAdapter();
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