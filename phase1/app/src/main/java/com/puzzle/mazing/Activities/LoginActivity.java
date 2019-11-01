package com.puzzle.mazing.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.puzzle.mazing.DataAccess.UserManager;
import com.puzzle.mazing.Models.User;
import com.puzzle.mazing.Network.Http;
import com.puzzle.mazing.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private UserManager userManager;
    private TextView promptText;
    private EditText txtEmail;
    private EditText txtPassword;
    private Button signIn;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        userManager = new UserManager();
        signIn = findViewById(R.id.login);
        promptText = findViewById(R.id.prompt_text);
        txtEmail = findViewById(R.id.Email);
        txtPassword = findViewById(R.id.password);
        setSignInListener();
        setRegisterListener();
    }

    private boolean isValidInput() {
        return (txtEmail.getText().toString().contains("@") &&
                txtPassword.getText().toString().length() > 0);
    }

    /**
     * Perform actions when trying to sign in.
     */
    public void setSignInListener() {

        signIn.setOnClickListener(
                (v) -> {
                    email = txtEmail.getText().toString();
                    password = txtPassword.getText().toString();
                    if (isValidInput()) {
                        userManager.authenticate(
                                email,
                                password, done());

                    } else {
                        promptText.setText(R.string.invalidInput);
                    }
                });
    }

    Callback done() {
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    handleResponse(response);
                } else {
                    promptText.setText(R.string.wrongPassword);
                }
            }
        };
    }

    void handleResponse(Response response) throws IOException {
        try {
            JSONObject responseObject = Http.parseResponseBody(response);
            String userToken = Http.getJSONAttribute(responseObject, "token");
            String nickname = Http.getJSONAttribute(responseObject, "nickname");
            userManager.setActiveUser(new User(email, nickname, userToken));

            Intent tmp = new Intent(LoginActivity.this, MainMenuActivity.class);
            startActivity(tmp);

        } catch (JSONException e) {
            e.printStackTrace();
            //  handle this
        }
    }

    /**
     * Activate the signUp button.
     */
    public void setRegisterListener() {
        Button register = findViewById(R.id.register);
        register.setOnClickListener(
                (v) -> {

                    Intent tmp = new Intent(this, RegisterActivity.class);
                    tmp.putExtra("myUM", userManager);
                    startActivity(tmp);
                });
    }
}
