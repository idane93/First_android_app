package com.example.favoriteshowassignment;

public class DataModel {
    private String name;
    private String info;
    private int id_;
    private int image;

    public DataModel(String name, String info, int id_, int image) {
        this.name = name;
        this.info = info;
        this.id_ = id_;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getId_() {
        return id_;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
