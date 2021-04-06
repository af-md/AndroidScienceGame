package com.example.cellgame.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.example.cellgame.R;
import com.example.cellgame.model.Factory;
import com.example.cellgame.model.GameObj;
import com.example.cellgame.model.Pathogen;
import com.example.cellgame.model.Player;
import com.example.cellgame.model.PredatoryCell;
import com.example.cellgame.model.RedBloodCells;
import com.example.cellgame.model.WhiteBloodCell;

import java.util.ArrayList;

public class GameSurfaceView extends SurfaceView implements Runnable {
    SurfaceHolder myHolder;
    Thread myThread;
    boolean isRunning=true;
    Paint pWhite;
    Context context;

    public void setHealthTextView(TextView healthTextView) {
        this.healthTextView = healthTextView;
    }

    TextView healthTextView;
    TextView pointsTextView;

    public void setMove(float move) {
        this.move = move;
    }

    float move = 0f;

    /**
     * The Factory.
     */
    Factory factory;

    /**
     * The Game objects stored inside an Array List.
     */
    ArrayList<GameObj> gameObjs = new ArrayList<GameObj>();

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Player player;

    public void setObserveGameEnd(Observer<Boolean> observeGameEnd) {
        this.observeGameEnd = observeGameEnd;
    }

    // move to other fragment
    Observer<Boolean> observeGameEnd;

    public GameSurfaceView(Context context, AttributeSet attrs) throws InterruptedException {
        super(context, attrs);
        pWhite = new Paint();
        pWhite.setColor(getResources().getColor(R.color.game));
        myThread = new Thread(this);
        myThread.start();
        myHolder = getHolder();
        this.context = context;
        this.factory = new Factory();
        CreateObjects();
    }

    @Override
    public void run() {
        while(isRunning)
        {
            if(!myHolder.getSurface().isValid())
                continue;
            Canvas canvas = myHolder.lockCanvas();
            canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(), pWhite);
            control(canvas, move);
            myHolder.unlockCanvasAndPost(canvas);
        }

        // interrupt the thread
        myThread.interrupt();

        // move to the leader board
        observeGameEnd.onChanged(true);
    }


    /**
     * Creates the first objects on the canvas to start the game
     */
    private void CreateObjects()  {

        // different cell types creation
        gameObjs.add(factory.CreateObject((Resources.getSystem().getDisplayMetrics().widthPixels / 2) - 100, Resources.getSystem().getDisplayMetrics().heightPixels - 400, "PredatoryCell" , context));
        gameObjs.add(factory.CreateObject(300, -1400, "Pathogen" , context));
        gameObjs.add(factory.CreateObject(800, 0, "RedBloodCell", context));
        gameObjs.add(factory.CreateObject(500, -800, "WhiteBloodCell", context));
        // health bar creation
    }


    /**
     * Control.
     *
     * @param canvas the canvas
     * @param move   the move
     */
    public void control(Canvas canvas, float move) {
        for (GameObj gameObj: gameObjs)
            if (gameObj instanceof PredatoryCell) gameObj.move(canvas, move, pWhite);
            else {
                if (!collisionDetected(gameObjs.get(0).getY() - gameObjs.get(0).getHeight(), gameObj.getY(), gameObjs.get(0).getX() + gameObjs.get(0).getWidth(), gameObjs.get(0).getX() , gameObj.getX(),gameObj.getX() + gameObj.getWidth())) {
                    gameObj.move(canvas);
                }
                else {
                    // if collision occurred

                    // check health and points of the Predatory Cell
                    PredatoryCell predatoryCell = (PredatoryCell) gameObjs.get(0);

                    if (predatoryCell.getHealth() == 4){
                        player.setScore(Integer.toString(predatoryCell.getPoints()));
                        isRunning = false;
                        break;
                    }

                    // remove health if collision occurred with pathogen
                    if (gameObj instanceof Pathogen){
                        predatoryCell.setHealth(predatoryCell.getHealth() - 1);
                        healthTextView.setText(Integer.toString(predatoryCell.getHealth()));
                    }

                    // update points if the cell has consumed blood cells
                    if (gameObj instanceof RedBloodCells || gameObj instanceof WhiteBloodCell){
                        predatoryCell.setPoints(predatoryCell.getPoints() + 1);
                        pointsTextView.setText(Integer.toString(predatoryCell.getPoints()));
                    }
                    // reset axis coordinates

                    resetInitial(gameObj);
                }

                if ( (int) gameObj.getY() > Resources.getSystem().getDisplayMetrics().heightPixels){   // 1880 Y position where objects disappear under the screen
                    resetInitial(gameObj);
                }
            }

    }

    /**
     * @param playerYPosMinusHeight
     * @param collidedObjYPos
     * @param playerXPosPlusWidth
     * @param collidedObjXPos
     * @param collidedObjXPosPlusWidth
     * @return
     */
    private boolean collisionDetected(float playerYPosMinusHeight, float collidedObjYPos, float playerXPosPlusWidth, float playerXPosition, float collidedObjXPos, float collidedObjXPosPlusWidth) {
        return hasCollidedOnYAxis(playerYPosMinusHeight, collidedObjYPos) && hasCollidedOnXAxis(playerXPosPlusWidth, playerXPosition, collidedObjXPos, collidedObjXPosPlusWidth);
    }

    /**
     * @param playerXPosPlusWidth
     * @param collidedObjXPos
     * @param collidedObjXPosPlusWidth
     * @return true if the collision has happened on the X axis of the objects
     */
    private boolean hasCollidedOnXAxis(float playerXPosPlusWidth, float playerXPosition, float collidedObjXPos, float collidedObjXPosPlusWidth) {
        return verticalRightHandSideCollision(playerXPosPlusWidth, collidedObjXPos, collidedObjXPosPlusWidth) || verticalLeftHandSideCollision(playerXPosition, collidedObjXPos, collidedObjXPosPlusWidth);
    }

    private boolean verticalRightHandSideCollision(float playerXPosPlusWidth, float collidedObjXPos, float collidedObjXPosPlusWidth) {
        return playerXPosPlusWidth > collidedObjXPos && playerXPosPlusWidth < collidedObjXPosPlusWidth;
    }

    private boolean verticalLeftHandSideCollision(float playerXPosition, float collidedObjXPos, float collidedObjXPosPlusWidth) {
        return playerXPosition < collidedObjXPosPlusWidth && playerXPosition > collidedObjXPos;
    }

    /**
     * @param playerYPosMinusHeight
     * @param collidedObjYPos
     * @return true if the collision has happened on the Y axis of the objects
     */
    private boolean hasCollidedOnYAxis(float playerYPosMinusHeight, float collidedObjYPos) {
        return collidedObjYPos > playerYPosMinusHeight;
    }

    /**
     * resets vertical and horizontal axis coordinates
     * @param gameObj
     */
    private void resetInitial(GameObj gameObj) {
        gameObj.setX(gameObj.getInitialHorizontalPosition());
        gameObj.setY(gameObj.getInitialVerticalPosition());
    }

    public void setPointsTextView(TextView pointsTextView) {
        this.pointsTextView = pointsTextView;
    }


}
