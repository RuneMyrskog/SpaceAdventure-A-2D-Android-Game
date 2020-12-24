package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    /**
     * starts the game
     *
     * @param view the button that called this method
     */
    public void play(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void resume(View view) {
        // If there is a saved state, create intent to GameActivity class, but pass on
        // Account.currLevel, Account.SavedLevelScore, and Account.Customization. If there is no
        // saved state, set error message to "No saved game"
    }

    public void goToScoreboard(View view) {
        // Create intent to go to ScoreboardActivity
    }
}
