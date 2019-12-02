package com.group0536.puzzlemazing.models.wordguessing;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private String spelling;
    private boolean guessed;
    private String hint;
    private List<Character> initialState;
    private List<Character> currentState;

    /**
     * Initialize a Word for word guessing game
     *
     * @param spelling     the spelling for the word
     * @param hint         the hint of the word
     * @param initialState a list of characters for the word
     */
    public Word(String spelling, String hint, List<Character> initialState) {
        this.spelling = spelling;
        this.hint = hint;
        this.initialState = initialState;
        this.currentState = new ArrayList<>(initialState);
    }

    /**
     * Get the spelling of the word
     *
     * @return spelling
     */
    public String getSpelling() {
        return spelling;
    }

    /**
     * Get whether the word is guessed
     *
     * @return guessed
     */
    public boolean isGuessed() {
        return guessed;
    }

    /**
     * Set the word is guessed or not
     *
     * @param guessed
     */
    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    /**
     * Get the hint of the word
     *
     * @return hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * Return the list of characters of a word
     *
     * @return initialState
     */
    public List<Character> getInitialState() {
        return initialState;
    }

    /**
     * Return the list of characters of a word
     *
     * @return currentState
     */
    public List<Character> getCurrentState() {
        return currentState;
    }

    /**
     * Set the current state
     *
     * @param newState a new list of characters
     */
    public void setCurrentState(List<Character> newState) {
        currentState = newState;
    }
}
