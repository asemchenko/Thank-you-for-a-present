package com.example.presentator.model.entities;


public class News {
    private User user;
    private long id;
    private String presentName;
    private String moneyCollected;
    private String creationDate;

    public String getPresentImageURL() {
        return presentImageURL;
    }

    public void setPresentImageURL(String presentImageURL) {
        this.presentImageURL = presentImageURL;
    }

    private String presentImageURL;

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public News(User user, long id, String presentName, String moneyCollected,
                String creationDate, String presentImageURL) {
        this.user = user;
        this.id = id;
        this.presentName = presentName;
        this.moneyCollected = moneyCollected;
        this.creationDate = creationDate;
        this.presentImageURL = presentImageURL;
    }

    public News() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPresentName() {
        return presentName;
    }

    public void setPresentName(String presentName) {
        this.presentName = presentName;
    }

    public String getMoneyCollected() {
        return moneyCollected;
    }

    public void setMoneyCollected(String moneyCollected) {
        this.moneyCollected = moneyCollected;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
