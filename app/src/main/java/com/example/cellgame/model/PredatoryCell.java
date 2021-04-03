package com.example.cellgame.model;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import org.w3c.dom.Text;

/**
 * The type Cell.
 */
public class PredatoryCell extends GameObj {


    private int health = 5;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    private int points = 0;

    /**
     * Instantiates a new Cell.
     *
     * @param x     the x
     * @param y     the y
     * @param image the image
     */
    public PredatoryCell(float x, float y, Drawable image) {
        super(x, y, image);
    }

    /**
     * Move.
     *
     * @param canvas the canvas
     * @param move   the move
     */
    public void move(Canvas canvas, float move, Paint paint)
    {
        drawHealth(canvas, paint);
        if (isAbleToMove(canvas, move)){
            x = x - move;
        }

        image.setBounds((int) x, (int) y, (int) (x + width), (int) (y + height));
        image.draw(canvas);
    }

    private void drawHealth(Canvas canvas, Paint paint) {

    }

    /**
     *
     * @param canvas
     * @param move
     * @return true under the condition the image hasn't gove over the canvas
     */
    private boolean isAbleToMove(Canvas canvas, float move) {
        if (move > 0.0) { // move left
            return x - move > 0;
        } else { // move right
            return canvas.getWidth() > x  + width;
        }
    }
}
