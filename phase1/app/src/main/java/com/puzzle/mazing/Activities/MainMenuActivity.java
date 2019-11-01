package com.puzzle.mazing.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.puzzle.mazing.DataAccess.UserManager;
import com.puzzle.mazing.Models.User;
import com.puzzle.mazing.R;

public class MainMenuActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_menu);
    logOutListener();
    Switch sbutton = findViewById(R.id.background);
            onSwitchClicked(sbutton);
            addGameButtonListeners();
  }
      // when starting mainMenuActivity, mainMenuActivity needs to have a game manager passed into
//      private Game game;

      /**
       * Activate the buttons for switching the background.
       */
      public void onSwitchClicked(View v){
          //Is the switch on?
          boolean on = ((Switch) v).isChecked();
          ConstraintLayout layout = findViewById(R.id.mainlayout);
          if(on)
          {
              layout.setBackground(ContextCompat.getDrawable(this, R.drawable.nightmountainbg));
          }
          else{
              layout.setBackground(ContextCompat.getDrawable(this, R.drawable.background));
          }

          }
      /**
       * Activate the buttons for each game.
       */
      private void addGameButtonListeners() {
          ImageView wordguess = findViewById(R.id.wordguess);
          wordguess.setOnClickListener(v -> enterGame("guess_word"));

          ImageView crazymatch = findViewById(R.id.crazymatch);
          crazymatch.setOnClickListener(v -> enterGame("crazymatch"));

          ImageView chess = findViewById(R.id.chess);
          chess.setOnClickListener(v -> enterGame("chess"));
    }

    private void enterGame(String gameID) {
          if (gameID.equals("guess_word")) {
              Intent tmp = new Intent(this, LoginActivity.class);
              startActivity(tmp);
          }
          else if (gameID.equals("crazymatch")){
              Intent tmp = new Intent(this, LoginActivity.class);
              startActivity(tmp);
          }
          else {
              Intent tmp = new Intent(this, LoginActivity.class);
              startActivity(tmp);
          }
          // In fact, GameId Should be the same as the page name to make it easier to implement.
    }

    /**
       * Switch to the correponding game.
       * @param gameId a string referring to the type of game.
       */
      public void enterGame(String gameId, User curr_user){
          //Make it more skillable that if you add new game into it, it will just access them by id
//          startActivities(new Intent(this, gameManager.getListOfGame().get(gameId)));
          // check whether the game that the user chose is a game that he has previous record.
          // if he has a record of playing this game
          // load old game
          // if he does not have a previous game state, then just start a new game for him
          // create a new intent
          // pass the current user into the intent
      }

  /** Activate the logout button. */
  public void logOutListener() {
    Button signIn = findViewById(R.id.logout);
    signIn.setOnClickListener(
        (v) -> {
          Intent tmp = new Intent(this, LoginActivity.class);
          startActivity(tmp);
        });
  }
}
