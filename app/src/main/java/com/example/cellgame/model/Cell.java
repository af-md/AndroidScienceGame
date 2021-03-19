package com.example.cellgame.model;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * The type Cell.
 */
public class Cell extends GameObj {

    /**
     * Instantiates a new Cell.
     *
     * @param x     the x
     * @param y     the y
     * @param image the image
     */
    public Cell(float x, float y, Drawable image) {
        super(x, y, image);
    }

    /**
     * Move.
     *
     * @param canvas the canvas
     * @param move   the move
     */
    public void move(Canvas canvas, float move)
    {
        if (isAbleToMove(canvas, move)){
            x = x - move;
        }

        image.setBounds((int) x, (int) y, (int) (x + width), (int) (y + height));
        image.draw(canvas);
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
