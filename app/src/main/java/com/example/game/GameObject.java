package com.example.game;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

abstract class GameObject {

    private static final int X_SCALE_FACTOR = GameView.screenWidth / Level.GRID_WIDTH;
    private static final int Y_SCALE_FACTOR = GameView.screenHeight / Level.GRID_HEIGHT;
    /**
     * Collision box for the object.
     */
    private Rect hitBox;
    /**
     * Paint that controls the color of the object.
     */
    private Paint paint;
    /**
     * The (unscaled) horizontal location of the object.
     */
    private int xCoordinate;
    /**
     * The (unscaled) vertical location of the object.
     */
    private int yCoordinate;

    /**
     * Constructor for GameObject.
     *
     * @param xCoordinate the (unscaled) horizontal location of the object.
     * @param yCoordinate the (unscaled) vertical location of the object.
     * @param radius      half the width of the object.
     */
    GameObject(int xCoordinate, int yCoordinate, int radius) {
        this.paint = new Paint();
        paint.setColor(Color.BLACK);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.hitBox =
                new Rect(
                        xCoordinate - radius,
                        yCoordinate - radius,
                        xCoordinate + radius,
                        yCoordinate + radius);
    }

    abstract void update();

    /**
     * Draws this object on a canvas.
     *
     * @param c The canvas to draw this object on.
     */
    void draw(Canvas c) {
        c.drawRect(hitBox.left * X_SCALE_FACTOR,
                hitBox.top * Y_SCALE_FACTOR,
                hitBox.right * X_SCALE_FACTOR,
                hitBox.bottom * Y_SCALE_FACTOR, paint);
    }

    /**
     * Changes the location of the object.
     *
     * @param x (unscaled) horizontal location to move this object to.
     * @param y (unscaled) vertical location to move this object to.
     */
    void setLocation(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    /**
     * Changes the color of this object.
     *
     * @param c Colorcode of the color to change to.
     */
    void setColor(int c) {
        this.paint.setColor(c);
    }

    Rect getHitBox() {
        return hitBox;
    }

    int getXCoordinate() {
        return this.xCoordinate;
    }

    int getYCoordinate() {
        return this.yCoordinate;
    }

    void setHitBox(Rect hitBox) {
        this.hitBox = hitBox;
    }

    /**
     * Checks if this GameObject collides with another GameObject.
     *
     * @param o The GameObject that might collide with this GameObject.
     * @return Whether the this object collides with the object in the parameter.
     */
    boolean collide(GameObject o) {
        return this.getHitBox().intersect(o.getHitBox());
    }

}
