package com.example.cellgame.model;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class Cell extends GameObj {

    public Cell(float x, float y, Drawable image) {
        super(x, y, image);
    }

    public void move(Canvas canvas, float move)
    {
        if (isAbleToMove(canvas, move)){
            x = x - move;
        }

        image.setBounds((int) x, (int) y, (int) (x + width), (int) (y + height));
        image.draw(canvas);
    }

    private boolean isAbleToMove(Canvas canvas, float move) {
        if (move > 0.0) { // move left
            return x - move > 0;
        } else { // move right
            return canvas.getWidth() > x  + width;
        }
    }
}
