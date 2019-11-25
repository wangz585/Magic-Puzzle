package com.group0536.puzzlemazing.models.wordguessing;

import java.util.List;

public class Word {
    private String spelling;
    private boolean guessed;
    private List<Character> shownChars;
    private List<Character> missingChars;
    private String hint;

    public Word(String spelling, List<Character> shownChars, List<Character> missingChars, String hint) {
        this.spelling = spelling;
        this.shownChars = shownChars;
        this.missingChars = missingChars;
        this.hint = hint;
        this.guessed = false;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public List<Character> getShownChars() {
        return shownChars;
    }

    public void setShownChars(List<Character> shownChars) {
        this.shownChars = shownChars;
    }

    public List<Character> getMissingChars() {
        return missingChars;
    }

    public void setMissingChars(List<Character> missingChars) {
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

    public void setHint(String hint) {
        this.hint = hint;
    }
}
