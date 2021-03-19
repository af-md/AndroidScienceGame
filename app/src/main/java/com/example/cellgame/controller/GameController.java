package com.example.cellgame.controller;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;

import com.example.cellgame.model.Cell;
import com.example.cellgame.model.Factory;
import com.example.cellgame.model.GameObj;

import java.util.ArrayList;

/**
 * The type Game controller.
 */
public class GameController {
    /**
     * The Factory.
     */
    Factory factory;
    /**
     * The Context used to get the Drawable object.
     */
    Context context;
    /**
     * The Game objects stored inside an Array List.
     */
    ArrayList<GameObj> gameObjs = new ArrayList<GameObj>();

    /**
     * Instantiates a new Game controller.
     *
     * @param context the context
     */
    public GameController(Context context){
        factory = new Factory();
        this.context = context;
        CreateObjects();
    }

    /**
     * Control.
     *
     * @param canvas the canvas
     * @param move   the move
     */
    public void control(Canvas canvas, float move) {
        for (GameObj gameObj: gameObjs)
            if (gameObj instanceof Cell) gameObj.move(canvas, move);
            else {
                if (!collisionDetected(gameObjs.get(0).getY() - gameObjs.get(0).getHeight(), gameObj.getY(), gameObjs.get(0).getX() + gameObjs.get(0).getWidth(), gameObj.getX(),gameObj.getX() + gameObj.getWidth())) gameObj.move(canvas); //
            }

    }

    /**
     * Creates the first objects on the canvas to start the game
     */
    private void CreateObjects() {
        gameObjs.add(factory.CreateObject((Resources.getSystem().getDisplayMetrics().widthPixels / 2) - 100, Resources.getSystem().getDisplayMetrics().heightPixels - 200, "Cell" , context));
        gameObjs.add(factory.CreateObject(300, 200, "Pathogen" , context));
    }

    /**
     * @param playerYPosMinusHeight
     * @param collidedObjYPos
     * @param playerXPosPlusWidth
     * @param collidedObjXPos
     * @param collidedObjXPosPlusWidth
     * @return
     */
    private boolean collisionDetected(float playerYPosMinusHeight, float collidedObjYPos, float playerXPosPlusWidth, float collidedObjXPos, float collidedObjXPosPlusWidth) {

        return hasCollidedOnYAxis(playerYPosMinusHeight, collidedObjYPos) && hasCollidedOnXAxis(playerXPosPlusWidth, collidedObjXPos, collidedObjXPosPlusWidth);
    }

    /**
     * @param playerXPosPlusWidth
     * @param collidedObjXPos
     * @param collidedObjXPosPlusWidth
     * @return true if the collision has happened on the X axis of the objects
     */
    private boolean hasCollidedOnXAxis(float playerXPosPlusWidth, float collidedObjXPos, float collidedObjXPosPlusWidth) {
        return playerXPosPlusWidth > collidedObjXPos && playerXPosPlusWidth < collidedObjXPosPlusWidth;
    }


    /**
     * @param playerYPosMinusHeight
     * @param collidedObjYPos
     * @return true if the collision has happened on the Y axis of the objects
     */
    private boolean hasCollidedOnYAxis(float playerYPosMinusHeight, float collidedObjYPos) {
        return collidedObjYPos > playerYPosMinusHeight;
    }

}
