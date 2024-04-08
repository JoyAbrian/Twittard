package com.example.twittard;

public class Tweet {
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
}
