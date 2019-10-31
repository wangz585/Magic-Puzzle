package com.puzzle.mazing.Activities;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.puzzle.mazing.R;

public class RegisterActivity extends AppCompatActivity {

  /** A UserManager */
  //    private UserManager usermanager;
  RadioGroup target = findViewById(R.id.radio);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    //        usermanager = LoginActivity.userManager;
    //    setRegisterListener();
  }

  //    /**
  //     * Store the target avatar for each user.
  //     */
  //    class Radio_check implements  RadioGroup.OnCheckedChangeListener
  //    {          @Override
  //    public void onCheckedChanged(RadioGroup group, int checkedId) {
  //        View radioButton = target.findViewById(checkedId);
  //        int index = target.indexOfChild(radioButton);
  //        switch (index) {
  //            case 0:
  //                usermanager.getActiveUser.setTarget(findViewById(R.id.kings));
  //            case 1:
  //                usermanager.getActiveUser.setTarget(findViewById(R.id.pauls));
  //            case 2:
  //                usermanager.getActiveUser.setTarget(findViewById(R.id.alis));
  //            case 3:
  //                usermanager.getActiveUser.setTarget(findViewById(R.id.knights));
  //        }
  //    }
  //
  //    }

  //    /**
  //     * Activate the Register button. Store relevant data of user
  //     */
  //    @SuppressLint("SetTextI18n")
  //    public void setRegisterListener() {
  //        Button signUp = findViewById(R.id.register);
  //        signUp.setOnClickListener((v) -> {
  //            EditText username = findViewById(R.id.username);
  //            EditText password = findViewById(R.id.password);
  //            EditText nickname = findViewById(R.id.nickname);
  //            TextView success = findViewById(R.id.registersucess);
  //
  //            try {
  //                usermanager.authenticate(username.getText().toString(),
  // password.getText().toString());
  //                // In authenticate, if True, the active user is already instantiate
  //            } catch (DuplicateAccountException e) {
  //                username.setError("Sorry this username has been registered");
  //                return;
  //            }
  //            if (username.getText().length() == 0) {
  //
  //                username.setError("Please fill in the username");
  //            }
  //            if (password.getText().length() == 0) {
  //                password.setError("Please fill in the password.");
  //            }
  //            //Check whether the user successfully registered
  //            User curr_user = usermanager.getActiveUser();
  //            if (curr_user != null){
  //                curr_user.setNickName(nickname);
  //                curr_user.setTarget(target);
  //            }
  //            success.setText("Register Successfully!");
  //            target.setOnCheckedChangeListener(new Radio_check());
  //            Intent tmp = new Intent(this, LoginActivity.class);
  //            startActivity(tmp);
  //
  //        });
  //    }

}
