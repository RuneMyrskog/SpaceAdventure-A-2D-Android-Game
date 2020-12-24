package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TextView invalidCredentialsMsg;
    private EditText usernameText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);


        AccountManager.getInstance().setContext(this);
        AccountManager.getInstance().populateAccounts();

        invalidCredentialsMsg = findViewById(R.id.invalidCrMsgText);
    }

    /**
     * Checks validity of credentials and signs in valid users
     *
     * @param view the button view that called this method.
     */
    public void signIn(View view) {

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        if (AccountManager.getInstance().credentialsBelongToAccount(username, password)) {
            AccountManager.getInstance().setActiveUser(username);
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        } else {
            passwordText.setText("");
            invalidCredentialsMsg.setVisibility(View.VISIBLE);
        }


    }

    /**
     * switches to CreateNewAccountActivity
     *
     * @param view the button view that called this method .
     */
    public void newAccount(View view) {
        Intent intent = new Intent(this, CreateNewAccountActivity.class);
        startActivity(intent);
    }
}
