package com.betvn.aptech88.Model;

public class League {
    public String id;
    public String name;
    public String image;

    public League(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
