package com.puzzle.mazing.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.puzzle.mazing.DataAccess.WordGuessingGameManager;
import com.puzzle.mazing.R;

import java.io.IOException;

public class WordGuessingMainActivity extends AppCompatActivity {
    private TextView countdownText;
    private Button countdownButton;
    private Button sumbitAnswer;
    private Button backToMain;
    private TextView givenchar1;
    private TextView givenchar2;
    private TextView givenchar3;
    private EditText missingChar1;
    private EditText missingChar2;
    private EditText missingChar3;
    private TextView hintSpot;
    private WordGuessingGameManager myWordGuessingGameManager;
    private AlertDialog.Builder alertDialogBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_guessing_activity_main);

        countdownText = findViewById(R.id.textViewTimer);
        countdownButton = findViewById(R.id.countdown_button);
        sumbitAnswer = findViewById(R.id.submit_button);
        backToMain = findViewById(R.id.back_button);

        // Set up for all text views that should be given to the player
        givenchar1 = findViewById(R.id.shownChar1);
        givenchar2 = findViewById(R.id.shownChar2);
        givenchar3 = findViewById(R.id.shownChar3);

        // Set up for all text views that should be filled by the player
        missingChar1 = findViewById(R.id.missChar1);
        missingChar2 = findViewById(R.id.missChar2);
        missingChar3 = findViewById(R.id.missChar3);
        hintSpot = findViewById(R.id.Hint);
        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder(WordGuessingMainActivity.this);

        try {
            myWordGuessingGameManager = new WordGuessingGameManager(countdownButton, missingChar1, missingChar2, missingChar3,
                    givenchar1, givenchar2, givenchar3, hintSpot, countdownText, alertDialogBuilder,
                    getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up a listener for the GameTimer / a trigger to start this level of game
        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myWordGuessingGameManager.startGame();
            }
        });

        // Set up a listener for the back to main page button / a trigger to exit this level
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainMenuActivity.class);
                startActivity(i);
            }
        });

        sumbitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myWordGuessingGameManager.submitAnswer();
            }
        });
    }

}

