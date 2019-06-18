package com.example.presentator.modules.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.presentator.R;
import com.example.presentator.common.Menu;
import com.example.presentator.modules.friends.FriendsActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements ProfileView {
    private ProfileController controller = new ProfileController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bindButtons();
        controller.loadUserInfo();
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

    @Override
    public void setFullName(String fullName) {
        TextView fullNameTw = findViewById(R.id.profile_full_name_et);
        fullNameTw.setText(fullName);
    }

    @Override
    public void setBirthday(String birthdayDate) {
        TextView emailTw = findViewById(R.id.profile_email_tw);
        emailTw.setText(birthdayDate);
    }

    @Override
    public void setEmail(String email) {
        TextView emailTw = findViewById(R.id.profile_email_tw);
        emailTw.setText(email);
    }

    @Override
    public void setUserIcon(String url) {
        CircleImageView userIconImageView = findViewById(R.id.profile_image_view);
        Picasso.with(this).load(url).into(userIconImageView);
    }

    @Override
    public void setNickName(String nickName) {
        TextView profileTw = findViewById(R.id.profile_nickname);
        profileTw.setText(nickName);
    }
}
