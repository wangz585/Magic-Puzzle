package com.puzzle.mazing.Activities;

import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.puzzle.mazing.DataAccess.UserManager;
import com.puzzle.mazing.Exceptions.IncorrectCredentialException;
import com.puzzle.mazing.R;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;

public class LoginActivity extends AppCompatActivity {

    //    /**
    //     * A UserManager.
    //     */
    //    private UserManager usermanager;
    //
    //    /**
    //     * The current player of the game.
    //     */
    //    public static User activePlayer;

    protected UserManager userManager = new UserManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        // Note: this needs to be run on a separate thread
        try {
            userManager.authenticate("1@2.com", "123456");
            Log.d("Network", userManager.getActiveUser().getNickname());
        } catch(Exception e) {
            Log.d("Network", "Cannot login");
            e.printStackTrace();
        }

                setSignInListener();
                setRegisterListener();
    }
    //
        /**
         *  Activate the signIn button.
         */
        @SuppressLint("SetTextI18n")
        public void setSignInListener() {
            Button signIn = findViewById(R.id.login);
            signIn.setOnClickListener((v) -> {
                TextView prompt_text = findViewById(R.id.login_title);
                EditText username = findViewById(R.id.username);
                EditText password = findViewById(R.id.password);

                try {
                    userManager.authenticate(username.getText().toString(),
                            password.getText().toString());
                }

                catch (IncorrectCredentialException e) {
                    password.setError("Password is incorrect.");
                    System.out.println("123");
                    return;
                } catch (IOException e) {
                    password.setError("Password is incorrect.");
                    return;
                } catch (JSONException e) {
                    password.setError("Password is incorrect.");
                    return;
                }
                Intent tmp = new Intent(this, MainMenuActivity.class);
                Gson gson = new Gson();
                String gameManager_json = gson.toJson(userManager);
                tmp.putExtra("userManager", gameManager_json);
                prompt_text.setText("Sign In Successfully!");
                startActivity(tmp);
            });
        }

    /**
     * Activate the signUp button.
     */
    public void setRegisterListener() {
        Button register = findViewById(R.id.register);
        register.setOnClickListener(
                (v) -> {
                    Intent tmp = new Intent(getBaseContext(), RegisterActivity.class);
                    Gson gson = new Gson();
                    String userManager_json = gson.toJson(userManager);
                    tmp.putExtra("userManager", userManager_json);
                    startActivity(tmp);
                });
    }
}
