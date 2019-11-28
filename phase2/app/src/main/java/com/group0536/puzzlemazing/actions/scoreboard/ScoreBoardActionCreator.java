package com.group0536.puzzlemazing.actions.scoreboard;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;

public class ScoreBoardActionCreator extends ActionCreator implements ScoreBoardActions {
    public ScoreBoardActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public void chooseScoreType(String scoreType){
        Action action = new Action.ActionBuilder(SELECT_SCORE_TYPE)
                .load("score_type", scoreType)
                .build();
        dispatcher.dispatch(action);
    }
}
