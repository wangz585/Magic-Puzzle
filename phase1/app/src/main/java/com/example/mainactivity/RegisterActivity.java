package com.example.mainactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {


    /**
     * A UserManager
     */
    private UserManager usermanager;
    RadioGroup targets = findViewById(R.id.radio);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usermanager = LoginActivity.userManager;
        setRegisterListener();
        targets.setOnCheckedChangeListener(new Radio_check());
    }


    /**
     * Store the target avatar for each user.
     */
    class Radio_check implements  RadioGroup.OnCheckedChangeListener
    {          @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View radioButton = targets.findViewById(checkedId);
        int index = targets.indexOfChild(radioButton);
        switch (index) {
            case 0:
                usermanager.getActiveUser.setTarget(findViewById(R.id.kings));
            case 1:
                usermanager.getActiveUser.setTarget(findViewById(R.id.pauls));
            case 2:
                usermanager.getActiveUser.setTarget(findViewById(R.id.alis));
            case 3:
                usermanager.getActiveUser.setTarget(findViewById(R.id.knights));
        }
    }

    }



    /**
     * Activate the Register button.
     */
    @SuppressLint("SetTextI18n")
    public void setRegisterListener() {
        Button signUp = findViewById(R.id.register);
        signUp.setOnClickListener((v) -> {
            EditText username = findViewById(R.id.username);
            EditText password = findViewById(R.id.password);
            TextView sucess = findViewById(R.id.registersucess);

            try {
                usermanager.authenticate(username.getText().toString(), password.getText().toString());
            } catch (DuplicateAccountException e) {
                username.setError("Sorry this username has been registered");
                return;
            }
            if (username.getText().length() == 0) {

                username.setError("Please fill in the username");
            }
            if (password.getText().length() == 0) {
                password.setError("Please fill in the password.");
            }
            usermanager.getActiveUser().settarget();
            sucess.setText("Register Successfully!");
            Intent tmp = new Intent(this, LoginActivity.class);
            startActivity(tmp);

        });
    }


    }
