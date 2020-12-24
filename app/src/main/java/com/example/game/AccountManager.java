package com.example.game;

import android.content.Context;
import android.content.res.Resources;
import android.renderscript.ScriptGroup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A collection of accounts and the operations that modify and extract information from it.
 */
class AccountManager {
    /**
     * A list of accounts.
     */
    private static List<Account> accounts;
    private static Account activeUser;
    private static AccountManager INSTANCE;
    private Context context;

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountManager();
        }
        return INSTANCE;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    //TODO Run tests to make sure this class works. CLEAN UP THIS CODE!!! Add FileReader and
    // FileWriter attributes to help out methods.

    /**
     * Reads a CSV file and initializes it to accounts after it converts the CSV into a list of
     * accounts. The CSV file is formatted as follows:
     * <p>
     * The CSV is order based on highScore in descending order.
     * Each line is an account.
     * The first field is the account's username.
     * The second field is the account's password.
     * The third field is the account's highScore.
     * The fourth field is the account's palladium.
     * The fifth field is the account's currLevel.
     * The sixth field is the account's savedLevelScore.
     * The seventh field is the account's customizationForSavedLevel.
     */

    public void populateAccounts() {
        if (context != null) {

            InputStream is = context.getResources().openRawResource(R.raw.account_data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            OutputStream outputStream = context

            String username;
            String password;
            int highScore;
            int palladium;
            int currLevel;
            int savedLevelScore;
            String customizationForSavedLevel;

            String line;

            try {
                while ((line = reader.readLine()) != null) {
                    // Parse the CSV by commas or new lines
                    String[] accountInfo;
                    accountInfo = line.split(",");

                    username = accountInfo[0];
                    password = accountInfo[1];
                    highScore = Integer.parseInt(accountInfo[2]);
                    palladium = Integer.parseInt(accountInfo[3]);
                    currLevel = Integer.parseInt(accountInfo[4]);
                    savedLevelScore = Integer.parseInt(accountInfo[5]);
                    customizationForSavedLevel = accountInfo[6];

                    Account account = new Account(username, password, highScore,
                            palladium, currLevel, savedLevelScore,
                            customizationForSavedLevel);

                    accounts.add(account);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Returns a True if the input username and password match that of an account.
     *
     * @param inputUsername An input Username.
     * @param inputPassword An input Password.
     * @return Whether or not the given Username and Password match an account.
     */
    public boolean credentialsBelongToAccount(String inputUsername, String inputPassword) {
        for (Account account : accounts) {
            if (account.username.equals(inputUsername) && account.password.equals(inputPassword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an account to both the AccountManager and the CSV file if the username does not already exist.
     * Since a new account has no information other than username and password, and a default
     * highscore of 0, it is added to the bottom of the CSV.
     *
     * @param newUsername A new account's username.
     * @param newPassword A new account's password.
     */

    public void addAccount(String newUsername, String newPassword) {
        if (usernameAvailable(newUsername)) {
            Account newAccount = new Account(newUsername, newPassword, 0, 0,
                    0, 0, "00");
            accounts.add(newAccount);
        }
    }

    /**
     * Adds an account to both the AccountManager and the CSV file if the username isn't taken.
     * Since a new account has no information other than username and password, and a default
     * highscore of 0, it is added to the bottom of the CSV.
     *
     * @param username a username.
     * @return Returns true if username does not belong to an account already
     */
    public boolean usernameAvailable(String username) {
        for (Account account : accounts) {
            if (account.username.equals(username)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a saved game to the user's account the AccountManager.
     *
     * @param user          The user who saved their game.
     * @param level         The level that the user saved at.
     * @param score         The score that the user accumulated before they saved.
     * @param customization The customization settings that the user had when they saved their game.
     */

    public void addSavedGame(Account user, int level, int score, String customization) {
        user.setCurrLevel(level);
        user.setSavedLevelScore(score);
        user.setCustomizationForSavedLevel(customization);
    }

    /**
     * Adds a new Highscore to the user. Sorts the accounts if that user's highscore moves them up
     * in the player ranks.
     *
     * @param user  The user who scored their new highScore.
     * @param score The user's new highScore.
     */
    public void addHighScore(Account user, int score) {
        user.setHighScore(score);
        List<Account> copy = new ArrayList<>(accounts);
        boolean higher = false;
        int sourcePosition = -1;
        int userPosition = -1;

        for (int i = 0; i < accounts.size(); i++) {
            if ((accounts.get(i).getHighScore() < score) && !higher) {
                userPosition = accounts.indexOf(user);
                sourcePosition = i;
                higher = true;
            }
        }

        if (higher) {
            copy.set(sourcePosition, user);
            for (int j = sourcePosition; j < userPosition; j++) {
                copy.set((j + 1), accounts.get(j));
            }
        }

        accounts = copy;

    }

    public List<List<String>> getScoreboard() {
        List<List<String>> scoreboard = new ArrayList<>();

        int num;
        if (accounts.size() < 5) {
            num = accounts.size();
        } else {
            num = 5;
        }

        for (int i = 0; i < num; i++) {
            List<String> curr = new ArrayList<>();
            Account currUser = accounts.get(i);
            String currUsername = currUser.username;
            String currHighScore = String.valueOf(currUser.getHighScore());
            curr.add(currUsername);
            curr.add(currHighScore);
            scoreboard.add(curr);
        }

        return scoreboard;
    }

    public Account getUser(String username) {
        for (Account account : accounts) {
            if (account.username.equals(username)) {
                return account;
            }
        }
        return null;
    }

    public Account getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(String username) {
        activeUser = getUser(username);
    }

    //TODO Implement
    public void saveAccounts() {
        if (context != null){





        }
    }
}
