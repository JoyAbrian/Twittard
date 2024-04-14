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
            holder.tweetPicture.setImageResource(tweet.getImage());
        } else if (tweet.getImage() == null) {
            holder.tweetText.setText(tweet.getTweet());
            holder.tweetPicture.setVisibility(View.GONE);
        } else {
            holder.tweetText.setText(tweet.getTweet());
            holder.tweetPicture.setImageResource(tweet.getImage());
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
        private final ImageView tweetPicture;
        private final TextView tweetText;

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
            tweetText = itemView.findViewById(R.id.tweetText);
        }
    }
}
