package com.example.cellgame.model;

import android.graphics.drawable.Drawable;

public class GameObj {
    public GameObj(float x, float y, Drawable image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    protected float x, y;

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Drawable getImage() {
        return image;
    }

    protected float width=200, height=200;
    protected Drawable image;


    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}
