package com.example.game;

import android.graphics.Canvas;

import java.util.ArrayList;


abstract class Level {
    /**
     * the width of the grid where the level is drawn
     */
    public static final int GRID_WIDTH = 1080;
    /**
     * the height of the grid where the level is drawn
     */
    public static final int GRID_HEIGHT = 1920;



    private boolean isComplete;


    /**
     * update the level
     */
    abstract void update();

    /**
     * draw this level
     *
     * @param canvas the canvas this level is drawn on
     */
    abstract void draw(Canvas canvas);

    abstract void onTouch(float x, float y);

    abstract int calculateScore();

    boolean isComplete() {
        return isComplete;
    }

    void completeLevel() {
        isComplete = true;
    }
}
