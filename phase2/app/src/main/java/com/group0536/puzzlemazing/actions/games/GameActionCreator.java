package com.group0536.puzzlemazing.actions.games;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;

/**
 * @author Jimmy Lan
 */
public class GameActionCreator extends ActionCreator implements GameCommonActions {
    protected GameActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    private static GameActionCreator instance;

    public static GameActionCreator getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new GameActionCreator(dispatcher);
        }
        return instance;
    }

    public void initGame() {
        Action action = new Action.ActionBuilder(INIT)
                .build();
        dispatcher.dispatch(action);
    }
}
