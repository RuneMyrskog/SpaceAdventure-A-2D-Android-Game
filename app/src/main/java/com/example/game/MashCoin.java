package com.example.game;

import java.util.Random;

class MashCoin extends GameObject {

    private Random rand = new Random();
    private int gridHeight;
    private int gridWidth;

    /**
     * Create a coin that appears in random parts of the screen.
     * Player earns coins for tapping on it.
     * @param xCoordinate horizontal coordinate of the coin
     * @param yCoordinate vertical coordinate of the coin
     * @param radius      size of the coin from center to outer edge
     * @param gridHeight  height of the grid the game is being played on
     * @param gridWidth   width of the grid the game is being played on
     */
    MashCoin(int xCoordinate, int yCoordinate, int radius, int gridHeight, int gridWidth) {
        super(xCoordinate, yCoordinate, radius);
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
    }

    /**
     * Moves the coin to a random location on screen.
     */
    private void forceMove() {
        int newX = rand.nextInt(gridWidth - 2 * this.getHitBox().width()) + this.getHitBox().width();
        int newY = rand.nextInt(gridHeight - 2 * this.getHitBox().height()) + this.getHitBox().height();
        this.setLocation(newX, newY);
    }

    /**
     * Decides whether the coin will be randomly moved to a new location each time step of the game.
     */
    @Override
    void update() {
        if (rand.nextDouble() < 0.05) {
            this.forceMove();
        }
    }
}
