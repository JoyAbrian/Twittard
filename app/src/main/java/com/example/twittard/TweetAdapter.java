package com.example.twittard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
            holder.tweetText.setText(tweet.getTweet());
        }

        if (tweet.getUriImage() == null) {
            holder.tweetImage.setVisibility(View.GONE);
            holder.tweetPicture.setVisibility(View.VISIBLE);
            holder.tweetPicture.setImageResource(tweet.getImage());
        } else {
            holder.tweetPicture.setVisibility(View.GONE);
            holder.tweetImage.setVisibility(View.VISIBLE);
            holder.tweetImage.setImageURI(tweet.getUriImage());
        }

        holder.accountPicture.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra(ProfileActivity.EXTRA_ACCOUNT, tweet.getAccount());
            holder.itemView.getContext().startActivity(intent);
        });

        holder.toggleDelete.setOnClickListener(v -> {
            tweets.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
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
        private final ImageView tweetPicture;
        private final ImageView tweetImage;
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
            tweetPicture = itemView.findViewById(R.id.tweetPicture);
            tweetImage = itemView.findViewById(R.id.tweetImage);
            tweetText = itemView.findViewById(R.id.tweetText);
            toggleDelete = itemView.findViewById(R.id.toggleDelete);
        }
    }
}
