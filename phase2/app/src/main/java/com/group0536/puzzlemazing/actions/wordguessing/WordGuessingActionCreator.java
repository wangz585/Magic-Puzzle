package com.group0536.puzzlemazing.actions.wordguessing;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.actions.crazymatch.CrazyMatchActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;

public class WordGuessingActionCreator extends ActionCreator implements WordGuessingActions {
    public WordGuessingActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public void checkAnswer(String word){
        Action action = new Action.ActionBuilder(CHECK)
                .load("word", word)
                .build();
        dispatcher.dispatch(action);
    }
}
