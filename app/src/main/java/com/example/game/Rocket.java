package com.example.game;

import android.graphics.Color;

class Rocket extends GameObject {
    /**
     * Distance the rocket travels each time.
     */
    private final int movementSpeed;
    /**
     * Time restriction between each movement.
     */
    private final int interval;
    /**
     * Time remaining until next movement is allowed.
     */
    private int timeRemain;
    /**
     * Direction of movement.
     */
    private boolean movingRight;

    /**
     * Creates a new Rocket object.
     *
     * @param xCoordinate   the horizontal coordinate of the Rocket.
     * @param yCoordinate   the vertical coordinate of the Rocket.
     * @param radius        Half the width of the rocket.
     * @param movementSpeed Distance travelled by the rocket each time it moves.
     * @param interval      Time restriction between each movement.
     */
    Rocket(int xCoordinate, int yCoordinate, int radius, int movementSpeed, int interval) {
        super(xCoordinate, yCoordinate, radius);
        this.movementSpeed = movementSpeed;
        this.interval = interval;
        this.setColor(Color.YELLOW);
        timeRemain = 0;
    }

    /**
     * Moves the rocket a set distance to the left or right.
     * The rocket can only move once every half a second (30 updates).
     */
    @Override
    void update() {
        if (timeRemain == 0) {
            if (movingRight) {
                this.setLocation(this.getXCoordinate() + movementSpeed, this.getYCoordinate());
            } else {
                this.setLocation(this.getXCoordinate() - movementSpeed, this.getYCoordinate());
            }
            timeRemain = interval;
        } else {
            timeRemain--;
        }
    }

    /**
     * Sets the direction of the rocket according to the input parameter.
     *
     * @param b Which direction the rocket will travel, true = right, false = left.
     */
    void setMovingRight(boolean b) {
        movingRight = b;
    }
}
