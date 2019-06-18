package com.example.presentator.modules.newsFeed;

import com.google.firebase.auth.FirebaseAuth;

public class NewsController {
    protected NewsService newsService;
    protected NewsFeedView newsFeedView;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public NewsController(NewsFeedView newsFeedView) {
        this.newsFeedView = newsFeedView;
        newsService = new NewsService(newsFeedView);
    }

    public void start() {
        String uid = auth.getCurrentUser().getUid();
        newsService.loadNews(uid);
    }

    protected void setNewsService(NewsService service) {
        newsService = service;
    }
}