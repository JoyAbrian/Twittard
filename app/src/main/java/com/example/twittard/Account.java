package com.example.twittard;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Account implements Parcelable {
    private String fullname;
    private String username;
    private Integer profilePhoto;
    private Integer profileBanner;
    private String type;
    private String signDate;
    private String following;
    private String followers;

    public Account(String fullname, String username, Integer profilePhoto, Integer profileBanner, String type, String signDate, String following, String followers) {
        this.fullname = fullname;
        this.username = username;
        this.profilePhoto = profilePhoto;
        this.profileBanner = profileBanner;
        this.type = type;
        this.signDate = signDate;
        this.following = following;
        this.followers = followers;
    }

    protected Account(Parcel in) {
        fullname = in.readString();
        username = in.readString();
        if (in.readByte() == 0) {
            profilePhoto = null;
        } else {
            profilePhoto = in.readInt();
        }
        if (in.readByte() == 0) {
            profileBanner = null;
        } else {
            profileBanner = in.readInt();
        }
        type = in.readString();
        signDate = in.readString();
        following = in.readString();
        followers = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Integer profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Integer getProfileBanner() {
        return profileBanner;
    }

    public void setProfileBanner(Integer profileBanner) {
        this.profileBanner = profileBanner;
    }

    public String getBirthdate() {
        return type;
    }

    public void setBirthdate(String type) {
        this.type = type;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(fullname);
        dest.writeString(username);
        if (profilePhoto == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(profilePhoto);
        }
        if (profileBanner == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(profileBanner);
        }
        dest.writeString(type);
        dest.writeString(signDate);
        dest.writeString(following);
        dest.writeString(followers);
    }
}
