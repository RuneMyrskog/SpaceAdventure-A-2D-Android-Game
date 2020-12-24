package com.example.game;


import android.graphics.Point;
import android.media.Image;

public class Astronaut extends GameObject {

    // checks if astronaut is in air
    private boolean isJumping;
    // change in the y coordinate, if there is any
    private int jumpSpeed;
    //Jump height of astronaut

    Astronaut(int xCoordinate, int yCoordinate, int radius) {
        super(xCoordinate, yCoordinate, radius);
        //initially set astronaut to not jump and there is no change in y coordinate
        isJumping = false;
        jumpSpeed = 0;
    }


    // update only changes Astronaut's Y coordinate by jumpSpeed. Initially jumpSpeed is 0
    // when player wants to jump, jumpspeed changes to -1 and on each update, Astronaut goes
    // up by 1, until it reaches y=450. After that, on each update it comes down by 1 until it
    // it reaches 500. This implementation is only for testing and can be changed later
    @Override
    void update() {
        // increases/decreases y coordinate based on whether astronaut jumping up or coming down
        setLocation(getXCoordinate(), getYCoordinate() + jumpSpeed);
        // if astronaut reaches max jump height, he should start falling down
        if (getYCoordinate() == 450)
                jumpSpeed = 1;
        // if astronaut is now back on the ground, he should not change his coordinates and
        // should stop jumping
        if (isJumping && getYCoordinate() == 500) {
            jumpSpeed = 0;
            isJumping = false;
        }
    }

    // returns true if astronaut is jumping
    boolean getIsJumping() {

        return isJumping;
    }

    // sets astronaut to start jumping and modifies it to change Y coordinate during update
    void jump() {
        isJumping = true;
        jumpSpeed = -1;
    }
}
