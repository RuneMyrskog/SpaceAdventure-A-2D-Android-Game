package com.example.game;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;


public class GameActivity extends AppCompatActivity{

    private boolean isPaused = false;
    private static Account user;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);


        gameView = findViewById(R.id.gameView);
    }


    //TODO

    /**
     * pause or resume the game
     *
     * @param view the pause button
     */
    public void onPausePlay(View view) {

        if (isPaused) {
            ((ImageButton) view).setImageResource(android.R.drawable.ic_media_pause);

            super.onResume();
            gameView.resumeGame();

        } else {
            ((ImageButton) view).setImageResource(android.R.drawable.ic_media_play);

            super.onPause();
            gameView.pauseGame();
        }
        isPaused = !isPaused;
    }

    public static void setUser(Account account){
        user = account;
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onStop(){
        super.onStop();
    }


}
