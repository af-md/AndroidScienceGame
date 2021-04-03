package com.example.cellgame.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * The type Game obj.
 * Game object used as a parent for every other objects
 */
public class GameObj {
    /**
     * Instantiates a new Game obj.
     *
     * @param x     the x
     * @param y     the y
     * @param image the image
     */
    public GameObj(float x, float y, Drawable image) {
        this.x = x;
        this.y = y;
        this.initialHorizontalPosition = x;
        initialVerticalPosition = y;
        this.image = image;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    protected float x, y;
    protected float initialHorizontalPosition;

    public float getInitialHorizontalPosition() {
        return initialHorizontalPosition;
    }

    public float getInitialVerticalPosition() {
        return initialVerticalPosition;
    }

    protected float initialVerticalPosition;


    /**
     * Gets width.
     *
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public Drawable getImage() {
        return image;
    }

    /**
     * The Width.
     */
    protected float width=200, /**
     * The Height.
     */
    height=200;
    /**
     * The Image.
     */
    protected Drawable image;


    /**
     * Gets x.
     *
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * Move.
     *
     * @param canvas the canvas
     * @param move   the move
     */
    public void move(Canvas canvas, float move, Paint paint) {
    }

    /**
     * Move.
     *
     * @param canvas the canvas
     */
    public void move(Canvas canvas) {
    }
}
