package com.example.ha_hai.animationinandroid.model;

/**
 * Created by ha_hai on 5/24/2018.
 */

public class Food {

    private int id;
    private String description;
    private String name;
    private long price;
    private String thumbnail;

    public Food() {
    }

    public Food(int id, String description, String name, long price, String thumbnail) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
