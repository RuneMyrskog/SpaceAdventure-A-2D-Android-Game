package com.example.game;

import android.graphics.Color;

class FallingPalladium extends Asteroid {

    /**
     * Constructs a FallingPalladium object that behaves like an asteroid.
     *
     * @param xCoordinate (unscaled) horizontal location of the palladium.
     * @param yCoordinate (unscaled) vertical location of the palladium.
     * @param radius      (unscaled) half the width of the palladium.
     */
    FallingPalladium(int xCoordinate, int yCoordinate, int radius) {
        super(xCoordinate, yCoordinate, radius);
        this.setColor(Color.WHITE);
    }

}
