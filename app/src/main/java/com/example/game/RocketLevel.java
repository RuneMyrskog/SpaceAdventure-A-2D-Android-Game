package com.example.game;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

class RocketLevel extends Level {
    /**
     * The rocket object that the player controls
     */
    private Rocket rocket;
    /**
     * A list of the asteroids.
     */
    private ArrayList<Asteroid> asteroids;
    /**
     * A list of the Palladium.
     */
    private ArrayList<FallingPalladium> palladium;

    /**
     * The number of palladium collected in this game.
     */
    private int pCollected = 0;
    /**
     * The center of the screen.
     */
    private final int centre = Level.getGridWidth() * Level.getxScaleFactor() / 2;

    /**
     * Creates a RocketLevel object with 5 asteroids.
     */
    RocketLevel() {
        this.asteroids = new ArrayList<>();
        this.palladium = new ArrayList<>();
        for (int i : new int[]{1, 2, 3, 4, 5}) {
            Asteroid a = new Asteroid(0, 0, 10);
            relocate(a);
            asteroids.add(a);
            FallingPalladium p = new FallingPalladium(0, 0, 10);
            relocate(p);
            palladium.add(p);
        }
        this.rocket = new Rocket(Level.getGridWidth() / 2,
                Level.getGridHeight() / 2,
                2, 10, 30);
        Timer.resetTimer();

    }

    /**
     * Calculate the score of this RocketLevel based on the player's statistics.
     *
     * @return Score of the level.
     */
    @Override
    int calculateScore() {
        return 100 * pCollected + 20 * Timer.getTimeSeconds();
    }

    /**
     * Updates the location of every asteroid and checks for collision.
     * If the rocket collides with an asteroid, end the game.
     */
    void update() {
        for (Asteroid asteroid : asteroids) {
            asteroid.update();
            if (asteroid.collide(rocket)) {
                this.completeLevel();
                return;
            }
            if (asteroid.getHitBox().top >= Level.getGridHeight()) {
                relocate(asteroid);
            }
        }
        for (FallingPalladium p : palladium) {
            p.update();
            if (p.collide(rocket)) {
                pCollected += 1;
                relocate(p);
            }
            if (p.getHitBox().top >= Level.getGridHeight()) {
                relocate(p);
            }
        }
        Timer.timeStep();
    }

    /**
     * Displays the current state of the game on the canvas.
     *
     * @param canvas the canvas this level is drawn on
     */
    @Override
    void draw(Canvas canvas) {
        for (Asteroid asteroid : asteroids) {
            asteroid.draw(canvas);
        }
        rocket.draw(canvas);
    }

    /**
     * Calls rocket.update() to inform rocket of the user's touch.
     *
     * @param x the (scaled) horizontal location of the screen where the user touched.
     * @param y the (scaled) vertical location of the screen where the user touched.
     */
    @Override
    void onTouch(float x, float y) {
        rocket.setMovingRight(x < centre);
        rocket.update();
    }

    /**
     * Changes the location of the given object randomly to above the screen.
     *
     * @param o GameObject to relocate.
     */
    private void relocate(GameObject o) {
        Random x = new Random();
        o.setLocation(x.nextInt(Level.getGridWidth()), -x.nextInt(Level.getGridHeight()));
    }

}
