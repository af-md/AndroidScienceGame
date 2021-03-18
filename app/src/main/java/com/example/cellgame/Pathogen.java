package com.example.cellgame;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Pathogen {
    protected float x = 300, y = 200;
    float width=200, height=200;
    protected Drawable image;
    private RotateDrawable imageRotate;

    public Pathogen(Drawable image) {
        this.image = image;
    }


    public void move(Canvas canvas)
    {
        image.setBounds((int) x, (int) y, (int) (x + width), (int) (y + height));
        image.draw(canvas);
        y = y + 10;
    }
}
