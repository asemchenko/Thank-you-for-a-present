package com.example.presentator.model.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String nick;
    private Gender gender;
    private String imageURL;
    // FIXME удоли это поле
    private String mail;
    private List<String> friendsUIDs;

    public User(String name, String nick, Gender gender) {
        this.name = name;
        this.nick = nick;
        this.gender = gender;
        this.friendsUIDs = new ArrayList<>();
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public enum Gender {MALE, FEMALE}

    ;
}
