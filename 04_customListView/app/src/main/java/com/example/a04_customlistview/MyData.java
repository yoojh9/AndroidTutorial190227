package com.example.a04_customlistview;

public class MyData {
    private String title;
    private String description;
    private int imgIcon;

    public MyData(String title, String description, int imgIcon) {
        this.title = title;
        this.description = description;
        this.imgIcon = imgIcon;
    }

    public MyData(){}

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImgIcon() {
        return imgIcon;
    }
}
