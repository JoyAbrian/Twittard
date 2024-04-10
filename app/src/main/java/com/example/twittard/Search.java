package com.example.twittard;

public class Search {
    private String type;
    private String name;
    private String posts;

    public Search(String type, String name, String posts) {
        this.type = type;
        this.name = name;
        this.posts = posts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }
}