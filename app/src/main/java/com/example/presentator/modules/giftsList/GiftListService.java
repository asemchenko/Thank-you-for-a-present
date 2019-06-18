package com.example.presentator.modules.giftsList;

import com.example.presentator.modules.newsFeed.NewsFeedView;
import com.example.presentator.modules.newsFeed.NewsService;

public class GiftListService extends NewsService {
    public GiftListService(NewsFeedView newsFeedView) {
        super(newsFeedView);
    }

    @Override
    public void loadNews(String curUserUid) {
        super.subscribeFriend(curUserUid);
    }
}
