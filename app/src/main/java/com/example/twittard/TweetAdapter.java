package com.example.twittard;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private final ArrayList<Tweet> tweets;

    public TweetAdapter(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public TweetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_tweet, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetAdapter.ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.accountPicture.setImageResource(tweet.getAccount().getProfilePhoto());
        holder.accountFullname.setText(tweet.getAccount().getFullname());
        holder.accountUsername.setText(tweet.getAccount().getUsername());
        holder.tweetDate.setText(tweet.getDate());
        holder.tweetComments.setText(tweet.getComment());
        holder.tweetReposts.setText(tweet.getRepost());
        holder.tweetLikes.setText(tweet.getLike());
        holder.tweetViews.setText(tweet.getView());

        if (tweet.getTweet() == null) {
            holder.tweetText.setVisibility(View.GONE);
        } else {
            holder.tweetText.setVisibility(View.VISIBLE);
            holder.tweetText.setText(tweet.getTweet());
        }

        if (tweet.getImage() == 1 && tweet.getUriImage() == null) {
            holder.tweetUriImage.setVisibility(View.GONE);
            holder.tweetDrawablePicture.setVisibility(View.GONE);
        } else if (tweet.getImage() != 1 && tweet.getUriImage() == null) {
            holder.tweetDrawablePicture.setVisibility(View.VISIBLE);
            holder.tweetUriImage.setVisibility(View.GONE);
            holder.tweetDrawablePicture.setImageResource(tweet.getImage());
        } else if (tweet.getImage() == 1 && tweet.getUriImage() != null) {
            holder.tweetDrawablePicture.setVisibility(View.GONE);
            holder.tweetUriImage.setVisibility(View.VISIBLE);
            holder.tweetUriImage.setImageURI(tweet.getUriImage());
        }

        holder.accountPicture.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_ACCOUNT, tweet.getAccount());
            holder.itemView.getContext().startActivity(intent);
        });

        holder.accountFullname.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_ACCOUNT, tweet.getAccount());
            holder.itemView.getContext().startActivity(intent);
        });

        holder.accountUsername.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_ACCOUNT, tweet.getAccount());
            holder.itemView.getContext().startActivity(intent);
        });

        holder.toggleDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Delete Image");
            builder.setMessage("Are you sure you want to delete this image?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tweets.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, getItemCount());
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing, image deletion canceled
                }
            });
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView accountPicture;
        private final TextView accountFullname;
        private final TextView accountUsername;
        private final TextView tweetDate;
        private final TextView tweetComments;
        private final TextView tweetReposts;
        private final TextView tweetLikes;
        private final TextView tweetViews;
        private final ImageView tweetDrawablePicture;
        private final ImageView tweetUriImage;
        private final TextView tweetText;
        private final ImageView toggleDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accountPicture = itemView.findViewById(R.id.accountPicture);
            accountFullname = itemView.findViewById(R.id.accountFullname);
            accountUsername = itemView.findViewById(R.id.accountUsername);
            tweetDate = itemView.findViewById(R.id.tweetDate);
            tweetComments = itemView.findViewById(R.id.tweetComments);
            tweetReposts = itemView.findViewById(R.id.tweetReposts);
            tweetLikes = itemView.findViewById(R.id.tweetLikes);
            tweetViews = itemView.findViewById(R.id.tweetViews);
            tweetDrawablePicture = itemView.findViewById(R.id.tweetDrawablePicture);
            tweetUriImage = itemView.findViewById(R.id.tweetUriImage);
            tweetText = itemView.findViewById(R.id.tweetText);
            toggleDelete = itemView.findViewById(R.id.toggleDelete);
        }
    }
}
