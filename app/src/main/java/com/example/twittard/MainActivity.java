package com.example.twittard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView footerHome, footerSearch, footerCommunity, footerNotification, footerMail; // Footer Items
    Button togglePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Footer Items
        footerHome = findViewById(R.id.footerHome);
        footerSearch = findViewById(R.id.footerSearch);
        footerCommunity = findViewById(R.id.footerCommunity);
        footerNotification = findViewById(R.id.footerNotification);
        footerMail = findViewById(R.id.footerMail);
        togglePost = findViewById(R.id.togglePost);

        homeFragment();

        footerHome.setOnClickListener(v -> { homeFragment(); });
        footerSearch.setOnClickListener(v -> { searchFragment(); });
        footerCommunity.setOnClickListener(v -> { communityFragment(); });
        footerNotification.setOnClickListener(v -> { notificationFragment(); });
        footerMail.setOnClickListener(v -> { mailFragment(); });
        togglePost.setOnClickListener(v -> { postingActivity(); });
    }

    private void inactiveFooter() {
        footerHome.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.footer_home));
        footerSearch.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.footer_search));
        footerCommunity.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.footer_community));
        footerNotification.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.footer_notification));
        footerMail.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.footer_mail));
    }

    private void homeFragment() {
        inactiveFooter();
        footerHome.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.footer_home_active));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new HomeFragment())
                .commit();
    }

    private void searchFragment() {
        inactiveFooter();
        footerSearch.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.footer_search_active));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new SearchFragment())
                .commit();
    }

    private void communityFragment() {
        inactiveFooter();
        footerCommunity.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.footer_community_active));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new CommunityFragment())
                .commit();
    }

    private void notificationFragment() {
        inactiveFooter();
        footerNotification.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.footer_notification_active));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new NotificationFragment())
                .commit();
    }

    private void mailFragment() {
        inactiveFooter();
        footerMail.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.footer_mail_active));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new MailFragment())
                .commit();
    }

    private void postingActivity() {
        Intent intent = new Intent(this, PostingActivity.class);
        startActivity(intent);
    }
}