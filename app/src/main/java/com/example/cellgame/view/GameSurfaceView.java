package com.example.cellgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.cellgame.R;
import com.example.cellgame.controller.GameController;

public class GameSurfaceView extends SurfaceView implements Runnable {
    SurfaceHolder myHolder;
    Thread myThread;
    boolean isRunning=true;
    Paint pWhite;
    GameController gameController;

    public void setMove(float move) {
        this.move = move;
    }

    float move = 0f;

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        pWhite = new Paint();
        pWhite.setColor(getResources().getColor(R.color.game));
        myThread = new Thread(this);
        myThread.start();
        myHolder = getHolder();
        gameController = new GameController(context);
    }

    @Override
    public void run() {
        while(isRunning)
        {
            if(!myHolder.getSurface().isValid())
                continue;
            Canvas canvas = myHolder.lockCanvas();
            canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(), pWhite);
            gameController.control(canvas, move);
            myHolder.unlockCanvasAndPost(canvas);
        }
    }
}
