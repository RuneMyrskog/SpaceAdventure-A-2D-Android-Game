package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreateNewAccountActivity extends AppCompatActivity {

    AccountManager accountManager;
    TextView newAccountMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
      //  accountManager = MainActivity.getAccountManager();
        newAccountMsg = findViewById(R.id.newAccountMessageText);
    }

    public void createNewAccount(View view) {

        EditText usernameText = findViewById(R.id.newAccountUsernameText);
        EditText passwordText = findViewById(R.id.newAccountPasswordText);
        EditText rPasswordText = findViewById(R.id.newAccountRPasswordText);

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        String rpassword = rPasswordText.getText().toString();

        newAccountMsg.setVisibility(View.INVISIBLE);

        if (username.isEmpty() || password.isEmpty() || rpassword.isEmpty()){
            newAccountMsg.setTextColor(Color.RED);
            newAccountMsg.setText(R.string.invalid_username_password);
            newAccountMsg.setVisibility(View.VISIBLE);
        }else {

            if (password.equals(rpassword)) {
                if (accountManager.usernameAvailable(username)) {
                    newAccountMsg.setTextColor(Color.GREEN);
                    newAccountMsg.setText(R.string.account_succesfully_created);
                    newAccountMsg.setVisibility(View.VISIBLE);
                } else {
                    usernameText.setText("");
                    newAccountMsg.setTextColor(Color.RED);
                    newAccountMsg.setText(R.string.username_taken);
                    newAccountMsg.setVisibility(View.VISIBLE);
                }
            } else {
                passwordText.setText("");
                rPasswordText.setText("");
                newAccountMsg.setTextColor(Color.RED);
                newAccountMsg.setText(R.string.passwords_no_match);
                newAccountMsg.setVisibility(View.VISIBLE);
            }
        }

        // Check if both passwords match. If not, set Error text to "Passwords Dont Match"
        // If the passwords do, call AccountManager.addAccount. If that returns false, set Error
        // text to "Account name already taken". If True, create intent to go to MenuActivity
    }

}
