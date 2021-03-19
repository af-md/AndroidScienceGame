package com.example.cellgame.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.Switch;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

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
            case "Cell":
                Drawable cellDrawable
                        = ContextCompat.getDrawable(context, R.drawable.game_cell);
                return new Cell(x,y,cellDrawable);
            case "Pathogen":
                Drawable pathogenDrawable
                        = ContextCompat.getDrawable(context, R.drawable.pathogen);
                return new Pathogen(x,y,pathogenDrawable);
        };
        return null;
    }


}
