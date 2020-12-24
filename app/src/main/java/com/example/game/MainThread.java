package com.example.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/* This class is modified from FishTank.MainThread*/

/**
 * the thread that the game runs on
 */
public class MainThread extends Thread {


    /**
     * Where the levels are drawn.
     */
    private GameView gameView;
    /**
     * The canvas container.
     */
    private final SurfaceHolder surfaceHolder;
    /**
     * Whether the thread is running.
     */
    private boolean running;
    /**
     * The canvas on which to draw the current level.
     */
    public static Canvas canvas;

    /**
     * Construct the thread.
     *
     * @param gameView          where the level is drawn.
     */
    public MainThread(GameView gameView) {
        this.gameView = gameView;
        this.surfaceHolder = gameView.getHolder();

    }

    @Override
    public void run() {
        while (running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            sleep();
        }
    }


    private void sleep() {
        try {
            sleep(17); /*approximatley 60fps*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}

