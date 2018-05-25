package com.example.ha_hai.animationinandroid.model;

/**
 * Created by ha_hai on 5/24/2018.
 */

public class AnimationItem {

    private String title;
    private int anim;

    public AnimationItem(String title, int anim) {
        this.title = title;
        this.anim = anim;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnim() {
        return anim;
    }

    public void setAnim(int anim) {
        this.anim = anim;
    }
}
