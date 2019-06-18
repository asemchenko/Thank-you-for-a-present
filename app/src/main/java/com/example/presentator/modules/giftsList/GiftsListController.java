package com.example.presentator.modules.giftsList;

import com.example.presentator.modules.newsFeed.NewsController;
import com.example.presentator.modules.newsFeed.NewsFeedView;
import com.google.firebase.auth.FirebaseAuth;

public class GiftsListController extends NewsController {
    private String uid;

    public GiftsListController(NewsFeedView newsFeedView) {
        super(newsFeedView);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public GiftsListController(NewsFeedView newsFeedView, String uid) {
        super(newsFeedView);
        this.uid = uid;
    }

    @Override
    public void start() {
        setNewsService(new GiftListService(newsFeedView));
        newsService.loadNews(uid);
    }

    public boolean shouldGiftBeShown() {
        return uid.equals(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }
}