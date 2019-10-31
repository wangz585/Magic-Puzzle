package com.puzzle.mazing.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.puzzle.mazing.R;

public class MainMenuActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_menu);
    logOutListener();
    Switch sbutton = findViewById(R.id.background);
    //        onSwitchClicked(sbutton);
    //        addGameButtonListeners();
  }
  //    private GameManager gameManager;
  //
  //    /**
  //     * Activate the buttons for switching the background.
  //     */
  //    public void onSwitchClicked(View v){
  //        //Is the switch on?
  //        boolean on = ((Switch) v).isChecked();
  //        ConstraintLayout layout = findViewById(R.id.mainlayout);
  //        if(on)
  //        {
  //            layout.setBackground(ContextCompat.getDrawable(this, R.drawable.nightmountainbg));
  //        }
  //        else{
  //            layout.setBackground(ContextCompat.getDrawable(this, R.drawable.background));
  //        }
  //
  //        }
  //    /**
  //     * Activate the buttons for each game.
  //     */
  //    private void addGameButtonListeners() {
  //        ImageView wordguess = findViewById(R.id.wordguess);
  //        wordguess.setOnClickListener(v -> enterGame("guess_word"));
  //
  //        ImageView crazymatch = findViewById(R.id.crazymatch);
  //        crazymatch.setOnClickListener(v -> enterGame("crazymatch"));
  //
  //        ImageView chess = findViewById(R.id.chess);
  //        chess.setOnClickListener(v -> enterGame("chess"));
  //    }

  //    /**
  //     * Switch to the correponding game.
  //     * @param gameId a string referring to the type of game.
  //     */
  //    public void enterGame(String gameId){
  //        //Make it more skillable that if you add new game into it, it will just access them by
  // id
  //        startActivities(new Intent(this, gameManager.getListOfGame().get(gameId)));
  //    }

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
