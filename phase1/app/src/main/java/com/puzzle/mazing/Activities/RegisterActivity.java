package com.puzzle.mazing.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.puzzle.mazing.DataAccess.UserManager;
import com.puzzle.mazing.Exceptions.DuplicateAccountException;
import com.puzzle.mazing.Exceptions.IncorrectCredentialException;
import com.puzzle.mazing.Models.User;
import com.puzzle.mazing.R;

import org.json.JSONException;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

  /** A UserManager */
      private UserManager userManager;
      RadioGroup target;

    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
        Intent intent = getIntent();
        Gson gson = new Gson();
        String json_userManager = (String)intent.getStringExtra("userManager");
        userManager = gson.fromJson(json_userManager, UserManager.class);
        target = findViewById(R.id.radio);
        setRegisterListener();
  }

      /**
       * Store the target avatar for each user.
       */
      class Radio_check implements  RadioGroup.OnCheckedChangeListener
      {          @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
          View radioButton = target.findViewById(checkedId);
          int index = target.indexOfChild(radioButton);
          switch (index) {
              case 0:
                  userManager.getActiveUser().setTarget(findViewById(R.id.kings));
              case 1:
                  userManager.getActiveUser().setTarget(findViewById(R.id.pauls));
              case 2:
                  userManager.getActiveUser().setTarget(findViewById(R.id.alis));
              case 3:
                  userManager.getActiveUser().setTarget(findViewById(R.id.knights));
          }
      }

      }

      /**
       * Activate the Register button. Store relevant data of user
       */
      @SuppressLint("SetTextI18n")
      public void setRegisterListener() {
          Button signUp = findViewById(R.id.register);
          signUp.setOnClickListener((v) -> {
              EditText username = findViewById(R.id.username);
              EditText password = findViewById(R.id.password);
              EditText nickname = findViewById(R.id.nickname);
              TextView success = findViewById(R.id.registersucess);

              try {
                  userManager.authenticate(username.getText().toString(),
   password.getText().toString());
              } catch (IOException e) {
                  e.printStackTrace();
              } catch (JSONException e) {
                  e.printStackTrace();
              } catch (IncorrectCredentialException e) {
                  e.printStackTrace();
              }
              // In authenticate, if True, the active user is already instantiate
              if (username.getText().length() == 0) {

                  username.setError("Please fill in the username");
              }
              if (password.getText().length() == 0) {
                  password.setError("Please fill in the password.");
              }
              //Check whether the user successfully registered
              User curr_user = userManager.getActiveUser();
              if (curr_user != null){
                  curr_user.setNickname(nickname.getText().toString());
                  curr_user.setTarget(target);
              }
              success.setText("Register Successfully!");
              target.setOnCheckedChangeListener(new Radio_check());
              Intent tmp = new Intent(this, LoginActivity.class);
                startActivity(tmp);
          });
      }

}
