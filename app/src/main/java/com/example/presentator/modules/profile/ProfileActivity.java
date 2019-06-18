package com.example.presentator.modules.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.presentator.R;
import com.example.presentator.common.Menu;
import com.example.presentator.modules.friends.FriendsActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bindButtons();
    }

    private void bindButtons() {
        ImageButton presentAddBtn = (ImageButton) findViewById(R.id.profile_add_present_btn);
        presentAddBtn.setOnClickListener((view) -> {
            Menu.goToFeed(this);
        });

        ImageButton feedBtn = (ImageButton) findViewById(R.id.profile_feed_btn);
        feedBtn.setOnClickListener((view) -> {
            Menu.goToFeed(this);
        });
        Button btnFriends = (Button) findViewById(R.id.button_friends);
        btnFriends.setOnClickListener(view -> {
            goToFriendsActivity();
        });
    }

    private void goToFriendsActivity() {
        Intent intent = new Intent(this, FriendsActivity.class);
        startActivity(intent);
    }
}
