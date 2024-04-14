package com.example.twittard;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    public static final String EXTRA_ACCOUNT = "BLABLABLA";
    private Account account;

    private ImageView toggleBack;
    private ImageView profileBanner;
    private CircleImageView profilePicture;
    private TextView profileFullname;
    private TextView profileNickname;
    private TextView profileType;
    private TextView profileDate;
    private TextView profileFollowing;
    private TextView profileFollower;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toggleBack = findViewById(R.id.toggleBack);
        profileBanner = findViewById(R.id.profileBanner);
        profilePicture = findViewById(R.id.profilePicture);
        profileFullname = findViewById(R.id.profileFullname);
        profileNickname = findViewById(R.id.profileNickname);
        profileType = findViewById(R.id.profileType);
        profileDate = findViewById(R.id.profileDate);
        profileFollowing = findViewById(R.id.profileFollowing);
        profileFollower = findViewById(R.id.profileFollower);

        account = getIntent().getParcelableExtra(EXTRA_ACCOUNT);
        toggleBack.setOnClickListener(v -> {
            finish();
        });

        profileBanner.setImageResource(account.getProfileBanner());
        profilePicture.setImageResource(account.getProfilePhoto());
        profileFullname.setText(account.getFullname());
        profileNickname.setText(account.getUsername());
        profileType.setText(account.getBirthdate());
        profileDate.setText(account.getSignDate());
        profileFollowing.setText(account.getFollowing());
        profileFollower.setText(account.getFollowers());
    }
}