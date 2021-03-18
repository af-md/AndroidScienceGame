package com.example.cellgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class Cell extends Activity {

    protected float x, y;
    float width=200, height=200;
    protected Drawable image;

    public Cell(float x, float y, Drawable image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    void move(Canvas canvas, float move)
    {
        if (isAbleToMove(canvas, move)){
            x = x - move;
        }

        Log.e("position", canvas.getWidth() + "   "  + x + "");
        image.setBounds((int) x, (int) y, (int) (x + width), (int) (y + height));
        image.draw(canvas);
    }

    private boolean isAbleToMove(Canvas canvas, float move) {
         // if (x + width < canvas.getWidth() || move > 0)
        //if ( || ((x-width) > 0))
        if (move > 0.0) { // move left
            return x - move > 0;
        } else { // move right
            return canvas.getWidth() > x  + width;
        }
    }
}
