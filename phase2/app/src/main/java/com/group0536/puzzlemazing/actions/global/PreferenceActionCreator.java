package com.group0536.puzzlemazing.actions.global;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.ActionCreator;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;

/**
 * This class represents an action creator for the preferences.
 */
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

    public void setAvatar(String avatar) {
        Action action = new Action.ActionBuilder(CLEAR_SOUND)
                .load("avatarSelected", avatar)
                .build();
        dispatcher.dispatch(action);
    }
}
