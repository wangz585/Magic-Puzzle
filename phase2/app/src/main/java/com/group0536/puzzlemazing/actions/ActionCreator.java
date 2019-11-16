package com.group0536.puzzlemazing.actions;

import com.group0536.puzzlemazing.dispatcher.Dispatcher;

/**
 * This class represents an action creator which build actions and dispatch
 * them after the action is built.
 *
 * This class belongs to our Flux framework. You should not modify this file
 * when adding new features.
 *
 * @author Jimmy
 * @version 1.0
 * @since 1.0
 */
public class ActionCreator {
    protected Dispatcher dispatcher;

    public ActionCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
}
