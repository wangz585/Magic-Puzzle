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

public class RegisterActivity extends AppCompatActivity {
    private UserManager userManager;
    private TextView promptText;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtNickName;
    private Button btnSignUp;
    String email;
    String nickName;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userManager = (UserManager) getIntent().getSerializableExtra("myUM");
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        txtNickName = findViewById(R.id.nickname);
        promptText = findViewById(R.id.promptText);
        btnSignUp = findViewById(R.id.register);
        setRegisterListener();
        clearTexts();
    }

    private void clearTexts() {
        promptText.setText("");
        txtNickName.setText(R.string.nickName);
        txtEmail.setText("");
        txtPassword.setText("");
    }

    boolean isValidInput() {
        return (txtEmail.getText().toString().contains("@") &&
                txtPassword.getText().toString().length() > 0 &&
                txtNickName.getText().toString().length() > 0);
    }

    @Override
    public void onUserInteraction(){
        promptText.setText("");
    }

    /**
     * Activate the Register button. Store relevant data of user
     */
    public void setRegisterListener() {
        btnSignUp.setOnClickListener((v) -> {
            email = txtEmail.getText().toString();
            nickName = txtNickName.getText().toString();
            password = txtPassword.getText().toString();
            if (isValidInput()) {
                userManager.register(email, password, done());
            } else {
                promptText.setText(R.string.invalidInput);
            }
        });
    }


    private Callback done() {
        return new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handleResponse(response);
            }
        };
    }

    void handleResponse(Response response) throws IOException {
        if (response.isSuccessful()) {
            try {
                JSONObject responseObject = Http.parseResponseBody(response);
                String userToken = Http.getJSONAttribute(responseObject, "token");
                // Did not pass nick name to the database
                userManager.setActiveUser(new User(email, nickName, userToken));
                Intent tmp = new Intent(RegisterActivity.this, MainMenuActivity.class);
                startActivity(tmp);
            } catch (JSONException e) {
                e.printStackTrace();
                // handle this
            }
        } else {
            System.out.println("fff");
            System.out.println("fff");
            //promptText.setText(R.string.signUpFail);
        }
    }


}
