package com.example.game;

/**
 * An account.
 */
class Account {
    /**
     * The account's username.
     */
    String username;

    /**
     * The account's password.
     */
    String password;

    /**
     * The account's highscore.
     */
    private int highScore;

    /**
     * The account's total Palladium.
     */
    private int palladium;

    /**
     * The level that the account saved at. 1 for Jump Level (level 1), 2 for Mash Level (level 2),
     * 3 for Rocket Level(level 3). If account has no saved level, takes value of 0.
     */
    private int currLevel;

    /**
     * The score the player had in the saved game.
     */
    private int savedLevelScore;

    /**
     * The customization options the player chose in the saved game. Takes the form XY, where
     * X = 1, 2, 3, or 4, and Y = 1, 2, 3, or 4. Takes value of 00 if there is no saved level. The
     * color key is:
     * X = Background (Black (default), Blue, Purple, Dark Red)
     * Y = Sprite colour (White (default), Lime Green, Bright Orange, Pink)
     */
    private String customizationForSavedLevel;

    //TODO Run tests to make sure that this class works.

    Account(String usernameInput, String passwordInput, int highScoreInput, int palladiumInput,
            int currLevelInput, int savedLevelScoreInput, String customizationForSavedLevelInput) {
        username = usernameInput;
        password = passwordInput;
        highScore = highScoreInput;
        palladium = palladiumInput;
        currLevel = currLevelInput;
        savedLevelScore = savedLevelScoreInput;
        customizationForSavedLevel = customizationForSavedLevelInput;
    }

    int getHighScore() {
        return highScore;
    }

    void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    int getPalladium() {
        return palladium;
    }

    void setPalladium(int palladium) {
        this.palladium = palladium;
    }

    int getCurrLevel() {
        return currLevel;
    }

    void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
    }

    int getSavedLevelScore() {
        return savedLevelScore;
    }

    void setSavedLevelScore(int savedLevelScore) {
        this.savedLevelScore = savedLevelScore;
    }

    String getCustomizationForSavedLevel() {
        return customizationForSavedLevel;
    }

    void setCustomizationForSavedLevel(String customizationForSavedLevel) {
        this.customizationForSavedLevel = customizationForSavedLevel;
    }
}
