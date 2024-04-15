package com.example.twittard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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

import java.io.IOException;
import java.io.InputStream;

public class PostingActivity extends AppCompatActivity {
    private ImageView toggleBack;
    private Button togglePost;
    private EditText inputTweet;
    private ImageView imagePreview;
    private ImageView inputImage;
    private Uri selectedImageUri;
    private Integer selectedImageResourceId;

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
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                selectedImageUri = data.getData();
                                try {
                                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                    imagePreview.setImageBitmap(bitmap);
                                    inputStream.close();
                                    selectedImageResourceId = getResourceIdFromUri(selectedImageUri);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
        );

        inputImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            launcherIntentGallery.launch(Intent.createChooser(intent, "Choose a picture"));
        });

        if ((!inputTweet.getText().toString().trim().isEmpty() || selectedImageUri != null)) {
            String tweet = !inputTweet.getText().toString().trim().isEmpty() ? inputTweet.getText().toString().trim() : null;
            Integer imageResourceId = selectedImageUri != null ? getResourceIdFromUri(selectedImageUri) : null;
            togglePost.setOnClickListener(v -> {
                DataSource.tweets.add(new Tweet(DataSource.accounts.get(6), "0m", tweet, imageResourceId, "", "", "", ""));
            });
        }
    }

    private int getResourceIdFromUri(Uri uri) {
        String uriString = uri.toString();
        String resourceName = uriString.substring(uriString.lastIndexOf('/') + 1);
        return getResources().getIdentifier(resourceName, "drawable", getPackageName());
    }
}