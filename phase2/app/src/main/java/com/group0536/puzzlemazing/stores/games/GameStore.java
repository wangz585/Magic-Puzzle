package com.group0536.puzzlemazing.stores.games;

import com.group0536.puzzlemazing.actions.Action;
import com.group0536.puzzlemazing.dispatcher.Dispatcher;
import com.group0536.puzzlemazing.models.User;
import com.group0536.puzzlemazing.stores.Store;
import com.group0536.puzzlemazing.stores.StoreChangeEvent;

public class GameStore extends Store {
    private static GameStore instance;

    private User user;

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
    public void onAction(Action action) {

    }
}
