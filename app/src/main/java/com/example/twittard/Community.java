package com.example.twittard;

public class Community {
    private Integer image;
    private String name;
    private String members;
    private String type;

    public Community(Integer image, String name, String members, String type) {
        this.image = image;
        this.name = name;
        this.members = members;
        this.type = type;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
