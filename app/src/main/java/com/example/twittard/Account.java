package com.example.twittard;

public class Account {
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
}
