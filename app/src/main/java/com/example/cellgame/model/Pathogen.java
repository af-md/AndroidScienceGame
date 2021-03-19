package com.example.cellgame.model;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;

/**
 * The type Pathogen.
 */
public class Pathogen extends GameObj {

    /**
     * Instantiates a new Pathogen.
     *
     * @param x     the x
     * @param y     the y
     * @param image the image
     */
    public Pathogen(float x, float y, Drawable image) {
        super(x, y, image);
    }

    /**
     * Method moves the pathogen across canvas rectangle
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
