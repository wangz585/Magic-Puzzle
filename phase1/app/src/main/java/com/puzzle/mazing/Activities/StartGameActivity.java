package com.puzzle.mazing.Activities;

import android.app.ActivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.puzzle.mazing.R;

public class StartGameActivity extends AppCompatActivity {

    private Button loadGameButton;

    private Button startNewGameButton;

    private Button backButton;

    private TextView rule;

    private String gameId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        loadGameButton = findViewById(R.id.loadgame);
        startNewGameButton = findViewById(R.id.newgame);
        backButton = findViewById(R.id.back);
        rule = findViewById(R.id.Rule);

        // if gameId == wordguessing,(is savable) then delete loadGameButton.
        //        // if it is the first game, delete button, and displays the rule.
        //        // else show buttons. rule.setText();
        // firstgame: When the princess clicks the start, the incomplete word will be displayed on
        // the screen with the hint. The princess has to fill in the missing part of the word based
        // on the hint and the shown part. After the princess fills all incomplete part, she can
        // submit to god of the Word to check her answer. If the answer is wrong, she has to guess
        // it again. Otherwise, she can step to the next word. The princess conquers this challenge
        // if and only if she guesses more than five words correctly in one minute. The more she
        // guessed correctly, the more points she will get in this game.
        // secondgame: The animals are sealed inside yellow balls. The princess can tap a ball to
        // open it. At most two balls can be opened at the same time. If the two balls contain the
        // same animal, that animal is rescued; if the two balls contain different animals, they are
        // sealed again. The princess conquers this challenge if and only if she has rescued all the
        // animals. The less steps she takes, the more points she gets.

        loadGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // load game?
            }
        });

        startNewGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // start a new game, cover old data
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //
            }
        });

    }
}
