package com.example.presentator.model.entities;


import java.util.Date;

public class News {
    // FIXME надо сделать чтоб оно не сохранялось в БД, а выставлялось уже после выгрузки из БД
    private String userUid;
    private String presentName;
    private String description;
    private String moneyCollected;
    private long creationDate;
    private String presentImageURL;

    public News(String userUid, String presentName, String moneyCollected, String presentImageURL) {
        this.userUid = userUid;
        this.presentName = presentName;
        this.moneyCollected = moneyCollected;
        this.presentImageURL = presentImageURL;
        this.creationDate = new Date().getTime();
    }

    public News() {
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
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

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public String getPresentImageURL() {
        return presentImageURL;
    }

    public void setPresentImageURL(String presentImageURL) {
        this.presentImageURL = presentImageURL;
    }
}
