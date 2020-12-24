package com.example.game;

class Obstacle extends GameObject {

    private final int OBSTACLE_SPEED = 1;

    Obstacle(int xCoordinate, int yCoordinate, int radius) {

        super(xCoordinate, yCoordinate, radius);
    }

    @Override
    void update() {
        // get obstacles coordinate and reduce x by obstacleSpeed.
        this.setLocation(this.getXCoordinate() - OBSTACLE_SPEED, this.getYCoordinate());
    }
}
