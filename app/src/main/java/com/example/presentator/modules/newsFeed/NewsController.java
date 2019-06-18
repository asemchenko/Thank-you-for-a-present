package com.example.presentator.modules.newsFeed;

import com.google.firebase.auth.FirebaseAuth;

public class NewsController {
    private NewsFeedView newsFeedView;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private NewsService newsService;

    public NewsController(NewsFeedView newsFeedView) {
        this.newsFeedView = newsFeedView;
        newsService = new NewsService(newsFeedView);
    }

    public void startObserveFriends() {
        String uid = auth.getCurrentUser().getUid();
        newsService.startObserveFriendEvents(uid);
    }
}