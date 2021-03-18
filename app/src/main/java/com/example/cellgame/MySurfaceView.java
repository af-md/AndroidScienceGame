package com.example.cellgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.core.content.ContextCompat;

public class MySurfaceView extends SurfaceView implements Runnable {
    SurfaceHolder myHolder;
    Thread myThread;
    boolean isRunning=true;
    Paint pWhite;
    Cell cell;

    public void setMove(float move) {
        this.move = move;
    }

    float move = 0f;

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        pWhite = new Paint();
        pWhite.setColor(getResources().getColor(R.color.game));
        myThread = new Thread(this);
        myThread.start();
        myHolder = getHolder();

        Drawable drawable
                = ContextCompat.getDrawable(context, R.drawable.game_cell);
        cell = new Cell((Resources.getSystem().getDisplayMetrics().widthPixels / 2) - 100, Resources.getSystem().getDisplayMetrics().heightPixels - 200,drawable);
    }

    @Override
    public void run() {
        while(isRunning)
        {
            if(!myHolder.getSurface().isValid())
                continue;
            Canvas canvas = myHolder.lockCanvas();
            canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(), pWhite);
            cell.move(canvas, move);
            myHolder.unlockCanvasAndPost(canvas);
        }

    }
}
