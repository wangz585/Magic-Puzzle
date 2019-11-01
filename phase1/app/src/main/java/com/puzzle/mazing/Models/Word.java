package com.puzzle.mazing.Models;

import com.puzzle.mazing.DataAccess.WordManager;

import java.util.ArrayList;

public class Word {

    private String spelling;
    private ArrayList<Character> shownChars;
    private ArrayList<Character> missingChars;
    private boolean guessed;
    private String hint;
    private WordManager myWordManager;

    public Word(String spelling, String hint, WordManager myWordManager) {
        this.spelling = spelling;
        this.guessed = false;
        this.hint = hint;
        this.myWordManager = myWordManager;
        this.shownChars = new ArrayList<Character>();
        this.missingChars = new ArrayList<Character>();
        splitSpelling();
    }

    private void splitSpelling() {
        int index = 0;
        while (index < spelling.length()) {
            if (index % 2 == 0) {
                // characters at even index will be given to the player
                this.shownChars.add(spelling.charAt(index));
            } else {
                this.missingChars.add(spelling.charAt(index));
            }
            index = index + 1;
        }
    }

    public String getSpelling() {
        return spelling;
    }

    void setSpelling(String newSpelling) {
        this.spelling = newSpelling;
    }

    public ArrayList<Character> getShownChars() {
        return shownChars;
    }

    void setShownChars(ArrayList<Character> shownChars) {
        this.shownChars = shownChars;
    }

    ArrayList<Character> getMissingChars() {
        return missingChars;
    }

    void setMissingChars(ArrayList<Character> missingChars) {
        this.missingChars = missingChars;
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

    void setHint(String hint) {
        this.hint = hint;
    }

    WordManager getMyWordManager() {
        return myWordManager;
    }

    void setMyWordManager(WordManager myWordManager) {
        this.myWordManager = myWordManager;
    }
}
