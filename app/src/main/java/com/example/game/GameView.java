package com.example.game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * The game view.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    /**
     * Height of navigation bar at bottom of screen.
     */
    private static int navBarHeight;
    /**
     * Screen width.
     */
    public static int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    /**
     * Screen height.
     */
    public static int screenHeight;
    /**
     * Whether the screen is pressed
     */
    private boolean isPressed;
    /**
     * x coordinate of last screen touch
     */
    private float touchX;
    /**
     * y coordinate of last screen touch
     */
    private float touchY;
    /**
     * The game's levels
     */
    ArrayList<Level> levels;
    /**
     * The current level
     */
    int currLevel = 0;
    /**
     * The thread the game runs on
     */
    private MainThread thread;

    /**
     * Create a new gameView in the context environment.
     *
     * @param context the environment.
     */
    public GameView(Context context) {
        super(context);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context) {

        getHolder().addCallback(this);
        thread = new MainThread(this);
        setFocusable(true);

        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }

        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels - navBarHeight;

        levels = new ArrayList<>(
                Arrays.asList(new MashLevel(), new RocketLevel())
        );
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        levels.get(currLevel).update();
        if (isPressed) {
            levels.get(currLevel).onTouch(touchX, touchY);

        }
        if (levels.get(currLevel).isComplete()) {
            nextLevel();
        }
    }

    public void nextLevel() {
        pauseGame();
        if (currLevel < levels.size()-1){
            currLevel++;
        }else{
         //   getContext().startActivity(new Intent(getContext(),gameCompletedActivity.class));
        }
        resumeGame();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            isPressed = false;
        } else {
            isPressed = true;
            touchX = event.getX();
            touchY = event.getY();
        }


        super.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            levels.get(currLevel).draw(canvas);
        }
    }

    public void pauseGame() {
        try {
            thread.setRunning(false);
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resumeGame() {
        thread = new MainThread(this);
        thread.setRunning(true);
        thread.start();
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }


}
