package com.example.presentator.model.entities;

public class User {
    private String name;
    private String nick;
    private Gender gender;
    private String imageURL;

    public User(String name, String nick, Gender gender, String imageURL) {
        this.name = name;
        this.nick = nick;
        this.gender = gender;
        this.imageURL = imageURL;
    }

    public User(String name, String nick, Gender gender) {
        this.name = name;
        this.nick = nick;
        this.gender = gender;
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

    public enum Gender {MALE, FEMALE}
}
