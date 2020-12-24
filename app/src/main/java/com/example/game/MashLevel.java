package com.example.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

public class MashLevel extends Level {

    private GameObject[] gameObjects;
    private int elapsedTime;
    private int numberLives;
    private int numberTaps;
    private int numCoins;
    private final int GAME_DURATION = 30;
    private final int SCORE_PER_TAP = 10;


    /**
     * Constructer for MashLevel object for the Button MAshing Game (Game 2).
     */
    public MashLevel() {
        Timer.resetTimer();
        this.elapsedTime = 0;
        this.numberLives = 3;
        this.numCoins = 0;

        // Initialize and call the builder for this level.
        MashLevelBuilder builder = new MashLevelBuilder();
        this.gameObjects = builder.buildLevel(this.getGridHeight(), this.getGridWidth());
    }

    /**
     * Helper method responsible for swapping the positions of the two buttons
     * every 5 seconds.
     */
    private void rearrangeButtons() {
        // run every 5 seconds.
        if (Timer.getTimeSeconds() % 5 == 0) {
            int alienX = gameObjects[0].getXCoordinate();
            int alienY = gameObjects[0].getYCoordinate();
            int bombX = gameObjects[1].getXCoordinate();
            int bombY = gameObjects[1].getYCoordinate();
            gameObjects[0].setLocation(bombX, bombY);
            gameObjects[1].setLocation(alienX, alienY);
        }

    }

    /**
     * Calulate and return the player's final score for this game
     * @return
     */
    @Override
    int calculateScore() {
        return (SCORE_PER_TAP * numberTaps) + (100 * numCoins) + (500 * numberLives);
    }



    /**
     * Denotes a time step in the game.
     * Increments Timer and calls rearrangeButtons.
     */
    @Override
    void update() {
        // Increment timer
        Timer.timeStep();
        rearrangeButtons();
    }

    /**
     * Draws the buttons on the game screen.
     *
     * @param canvas the canvas this level is drawn on
     */
    @Override
    void draw(Canvas canvas) {
        for (GameObject item : this.gameObjects) {
            item.draw(canvas);
        }
    }

    /**
     * Increases player's points on this level upon tapping on the Alien button.
     * Decrements the player's number of lives upon tapping on a Bomb button.
     * Ends the game if player runs out of lives or runs out of time.
     *
     * @param x float value denoting the horizontal coordinate of the player's tap.
     * @param y float vlaue denoting the vertical coordinate of the player's tap.
     */
    @Override
    void onTouch(float x, float y) {
        // convert touch into integer coordinates.
        int xTouch = (int) x / Level.getxScaleFactor();
        int yTouch = (int) y / Level.getyScaleFactor();
        Log.i("COORDINATES OF TAP:", Integer.toString(xTouch) + " , " + Integer.toString(yTouch));
        if (gameObjects[0].getHitBox().contains(xTouch, yTouch)) {
            numberTaps += SCORE_PER_TAP;

        } else if (gameObjects[2].getHitBox().contains(xTouch, yTouch)) {
            numCoins += 1;
            Random rand = new Random();
            int newX = rand.nextInt(Level.getGridWidth() - 2 * gameObjects[2].getHitBox().width()) + gameObjects[2].getHitBox().width();
            int newY = rand.nextInt(Level.getGridWidth() - 2 * gameObjects[2].getHitBox().height()) + gameObjects[2].getHitBox().height();
            gameObjects[2].setLocation(newX, newY);
        }
        // Check if player touched bomb.
        else if (gameObjects[1].getHitBox().contains(xTouch, yTouch)) {
            numberLives--;
            if (numberLives == 0 || Timer.getTimeSeconds() == GAME_DURATION) {
                elapsedTime = Timer.getTimeSeconds();
                this.completeLevel();
            }
        }
    }
}
