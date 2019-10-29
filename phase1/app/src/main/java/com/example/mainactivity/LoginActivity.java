package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    /**
     * A UserManager.
     */
    private UserManager users;

    /**
     * The current player of the game.
     */
    public static User activePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        setSignInListener();
    }

    /**
     *  Activate the signIn button.
     */
    @SuppressLint("SetTextI18n")
    public void setSignInListener() {
        Button signIn = findViewById(R.id.login);
        signIn.setOnClickListener((v) -> {
            TextView prompt_text = findViewById(R.id.prompt_text);
            String userName = ((EditText)findViewById(R.id.username)).getText().toString();
            String passWord = ((EditText)findViewById(R.id.password)).getText().toString();

            try {
                activePlayer = users.authenticate(userName, passWord);
            }
            catch (AuthenticatorException e){
                prompt_text.setText("Password is incorrect.");
                return;
            }
            catch (AccountsException e) {
                prompt_text.setText("Username is not found.");
                return;
            }
            prompt_text.setText("Sign In Successfully!");
            Intent tmp = new Intent(this, MainMenuActivity.class);
            startActivity(tmp);
        });
    }


    /**
     * Activate the signUp button.
     */
    public void setRegisterListener(){
        Button register = findViewById(R.id.register);
        register.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, RegisterActivity.class);
            startActivity(tmp);
        });

    }

}
