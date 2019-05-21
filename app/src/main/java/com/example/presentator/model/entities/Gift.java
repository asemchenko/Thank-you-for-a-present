package com.example.presentator.model.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gift {
    private String presentName;
    private String description;
    private String moneyCollected;
    private long creationDate;
    private String presentImageURL;

    public Gift(String presentName, String description, String moneyCollected, long creationDate, String presentImageURL) {
        this.presentName = presentName;
        this.description = description;
        this.moneyCollected = moneyCollected;
        this.creationDate = creationDate;
        this.presentImageURL = presentImageURL;
    }

    public Gift(String presentName, String description, String moneyCollected, String presentImageURL) {
        this.presentName = presentName;
        this.description = description;
        this.moneyCollected = moneyCollected;
        this.presentImageURL = presentImageURL;
        this.creationDate = new Date().getTime();
    }

    public String getPresentName() {
        return presentName;
    }

    public void setPresentName(String presentName) {
        this.presentName = presentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String stringCreatedDate() {
        SimpleDateFormat.getInstance();
        DateFormat dateFormat = SimpleDateFormat.getInstance();// FIXME new SimpleDateFormat("yyyy-mm-dd hh:mm");
        return dateFormat.format(new Date(creationDate));
    }
}
