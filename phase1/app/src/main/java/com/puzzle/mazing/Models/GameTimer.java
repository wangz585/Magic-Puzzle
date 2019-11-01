package com.puzzle.mazing.Models;

import android.os.CountDownTimer;

import com.puzzle.mazing.DataAccess.WordGuessingGameManager;

public class GameTimer {
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds;
    private WordGuessingGameManager myWordGuessingGameManager;

    public GameTimer(int minutes, WordGuessingGameManager myManager) {
        this.timeLeftInMilliseconds = minutes * 60000;
        this.myWordGuessingGameManager = myManager;
        this.countDownTimer = new CountDownTimer(minutes * 60000, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                myWordGuessingGameManager.sumbitAnswer.setEnabled(false);
            }
        }.start();
    }


    public void updateTimer() {
        int minutes =  (int)timeLeftInMilliseconds / 60000;
        int seconds =  (int)timeLeftInMilliseconds % 60000 / 1000 ;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;
        myWordGuessingGameManager.countdownText.setText(timeLeftText);
    }
}

