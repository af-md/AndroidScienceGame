package com.example.cellgame.model;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class RedBloodCells extends GameObj{

    /**
     * Instantiates a new Game obj.
     *
     * @param x     the x
     * @param y     the y
     * @param image the image
     */
    public RedBloodCells(float x, float y, Drawable image) {
        super(x, y, image);
    }


    /**
     * Method moves the cell on the Y axis of the canvas
     *
     * @param canvas the canvas
     */
    public void move(Canvas canvas)
    {
        image.setBounds((int) x, (int) y, (int) (x + width), (int) (y + height));
        image.draw(canvas);
        y = y + 10;
    }
}
