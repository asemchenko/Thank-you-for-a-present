package com.example.presentator.modules.giftsList;

import com.example.presentator.modules.newsFeed.NewsAdapter;
import com.example.presentator.modules.newsFeed.NewsFeedActivity;

public class GiftsListActivity extends NewsFeedActivity {
    @Override
    protected void createController() {
        String uid = getIntent().getStringExtra("uid");
        if (uid != null) {
            newsController = new GiftsListController(this, uid);
        } else {
            newsController = new GiftsListController(this);
        }
    }

    @Override
    protected void createAdapter() {
        newsAdapter = new NewsAdapter(((GiftsListController) newsController).shouldGiftBeShown());
    }
}
