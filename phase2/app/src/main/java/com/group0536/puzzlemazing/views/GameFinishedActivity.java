package com.group0536.puzzlemazing.views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group0536.puzzlemazing.R;
import com.group0536.puzzlemazing.views.animation.AnimationActivity;
import com.group0536.puzzlemazing.views.menu.MenuPageActivity;
import com.group0536.puzzlemazing.views.appinit.AppInitializeActivity;

/**
 * An activity after a game has finished
 */
public class GameFinishedActivity extends AppInitializeActivity {
    private int challenge;

    // Components
    private Button btnMenu;
    private Button btnNext;
    private TextView txtScore;
    private TextView txtTimeUsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_exit);
        bindView();
    }

    /**
     * Initialize all the components on this activity
     */
    private void bindView() {
        Intent mIntent = getIntent();
        int score = mIntent.getIntExtra("score", 0);
        challenge = mIntent.getIntExtra("challenge", 0);
        initializeNextButton();
        initializeMenuButton();
        initializeScoreText(score);
        initializeTimeText();
    }

    /**
     * Initialize the text view displaying the time used
     */
    private void initializeTimeText() {
        txtTimeUsed = findViewById(R.id.txtTime);
    }

    /**
     * Initialize the text view displaying the score
     *
     * @param score the score of this game
     */
    private void initializeScoreText(int score) {
        txtScore = findViewById(R.id.txtScore);
        String scoreText = getString(R.string.score, score);
        txtScore.setText(scoreText);
    }

    /**
     * Initialize the menu button
     */
    private void initializeMenuButton() {
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameFinishedActivity.this, MenuPageActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Initialize the next button
     */
    private void initializeNextButton() {
        btnNext = findViewById(R.id.btnNextChallenge);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameFinishedActivity.this, AnimationActivity.class);
                intent.putExtra("challenge", challenge);
                startActivity(intent);
            }
        });
    }
}
