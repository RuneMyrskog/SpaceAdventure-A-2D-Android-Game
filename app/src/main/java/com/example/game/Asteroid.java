package com.example.game;

import android.graphics.Color;


class Asteroid extends GameObject {

    private int asteroidSpeed;

    /**
     * Constructs a new asteroid.
     *
     * @param xCoordinate (unscaled) horizontal coordinate of asteroid.
     * @param yCoordinate (unscaled) vertical coordiante of asteroid.
     * @param radius      (unscaled) size of asteroid from center to outer edge.
     */
    Asteroid(int xCoordinate, int yCoordinate, int radius) {
        super(xCoordinate, yCoordinate, radius);
        this.asteroidSpeed = 1;
        this.setColor(Color.GRAY);
    }

    /**
     * Moves the asteroid downward according to asteroidSpeed.
     */
    @Override
    void update() {
        this.setLocation(this.getXCoordinate(), this.getYCoordinate() + this.asteroidSpeed);
    }
}
