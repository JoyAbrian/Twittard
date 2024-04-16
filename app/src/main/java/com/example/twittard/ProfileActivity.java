package com.example.twittard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

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

        LinearLayout tweetList = findViewById(R.id.tweetList);

        ArrayList<Tweet> accountTweets = new ArrayList<>();
        for (Tweet tweet : DataSource.tweets) {
            if (tweet.getAccount().getUsername().equals(account.getUsername())) {
                accountTweets.add(tweet);
            }
        }

        int tweetSize = accountTweets.size();
        for (int i = 0; i < tweetSize; i++) {
            View tweetView = LayoutInflater.from(this).inflate(R.layout.template_tweet, null);

            CircleImageView accountPicture = tweetView.findViewById(R.id.accountPicture);
            TextView accountFullname = tweetView.findViewById(R.id.accountFullname);
            TextView accountUsername = tweetView.findViewById(R.id.accountUsername);
            TextView tweetDate = tweetView.findViewById(R.id.tweetDate);
            TextView tweetText = tweetView.findViewById(R.id.tweetText);
            ImageView tweetDrawablePicture = tweetView.findViewById(R.id.tweetDrawablePicture);
            ImageView tweetUriImage = tweetView.findViewById(R.id.tweetUriImage);
            TextView tweetComments = tweetView.findViewById(R.id.tweetComments);
            TextView tweetReposts = tweetView.findViewById(R.id.tweetReposts);
            TextView tweetLikes = tweetView.findViewById(R.id.tweetLikes);
            TextView tweetViews = tweetView.findViewById(R.id.tweetViews);

            accountPicture.setImageResource(accountTweets.get(i).getAccount().getProfilePhoto());
            accountFullname.setText(accountTweets.get(i).getAccount().getFullname());
            accountUsername.setText(accountTweets.get(i).getAccount().getUsername());
            tweetDate.setText(accountTweets.get(i).getDate());
            tweetText.setText(accountTweets.get(i).getTweet());

            if (accountTweets.get(i).getTweet() == null) {
                tweetText.setVisibility(View.GONE);
            } else {
                tweetText.setVisibility(View.VISIBLE);
                tweetText.setText(accountTweets.get(i).getTweet());
            }

            if (accountTweets.get(i).getImage() == 1 && accountTweets.get(i).getUriImage() == null) {
                tweetUriImage.setVisibility(View.GONE);
                tweetDrawablePicture.setVisibility(View.GONE);
            } else if (accountTweets.get(i).getImage() != 1 && accountTweets.get(i).getUriImage() == null) {
                tweetDrawablePicture.setVisibility(View.VISIBLE);
                tweetUriImage.setVisibility(View.GONE);
                tweetDrawablePicture.setImageResource(accountTweets.get(i).getImage());
            } else if (accountTweets.get(i).getImage() == 1 && accountTweets.get(i).getUriImage() != null) {
                tweetDrawablePicture.setVisibility(View.GONE);
                tweetUriImage.setVisibility(View.VISIBLE);
                tweetUriImage.setImageURI(accountTweets.get(i).getUriImage());
            }

            tweetComments.setText(accountTweets.get(i).getComment());
            tweetReposts.setText(accountTweets.get(i).getRepost());
            tweetLikes.setText(accountTweets.get(i).getLike());
            tweetViews.setText(accountTweets.get(i).getView());

            tweetList.addView(tweetView);
        }
    }
}