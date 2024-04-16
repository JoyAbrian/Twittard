package com.example.twittard;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PostingActivity extends AppCompatActivity {
    private ImageView toggleBack;
    private Button togglePost;
    private EditText inputTweet;
    private ImageView imagePreview;
    private ImageView inputImage;
    private Uri selectedImageUri;
    private boolean hasTweet;
    private boolean hasImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        toggleBack = findViewById(R.id.toggleBack);
        togglePost = findViewById(R.id.togglePost);
        inputTweet = findViewById(R.id.inputTweet);
        imagePreview = findViewById(R.id.imagePreview);
        inputImage = findViewById(R.id.inputImage);

        toggleBack.setOnClickListener(v -> {
            finish();
        });

        ActivityResultLauncher<Intent> launcherIntentGallery = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            selectedImageUri = data.getData();
                            if (selectedImageUri != null) {
                                imagePreview.setImageURI(selectedImageUri);
                                hasImage = true;
                            }
                        }
                    }
                }
        );

        inputImage.setOnClickListener(v -> {
            Intent openGallery = new Intent(Intent.ACTION_PICK);
            openGallery.setType("image/*");
            launcherIntentGallery.launch(openGallery);
        });

        inputTweet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateTogglePostButton();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed
            }
        });
    }

    private void updateTogglePostButton() {
        String tweet = inputTweet.getText().toString();
        hasTweet = !tweet.isEmpty();

        if (hasTweet || hasImage) {
            togglePost.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00B2CA")));
            togglePost.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
            togglePost.setOnClickListener(v -> {
                Tweet newTweet = new Tweet(DataSource.accounts.get(6), "0m", tweet, 1, "", "", "", "", selectedImageUri);
                DataSource.tweets.add(0, newTweet);
                Intent intent = new Intent(PostingActivity.this, MainActivity.class);
                startActivity(intent);
            });
            togglePost.setEnabled(true);
        } else {
            togglePost.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2A00B2CA")));
            togglePost.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
            togglePost.setOnClickListener(null);
            togglePost.setEnabled(false);
        }
    }
}