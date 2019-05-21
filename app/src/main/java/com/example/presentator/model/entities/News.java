package com.example.presentator.model.entities;

import java.util.Objects;

public class News {
    private String giftId;
    private Gift gift;
    private String userId;
    private User user;

    public News(Gift gift, User user, String giftId, String userId) {
        this.gift = gift;
        this.user = user;
        this.giftId = giftId;
        this.userId = userId;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(giftId, news.giftId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giftId);
    }
}
