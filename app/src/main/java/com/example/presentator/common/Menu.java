package com.example.presentator.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.presentator.modules.newsFeed.NewsFeedActivity;
import com.example.presentator.modules.addNew.PresentAdding;

public class Menu {
    public static void goToFeed(AppCompatActivity a) {
        Intent intent = new Intent(a.getApplicationContext(), NewsFeedActivity.class);
        a.startActivity(intent);
        a.finish();
    }

    public static void goToGiftAdding(AppCompatActivity a) {
        Intent intent = new Intent(a.getApplicationContext(), PresentAdding.class);
        a.startActivity(intent);
        a.finish();
    }

    public static void goToProfile(AppCompatActivity a) {
        // TODO
//        new Intent(a.getApplicationContext(), )
    }
}