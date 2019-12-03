package com.group0536.puzzlemazing.stores.games;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.actions.appinit.AppInitializeActions;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;
import com.squareup.otto.Subscribe;

public class GameStore extends Store implements AppInitializeActions {
    private static GameStore instance;
    private int challenge;
    protected User user;

    public User getUser() {
        return user;
    }

    protected GameStore(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static GameStore getInstance(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new GameStore(dispatcher);
        }
        return instance;
    }

    @Override
    protected StoreChangeEvent getChangeEvent() {
        return new GameStoreChangeEvent();
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
            case SEND_USER:
                extractUser(action);
                break;
        }
        postChange();
    }

    private void extractUser(Action action) {
        user = (User) action.getPayloadEntry(KEY_CURRENT_USER);
    }

    protected void setChallenge(int challenge) {
        this.challenge = challenge;
    }

    protected int getChallenge() {
        return challenge;
    }
}
