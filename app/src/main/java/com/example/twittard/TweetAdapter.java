package com.example.twittard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private final ArrayList<Tweet> tweets;
    private final Tweet tweet;

    public TweetAdapter(ArrayList<Tweet> tweets, Tweet tweet) {
        this.tweets = tweets;
        this.tweet = tweet;
    }

    @NonNull
    @Override
    public TweetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tweet tweet = tweets.get(viewType);
        View itemView;

        if (tweet.getTweet() == null) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_tweet, parent, false);
        } else if (tweet.getImage() == null)  {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_tweet_only_image, parent, false);
        } else  {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_tweet_with_image, parent, false);
        }
        return new ViewHolder(itemView, tweet);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetAdapter.ViewHolder holder, int position) {
        holder.accountPicture.setImageResource(tweet.getAccount().getProfilePhoto());
        holder.accountFullname.setText(tweet.getAccount().getFullname());
        holder.accountUsername.setText(tweet.getAccount().getUsername());
        holder.tweetDate.setText(tweet.getDate());
        holder.tweetComments.setText(tweet.getComment());
        holder.tweetReposts.setText(tweet.getRepost());
        holder.tweetLikes.setText(tweet.getLike());
        holder.tweetViews.setText(tweet.getView());

        if (tweet.getTweet() == null) {
            holder.tweetPicture.setImageResource(tweet.getImage());
        } else if (tweet.getImage() == null) {
            holder.tweetText.setText(tweet.getTweet());
        } else {
            holder.tweetPicture.setImageResource(tweet.getImage());
            holder.tweetText.setText(tweet.getTweet());
        }
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView accountPicture;
        private final TextView accountFullname;
        private final TextView accountUsername;
        private final TextView tweetDate;
        private final TextView tweetComments;
        private final TextView tweetReposts;
        private final TextView tweetLikes;
        private final TextView tweetViews;
        private ImageView tweetPicture = null;
        private TextView tweetText = null;

        public ViewHolder(@NonNull View itemView, Tweet tweet) {
            super(itemView);
            accountPicture = itemView.findViewById(R.id.accountPicture);
            accountFullname = itemView.findViewById(R.id.accountFullname);
            accountUsername = itemView.findViewById(R.id.accountUsername);
            tweetDate = itemView.findViewById(R.id.tweetDate);
            tweetComments = itemView.findViewById(R.id.tweetComments);
            tweetReposts = itemView.findViewById(R.id.tweetReposts);
            tweetLikes = itemView.findViewById(R.id.tweetLikes);
            tweetViews = itemView.findViewById(R.id.tweetViews);

            if (tweet.getTweet() == null) {
                tweetPicture = itemView.findViewById(R.id.tweetPicture);
            } else if (tweet.getImage() == null) {
                tweetText = itemView.findViewById(R.id.tweetText);
            } else {
                tweetText = itemView.findViewById(R.id.tweetText);
                tweetPicture = itemView.findViewById(R.id.tweetPicture);
            }
        }
    }
}
