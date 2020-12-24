package com.example.game;

import android.graphics.Canvas;
import android.graphics.Point;
import android.media.Image;

import java.util.List;

public class JumpLevel extends Level {

    //store astronaut and a list containing an obstacle just for testing
    private Astronaut astro;
    private List<Obstacle> obstacles;
    private int lives;

    public JumpLevel() {
        super();
        // assigning default values which can be changed later
        astro = new Astronaut(500, 500, 10);
        obstacles.add(new Obstacle(500, 500, 10));
        lives = 3;
    }

    /**
     * Update should modify obstacles location and detect collision.
     * If there is any collision, relocate obstacle to starting position.
     * Testing with default attributes right now
     */

    @Override
    void update() {
        astro.update();
        for(Obstacle o: obstacles)
        {
            o.update();
        }


    }

    @Override
    void draw(Canvas canvas) {
        astro.draw(canvas);
        for(Obstacle o: obstacles)
        {
            o.draw(canvas);
        }

    }

    // makes the astronaut jump if he is already not jumping
    public void onTouch(float x, float y){
        // jump only if astronaut is NOT jumping
        if (!astro.getIsJumping())
            astro.jump();
    }
    public int calculateScore(){return 0;}

}


