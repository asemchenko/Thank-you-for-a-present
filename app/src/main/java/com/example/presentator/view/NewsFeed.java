package com.example.presentator.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.presentator.R;
import com.example.presentator.adapter.NewsAdapter;
import com.example.presentator.model.entities.News;
import com.example.presentator.model.entities.User;

import java.util.Arrays;
import java.util.Collection;

public class NewsFeed extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        initRecyclerView();
        loadNews();
    }

    private Collection<News> getNews() {
        User user = new User();
        user.setName("Shurik");
        user.setNick("SanekTNT");
        user.setImageURL("https://cdn.arstechnica.net/wp-content/uploads/2016/02/5718897981_10faa45ac3_b-640x624.jpg");
        return Arrays.asList(
                new News(user, 1L, "Iphone4", "0/1000", "10:34",
                        "https://upload.wikimedia.org/wikipedia/commons/c/c9/Moon.jpg"),
                new News(user, 2L, "Iphone4", "20/1000", "May 3",
                        "https://upload.wikimedia.org/wikipedia/commons/c/c9/Moon.jpg"),
                new News(user, 3L, "Iphone4", "500/1000", "Dec 9",
                        "https://drop.ndtv.com/TECH/product_database/images/530201374038PM_635_iPhone_4.png?downsize=770:*&output-quality=70&output-format=webp"));

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
