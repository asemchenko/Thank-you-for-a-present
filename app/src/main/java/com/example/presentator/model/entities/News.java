package com.example.presentator.model.entities;

public class News {
    private Gift gift;
    private User user;

    public News(Gift gift, User user) {
        this.gift = gift;
        this.user = user;
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
}
