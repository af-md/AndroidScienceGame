package com.example.cellgame.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import com.example.cellgame.R;
import com.example.cellgame.controller.GameController;
import com.example.cellgame.model.Cell;
import com.example.cellgame.model.Pathogen;

public class GameSurfaceView extends SurfaceView implements Runnable {
    SurfaceHolder myHolder;
    Thread myThread;
    boolean isRunning=true;
    Paint pWhite;
    Cell cell;
    Pathogen pathogen;
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

        gameController = new GameController();
        Drawable cellDrawable
                = ContextCompat.getDrawable(context, R.drawable.game_cell);
        Drawable pathogenDrawable = ContextCompat.getDrawable(context, R.drawable.pathogen);
        cell = new Cell((Resources.getSystem().getDisplayMetrics().widthPixels / 2) - 100, Resources.getSystem().getDisplayMetrics().heightPixels - 200,cellDrawable);
        pathogen = new Pathogen(300, 200, pathogenDrawable);
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
            if (!collisionDetected(cell.getY() - 200, pathogen.getY(), cell.getX() + cell.getWidth(), pathogen.getY())) pathogen.move(canvas);
            myHolder.unlockCanvasAndPost(canvas);
        }

    }

    private boolean collisionDetected(float y, float v, float x, float x1) {
        Log.e("y pos:", y + "  " + v);
        return v > y && (x > x1 && x < x1 + 200 );
    }
}
