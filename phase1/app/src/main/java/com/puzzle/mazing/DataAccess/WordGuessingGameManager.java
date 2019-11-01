package com.puzzle.mazing.DataAccess;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.puzzle.mazing.Activities.CrazyMatchActivity;
import com.puzzle.mazing.Activities.WordGuessingMainActivity;
import com.puzzle.mazing.Models.GameTimer;
import com.puzzle.mazing.Models.Word;

import java.io.IOException;
import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class WordGuessingGameManager {
    private WordManager myWordManager;
    private EditText missingChar1;
    private EditText missingChar2;
    private EditText missingChar3;
    private TextView givenchar1;
    private TextView givenchar2;
    private TextView givenchar3;
    public Button sumbitAnswer;
    private Button countdownButton;
    public TextView countdownText;
    private TextView hintSpot;
    private int score;
    private int numFinishedGames;
    private int totalGames;
    com.puzzle.mazing.Models.GameTimer GameTimer;
    private AlertDialog.Builder alertDialogBuilder;


    public WordGuessingGameManager(Button countdownButton, EditText missingChar1, EditText missingChar2, EditText missingChar3,
                                   TextView givenchar1, TextView givenchar2, TextView givenchar3,
                                   TextView hintSpot, TextView countdownText, AlertDialog.Builder alertDialogBuilder,
                                   Context currentContext) throws IOException {
        this.countdownButton = countdownButton;
        this.missingChar1 = missingChar1;
        this.missingChar2 = missingChar2;
        this.missingChar3 = missingChar3;
        this.givenchar1 = givenchar1;
        this.givenchar2 = givenchar2;
        this.givenchar3 = givenchar3;
        this.hintSpot = hintSpot;
        this.score = 0;
        this.numFinishedGames = 0;
        this.totalGames = 5;
        this.myWordManager = new WordManager(currentContext);
        this.countdownText = countdownText;
        countdownText.setText("2:00");
    }

    public void startGame(){
        countdownButton.setEnabled(false);
        playGame();
        this.GameTimer = new GameTimer(2, this);
    }

    void playGame() {
        generateWord();
    }

    private void generateWord() {
        Word currentWord = myWordManager.createAWordForGuessing();
        showWord(currentWord);
        showHint(currentWord);
    }

    private void showHint(Word currentWord) {
        hintSpot.setText(currentWord.getHint());
    }

    private void showWord(Word currentWord) {
        ArrayList givenChars = currentWord.getShownChars();
        this.givenchar1.setText(String.valueOf(givenChars.get(0)));
        this.givenchar2.setText(String.valueOf(givenChars.get(1)));
        this.givenchar3.setText(String.valueOf(givenChars.get(2)));
    }

    public void submitAnswer(){
        String userInput = getInput();
        if(myWordManager.inDictionary(userInput)){
            win_updateOneGuess();
            if(gameIsFinished()){
//                goToWinningPage();
            }
            else{
                // the player guesses a word correctly
                // game is not finished
                cleanUserInput();
                cleanGivenChars();
                generateWord();
            }
        }
        cleanUserInput();
    }


    private void cleanGivenChars() {
        this.givenchar1.setText("");
        this.givenchar2.setText("");
        this.givenchar3.setText("");
    }

    private void cleanUserInput() {
        this.missingChar1.getText().clear();
        this.missingChar2.getText().clear();
        this.missingChar3.getText().clear();
    }

    private boolean gameIsFinished() {
        return numFinishedGames == totalGames;
    }

    private void win_updateOneGuess() {
        this.numFinishedGames ++;
        this.score ++;

    }

    private String getInput() {
        String userInput = (String) this.givenchar1.getText() + this.missingChar1.getText() +
                this.givenchar2.getText() + this.missingChar2.getText() +
                this.givenchar3.getText() + this.missingChar3.getText();
        return userInput;
    }
}
