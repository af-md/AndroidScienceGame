package com.example.cellgame.model;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;

public class Pathogen extends GameObj {

    public Pathogen(float x, float y, Drawable image) {
        super(x, y, image);
    }

    public void move(Canvas canvas)
    {
        image.setBounds((int) x, (int) y, (int) (x + width), (int) (y + height));
        image.draw(canvas);
        y = y + 10;
    }
}
