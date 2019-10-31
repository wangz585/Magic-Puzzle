package com.puzzle.mazing.Activities;


import android.app.ActivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.puzzle.mazing.R;

import androidx.appcompat.app.AppCompatActivity;

import com.puzzle.mazing.R;

public class GameFinishedActivity extends AppCompatActivity {

    private Button restartButton;

    private Button backButton;

    private Button nextLevelButton;

    private TextView score;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finished);

        nextLevelButton = findViewById(R.id.NextLevel);
        restartButton = findViewById(R.id.Restart);
        backButton = findViewById(R.id.finishback);
        score = findViewById(R.id.Score);

        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go to next game's start page
                // if this game is the last game, then deletes this button
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go back to main page
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go to this game's activity.
            }
        });
    }




}
