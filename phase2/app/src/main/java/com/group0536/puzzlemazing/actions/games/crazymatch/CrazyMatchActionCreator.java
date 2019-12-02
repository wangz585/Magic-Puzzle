package com.group0536.puzzlemazing.actions.games.crazymatch;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;

public class CrazyMatchActionCreator extends ActionCreator implements CrazyMatchActions {
    public CrazyMatchActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public void flip(int row, int col){
        Action action = new Action.ActionBuilder(FLIP)
                .load("row", row)
                .load("col", col)
                .build();
        dispatcher.dispatch(action);
    }

    public void initializeBoard(int level){
        Action action = new Action.ActionBuilder(INITIALIZE_BOARD)
                .load("level", level)
                .build();
        dispatcher.dispatch(action);
    }
}
