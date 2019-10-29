package com.example.mainactivity;

import android.accounts.AccountsException;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {


    /**
     * A UserManager
     */
    private UserManager usermanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usermanager = LoginActivity.userManager;
        setRegisterListener();
    }




    /**
     * Activate the Register button.
     */
    @SuppressLint("SetTextI18n")
    public void setRegisterListener(){
        Button signUp = findViewById(R.id.register);
        signUp.setOnClickListener((v) -> {
            EditText username = findViewById(R.id.username);
            EditText password = findViewById(R.id.password);
            TextView sucess = findViewById(R.id.registersucess);

            try{
                usermanager.authenticate(username.getText().toString(), password.getText().toString());
            }
            catch (DuplicateAccountException e) {
                username.setError("Sorry this username has been registered")
                return;
            }
            if (username.getText().length()== 0 ) {

                username.setError("Please fill in the username");
            }
            if (password.getText().length() ==0){
                password.setError("Please fill in the password.");
            }

            sucess.setText("Register Successfully!");
            Intent tmp = new Intent(this, LoginActivity.class);
            startActivity(tmp);

        });
    }
}
