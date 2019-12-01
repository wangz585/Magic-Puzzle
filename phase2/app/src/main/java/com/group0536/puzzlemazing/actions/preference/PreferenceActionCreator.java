package com.group0536.puzzlemazing.actions.preference;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;

public class PreferenceActionCreator extends ActionCreator implements PreferenceActions {
    public PreferenceActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public void setSound(int soundTrack) {
        Action action = new Action.ActionBuilder(SET_SOUND)
                .load("soundTrack", soundTrack)
                .build();
        dispatcher.dispatch(action);
    }

    public void clearSound() {
        Action action = new Action.ActionBuilder(CLEAR_SOUND)
                .build();
        dispatcher.dispatch(action);
    }
}
