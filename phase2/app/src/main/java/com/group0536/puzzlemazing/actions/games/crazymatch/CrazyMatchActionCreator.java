package com.group0536.puzzlemazing.actions.games.crazymatch;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;

public class CrazyMatchActionCreator extends ActionCreator implements CrazyMatchActions {
    public CrazyMatchActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }
    /**
     * A card at (row, col) is flipped
     * @param row row position of a card
     * @param col col position of a card
     */
    public void flip(int row, int col){
        Action action = new Action.ActionBuilder(FLIP)
                .load("row", row)
                .load("col", col)
                .build();
        dispatcher.dispatch(action);
    }

    /**
     * A match board is initialized based on the game level
     * @param level game level
     */
    public void initializeBoard(int level){
        Action action = new Action.ActionBuilder(INITIALIZE_BOARD)
                .load("level", level)
                .build();
        dispatcher.dispatch(action);
    }
}
