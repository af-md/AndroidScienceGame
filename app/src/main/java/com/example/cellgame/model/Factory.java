package com.example.cellgame.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.cellgame.R;

/**
 * The type Factory.
 * The Factory class is used to generate game objects on demand.
 */
public class Factory {

    /**
     * Instantiates a new Factory.
     */
    public Factory() {
    }

    /**
     * Create game objects based on the discriminator.
     *
     * @param x             the x
     * @param y             the y
     * @param discriminator the discriminator
     * @param context       the context
     * @return the game obj
     */
    public GameObj CreateObject(float x, float y, String discriminator, Context context){
        switch (discriminator){
            case "PredatoryCell":
                Drawable cellDrawable
                        = ContextCompat.getDrawable(context, R.drawable.predatory_cell);
                return new PredatoryCell(x,y,cellDrawable);
            case "Pathogen":
                Drawable pathogenDrawable
                        = ContextCompat.getDrawable(context, R.drawable.pathogen);
                return new Pathogen(x,y,pathogenDrawable);
            case "RedBloodCell":
                Drawable redBloodCellDrawable =
                        ContextCompat.getDrawable(context, R.drawable.red_blood_cell);
                return new RedBloodCells(x, y, redBloodCellDrawable);
            case "WhiteBloodCell":
                Drawable whiteBloodCellDrawable =
                        ContextCompat.getDrawable(context, R.drawable.white_blood_cell_);
                return new WhiteBloodCell(x, y, whiteBloodCellDrawable);
        };
        return null;
    }


}
