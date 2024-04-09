package com.example.twittard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView footerHome, footerSearch, footerCommunity, footerNotification, footerMail; // Footer Items

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        footerHome = findViewById(R.id.footerHome);
        footerSearch = findViewById(R.id.footerSearch);
        footerCommunity = findViewById(R.id.footerCommunity);
        footerNotification = findViewById(R.id.footerNotification);
        footerMail = findViewById(R.id.footerMail);
    }
}