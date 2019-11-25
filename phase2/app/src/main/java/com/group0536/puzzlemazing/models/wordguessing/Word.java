package com.group0536.puzzlemazing.models.wordguessing;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private String spelling;
    private boolean guessed;
    private String hint;
    private List<Character> initialState;
    private List<Character> currentState;


    public Word(String spelling, String hint, List<Character> initialState) {
        this.spelling = spelling;
        this.hint = hint;
        this.initialState = initialState;
        this.currentState = new ArrayList<>(initialState);
    }

    public String getSpelling() {
        return spelling;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public String getHint() {
        return hint;
    }

    public void setCharAt(int index, char character) {
        currentState.set(index, character);
    }

    public List<Character> getInitialState() {
        return initialState;
    }

    public List<Character> getCurrentState() {
        return currentState;
    }
}
