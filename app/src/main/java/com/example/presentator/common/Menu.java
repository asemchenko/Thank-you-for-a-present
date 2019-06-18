package com.example.presentator.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.presentator.modules.addNew.PresentAddingActivity;
import com.example.presentator.modules.friends.FriendsActivity;
import com.example.presentator.modules.newsFeed.NewsFeedActivity;

public class Menu {
    public static void goToFeed(AppCompatActivity a) {
        Intent intent = new Intent(a.getApplicationContext(), NewsFeedActivity.class);
        a.startActivity(intent);
        a.finish();
    }

    public static void goToGiftAdding(AppCompatActivity a) {
        Intent intent = new Intent(a.getApplicationContext(), PresentAddingActivity.class);
        a.startActivity(intent);
        a.finish();
    }

    public static void goToProfile(AppCompatActivity a) {
        Intent intent = new Intent(a.getApplicationContext(), FriendsActivity.class);
        a.startActivity(intent);
        a.finish();
    }
}
