package com.example.twittard;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Tweet implements Parcelable {
    private Account account;
    private String date;
    private String tweet;
    private Integer image;
    private String comment;
    private String repost;
    private String like;
    private String view;
    private Uri uriImage;

    public Tweet(Account account, String date, String tweet, Integer image, String comment, String repost, String like, String view, Uri uriImage) {
        this.account = account;
        this.date = date;
        this.tweet = tweet;
        this.image = image;
        this.comment = comment;
        this.repost = repost;
        this.like = like;
        this.view = view;
        this.uriImage = uriImage;
    }

    protected Tweet(Parcel in) {
        account = in.readParcelable(Account.class.getClassLoader());
        date = in.readString();
        tweet = in.readString();
        if (in.readByte() == 0) {
            image = null;
        } else {
            image = in.readInt();
        }
        comment = in.readString();
        repost = in.readString();
        like = in.readString();
        view = in.readString();
        uriImage = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Tweet> CREATOR = new Creator<Tweet>() {
        @Override
        public Tweet createFromParcel(Parcel in) {
            return new Tweet(in);
        }

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRepost() {
        return repost;
    }

    public void setRepost(String repost) {
        this.repost = repost;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Uri getUriImage() {
        return uriImage;
    }

    public void setUriImage(Uri uriImage) {
        this.uriImage = uriImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(account, flags);
        dest.writeString(date);
        dest.writeString(tweet);
        if (image == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(image);
        }
        dest.writeString(comment);
        dest.writeString(repost);
        dest.writeString(like);
        dest.writeString(view);
        dest.writeParcelable(uriImage, flags);
    }
}