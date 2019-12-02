package com.group0536.puzzlemazing.actions.games;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;

/**
 * @author Jimmy Lan
 */
public class gameActionCreator extends ActionCreator implements GameCommonActions {
    public gameActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    /**
     * Create and dispatch an action to play an intro video for this game.
     */
    public void playIntroStory() {
        Action action = new Action.ActionBuilder(PLAY_INTRO)
                .build();
        dispatcher.dispatch(action);
    }
}
