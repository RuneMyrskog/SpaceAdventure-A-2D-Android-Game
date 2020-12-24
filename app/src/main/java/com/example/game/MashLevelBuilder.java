package com.example.game;

import android.graphics.Color;

class MashLevelBuilder {

    /**
     * Creates and returns an array of TeleportingButton objects required for MashLevel.
     * @param gridHeight height of game grid.
     * @param gridWidth width of game grid.
     * @return Array of TeleportingButton objects to be used in MashLevel.
     */
    GameObject[] buildLevel(int gridHeight, int gridWidth) {

        GameObject[] gameObjects = new GameObject[3];

        // Create the two buttons and coin.
        TeleportingButton alienButton = new TeleportingButton(gridWidth / 2, gridHeight * (2 / 4), 100);
        TeleportingButton bombButton1 = new TeleportingButton(gridWidth / 2, gridHeight * (3 / 4), 100);
        MashCoin coin = new MashCoin(gridHeight/4,gridHeight/4, 30, gridHeight, gridWidth);
        // Set the colours of each button and coin.
        alienButton.setColor(Color.GREEN);
        bombButton1.setColor(Color.RED);
        coin.setColor(Color.YELLOW);

        // Add the thee buttons to the list of gameObjects.
        gameObjects[0] = alienButton;
        gameObjects[1] = bombButton1;
        gameObjects[2] = coin;


        return gameObjects;
    }
}
