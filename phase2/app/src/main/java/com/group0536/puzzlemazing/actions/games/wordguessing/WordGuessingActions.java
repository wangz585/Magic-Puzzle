package com.group0536.puzzlemazing.actions.games.wordguessing;

import com.group0536.puzzlemazing.actions.games.GameCommonActions;

public interface WordGuessingActions extends GameCommonActions {
    String SUBMIT_ANSWER = "submit_answer";
    String START_GAME = "start-game";
    String INITIALIZE_GAME = "initialize-word-bank";
    String TIME_OUT = "time-out";
    String UPDATE_SCORE = "update-score";
}
