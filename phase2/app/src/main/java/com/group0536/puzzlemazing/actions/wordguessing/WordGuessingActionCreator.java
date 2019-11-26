package com.group0536.puzzlemazing.actions.wordguessing;

import android.content.Context;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;

public class WordGuessingActionCreator extends ActionCreator implements WordGuessingActions {
    public WordGuessingActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public void submitAnswer(String word){
        Action action = new Action.ActionBuilder(SUBMIT_ANSWER)
                .load("word", word)
                .build();
        dispatcher.dispatch(action);
    }

    public void startGame(){
        Action action = new Action.ActionBuilder(START_GAME).build();
        dispatcher.dispatch(action);
    }

    public void initializeWordBank(int level, Context context){
        Action action = new Action.ActionBuilder(INITIALIZE_WORDBANK)
                .load("level", level)
                .load("context", context)
                .build();
        dispatcher.dispatch(action);
    }

    public void timeOver() {
        Action action = new Action.ActionBuilder(TIME_OUT).build();
        dispatcher.dispatch(action);
    }
}
