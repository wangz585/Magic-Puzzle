package com.group0536.puzzlemazing.models.wordguessing;

public class ScoreCalculator {
    private int numberOfAttempts;
    private int currentWordScore;
    private int totalScore;

    public void increaseNumberOfAttempts() {
        numberOfAttempts += 1;
    }

    public void updateTotalScore() {
        totalScore += currentWordScore;
    }

    public void setCurrentWordScore(int score) {
        currentWordScore = score;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
