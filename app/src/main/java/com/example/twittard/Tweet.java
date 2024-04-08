package com.example.twittard;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Tweet implements Parcelable {
    private Account account;
    private String date;
    private String tweet;
    private Integer image;
    private Integer comment;
    private Integer repost;
    private Integer like;
    private Integer view;

    public Tweet(Account account, String date, String tweet, Integer image, Integer comment, Integer repost, Integer like, Integer view) {
        this.account = account;
        this.date = date;
        this.tweet = tweet;
        this.image = image;
        this.comment = comment;
        this.repost = repost;
        this.like = like;
        this.view = view;
    }

    protected Tweet(Parcel in) {
        date = in.readString();
        tweet = in.readString();
        if (in.readByte() == 0) {
            image = null;
        } else {
            image = in.readInt();
        }
        if (in.readByte() == 0) {
            comment = null;
        } else {
            comment = in.readInt();
        }
        if (in.readByte() == 0) {
            repost = null;
        } else {
            repost = in.readInt();
        }
        if (in.readByte() == 0) {
            like = null;
        } else {
            like = in.readInt();
        }
        if (in.readByte() == 0) {
            view = null;
        } else {
            view = in.readInt();
        }
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

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getRepost() {
        return repost;
    }

    public void setRepost(Integer repost) {
        this.repost = repost;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(tweet);
        if (image == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(image);
        }
        if (comment == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(comment);
        }
        if (repost == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(repost);
        }
        if (like == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(like);
        }
        if (view == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(view);
        }
    }
}
