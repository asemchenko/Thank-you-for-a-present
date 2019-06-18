package com.example.presentator.modules.newsFeed;

import com.example.presentator.model.entities.News;

public interface NewsFeedView {
    void addItem(News news);

    void updateItem(News news);
}